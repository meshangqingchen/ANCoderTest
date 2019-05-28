package com.example.ancodertest.GridLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;

import com.example.ancodertest.R;

public class GridLayoutActivity extends AppCompatActivity {

    GridLayout gridLayout;
    String[] chars = new String[]{
            "7" ,"8", "9", "/",
            "4" ,"5", "6", "*",
            "7" ,"8", "9", "-",
            "." ,"0", "=", "+"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);
        gridLayout = (GridLayout)findViewById(R.id.gridLayout_id);
        for (int i=0; i<chars.length; i++){
            Button bt = new Button(this);
            bt.setText(chars[i]);
            bt.setTextSize(40);
            bt.setPadding(5,35,5,35);
            //指定组件所在的行
            GridLayout.Spec rowSpec = GridLayout.spec(i/4+2);
            //指定组件所在的列
            GridLayout.Spec columnSpec = GridLayout.spec(i%4);
            GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec,columnSpec);
            params.setGravity(Gravity.FILL);
            gridLayout.addView(bt,params);
        }
    }
}
