package com.example.wechating;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wechating.domain.User;
import com.example.wechating.utils.UserUtil;

/**
 * Created by txllg on 2020/11/29.
 */

public class LoginActivity extends AppCompatActivity {
    private Button loginbtn;
    private EditText usernameText;
    private EditText passwordText;
    private TextView errorTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        loginbtn=(Button)findViewById(R.id.btn_login);
        usernameText=(EditText)findViewById(R.id.login_username_input);
        passwordText=(EditText)findViewById(R.id.login_password_input);
        errorTip=(TextView) findViewById(R.id.error_tip);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameText.getText().toString();
                String password=passwordText.getText().toString();
                User user=new User(username,password);
                Boolean res=UserUtil.login(user);
                if(!res)//用户名或密码错误
                    errorTip.setText("用户名或密码错误,请检查");

                else{//登陆成功
                    Intent intent=new Intent(LoginActivity.this,Index.class);
                    startActivity(intent);

                }
            }
        });
    }
}
