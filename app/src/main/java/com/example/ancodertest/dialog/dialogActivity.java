package com.example.ancodertest.dialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.ancodertest.R;

public class dialogActivity extends AppCompatActivity {

    CustomDialogPad customDialogPad;
    SelectYearDialog selectYearDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        customDialogPad = new CustomDialogPad(this);

        selectYearDialog = new SelectYearDialog(this);

    }

    public void show(View bt){
        customDialogPad.setTitleText("hahah").show();
    }

    public void showSelectYearDialog (View bt){

        selectYearDialog.setOnYearSelectListener(new SelectYearDialog.OnYearSelectListener() {

            @Override
            public void onResult(int year) {
                Log.i("year","-"+year);
            }

        });

        selectYearDialog.show();
    }

    public void dismiss(View bt){
        customDialogPad.dismiss();
    }
}
