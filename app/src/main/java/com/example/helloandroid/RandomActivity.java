package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RandomActivity extends AppCompatActivity {

    private TextView tv1;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private List<String> ids;
    private List<String> pros;
    private Timer timer;
    private Handler handler1;
    private boolean isfalse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);
        ids = new ArrayList<String>();
        for(int i = 1; i<=45; i++){
            if(i<10){
                ids.add("2140218010"+i);
                ids.add("2140218020"+i);
            }else{
                ids.add("214021801"+i);
                ids.add("214021802"+i);
            }
        }
        pros = new ArrayList<String>();
        pros.add("1，相对布局");
        pros.add("2，线性布局");
        pros.add("3，线性布局2");
        pros.add("4，表格布局");
        pros.add("5，网格布局2");
        pros.add("6，计算器实现");
        pros.add("7，资源访问");
        pros.add("8，自定义视图");
        pros.add("9，MVC编程");

        tv1 = findViewById(R.id.tv1);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        timer = new Timer();
        handler1=new Handler();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isfalse){
                    timer = new Timer();
                }
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler1.post(new Runnable() {

                            @Override
                            public void run() {
                                Random r = new Random();
                                int ran1 = r.nextInt(ids.size());
                                tv1.setText(ids.get(ran1));
                            }
                        });

                    }
                },5, 50);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                isfalse=true;
                String text = tv1.getText().toString();
                ids.remove(text);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isfalse){
                    timer = new Timer();
                }
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler1.post(new Runnable() {

                            @Override
                            public void run() {
                                Random r = new Random();
                                int ran1 = r.nextInt(pros.size());
                                tv1.setText(pros.get(ran1));
                            }
                        });

                    }
                },5, 50);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                isfalse=true;
            }
        });
    }
}