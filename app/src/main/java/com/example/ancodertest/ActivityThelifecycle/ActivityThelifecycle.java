package com.example.ancodertest.ActivityThelifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.example.ancodertest.R;

public class ActivityThelifecycle extends AppCompatActivity {
    final String TAG = "--CrazyIt--";
    private long exitTime = 0;
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.i("aa",keyCode+"+++");
//        KeyEvent.KEYCODE_VOLUME_UP; //音乐键上
//        KeyEvent.KEYCODE_VOLUME_DOWN;//音乐键下
        if (keyCode == KeyEvent.KEYCODE_BACK){
            //返回
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    public void exit(){
        if ((System.currentTimeMillis() - exitTime)>2000){
            Toast.makeText(getApplicationContext(),"在按一次返回键退出程序",Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        }else {
            finish();
            System.exit(0);
        }
    }
}
