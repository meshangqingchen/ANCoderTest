package com.example.ancodertest.InternPush;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ancodertest.R;

public class intentPushActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt1,bt2,bt3;
    public final static int aa = 0x11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_push);
        bt1 = (Button)findViewById(R.id.intent1);
        bt2 = (Button)findViewById(R.id.intent2);
        bt3 = (Button)findViewById(R.id.intent3);
        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(this,IntentPushActivityB.class);
        if (v == bt1){
            startActivity(intent);
        }else if (v == bt2){
            Bundle bundle = new Bundle();
            bundle.putCharSequence("name","lcc");
            intent.putExtras(bundle);
            startActivity(intent);
        }else if (v == bt3){
            startActivityForResult(intent,aa);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == aa && resultCode == IntentPushActivityB.bb){

            Bundle bundle = data.getExtras();
            if (bundle != null){
                String lcc = bundle.getString("key");
                Log.i("aa",lcc);
            }
        }
    }
}
