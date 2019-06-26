package com.example.ancodertest.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.ancodertest.R;


/**
 * Created by generalsma on 2019/4/24 下午4:33.
 */
public class SelectYearDialog extends Dialog implements View.OnClickListener {

    TextView txtCancel;
    TextView txtOk;
    YearPicker yearPicker;
    private OnYearSelectListener onYearSelectListener;


    public void setOnYearSelectListener(OnYearSelectListener onYearSelectListener) {
        this.onYearSelectListener = onYearSelectListener;
    }

    public SelectYearDialog(@NonNull Context context) {
        super(context, R.style.select_year_dialog);

    }

    public SelectYearDialog(@NonNull Context context, int themeResId) {
        super(context, R.style.select_year_dialog);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_year);

        txtCancel = findViewById(R.id.txt_cancel);
        txtOk = findViewById(R.id.txt_ok);
        yearPicker = findViewById(R.id.year_picker);

        setCanceledOnTouchOutside(false);
        init();
    }

    private void init() {
        txtOk.setOnClickListener(this);
        txtCancel.setOnClickListener(this);
    }

    @Override
    public void show() {
        super.show();
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);
    }

    @Override
    public void onClick(View v) {
        if (v == txtOk) {
            dismiss();
            if (onYearSelectListener != null) {
                onYearSelectListener.onResult(yearPicker.getSelectedYear());
            }
        } else if (v == txtCancel) {
            dismiss();
        }
    }

    public interface OnYearSelectListener {
        void onResult(int year);
    }
}
