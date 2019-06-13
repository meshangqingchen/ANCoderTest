package com.example.ancodertest.Broadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ancodertest.R;

import java.util.Calendar;


public class broadcastActivity extends AppCompatActivity {

    MyReceiver receiver;
    MyReceiver2 receiver2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        int mSelectedYear = Calendar.getInstance().get(Calendar.YEAR);

        //网络状态转换通知 动态通知
        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        receiver = new MyReceiver();
        registerReceiver(receiver,filter);

        IntentFilter filter1 = new IntentFilter("asdfghjkl");
        receiver2 = new MyReceiver2();
        registerReceiver(receiver2,filter1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        unregisterReceiver(receiver2);
    }

    public void sendCustomBroadcast (View view){
        Intent intent = new Intent();
        intent.setAction("asdfghjkl");
        sendBroadcast(intent);
    }
}
