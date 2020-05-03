package com.example.helloandroid.controller;

import com.example.helloandroid.ContentActivity;
import com.example.helloandroid.model.Net;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;


public class Controller {

    private static Context context;

    public Controller(Context context) {
        this.context = context;
    }

    public static Handler handler = new Handler(){

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String json_str = msg.obj.toString();

            try {
                JSONObject json = new JSONObject(json_str);
                String cmd = json.getString("cmd");
                String code = json.getString("code");
                if(cmd!=null&&code!=null&&code.equals("0")){
                    if(cmd.equals("1")){

                        String uid = json.getString("uid");
                        SharedPreferences sp = context.getSharedPreferences("config",0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("token",uid);
                        editor.commit();

                        Intent intent = new Intent(context, ContentActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                    }else{

                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    public void sent(String cmd){
        Net.sent(cmd);

    }
}
