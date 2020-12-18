package com.example.wechating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wechating.ui.friends.FriendsFragment;

public class AddFriendPage extends AppCompatActivity implements View.OnClickListener{
    private Button search;
    private Button addfriendBtn;
    private LinearLayout l1;
    private LinearLayout l2;
    private EditText searchEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_friend_page);
        search=(Button)findViewById(R.id.search_id);
        addfriendBtn=(Button)findViewById(R.id.add_friend_btn);
        l1=(LinearLayout)findViewById(R.id.add_friend_page);
        l2=(LinearLayout)findViewById(R.id.add_friend_page2);
        searchEdit=(EditText)findViewById(R.id.add_friend_edit);
        l1.setVisibility(View.GONE);
        l2.setVisibility(View.GONE);
        search.setOnClickListener(this);
        addfriendBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.search_id){
            if(searchEdit.getText().toString().equals("txltxl8")){
                l2.setVisibility(View.GONE);
                l1.setVisibility(View.VISIBLE);
            }
            else
                l2.setVisibility(View.VISIBLE);

        }
        else if(v.getId()==R.id.add_friend_btn){
            Toast.makeText(AddFriendPage.this,"恭喜，添加成功!",Toast.LENGTH_LONG).show();
            FriendsFragment.update();
            Intent intent=new Intent(AddFriendPage.this, Index.class);
            intent.putExtra("flag", 2);
            startActivity(intent);
            AddFriendPage.this.finish();
        }
    }
}