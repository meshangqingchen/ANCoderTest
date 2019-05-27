package com.example.ancodertest.Test;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import com.example.ancodertest.R;
import android.os.Bundle;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ConstraintLayout root = (ConstraintLayout)findViewById(R.id.root);
        final DrawView drawView = new DrawView(this);
        drawView.setMinimumHeight(300);
        drawView.setMinimumHeight(300);
        drawView.setBackgroundColor(Color.YELLOW);
        root.addView(drawView);
    }
}
