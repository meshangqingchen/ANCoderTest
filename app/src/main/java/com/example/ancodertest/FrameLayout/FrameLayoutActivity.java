package com.example.ancodertest.FrameLayout;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.ancodertest.R;

import java.util.Timer;
import java.util.TimerTask;


public class FrameLayoutActivity extends AppCompatActivity {

    private int currentColor = 0;
    final int[] colors = new int[]{
            R.color.color1,
            R.color.color2,
            R.color.color3,
            R.color.color4,
            R.color.color5,
            R.color.color6
    };

    final int[] textIds = new int[]{
            R.id.frameLayout_text1,
            R.id.frameLayout_text2,
            R.id.frameLayout_text3,
            R.id.frameLayout_text4,
            R.id.frameLayout_text5,
            R.id.frameLayout_text6
    };

    TextView[] views = new TextView[textIds.length];

    Handler handeler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0x123){
                for (int i=0;i<textIds.length;i++){

                    views[i].setBackgroundResource(colors[(i + currentColor)%textIds.length]);
                }
                currentColor++;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout);
        for (int i=0;i<textIds.length;i++){
            views[i] = (TextView)findViewById(textIds[i]);
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handeler.sendEmptyMessage(0x123);
            }
        },0,200);
    }


}
