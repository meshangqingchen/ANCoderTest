package com.example.ancodertest.LinearLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.ancodertest.R;

public class LinearLayoutActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    Switch s;
    ToggleButton toggleButton;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_layout);
        layout = findViewById(R.id.linearLayout);

        s = findViewById(R.id.switchid);
        toggleButton = findViewById(R.id.togglebutton);
        toggleButton.setOnCheckedChangeListener(this);
        s.setOnCheckedChangeListener(this);


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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView == s){
            toggleButton.setChecked(isChecked);
        }else if (buttonView == toggleButton){
            s.setChecked(isChecked);
        }

        if (isChecked){
            layout.setGravity(Gravity.CENTER_VERTICAL|Gravity.RIGHT);
        }else {
            layout.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.BOTTOM);
        }
    }
}
