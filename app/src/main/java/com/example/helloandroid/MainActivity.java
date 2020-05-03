package com.example.helloandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("Hello", "I am creating~~");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Hello", "I am starting~~");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Hello", "I am stopping~~");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Hello", "I am destroying~~");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("Hello", "I am saving~~");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Hello", "I am pausing~~");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Hello", "I am resuming~~");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("Hello", "I am restarting~~");
    }
}
