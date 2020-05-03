package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.helloandroid.controller.Controller;

public class RegActivity extends AppCompatActivity {

    public EditText name;
    public EditText pass;
    public Button reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        //read sp
        SharedPreferences sp = getApplicationContext().getSharedPreferences("config",0);
        String token = null;
        token = sp.getString("token", null);
//        if(true){
        if(token!=null){
            new Thread(){
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getApplicationContext(), ContentActivity.class);
                    startActivity(intent);
                }
            }.start();


        }else{
            reg();
        }
    }

    private void reg(){
        name = findViewById(R.id.name);
        pass = findViewById(R.id.password);
        reg = findViewById(R.id.reg);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name_str = name.getText().toString().trim();
                String pass_str = pass.getText().toString();
                //create a http post with the information
                String cmd_str = "cmd=1";
                cmd_str = cmd_str + "&name=" + name_str;
                cmd_str = cmd_str + "&password=" + pass_str;
                Log.e("Reg",cmd_str);
                //sent the post
                Controller c = new Controller(getApplicationContext());
                c.sent(cmd_str);

            }
        });
    }
}
