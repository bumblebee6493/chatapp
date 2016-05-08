package com.example.pankajkumar.letschat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pankajkumar on 6/5/16.
 */
public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    List<ChatInfo> chatInfoList = new ArrayList<>();

    public ChatAdapter(Context context, List<ChatInfo> chatInfoList){
        this.chatInfoList = chatInfoList;
    }

    public ChatAdapter(){}

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return chatInfoList.size();
    }

    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        holder.text.setText(chatInfoList.get(position).text);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        holder.text.setLayoutParams(layoutParams);
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_row, parent, false);
        return new ChatViewHolder(item);
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        RelativeLayout layout;

        public ChatViewHolder(View v){
            super(v);
            text = (TextView)v.findViewById(R.id.chat_text);
            layout = (RelativeLayout) v.findViewById(R.id.chat_row_layout);
        }
    }
}
