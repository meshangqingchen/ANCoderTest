package com.example.ancodertest.SeverTest;
import com.example.ancodertest.R;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SeverActivity extends AppCompatActivity {

    Intent intent;
    Intent intent1;
    SecondService.MyBinder binder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("SecondService","onServiceConnected");
            binder = (SecondService.MyBinder)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("SecondService","onServiceDisconnected");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sever);
        intent = new Intent(this,FirstService.class);
        intent1 = new Intent(this,SecondService.class);

    }

//    启动指定sever
    public void qidong(View bt){
        startService(intent);
    }


//    停止指定sever
    public void guanbi(View bt){
        stopService(intent);
    }

    //    启动指定sever
    public void qidong1(View bt){
        bindService(intent1,connection, Service.BIND_AUTO_CREATE);
    }


    //    停止指定sever
    public void guanbi1(View bt){
        unbindService(connection);
    }

    public void getCount(View bt){
        Log.i("SecondService","getCount"+binder.getCount());
    }
}
