package com.loften.passwordkeyboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.btn_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PassWordFragment.newInstace().show(getSupportFragmentManager(),"PassWordFragment");
                // 弹出支付密码的框
                PasswordPopupWindow passwordPopupWindow = new PasswordPopupWindow(MainActivity.this)
                        .setForgetListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                            }
                        })
                        .setPasswordChangedListener(new PasswordPopupWindow.PasswordFinishedListener() {
                            @Override
                            public void onInputFinish(PasswordPopupWindow window, String psw) {
                                window.cancel();
                                Toast.makeText(MainActivity.this,psw,Toast.LENGTH_SHORT).show();
                            }
                        });
                passwordPopupWindow.show(button);
            }
        });


    }
}
