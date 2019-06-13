package com.example.ancodertest.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.example.ancodertest.R;


public class CustomDialogPad extends Dialog {

    TextView mTitleTv;
    TextView mContentTv;
    TextView mCancelTv;
    TextView mConfirmTv;

    private View.OnClickListener mOnConfirmListener, mOnCancelListener;

    private String mTitleText, mContentText, mConfirmText, mCancelText;

    public CustomDialogPad(Context context){
        super(context, R.style.MyDialogTheme);
        setContentView(R.layout.dialog_custom_pad_layout);

        mTitleTv = findViewById(R.id.custom_pad_dialog_title);
        mContentTv = findViewById(R.id.custom_pad_dialog_content);
        mCancelTv = findViewById(R.id.custom_pad_dialog_cancel);
        mConfirmTv = findViewById(R.id.custom_pad_dialog_ok);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (TextUtils.isEmpty(mTitleText)) {
            mTitleTv.setVisibility(View.GONE);
        } else {
            mTitleTv.setText(mTitleText);
            mTitleTv.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(mContentText)) {
            mContentTv.setVisibility(View.GONE);
        } else {
            mContentTv.setText(mContentText);
            mContentTv.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(mCancelText)) mCancelTv.setText(mCancelText);
        if (!TextUtils.isEmpty(mConfirmText)) mConfirmTv.setText(mConfirmText);

        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnCancelListener != null) mOnCancelListener.onClick(v);
            }
        });

        mConfirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (mOnConfirmListener != null) mOnConfirmListener.onClick(v);
            }
        });

        setOnKeyListener(new OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount()==0) {
                    //没有cancel键的dialog，不接收back key进行dismiss
                    if (mCancelTv.getVisibility() == View.GONE)
                        return true;
                }
                return false;
            }
        });
    }

    public CustomDialogPad setCancelTouchOutside(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        return this;
    }

    public CustomDialogPad setTitleText(String title) {
        this.mTitleText = title;
        this.mTitleTv.setText(title);
        return this;
    }

    public CustomDialogPad setContentText(String content) {
        this.mContentText = content;
        this.mContentTv.setText(content);
        return this;
    }

    public CustomDialogPad hideCancelButton() {
        mCancelTv.setVisibility(View.GONE);
        return this;
    }

    public CustomDialogPad setCancelListener(String cancelText, View.OnClickListener onCancelListener) {
        this.mCancelText = cancelText;
        this.mCancelTv.setText(cancelText);
        this.mOnCancelListener = onCancelListener;
        return this;
    }

    public CustomDialogPad setCancelListener(View.OnClickListener onCancelListener) {
        return setCancelListener("取消", onCancelListener);
    }

    public CustomDialogPad setConfirmClickListener(String okText, View.OnClickListener onOkClickListener) {
        this.mConfirmText = okText;
        this.mConfirmTv.setText(okText);
        this.mOnConfirmListener = onOkClickListener;
        return this;
    }

    public CustomDialogPad setConfirmClickListener(View.OnClickListener onOkClickListener) {
        return setConfirmClickListener("确定", onOkClickListener);
    }

}
