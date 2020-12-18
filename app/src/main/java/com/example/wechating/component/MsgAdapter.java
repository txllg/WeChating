package com.example.wechating.component;
 
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
 

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wechating.R;
import com.example.wechating.domain.Msg;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder> {
    List<Msg> msgs = new ArrayList<>();
    Context context;
    LayoutInflater inflater;
 
    public MsgAdapter(List<Msg> msgs, Context context) {
        this.msgs = msgs;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
 
    @NonNull
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.msg_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
 
    @Override
    public void onBindViewHolder(@NonNull MsgAdapter.ViewHolder holder, int position) {
        Msg msg = msgs.get(position);
        if (msg.getType() == Msg.type_received) {
            if(position<2)
                holder.left_layout.setVisibility(View.VISIBLE);
            else
                holder.left_layout.setVisibility(View.GONE);
            holder.right_layout.setVisibility(View.GONE);
            holder.left_msg.setText(msg.getContent());
        } else if (msg.getType() == Msg.type_sent) {
            holder.right_layout.setVisibility(View.VISIBLE);
            holder.left_layout.setVisibility(View.GONE);
            holder.right_msg.setText(msg.getContent());
 
        }
    }
 
    @Override
    public int getItemCount() {
        return msgs.size();
    }
 
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView left_msg;
        TextView right_msg;
        LinearLayout left_layout;
        LinearLayout right_layout;
 
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            left_msg = itemView.findViewById(R.id.left_msg);
            right_msg = itemView.findViewById(R.id.right_msg);
            left_layout = itemView.findViewById(R.id.left_layout);
            right_layout = itemView.findViewById(R.id.right_layout);
        }
    }
}