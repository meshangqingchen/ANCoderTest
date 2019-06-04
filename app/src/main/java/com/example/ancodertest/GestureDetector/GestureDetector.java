package com.example.ancodertest.GestureDetector;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.ancodertest.R;

public class GestureDetector extends AppCompatActivity implements android.view.GestureDetector.OnGestureListener {

    android.view.GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_detector);
        detector = new android.view.GestureDetector(this,this);

        View shoushi = findViewById(R.id.shoushi);
        shoushi.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i("tag","------onTouch"+event.getAction());
                return false;
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

//        return super.onTouchEvent(event);
        return detector.onTouchEvent(event);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        //按下
        Log.i("tag","onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        //按下没有移动也没有松开
        Log.i("tag","onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        //轻击屏幕事件
        Log.i("tag","onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.i("tag","onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

        Log.i("tag","onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.i("tag","onFling");
        return false;
    }
}
