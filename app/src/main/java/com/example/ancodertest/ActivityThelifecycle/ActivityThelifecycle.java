package com.example.ancodertest.ActivityThelifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.ancodertest.R;

public class ActivityThelifecycle extends AppCompatActivity {
    final String TAG = "--CrazyIt--";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thelifecycle);
        Log.d(TAG, "-------onCreate创建------");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        // 输出日志
        Log.d(TAG, "-------onStart开始------");
    }


    @Override
    public void onResume()
    {
        super.onResume();
        // 输出日志
        Log.d(TAG, "-------onResume正在运行------");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        // 输出日志
        Log.d(TAG, "-------onPause暂停------");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        // 输出日志
        Log.d(TAG, "-------onStop------");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        // 输出日志
        Log.d(TAG, "-------onDestroy------");
    }

    public void next(View view){
        Intent intent = new Intent(this,ActivityThelifecycleB.class);
        startActivity(intent);
    }

    public void goback(View view){
        finish();
    }
}
