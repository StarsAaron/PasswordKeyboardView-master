package com.loften.passwordkeyboard;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jungly.gridpasswordview.GridPasswordView;
import com.loften.passwordkeyboard.view.PasswordKeyboardView;

/**
 * 密码输入弹框
 * Created by Aaron on 2016/12/24.
 */
public class PasswordPopupWindow {
    private PopupWindow popupWindow;
    private PasswordKeyboardView mPassword;

    public PasswordPopupWindow(){}

    public PasswordPopupWindow(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_password, null);
        mPassword = (PasswordKeyboardView)view.findViewById(R.id.view_password);
        popupWindow = new PopupWindow(view, displayMetrics.widthPixels, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setFocusable(true);

        mPassword.getCloseImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 显示
     */
    public void show(View showBelow){
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        popupWindow.showAtLocation(showBelow, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 取消弹框
     */
    public void cancel(){
        if(popupWindow.isShowing()){
            popupWindow.dismiss();
            popupWindow = null;
            mPassword = null;
        }
    }

    /**
     * 设置点击忘记密码监听
     * @param listener
     * @return
     */
    public PasswordPopupWindow setForgetListener(View.OnClickListener listener){
        mPassword.getForgetTextView().setOnClickListener(listener);
        return this;
    }

    /**
     * 设置输入密码监听
     * @param listener
     * @return
     */
    public PasswordPopupWindow setPasswordChangedListener(final PasswordFinishedListener listener){
        mPassword.getPswView().setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                Log.i("","");
                if(mPassword.getPassword().length() == 6){
                    listener.onInputFinish(PasswordPopupWindow.this,mPassword.getPassword());
                }
                Log.i("","");
            }

            @Override
            public void onInputFinish(String psw) {
            }
        });
        return this;
    }

    public interface PasswordFinishedListener{
        void onInputFinish(PasswordPopupWindow window, String psw);
    }
}
