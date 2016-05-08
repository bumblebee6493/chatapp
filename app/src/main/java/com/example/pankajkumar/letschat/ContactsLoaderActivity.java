package com.example.pankajkumar.letschat;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by pankajkumar on 5/5/16.
 */
public class ContactsLoaderActivity extends AppCompatActivity{
    private List<ContactInfo> contactList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ContactsLoaderAdapter contactsLoaderAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contacts);

        contactList.add(new ContactInfo("Pankaj Kumar","hi"));ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);

        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(
                        cur.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = cr.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = ?",
                            new String[]{id}, null);
                    String phoneNo = "";
                    while (pCur.moveToNext()) {
                        phoneNo = pCur.getString(pCur.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                    if(phoneNo.length() > 9)
                        contactList.add(new ContactInfo(name, phoneNo));
                    pCur.close();
                }
            }
        }
        Collections.sort(contactList, new Comparator<ContactInfo>(){
            @Override
            public int compare(ContactInfo a, ContactInfo b) {
                return a.name.compareTo(b.name);
            }
        });
        contactsLoaderAdapter = new ContactsLoaderAdapter(this, contactList);
        recyclerView = (RecyclerView)findViewById(R.id.contact_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactsLoaderAdapter);

    }
}
