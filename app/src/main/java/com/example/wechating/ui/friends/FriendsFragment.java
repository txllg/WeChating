package com.example.wechating.ui.friends;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.wechating.ChatPage;
import com.example.wechating.R;
import com.example.wechating.component.SideBar;
import com.example.wechating.component.FriendsSlideLayout;
import com.example.wechating.domain.Friends;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FriendsFragment extends Fragment {

    private FriendsViewModel friendsViewModel;
    private ListView listView;
    private SideBar sideBar;
    private ArrayList<Friends> list;
    private ArrayList<Integer> idList;
    private MyAdapter myAdapter;
    private Set<FriendsSlideLayout> sets = new HashSet();
    private static boolean flag=false;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        friendsViewModel =
                new ViewModelProvider(this).get(FriendsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_friends, container, false);
        friendsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        listView = (ListView) root.findViewById(R.id.friends_listView);
        sideBar = (SideBar) root.findViewById(R.id.side_bar);
        initView();
        initData();
        return root;
    }


        private void initView() {

        sideBar.setOnStrSelectCallBack(new SideBar.ISideBarSelectCallBack() {
            @Override
            public void onSelectStr(int index, String selectStr) {
                for (int i = 0; i < list.size(); i++) {
                    if (selectStr.equalsIgnoreCase(list.get(i).getFirstLetter())) {
                        listView.setSelection(i); // 选择到首字母出现的位置
                        return;
                    }
                }
            }
        });
    }




    class MyAdapter extends BaseAdapter
    {
        private Context content;
        private ArrayList<Friends> datas;
        private MyAdapter(Context context, ArrayList<Friends> datas)
        {
            this.content = context;
            this.datas = datas;
        }
        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder=null;
            final Friends friends = datas.get(position);
            final Integer p_id=idList.get(position);

            if (convertView == null)
            {
                convertView = LayoutInflater.from(content).inflate(R.layout.friends_item, null);
                viewHolder = new ViewHolder();
                viewHolder.nameView= (TextView) convertView.findViewById(R.id.name);
                viewHolder.catlogView= (TextView) convertView.findViewById(R.id.catalog);
                viewHolder.menuView = (TextView) convertView.findViewById(R.id.menu_friends);
                viewHolder.profile=(ImageView) convertView.findViewById(R.id.friends_profile);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.profile.setImageResource(idList.get(position));
            viewHolder.nameView.setText(datas.get(position).getName());


            //根据position获取首字母作为目录catalog
            String catalog = list.get(position).getFirstLetter();

            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if(position == getPositionForSection(catalog)){
                viewHolder.catlogView.setVisibility(View.VISIBLE);
                viewHolder.catlogView.setText(friends.getFirstLetter().toUpperCase());
            }else{
                viewHolder.catlogView.setVisibility(View.GONE);
            }


            String nickname=viewHolder.nameView.getText().toString();
            viewHolder.nameView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(content, "click "+((TextView)v).getText(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getActivity(), ChatPage.class);
                    intent.putExtra("nickname",nickname);
                    startActivity(intent);
                }
            });

            viewHolder.menuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FriendsSlideLayout slideLayout = (FriendsSlideLayout) v.getParent();
                    slideLayout.closeMenu(); //解决删除item后下一个item变成open状态问题
                    datas.remove(friends);
                    idList.remove(p_id);
                    notifyDataSetChanged();
                }
            });

            FriendsSlideLayout slideLayout = (FriendsSlideLayout) convertView;
            slideLayout.setOnStateChangeListener(new MyOnStateChangeListener());


            return convertView;
        }

        public FriendsSlideLayout slideLayout = null;
        class MyOnStateChangeListener implements FriendsSlideLayout.OnStateChangeListener
        {
            /**
             * 滑动后每次手势抬起保证只有一个item是open状态，加入sets集合中
             **/
            @Override
            public void onOpen(FriendsSlideLayout layout) {
                slideLayout = layout;
                if (sets.size() > 0) {
                    for (FriendsSlideLayout s : sets) {
                        s.closeMenu();
                        sets.remove(s);
                    }
                }
                sets.add(layout);
            }

            @Override
            public void onMove(FriendsSlideLayout layout) {
                if (slideLayout != null && slideLayout !=layout)
                {
                    slideLayout.closeMenu();
                }
            }

            @Override
            public void onClose(FriendsSlideLayout layout) {
                if (sets.size() > 0) {
                    sets.remove(layout);
                }
                if(slideLayout ==layout){
                    slideLayout = null;
                }
            }
        }

        public int getPositionForSection(String catalog) {
            for (int i = 0; i < getCount(); i++) {
                String sortStr = list.get(i).getFirstLetter();
                if (catalog.equalsIgnoreCase(sortStr)) {
                    return i;
                }
            }
            return -1;
        }
    }
    static class ViewHolder
    {
        public TextView catlogView;
        public TextView menuView;
        public TextView nameView;
        public ImageView profile;
    }

    private void initData() {
        list=new ArrayList<>();
        list.add(new Friends("四狗子"));
        list.add(new Friends("五娃"));
        list.add(new Friends("刘富贵"));
        list.add(new Friends("七娃"));
        list.add(new Friends("梁朝伟"));
        list.add(new Friends("亳州"));
        list.add(new Friends("杜琪峰"));
        list.add(new Friends("白嫖狗"));
        list.add(new Friends("大炮"));
        list.add(new Friends("二娃"));
        list.add(new Friends("饿汉"));
        list.add(new Friends("三娃"));
        list.add(new Friends("美羊羊"));
        list.add(new Friends("蜘蛛侠"));
        list.add(new Friends("渣渣辉"));

        if(flag)
            list.add(new Friends("张三"));
        Collections.sort(list); // 对list进行排序，需要让User实现Comparable接口重写compareTo方法
        myAdapter = new MyAdapter(getActivity().getApplicationContext(), list);
        listView.setAdapter(myAdapter);

        idList=new ArrayList<>();
        idList.add(R.drawable.p1);
        idList.add(R.drawable.p2);
        idList.add(R.drawable.p3);
        idList.add(R.drawable.p4);
        idList.add(R.drawable.p5);
        idList.add(R.drawable.p6);
        idList.add(R.drawable.p7);
        idList.add(R.drawable.p8);
        idList.add(R.drawable.p9);
        idList.add(R.drawable.p10);
        idList.add(R.drawable.p11);
        idList.add(R.drawable.p12);
        idList.add(R.drawable.p13);
        if(flag)
            idList.add(R.drawable.p17);
        idList.add(R.drawable.p18);
        idList.add(R.drawable.p17);

    }

    public static void update(){
        flag=true;
    }

}