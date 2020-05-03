package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {

    public ListView lv;
    public Button btn;

    ArrayList<String> names = new ArrayList<String>();
    ArrayList<String> phones = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);

        lv = findViewById(R.id.lv);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get all the contacts' names and phone numbers

                Cursor cursor = getContentResolver().query(
                        ContactsContract.Contacts.CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );

                while (cursor.moveToNext()) {
                    String con_name = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    String con_id = cursor.getString(
                            cursor.getColumnIndex(ContactsContract.Contacts._ID));
                    Cursor ph_cursor = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"="+con_id,
                            null,
                            null
                    );
                    ArrayList<String> con_phones = new ArrayList<String>();
                    while(ph_cursor.moveToNext()){
                        String con_phone = ph_cursor.getString(
                                ph_cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        con_phones.add(con_phone);
                    }
                    names.add(con_name);
                    phones.add(con_phones.get(0));


                }

                lv.setAdapter(new MyAdapter(ContentActivity.this));

            }


        });
    }

    private class MyAdapter extends BaseAdapter {
        Context context;

        public MyAdapter(Context context) {
            super();
            this.context = context;
        }

        public MyAdapter() {
            super();
        }

        @Override
        public int getCount() {
            return names.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            if (position % 2 == 0) {
                v = LayoutInflater.from(context).inflate(R.layout.listitem1, null);
            } else {
                v = LayoutInflater.from(context).inflate(R.layout.listitem2, null);
            }
            TextView tvv1 = v.findViewById(R.id.tvv1);
            TextView tvv2 = v.findViewById(R.id.tvv2);
            tvv1.setText(names.get(position));
            tvv2.setText(phones.get(position));
            return v;
        }
    }
}
