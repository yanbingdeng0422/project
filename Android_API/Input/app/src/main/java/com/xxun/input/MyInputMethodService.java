package com.xxun.input;

import android.content.Context;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.xxun.input.constants.DemoKeyCode;
import com.xxun.input.keyboard.MyKeyboardView;

/**
 * 
 * 类说明: 
 *
 * @author qing
 * @time 31/01/2018 17:59
 */
public class MyInputMethodService extends InputMethodService {

    private MyKeyboardView myKeyboardView_Num,myKeyboardView_Letter,myKeyboardView_Function;
    private Keyboard keyboard_num,keyboard_letter,keyboard_Function;
    private Context mContext;


    /**
     * 初始化键盘视图
     *
     * @return
     */
    @Override
    public View onCreateInputView() {
        mContext =this;

        View view = getLayoutInflater().
                inflate(R.layout.keyboard_global, null);

        myKeyboardView_Num = view.findViewById(R.id.keyboardView_Num);
        myKeyboardView_Letter = view.findViewById(R.id.keyboardView_Letter);
        myKeyboardView_Function = view.findViewById(R.id.keyboardView_Function);

        keyboard_num = new Keyboard(this,R.xml.num);
        keyboard_letter = new Keyboard(this,R.xml.letter);
        keyboard_Function= new Keyboard(this,R.xml.function);

        myKeyboardView_Num.setKeyboard(keyboard_num);
        myKeyboardView_Letter.setKeyboard(keyboard_letter);
        myKeyboardView_Function.setKeyboard(keyboard_Function);

        myKeyboardView_Function.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {

            }

            @Override
            public void onRelease(int primaryCode) {

            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {

            }

            @Override
            public void onText(CharSequence text) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });

        myKeyboardView_Letter.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {

            }

            @Override
            public void onRelease(int primaryCode) {

            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                //键盘服务
                MyInputMethodService service = (MyInputMethodService) mContext;
                //当前输入的连接
                InputConnection ic = service.getCurrentInputConnection();


                switch (primaryCode) {

                    //删除
                    case Keyboard.KEYCODE_DELETE:


                        ic.deleteSurroundingText(1, 0);


                        break;
                    //完成
                    case Keyboard.KEYCODE_DONE:


                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));


                        break;

                    // 大小写切换
                    case Keyboard.KEYCODE_SHIFT:

//                        MyUtil.switchUpperOrLowerCase(mIsUpper, mKeyboardLetter);
//                        mIsUpper = !mIsUpper;
//                        setKeyboard(mKeyboardLetter);

                        break;

                    // 数字键盘切换
                    case DemoKeyCode.CODE_TYPE_NUM:
//                        setKeyboard(mKeyboardNum);

                        break;
                    // 字母键盘切换
                    case DemoKeyCode.CODE_TYPE_QWERTY:
//                        setKeyboard(mKeyboardLetter);

                        break;

                    // 符号键盘切换
                    case DemoKeyCode.CODE_TYPE_SYMBOL:
//                        setKeyboard(mKeyboardSymbol);

                        break;

                    //settings
                    case DemoKeyCode.CODE_SETTING:
                        Toast.makeText(service, "Settings==", Toast.LENGTH_SHORT).show();

                        break;

                    //切换输入法
                    case DemoKeyCode.CODE_TYPE_SWITCH_INPUT:


                        ((InputMethodManager) service.getSystemService(Context.INPUT_METHOD_SERVICE)).showInputMethodPicker();

                        break;

                    //一般文本
                    default:
                        char inputChar = (char) primaryCode;

                        ic.commitText(String.valueOf(inputChar), 1);


                }

            }

            @Override
            public void onText(CharSequence text) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });


        myKeyboardView_Num.setOnKeyboardActionListener(new KeyboardView.OnKeyboardActionListener() {
            @Override
            public void onPress(int primaryCode) {

            }

            @Override
            public void onRelease(int primaryCode) {

            }

            @Override
            public void onKey(int primaryCode, int[] keyCodes) {
                //键盘服务
                MyInputMethodService service = (MyInputMethodService)mContext;
                //当前输入的连接
                InputConnection ic = service.getCurrentInputConnection();


                switch (primaryCode) {

                    //删除
                    case Keyboard.KEYCODE_DELETE:


                        ic.deleteSurroundingText(1, 0);


                        break;
                    //完成
                    case Keyboard.KEYCODE_DONE:


                        ic.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ENTER));


                        break;

                    // 大小写切换
                    case Keyboard.KEYCODE_SHIFT:

//                        MyUtil.switchUpperOrLowerCase(mIsUpper, mKeyboardLetter);
//                        mIsUpper = !mIsUpper;
//                        setKeyboard(mKeyboardLetter);

                        break;

                    // 数字键盘切换
                    case DemoKeyCode.CODE_TYPE_NUM:
//                        setKeyboard(mKeyboardNum);

                        break;
                    // 字母键盘切换
                    case DemoKeyCode.CODE_TYPE_QWERTY:
//                        setKeyboard(mKeyboardLetter);

                        break;

                    // 符号键盘切换
                    case DemoKeyCode.CODE_TYPE_SYMBOL:
//                        setKeyboard(mKeyboardSymbol);

                        break;

                    //settings
                    case DemoKeyCode.CODE_SETTING:
                        Toast.makeText(service, "Settings==", Toast.LENGTH_SHORT).show();

                        break;

                    //切换输入法
                    case DemoKeyCode.CODE_TYPE_SWITCH_INPUT:


                        ((InputMethodManager) service.getSystemService(Context.INPUT_METHOD_SERVICE)).showInputMethodPicker();

                        break;

                    //一般文本
                    default:
                        char inputChar = (char) primaryCode;

                        ic.commitText(String.valueOf(inputChar), 1);


                }

            }

            @Override
            public void onText(CharSequence text) {

            }

            @Override
            public void swipeLeft() {

            }

            @Override
            public void swipeRight() {

            }

            @Override
            public void swipeDown() {

            }

            @Override
            public void swipeUp() {

            }
        });
        return view;
    }




    @Override
    public View onCreateCandidatesView() {
        TextView textView = new TextView(getBaseContext());
        textView.setText("fdsfdsf");
        return textView;
    }
}
