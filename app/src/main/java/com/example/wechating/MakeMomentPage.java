package com.example.wechating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wechating.ui.news.NewsFragment;

public class MakeMomentPage extends AppCompatActivity {

    private Button makeMomentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_moment_page);


        makeMomentBtn=(Button)findViewById(R.id.make_moment);
        makeMomentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsFragment.update();
                Intent intent=new Intent(MakeMomentPage.this, Index.class);
                intent.putExtra("flag", 2);
                startActivity(intent);
                MakeMomentPage.this.finish();
            }
        });
    }

}