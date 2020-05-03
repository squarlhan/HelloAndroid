package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ServiceActivity extends AppCompatActivity {

    public Button btn1;
    public Button btn2;
    public Button btn3;
    public Button btn4;
    public Button btn5;
    public Button btn6;
    public TextView tv;
    public MyService.MyBinder binder;

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("Service", "Service Connected!");
            binder = (MyService.MyBinder)service;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("Service", "Service DisConnected!");

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        tv = findViewById(R.id.tv);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        final Intent intent = new Intent();
        intent.setAction("com.example.helloandroid.MyService");
        intent.setPackage("com.example.helloandroid");

        //start service
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });

        //stop service
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(intent);
            }
        });

        //bind the service
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               bindService(intent, conn, BIND_AUTO_CREATE);
            }
        });

        //get the count
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               tv.setText(String.valueOf(binder.getCount()));

            }
        });

        //unbind the service
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(conn);

            }
        });

        //play music
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AudioManager audioManager = (AudioManager)getSystemService(Service.AUDIO_SERVICE);
                MediaPlayer mediaPlayer = MediaPlayer.create(ServiceActivity.this, R.raw.music);
                mediaPlayer.start();

            }
        });


    }
}
