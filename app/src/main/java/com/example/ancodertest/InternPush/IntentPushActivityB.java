package com.example.ancodertest.InternPush;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ancodertest.R;

public class IntentPushActivityB extends AppCompatActivity implements View.OnClickListener{

    Button bt1,bt2;
    public final static int bb = 0x22;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_push_b);
        bt1 = (Button)findViewById(R.id.intentB1);
        bt2 = (Button)findViewById(R.id.intentB2);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            String lcc = bundle.getString("name");
            Log.i("aa",lcc);
        }
    }

    @Override
    public void onClick(View v) {
        if (v == bt1){
            finish();
        }else if (v == bt2){
            //getIntent
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("key","回调");
            intent.putExtras(bundle);
            setResult(bb,intent);
            finish();
        }
    }

}
