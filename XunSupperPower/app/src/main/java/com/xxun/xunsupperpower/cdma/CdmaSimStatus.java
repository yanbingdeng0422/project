package com.xxun.xunsupperpower.cdma;

import android.content.Context;
import android.preference.PreferenceScreen;
import android.provider.ContactsContract;
import android.telephony.ServiceState;
import android.telephony.SubscriptionInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by dengyanbing on 2018/9/20.
 */

public class CdmaSimStatus {

    private static final String TAG = "CdmaSimStatus";

    private static final String KEY_MCC_MNC = "current_operators_mccmnc";
    private static final String KEY_SIN_NID = "current_sidnid";
    private static final String KEY_CELL_ID = "current_cellid";

    private static final int MCC_LENGTH = 3;

    private PreferenceScreen mPreferenceScreen;

//    private Phone mPhone;
    private ServiceState mServiceState;
    // Default summary for items refer to SimStatus
    private String mDefaultText;

    private TelephonyManager mTelephonyManager;
    private SubscriptionInfo mSubInfo;


    public CdmaSimStatus(Context context, SubscriptionInfo subInfo) {
        mSubInfo = subInfo;
        mTelephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
    }

    public void setSubscriptionInfo(SubscriptionInfo subInfo) {
        mSubInfo = subInfo;
        Log.d(TAG, "setSubscriptionInfo = " + mSubInfo);
    }


}
