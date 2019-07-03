package com.example.ancodertest.superEdittext1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.example.ancodertest.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class superEdittextActivity1 extends AppCompatActivity {

    @BindView(R.id.superEdittext1)
    superEdittext1 edittext1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_edittext1);
        ButterKnife.bind(this);
    }

}
