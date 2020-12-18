package com.example.wechating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wechating.component.MsgAdapter;
import com.example.wechating.domain.Msg;
import com.github.xubo.statusbarutils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

public class ChatPage extends AppCompatActivity {

    List<Msg> list = new ArrayList<>();
    RecyclerView recyclerView;
    Toolbar toolbar;
    MsgAdapter msgAdapter;
    Context context = ChatPage.this;
    EditText msg_say;
    Button emoji;
    TextView send;
    LinearLayout linearLayout;
    int lastx = 0;
    int lasty = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_page);
        System.out.println("执行了charPage");
        recyclerView = findViewById(R.id.msg_list);
        toolbar = findViewById(R.id.msg_toolbar);
        msg_say = findViewById(R.id.msg_say);
//        emoji = findViewById(R.id.msg_emoji);
        send = findViewById(R.id.msg_send);
        linearLayout = findViewById(R.id.linear);
        initData();
        initView();
        initStatusBar();
    }

    @SuppressLint("ResourceAsColor")
    private void initStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            StatusBarUtils.setStatusBarColor(ChatPage.this, R.color.colorGray);
        }
    }

    private void initData() {
//        User user = (User) getIntent().getSerializableExtra("user_data");
//        toolbar.setTitle(user.getName());
        Intent intent=getIntent();
        toolbar.setTitle(intent.getStringExtra("nickname"));

        Msg msg = new Msg("hello", Msg.type_received);
        list.add(msg);
        Msg msg1 = new Msg("hello", Msg.type_sent);
        list.add(msg1);


    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        msgAdapter = new MsgAdapter(list, context);
        recyclerView.setAdapter(msgAdapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        send.getLayoutParams().width = emoji.getLayoutParams().width;
        msg_say.addTextChangedListener(textWatcher);
//        WithSoftUp.addLayoutListener(findViewById(R.id.parent), linearLayout);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (msg_say.getEditableText().length() >= 1) {
                    String content = msg_say.getText().toString();
                    if (!"".equals(content)) {
                        Msg msg = new Msg(content, Msg.type_sent);
                        list.add(msg);
                        Msg msg1 = new Msg(content, Msg.type_received);
                        list.add(msg1);
                        msgAdapter.notifyItemInserted(list.size() - 1);
                        recyclerView.scrollToPosition(list.size() - 1);
                        msg_say.setText("");

                    }
                } else {
                    Toast.makeText(context, "点击add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void afterTextChanged(Editable s) {
            if (msg_say.getEditableText().length() >= 1) {
                send.setBackgroundResource(R.drawable.buttonbackground);
                send.setText("发送");
//                send.getLayoutParams().height = emoji.getLayoutParams().height;
//                send.getLayoutParams().width = emoji.getLayoutParams().width + 30;
                send.setTextColor(context.getResources().getColor(R.color.white));
            } else {
                send.setBackgroundResource(R.drawable.addbtn);
//                send.getLayoutParams().height = emoji.getLayoutParams().height;
//                send.getLayoutParams().width = emoji.getLayoutParams().width;
                send.setText("");
            }
        }
    };

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastx = x;
                lasty = y;
                break;
            case MotionEvent.ACTION_UP:
                int curx = x;
                int cury = y;

                if (curx-lastx>300&&Math.abs(cury-lasty)<200)
                    finish();
                break;
        }
        return super.dispatchTouchEvent(event);
    }
}