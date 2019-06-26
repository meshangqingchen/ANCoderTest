package com.example.ancodertest.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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
//        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
//        receiver = new MyReceiver();
//        registerReceiver(receiver,filter);

        IntentFilter filter1 = new IntentFilter("asdfghjkl");
        receiver2 = new MyReceiver2();
        registerReceiver(receiver2,filter1);


        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver(this);
        registerReceiver(networkChangeReceiver,filter);
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


    private  class NetworkChangeReceiver extends BroadcastReceiver {

        private Context context;

        public NetworkChangeReceiver(Context context){
            super();
            this.context = context;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (connectivityManager == null){
                return;
            }
            NetworkInfo networkinfo = connectivityManager.getActiveNetworkInfo();
            if (networkinfo != null && networkinfo.isAvailable()) {
                Toast.makeText(context, "当前网络可用", Toast.LENGTH_SHORT).show();
                context.unregisterReceiver(this);
            }else {
                Toast.makeText(context, "当前网络🙅不可‍用", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
