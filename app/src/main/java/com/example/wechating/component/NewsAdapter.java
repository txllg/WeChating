package com.example.wechating.component;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wechating.R;
import com.example.wechating.domain.Friends;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private Context mContext;
        private List<Friends> mFriendList;
        private List<Integer> profiles;
        private List<String> nickname;
        private List<List<Integer>> itemPictures;
        private List<String> copyWriting;
        private List<Integer> times;

        static class ViewHolder extends RecyclerView.ViewHolder {
            CardView cardView;
            ImageView image1;
            ImageView image2;
            ImageView image3;
            ImageView imageStar;
            ImageView profile;
            TextView nickname;
            TextView timeText;
            TextView copyWriting;

            public ViewHolder(View view) {
                super(view);
                cardView = (CardView) view;
                image1 = (ImageView) view.findViewById(R.id.friend_image1);
                image2 = (ImageView) view.findViewById(R.id.friend_image2);
                image3 = (ImageView) view.findViewById(R.id.friend_image3);
                imageStar=(ImageView) view.findViewById(R.id.star1);
                profile=(ImageView) view.findViewById(R.id.news_profile);
                nickname=(TextView) view.findViewById(R.id.friend_name);
                timeText=(TextView) view.findViewById(R.id.time_text);
                copyWriting=(TextView) view.findViewById(R.id.copyWriting);
            }
        }

        public NewsAdapter(List<Friends> friendList,List<Integer> profiles,
                           List<String> nickname,List<List<Integer>> itemPictures,List<String> copyWriting,List<Integer> times) {

            mFriendList = friendList;//朋友圈好友显示的昵称
            this.profiles=profiles;
            this.nickname=nickname;
            this.itemPictures=itemPictures;
            this.copyWriting=copyWriting;
            this.times=times;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mContext == null) {
                mContext = parent.getContext();
            }
            View view = LayoutInflater.from(mContext).inflate(R.layout.news_item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,int position) {

            holder.imageStar.setOnClickListener(new View.OnClickListener() {//点赞
                @Override
                public void onClick(View v) {
                    holder.imageStar.setImageResource(R.drawable.star2);
                }
            });

            holder.profile.setImageResource(profiles.get(position));
            holder.nickname.setText(nickname.get(position));
            holder.copyWriting.setText(copyWriting.get(position));
            if(itemPictures.get(position).size()==0);
            else if(itemPictures.get(position).size()==1)
                holder.image1.setImageResource(itemPictures.get(position).get(0));
            else if(itemPictures.get(position).size()==2)
                holder.image2.setImageResource(itemPictures.get(position).get(1));
            else if(itemPictures.get(position).size()==3)
                holder.image3.setImageResource(itemPictures.get(position).get(2));


            int time=times.get(position);
            if(time>0&&time<9)
                holder.timeText.setText(time+"小时前");
            else if(time>10)
                holder.timeText.setText(time+"分钟前");
            else holder.timeText.setText("刚刚");
        }

        @Override
        public int getItemCount() {
            return mFriendList.size();
        }

}
