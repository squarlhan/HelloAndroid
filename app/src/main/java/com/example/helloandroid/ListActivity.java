package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ListActivity extends AppCompatActivity {

    public String[] sp_array = {"Kobe", "Jame", "Kate"};
    public ListView lv2;
    public ListView lv3;
    public RadioGroup rg;
    public Button btn;
    public String str;
    public TextView tv;
    public Button btn1;
    public Button btn2;
    public EditText et;

    public final String file_name = "ttt.txt";

    public SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv2 = findViewById(R.id.lv2);
        ArrayAdapter<String> arrad = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1, sp_array);
        lv2.setAdapter(arrad);
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, sp_array[position], Toast.LENGTH_SHORT).show();
            }
        });

        lv3 = findViewById(R.id.lv3);
        lv3.setAdapter(new MyAdapter(ListActivity.this));

        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1:
                        str = "Female";
                        break;
                    case R.id.rb2:
                        str = "Male";
                        break;
                    default:
                        str = "nothing";
                        break;
                }

            }
        });


        //sharedpreferce example
        sp = getSharedPreferences("config", MODE_PRIVATE);
        int c_count = sp.getInt("count", 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("count", ++c_count);
        editor.commit();

        tv = findViewById(R.id.tv);
        btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText(str);
                Toast.makeText(ListActivity.this,
                        String.valueOf(sp.getInt("count", 0)),
                        Toast.LENGTH_LONG).show();

            }
        });

        et = findViewById(R.id.et);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //write text from EditText to File
                try {
                    FileOutputStream fos = openFileOutput(file_name, MODE_PRIVATE);
                    PrintStream ps = new PrintStream(fos);
                    ps.println(et.getText());
                    ps.close();
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Read text from File to TextView
                try {
                    FileInputStream fis = openFileInput(file_name);
                    byte[] buff = new byte[1024];
                    int hasRead = 0;
                    StringBuilder sb = new StringBuilder("");
                    while((hasRead=fis.read(buff))>0){
                        sb.append(new String(buff,0,hasRead));
                    }
                    fis.close();
                    tv.setText(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
            return sp_array.length;
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
            tvv1.setText(sp_array[position]);
            tvv2.setText("18");
            return v;
        }
    }

}
