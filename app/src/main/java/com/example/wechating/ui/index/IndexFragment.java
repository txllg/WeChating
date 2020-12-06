package com.example.wechating.ui.index;

import android.content.Context;
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

import com.example.wechating.R;
import com.example.wechating.component.FriendsSlideLayout;
import com.example.wechating.component.MessageSlideLayout;
import com.example.wechating.domain.Friends;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class IndexFragment extends Fragment {

    private IndexViewModel indexViewModel;
    private ListView listView;
    private ArrayList<Friends> mDatas;
    private MyAdapter myAdapter;
    private Set<MessageSlideLayout> sets = new HashSet();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        indexViewModel =
                new ViewModelProvider(this).get(IndexViewModel.class);
        View root = inflater.inflate(R.layout.fragment_index, container, false);
        indexViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });


        listView=(ListView)root.findViewById(R.id.index_listView);
        initData();

        return root;
    }

    public void initData(){
        mDatas = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            mDatas.add(new Friends("content"+i));
        }
        myAdapter = new MyAdapter(getActivity().getApplicationContext(), mDatas);
        listView.setAdapter(myAdapter);
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
            if (convertView == null)
            {
                convertView = LayoutInflater.from(content).inflate(R.layout.message_item, null);
                viewHolder = new ViewHolder();
                viewHolder.contentView= (TextView) convertView.findViewById(R.id.content);
                viewHolder.menuView = (TextView) convertView.findViewById(R.id.message_menu);
                viewHolder.profile=(ImageView) convertView.findViewById(R.id.message_profile);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            viewHolder.profile.setImageResource(R.drawable.app);
            viewHolder.contentView.setText(datas.get(position).getName());

            viewHolder.contentView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(content, "click "+((TextView)v).getText(), Toast.LENGTH_SHORT).show();
                }
            });
            final Friends myContent = datas.get(position);
            viewHolder.menuView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MessageSlideLayout slideLayout = (MessageSlideLayout) v.getParent();
                    slideLayout.closeMenu(); //解决删除item后下一个item变成open状态问题
                    datas.remove(myContent);
                    notifyDataSetChanged();
                }
            });

            MessageSlideLayout slideLayout = (MessageSlideLayout) convertView;
            slideLayout.setOnStateChangeListener(new MyOnStateChangeListener());


            return convertView;
        }

        public MessageSlideLayout slideLayout = null;
        class MyOnStateChangeListener implements MessageSlideLayout.OnStateChangeListener
        {
            /**
             * 滑动后每次手势抬起保证只有一个item是open状态，加入sets集合中
             **/
            @Override
            public void onOpen(MessageSlideLayout layout) {
                slideLayout = layout;
                if (sets.size() > 0) {
                    for (MessageSlideLayout s : sets) {
                        s.closeMenu();
                        sets.remove(s);
                    }
                }
                sets.add(layout);
            }

            @Override
            public void onMove(MessageSlideLayout layout) {
                if (slideLayout != null && slideLayout !=layout)
                {
                    slideLayout.closeMenu();
                }
            }

            @Override
            public void onClose(MessageSlideLayout layout) {
                if (sets.size() > 0) {
                    sets.remove(layout);
                }
                if(slideLayout ==layout){
                    slideLayout = null;
                }
            }
        }
    }

    static class ViewHolder
    {
        public TextView contentView;
        public TextView menuView;
        public ImageView profile;
    }

}