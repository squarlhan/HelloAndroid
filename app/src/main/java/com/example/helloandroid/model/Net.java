package com.example.helloandroid.model;

import android.os.Message;
import android.util.Log;

import com.example.helloandroid.controller.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Net {
    public static void sent(final String cmd){
        new Thread(new Runnable() {
            @Override
            public void run() {
                //define the post url
                String url_str = "http://192.168.123.208:8080/leisurelife/dealcmd?";
                url_str = url_str + cmd;
                //open the url
                try {
                    URL url = new URL(url_str);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    //response based on the return result
                    if(conn.getResponseCode() == 200){
                        Log.e("Reg", "Success!");
                        //read the message
                        InputStream input = conn.getInputStream();
                        byte[] buff = new byte[1024];
                        int len = -1;
                        StringBuilder sb = new StringBuilder();
                        while((len=input.read(buff))!=-1){
                            sb.append(new String(buff,0,len,"gbk"));
                        }
                        String res_str = sb.toString();
                        Log.e("Reg", res_str);
                        //callback method
                        Message m = new Message();
                        m.obj = res_str;
                        Controller.handler.handleMessage(m);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
