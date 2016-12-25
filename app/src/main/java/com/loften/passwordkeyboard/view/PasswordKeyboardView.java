package com.loften.passwordkeyboard.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jungly.gridpasswordview.GridPasswordView;
import com.loften.passwordkeyboard.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lcw on 2016/11/28.
 *
 布局添加：
 <com.loften.passwordkeyboard.view.PasswordKeyboardView
 android:id="@+id/view_password"
 android:layout_alignParentBottom="true"
 android:layout_width="match_parent"
 android:layout_height="wrap_content"/>
 */

public class PasswordKeyboardView extends RelativeLayout{

    private View mView;
    private ImageView mClose;
    private TextView mTitle;
    private TextView mForgetPwd;
    private GridPasswordView mPassword;
    private CustomKeyboardView mKeyboard;
    private List<String> passwordList;//记录键盘输入的值
    private StringBuilder mValue;//最后保存的密码

    public PasswordKeyboardView(Context context) {
        this(context, null);
    }

    public PasswordKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mView = View.inflate(context, R.layout.view_password, null);

        initView();
        initEvent();

        addView(mView);
    }

    private void initView(){
        mPassword = (GridPasswordView) mView.findViewById(R.id.view_password);
        mClose = (ImageView) mView.findViewById(R.id.img_close);
        mTitle = (TextView) mView.findViewById(R.id.tv_title);
        mForgetPwd = (TextView) mView.findViewById(R.id.tv_forgetPwd);
        mKeyboard = (CustomKeyboardView) mView.findViewById(R.id.view_keyboard);
        //打乱数字的位置
        mKeyboard.shuffleKeyboard();
    }

    private void initEvent(){
        mValue = new StringBuilder();
        passwordList = new ArrayList<>();
        mKeyboard.setIOnKeyboardListener(new CustomKeyboardView.IOnKeyboardListener() {

            @Override
            public void onInsertKeyEvent(String text) {
                mValue.setLength(0);
                passwordList.add(text);
                for (int i = 0; i < passwordList.size(); i++) {
                    mValue.append(passwordList.get(i));
                }
                mPassword.setPassword(mValue.toString());
            }

            @Override
            public void onDeleteKeyEvent() {
                mValue.setLength(0);
                if(passwordList.size() != 0){
                    passwordList.remove(passwordList.size()-1);
                    for (int i = 0; i < passwordList.size(); i++) {
                        mValue.append(passwordList.get(i));
                    }
                    mPassword.setPassword(mValue.toString());
                }
            }
        });
    }

    //获取输入的密码
    public String getPassword(){
        return mValue.toString();
    }

    //取消
    public ImageView getCloseImageView(){
        return mClose;
    }

    //标题
    public TextView getTitleTextView(){
        return mTitle;
    }

    //忘记密码
    public TextView getForgetTextView() {
        return mForgetPwd;
    }

    //输入密码控件
    public GridPasswordView getPswView(){
        return mPassword;
    }

}
