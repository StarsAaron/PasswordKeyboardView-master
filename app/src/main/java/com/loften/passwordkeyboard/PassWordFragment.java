package com.loften.passwordkeyboard;


import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jungly.gridpasswordview.GridPasswordView;
import com.loften.passwordkeyboard.view.PasswordKeyboardView;

//也可继承design中的BottomSheetDialogFragment
public class PassWordFragment extends DialogFragment {

    private PasswordKeyboardView mPassword;

    public static PassWordFragment newInstace(){
        PassWordFragment passWordFragment = new PassWordFragment();

        return passWordFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //继承BottomSheetDialogFragment时onStart()可注释掉
    @Override
    public void onStart() {
        super.onStart();

        Window win = getDialog().getWindow();
        win.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.transparent)));
        WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();

        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        layoutParams.width = (int) (dm.widthPixels);
        win.setAttributes(layoutParams);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.dialog_password, container);
        init(view);
        return view;
    }

    private void init(View view){
        mPassword = (PasswordKeyboardView) view.findViewById(R.id.view_password);
        mPassword.getCloseImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mPassword.getForgetTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"忘记密码",Toast.LENGTH_SHORT).show();
            }
        });

        mPassword.getPswView().setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {
                if (mPassword.getPassword().length() == 6){
                    dismiss();
                    Toast.makeText(getActivity(),mPassword.getPassword(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onInputFinish(String psw) {

            }
        });
    }
}
