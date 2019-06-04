package com.example.ancodertest.TheCustomViewTest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.example.ancodertest.R;

public class StarView extends View {


    private Paint mPaint;
    private int startSpace = 0;
    private int startSize;
    private int startCount;

    private Drawable startDarkDrawable;
    private Bitmap startLightBitmap;
    private float startMark = 2.0F;

    public StarView(Context context){
        super(context);
    }

    public StarView(Context context, @Nullable AttributeSet attributeSet){
        super(context,attributeSet);
        init(context,attributeSet);
    }

    private void init(Context context,AttributeSet attrs){
        setClickable(false);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.StarView);
        startSpace = (int) typedArray.getDimension(R.styleable.StarView_startSpace, 0);
        startSize = (int) typedArray.getDimension(R.styleable.StarView_startSize, 2);
        startCount = typedArray.getInteger(R.styleable.StarView_startCount, 5);
        startDarkDrawable = typedArray.getDrawable(R.styleable.StarView_startEmpty);
        Drawable lightDrawable = typedArray.getDrawable(R.styleable.StarView_startFill);
        startLightBitmap = drawableToBitmap(lightDrawable);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setShader(new BitmapShader(startLightBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(startSize * startCount + startSpace * (startCount - 1), startSize);
    }

    private Bitmap drawableToBitmap(Drawable drawable){
        if (drawable == null){
         return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(startSize,startSize,Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0,0,startSize,startSize);
        drawable.draw(canvas);
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (startDarkDrawable == null || startLightBitmap == null){
            return;
        }
        for (int i=0;i<startCount;i++){
            startDarkDrawable.setBounds((startSpace + startSize) * i, 0, (startSpace + startSize) * i + startSize, startSize);
            startDarkDrawable.draw(canvas);
        }
        if (startMark>=1){
            for (int i=0;i<startMark;i++){
                canvas.drawRect(0,0,startSize,startSize,mPaint);
                canvas.translate(startSpace + startSize, 0);
            }
        }
    }

    public void setStartMark(int mark) {

        startMark = Math.round(mark * 10) * 1.0f / 10;
        startMark = (float) Math.rint((startMark * startCount) / 10);
        invalidate();
    }
}


