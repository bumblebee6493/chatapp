package com.example.pankajkumar.letschat;

/**
 * Created by pankajkumar on 6/5/16.
 */
public class ChatInfo {
    public String text;
    public boolean from;//true means user false means server

    public ChatInfo(String text){
        this.text = text;
    }
}
