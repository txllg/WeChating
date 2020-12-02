package com.example.wechating;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.wechating.domain.User;
import com.example.wechating.utils.JDBCUtils;
import com.example.wechating.utils.UserUtil;

import java.sql.SQLException;

/**
 * Created by txllg on 2020/11/29.
 */

public class RegistActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usernameText;
    private EditText passwordText;
    private TextView errorTip;
    private Button registbtn;
    private Button toLoginbtn;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regist);
        registbtn=(Button)findViewById(R.id.btn_regist);
        toLoginbtn=(Button)findViewById(R.id.toLogin_id);
        usernameText=(EditText)findViewById(R.id.regist_username_input);
        passwordText=(EditText)findViewById(R.id.regist_password_input);
        errorTip=(TextView)findViewById(R.id.error_tip);
        registbtn.setOnClickListener(this);
        toLoginbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_regist){//注册
            User user=new User(usernameText.getText().toString(),passwordText.getText().toString());
            boolean res=false;
            res= UserUtil.addUser(user);//res为true，则插入成功

            if(!res)//用户名存在
                errorTip.setText("用户名已经存在,请重新输入");
            else{
                errorTip.setText("");
                Toast.makeText(RegistActivity.this,"注册成功，现在可以去登陆了",Toast.LENGTH_LONG).show();
            }
        }
        else if(v.getId()==R.id.toLogin_id){//去登陆页面
            Intent intent=new Intent(RegistActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}
