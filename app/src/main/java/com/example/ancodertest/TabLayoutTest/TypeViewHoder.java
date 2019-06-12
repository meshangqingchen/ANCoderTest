package com.example.ancodertest.TabLayoutTest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.ancodertest.R;

public class TypeViewHoder extends RecyclerView.ViewHolder {

    private String title;
    private TextView titleView;
    public TypeViewHoder(View view){
        super(view);
        titleView = view.findViewById(R.id.titleid);
    }

    public void setModel(String title){
        this.title = title;
        titleView.setText(title);
    }
}
