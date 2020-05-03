package com.example.helloandroid;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    private int count;
    private boolean quit;
    private MyBinder binder = new MyBinder();

    public MyService() {
    }

    public class MyBinder extends Binder{
        public int getCount(){
            return count;
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.e("Service", "Service Binding~");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Service", "Service Creating~");
        new Thread(){
            @Override
            public void run() {
                super.run();
                while(!quit){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count++;
                }
            }
        }.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("Service", "Service Starting~");
        Intent bro_in = new Intent();
        bro_in.setAction("com.example.helloandroid.ServiceSender");
        bro_in.putExtra("msg",String.valueOf(count));
        sendBroadcast(bro_in);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.e("Service", "Service Destroying~");
        quit = true;
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e("Service", "Service unBinding~");
        return super.onUnbind(intent);
    }
}
