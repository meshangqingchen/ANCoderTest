package com.example.ancodertest.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ancodertest.R;

public class dialogActivity extends AppCompatActivity {

    CustomDialogPad customDialogPad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        customDialogPad = new CustomDialogPad(this);
    }

    public void show(View bt){
        customDialogPad.setTitleText("hahah").show();
    }

    public void dismiss(View bt){
        customDialogPad.dismiss();
    }
}
