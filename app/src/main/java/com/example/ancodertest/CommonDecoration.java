package com.example.ancodertest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class CommonDecoration extends RecyclerView.ItemDecoration {

    private Context mContext;
    private Drawable mDrawable;
    private int mTotalItems;//总Item数

    public CommonDecoration(Context context, int drawableId) {
        this.mContext = context;
        this.mDrawable = ContextCompat.getDrawable(this.mContext, drawableId);
    }


    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //这里是对 item 进行偏移
//        Log.i("putRect",outRect.toString());
//        outRect.bottom = mDrawable.getIntrinsicHeight();
//        Log.i("putRect-",outRect.toString());

        if (0 == mTotalItems)
            mTotalItems = parent.getAdapter().getItemCount();
        //在源码中有一个过时的方法，里面有获取当前ItemPosition
        int currentItemPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        ((RecyclerView.LayoutParams)view.getLayoutParams()).getViewPosition();
        if (!isLastRow(currentItemPosition, mTotalItems)){
            outRect.bottom = mDrawable.getIntrinsicWidth();
        } else{
            outRect.bottom = 0;
        }

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for (int i = 0; i < parent.getChildCount(); i++) {
            drawHorizontalDecoration(c, parent.getChildAt(i));
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);

    }

    private void drawHorizontalDecoration(Canvas canvas, View childView) {
        Rect rect = new Rect(0, 0, 0, 0);
        rect.top = childView.getBottom();
        rect.bottom = rect.top + mDrawable.getIntrinsicHeight();
        rect.left = childView.getLeft();
        rect.right = childView.getRight();
        mDrawable.setBounds(rect);
        mDrawable.draw(canvas);
    }

    private boolean isLastRow(int currentItemPosition, int totalItems) {
        boolean result = false;
        if (currentItemPosition + 1 >= totalItems)
            result = true;
        return result;
    }

}
