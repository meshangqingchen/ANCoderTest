package com.example.ancodertest.ScrowViewActivityTest;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.ancodertest.R;

public class ScrowViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrow_view);
        ScrollView scrollView = (ScrollView)findViewById(R.id.scrollView);
        scrollView.setVerticalScrollBarEnabled(false);

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                Log.i("scrollX",scrollX+"");
                Log.i("scrollY",scrollY+"");
                Log.i("oldScrollX",oldScrollX+"");
                Log.i("oldScrollY",oldScrollY+"");
            }
        });
    }
}
