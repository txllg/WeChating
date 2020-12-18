package com.example.wechating.ui.news;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wechating.MainActivity;
import com.example.wechating.MakeMomentPage;
import com.example.wechating.R;
import com.example.wechating.component.NewsAdapter;
import com.example.wechating.domain.Friends;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsFragment extends Fragment implements View.OnClickListener {

    private NewsViewModel newsViewModel;
    private List<Friends> list;
    private static NewsAdapter adapter;
    private ImageView goMakeMoment;
    private List<Integer> profiles;
    private List<String> nickname;
    private List<List<Integer>> itemPictures;
    private List<Integer> itemPicture;
    private List<String> copyWriting;
    private List<Integer> times;
    private static boolean flag=false;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_news, container, false);
        newsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });


        AppBarLayout appBarLayout = root.findViewById(R.id.appBar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                CollapsingToolbarLayout collapsingToolbar = root.findViewById(R.id.collapsing_toolbar);
                int color = Color.argb(200,0,0,0);
                collapsingToolbar.setCollapsedTitleTextColor(color);
                ImageView imageView = root.findViewById(R.id.image1);
                if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) { // 折叠状态
                    collapsingToolbar.setTitle("朋友圈");
                    imageView.setVisibility(View.GONE);
                } else { // 非折叠状态
                    collapsingToolbar.setTitle("");
                    imageView.setVisibility(View.VISIBLE);
                }
            }
        });
        initFriends();
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NewsAdapter(list,profiles,nickname,itemPictures,copyWriting,times);
        recyclerView.setAdapter(adapter);


        goMakeMoment=root.findViewById(R.id.camera_id);
        goMakeMoment.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getActivity(), MakeMomentPage.class);
        startActivity(intent);

    }



    private void initFriends() {
        list=new ArrayList<>();
        if(flag)
            list.add(new Friends("陈冠希"));
        list.add(new Friends("四狗子"));
        list.add(new Friends("五娃"));
        list.add(new Friends("刘富贵"));
        list.add(new Friends("七娃"));
        list.add(new Friends("梁朝伟"));
        list.add(new Friends("亳州"));
        list.add(new Friends("杜琪峰"));
        list.add(new Friends("大炮"));

        nickname=new ArrayList<>();
        if(flag)
            nickname.add("陈冠希");
        nickname.add("四狗子");
        nickname.add("五娃");
        nickname.add("刘富贵");
        nickname.add("七娃");
        nickname.add("梁朝伟");
        nickname.add("亳州");
        nickname.add("杜琪峰");
        nickname.add("大炮");

        profiles=new ArrayList<>();//朋友圈好友的头像
        if(flag)
            profiles.add(R.drawable.p16);
        profiles.add(R.drawable.p12);
        profiles.add(R.drawable.p13);
        profiles.add(R.drawable.p8);
        profiles.add(R.drawable.p10);
        profiles.add(R.drawable.p7);
        profiles.add(R.drawable.p2);
        profiles.add(R.drawable.p4);
        profiles.add(R.drawable.p3);

        times=new ArrayList<>();//发布动态距离现在的时间
        if(flag)
            times.add(0);
        times.add(25);
        times.add(35);
        times.add(50);
        times.add(1);
        times.add(2);
        times.add(5);
        times.add(7);
        times.add(8);



        itemPictures=new ArrayList<>();
        itemPicture=new ArrayList<>();
        if(flag){
            itemPictures.add(itemPicture);
        }

        itemPicture=new ArrayList<>();
        itemPicture.add(R.drawable.pyq1);
        itemPictures.add(itemPicture);

        itemPicture=new ArrayList<>();
        itemPicture.add(R.drawable.pyq2_1);
        itemPicture.add(R.drawable.pyq2_2);
        itemPictures.add(itemPicture);

        itemPicture=new ArrayList<>();
        itemPicture.add(R.drawable.pyq3_1);
        itemPicture.add(R.drawable.pyq3_2);
        itemPictures.add(itemPicture);

        itemPicture=new ArrayList<>();
        itemPicture.add(R.drawable.pyq4_1);
        itemPicture.add(R.drawable.pyq4_2);
        itemPicture.add(R.drawable.pyq4_3);
        itemPictures.add(itemPicture);

        itemPicture=new ArrayList<>();
        itemPicture.add(R.drawable.pyq5_1);
        itemPicture.add(R.drawable.pyq5_2);
        itemPictures.add(itemPicture);

        itemPicture=new ArrayList<>();
        itemPicture.add(R.drawable.pyq6);
        itemPictures.add(itemPicture);

        itemPicture=new ArrayList<>();
        itemPicture.add(R.drawable.pyq7);
        itemPictures.add(itemPicture);

        itemPicture=new ArrayList<>();
        itemPicture.add(R.drawable.pyq8_1);
        itemPicture.add(R.drawable.pyq8_2);
        itemPictures.add(itemPicture);


        copyWriting=new ArrayList<>();
        if(flag)
            copyWriting.add("hello");
        copyWriting.add("又挂科了");
        copyWriting.add("今天辛苦了，犒劳自己一下");
        copyWriting.add("给大家推荐两部好看的电影，真心不错");
        copyWriting.add("今天天气真不错");
        copyWriting.add("两只都好可爱");
        copyWriting.add("有要打球的吗？没有我等一会儿再来问问");
        copyWriting.add("哪位大哥有爱奇艺会员，求借一下");
        copyWriting.add("风景真的好");

    }

    public static void update(){
        flag=true;
        adapter.notifyDataSetChanged();
    }

}




