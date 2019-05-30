package com.example.ancodertest.SeekBarTest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.example.ancodertest.R;

public class SeekBarTestActivity extends AppCompatActivity {


    ImageView image;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_seek_bar_test);
        image = (ImageView) findViewById(R.id.image);
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            // 当拖动条的滑块位置发生改变时触发该方法
            @Override
            public void onProgressChanged(SeekBar arg0, int progress,
                                          boolean fromUser)
            {
                // 动态改变图片的透明度
                image.setImageAlpha(progress);
            }
            @Override
            public void onStartTrackingTouch(SeekBar bar)
            {
            }
            @Override
            public void onStopTrackingTouch(SeekBar bar)
            {
            }
        });
    }

}
