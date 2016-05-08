package com.example.pankajkumar.letschat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pankajkumar on 6/5/16.
 */
public class ChatActivity extends AppCompatActivity {
    String contactName = "";
    EditText editText;
    ImageView sendButton;
    List<ChatInfo> chatInfoList = new ArrayList<>();
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.chat_layout);
        startService(new Intent(this, ChatHeadService.class));
/*
        contactName = getIntent().getStringExtra("contactName");
        setTitle(contactName);


        chatAdapter = new ChatAdapter(this, chatInfoList);
        recyclerView = (RecyclerView)findViewById(R.id.chat_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);

        editText = (EditText) findViewById(R.id.enter_text);
        sendButton = (ImageView)findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatInfoList.add(new ChatInfo(""+editText.getText()));
                chatAdapter.notifyItemInserted(chatInfoList.size()-1);
                recyclerView.scrollToPosition(chatInfoList.size()-1);
                editText.setText("");
            }
        });*/
    }
}
