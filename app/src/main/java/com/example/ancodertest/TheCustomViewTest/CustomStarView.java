package com.example.ancodertest.TheCustomViewTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ancodertest.R;

public class CustomStarView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_star_view);
        TextView textView = (TextView)findViewById(R.id.tv_average_count);
        Log.i("textView",textView.toString());
    }

}
