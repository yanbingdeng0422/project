package com.xxun.xunsupperpower;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.xxun.xunsupperpower.cdma.CdmaSimStatus;


import java.util.List;

public class MainActivity extends Activity {

    private String TAG = "MainActivity";
    private TelephonyManager mTelephonyManager;
    private CarrierConfigManager mCarrierConfigManager;
    private SignalStrength signalStrength;
    private PhoneStateListener mPhoneStateListener;

    private CdmaSimStatus mCdmaSimStatus;
    private List<SubscriptionInfo> mSelectableSubInfos;
    private SubscriptionInfo mSir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        mCarrierConfigManager = (CarrierConfigManager) getSystemService(Context.CARRIER_CONFIG_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
            mSelectableSubInfos = SubscriptionManager.from(getApplicationContext())
                    .getActiveSubscriptionInfoList();
        }

        setContentView(R.layout.activity_main);

        mCdmaSimStatus = new CdmaSimStatus(this, null);
        updatePhoneInfos();
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        if(mCdmaSimStatus==null){
            mCdmaSimStatus = new CdmaSimStatus(this, null);
        }
        Log.d(TAG,"mCdmaSimStatus ="+mCdmaSimStatus);
        if (mSelectableSubInfos == null) {
            mSir = null;
            mCdmaSimStatus.setSubscriptionInfo(mSir);
        } else {
            mSir = mSelectableSubInfos.size() > 0 ? mSelectableSubInfos.get(0) : null;

            mCdmaSimStatus.setSubscriptionInfo(mSir);
            if (mSelectableSubInfos.size() > 1) {//第二张卡

            }
        }

        return super.onCreateView(parent, name, context, attrs);
    }

    private void updatePhoneInfos() {
        if (mSir != null) {
            if (mPhoneStateListener != null) {
                Log.d(TAG, "remove the phone state listener mPhoneStateListener = "
                        + mPhoneStateListener);
                mTelephonyManager.listen(mPhoneStateListener, PhoneStateListener.LISTEN_NONE);
            }

            mPhoneStateListener = new PhoneStateListener() {
                @Override
                public void onDataConnectionStateChanged(int state) {
                    if (getApplication() == null) {
                        Log.d(TAG, "DataConnectionStateChanged activity is null");
                        return;
                    }
                    Log.d(TAG, "onDataConnectionStateChanged sub = " + mSir + " state = "
                            + state);
                    updateDataState();
                    updateNetworkType();
                }

                @Override
                public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                    if(getApplication()==null){
                        return;
                    }

                    Log.d(TAG,"onSignalStrengthsChanged sub = " + mSir);
                    updateSignalStrength(signalStrength);
                }
            };
        }

    }

    private void updateSignalStrength(SignalStrength signalStrength) {
        if(signalStrength!=null){
            int CdmaDbm =signalStrength.getCdmaDbm();
            int CdmaEcio = signalStrength.getCdmaEcio();
            int EvdoEcio = signalStrength.getEvdoEcio();
            int EvdoDbm =signalStrength.getEvdoDbm();
            signalStrength.describeContents();
//            int signalDbm = signalStrength.getDbm();
//            int signalAsu = signalStrength.getAsuLevel();
            Log.d(TAG,"CdmaDbm= "+CdmaDbm +" CdmaEcio="+CdmaEcio+" EvdoEcio="+EvdoEcio+" EvdoDbm="+EvdoDbm);
        }
    }

    private void updateNetworkType() {
        String networktype = null;
        final int actualVoiceNetworkType;
        final int actualDataNetworkType;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
            final int subId = mSir.getSubscriptionId();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            actualDataNetworkType = mTelephonyManager.getDataNetworkType();
            Log.d(TAG,"actualDataNetworkType"+actualDataNetworkType);
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            actualVoiceNetworkType = mTelephonyManager.getVoiceNetworkType();
            Log.d(TAG,"actualVoiceNetworkType"+actualVoiceNetworkType);
        }


    }

    private void updateDataState() {
//        final int state =
//                DefaultPhoneNotifier.convertDataState(mPhone.getDataConnectionState());
//        String display = mRes.getString(R.string.radioInfo_unknown);
//
//        switch (state) {
//            case TelephonyManager.DATA_CONNECTED:
//                display = mRes.getString(R.string.radioInfo_data_connected);
//                break;
//            case TelephonyManager.DATA_SUSPENDED:
//                display = mRes.getString(R.string.radioInfo_data_suspended);
//                break;
//            case TelephonyManager.DATA_CONNECTING:
//                display = mRes.getString(R.string.radioInfo_data_connecting);
//                break;
//            case TelephonyManager.DATA_DISCONNECTED:
//                display = mRes.getString(R.string.radioInfo_data_disconnected);
//                break;
//        }
//
//        setSummaryText(KEY_DATA_STATE, display);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG,"mTelephonyManager= "+mTelephonyManager);
        if(mTelephonyManager==null){
            return;
        }
        mTelephonyManager.listen(mPhoneStateListener,
                PhoneStateListener.LISTEN_DATA_CONNECTION_STATE
        | PhoneStateListener.LISTEN_SIGNAL_STRENGTHS
        | PhoneStateListener.LISTEN_SERVICE_STATE);
    }
}
