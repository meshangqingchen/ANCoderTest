package com.example.ancodertest.ActivityThelifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ancodertest.R;

public class ActivityThelifecycleB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thelifecycle_b);
    }

    public void goback(View view){
        finish();
    }
}
