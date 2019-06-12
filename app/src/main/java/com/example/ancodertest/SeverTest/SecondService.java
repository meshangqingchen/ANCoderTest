package com.example.ancodertest.SeverTest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class SecondService extends Service {
    public SecondService() {

    }

    private int count;
    private boolean quit;
    private MyBinder binder;

    public class MyBinder extends Binder{
        public int getCount(){
            return count;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i("SecondService","onBind");
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
        Log.i("SecondService","onCreate");
        new Thread(){
            @Override
            public void run() {
                while (!quit){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                    count++;
                }
            }
        }.start();
    }

//    这个不走
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("SecondService","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("SecondService","onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i("SecondService","onDestroy");
        super.onDestroy();
        this.quit = true;
    }
}

