package com.example.ancodertest.LinearLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.ancodertest.R;

public class LinearLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        final LinearLayout layout = findViewById(R.id.linearLayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                center_horizontal
                if (layout.getGravity() == (Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM)){
                    layout.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
                }else {
                    layout.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
                }
            }
        });
    }
}
