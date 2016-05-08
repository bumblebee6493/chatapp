package com.example.pankajkumar.letschat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pankajkumar on 5/5/16.
 */
public class ContactsLoaderAdapter extends RecyclerView.Adapter<ContactsLoaderAdapter.ContactVH> {
    List<ContactInfo> contactList = new ArrayList<>();
    Context context;

    public ContactsLoaderAdapter(Context context,List<ContactInfo> contactList){
        this.contactList = contactList;
        this.context = context;
    }

    public ContactsLoaderAdapter(){
    }

    public ContactsLoaderAdapter(Context context){
        this.context = context;
    }

    @Override
    public void onBindViewHolder(ContactVH holder, int position) {
        final ContactInfo contactInfo = contactList.get(position);
        holder.name.setText(contactInfo.name);
        holder.text.setText(contactInfo.text);
//        holder.image.setImageResource(contactInfo.image);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ChatActivity.class);
                i.putExtra("contactName", contactInfo.name);
                context.startActivity(i);
            }
        });
    }

    @Override
    public ContactVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_contact_row, parent,false);
        return new ContactVH(item);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactVH extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView text;
        public ImageView image;
        public LinearLayout linearLayout;

        public ContactVH(View v){
            super(v);
            name = (TextView)v.findViewById(R.id.contact_name);
            text = (TextView)v.findViewById(R.id.contact_text);
            linearLayout = (LinearLayout) v.findViewById(R.id.contact_row_layout);
            //image = (ImageView) v.findViewById(R.id.contact_image);
        }
    }


}
