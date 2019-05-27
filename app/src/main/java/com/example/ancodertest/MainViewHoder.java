package com.example.ancodertest;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MainViewHoder extends RecyclerView.ViewHolder {

    private TextView classNameTextView;
    private TextView describeTextView;

    public MainClassTypeListModel.MainClassTypeModel model;

    public MainViewHoder(View itemView){
        super(itemView);
        classNameTextView = itemView.findViewById(R.id.main_class_name);
        describeTextView = itemView.findViewById(R.id.main_describe);
    }

    public void setModel(MainClassTypeListModel.MainClassTypeModel model) {
        this.model = model;
        classNameTextView.setText(this.model.getClassName());
        describeTextView.setText(this.model.getDescribe());
    }
}
