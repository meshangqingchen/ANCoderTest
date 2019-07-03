package com.example.ancodertest.superEdittext1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.ancodertest.R;

import java.lang.reflect.Field;

public class superEdittext1 extends AppCompatEditText {

    /*
     * 定义属性变量
     * */
    private Paint mPaint; // 画笔


    private int  ic_left_clickResID,ic_left_unclickResID;    // 左侧图标 资源ID（点击 & 无点击）
    private Drawable  ic_left_click,ic_left_unclick; // 左侧图标（点击 & 未点击）
    private int left_x,left_y,left_width,left_height; // 左侧图标起点（x,y）、左侧图标宽、高（px）

    private int  ic_deleteResID; // 删除图标 资源ID
    private Drawable  ic_delete; // 删除图标
    private int delete_x,delete_y,delete_width,delete_height; // 删除图标起点(x,y)、删除图标宽、高（px）

    private int cursor; // 光标

    // 分割线变量
    private int lineColor_click,lineColor_unclick;// 点击时 & 未点击颜色
    private int color;
    private int linePosition;

    float strokeWidth;



    public superEdittext1(Context context) {
        super(context);

    }

    public superEdittext1(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public superEdittext1(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init (Context context, AttributeSet attrs){

        // 获取控件资源
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.superEdittext1);


        // a. 点击状态的左侧图标
        // 1. 获取资源ID
        ic_left_clickResID = typedArray.getResourceId(R.styleable.superEdittext1_ic_left_clickResID, R.drawable.ic_left_click);
        // 2. 根据资源ID获取图标资源（转化成Drawable对象）
        ic_left_click =  getResources().getDrawable(ic_left_clickResID);
        // 3. 设置图标大小
        // 起点(x，y)、宽= left_width、高 = left_height
        left_x = typedArray.getInteger(R.styleable.superEdittext1_left_x, 0);
        left_y = typedArray.getInteger(R.styleable.superEdittext1_left_y, 0);
        left_width = typedArray.getInteger(R.styleable.superEdittext1_left_width, 60);
        left_height = typedArray.getInteger(R.styleable.superEdittext1_left_height, 60);

        ic_left_click.setBounds(left_x, left_y,left_width, left_height);
        // Drawable.setBounds(x,y,width,height) = 设置Drawable的初始位置、宽和高等信息
        // x = 组件在容器X轴上的起点、y = 组件在容器Y轴上的起点、width=组件的长度、height = 组件的高度

        // b. 未点击状态的左侧图标
        // 1. 获取资源ID
        ic_left_unclickResID = typedArray.getResourceId(R.styleable.superEdittext1_ic_left_unclickResID, R.drawable.ic_left_unclick);
        // 2. 根据资源ID获取图标资源（转化成Drawable对象）
        // 3. 设置图标大小（此处默认左侧图标点解 & 未点击状态的大小相同）
        ic_left_unclick =  getResources().getDrawable(ic_left_unclickResID);
        ic_left_unclick.setBounds(left_x, left_y,left_width, left_height);

        /**
         * 初始化删除图标
         * */
        ic_deleteResID = typedArray.getResourceId(R.styleable.superEdittext1_ic_delete_ResID,R.drawable.delete);
        ic_delete = getResources().getDrawable(ic_deleteResID);
        delete_x = typedArray.getInteger(R.styleable.superEdittext1_delete_x,0);
        delete_y = typedArray.getInteger(R.styleable.superEdittext1_delete_y,0);
        delete_width = typedArray.getInteger(R.styleable.superEdittext1_delete_width,60);
        delete_height = typedArray.getInteger(R.styleable.superEdittext1_delete_height,60);
        ic_delete.setBounds(delete_x,delete_y,delete_width,delete_height);
        setCompoundDrawables(ic_left_unclick,null,null,null);

        cursor = typedArray.getResourceId(R.styleable.superEdittext1_cursor,R.drawable.cursor);

        /**
         * 初始化光标 颜色粗细
         * */
        cursor = typedArray.getResourceId(R.styleable.superEdittext1_cursor,R.drawable.cursor);

        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(this,cursor);
        }catch (Exception e){
            e.printStackTrace();
        }

        /**
         * 初始化分割线 (颜色 粗细 位置)
         * */
        mPaint = new Paint();

        strokeWidth = typedArray.getFloat(R.styleable.superEdittext1_s_stroke_Width, (float)2.0); //stroke_Width
        mPaint.setStrokeWidth(strokeWidth);
        int lineColorClick_default = context.getResources().getColor(R.color.lineColor_click); // 默认 = 蓝色#1296db
        int lineColorunClick_default = context.getResources().getColor(R.color.lineColor_unclick); // 默认 = 灰色#9b9b9b
        lineColor_click = typedArray.getColor(R.styleable.superEdittext1_lineColor_click,lineColorClick_default);
        lineColor_unclick = typedArray.getColor(R.styleable.superEdittext1_lineColor_unclick,lineColorunClick_default);

        mPaint.setColor(lineColor_unclick);
        setTextColor(lineColor_unclick);
        color = lineColor_unclick;
        //分割线位置
        linePosition = typedArray.getInteger(R.styleable.superEdittext1_linePosition,1);
        //消除自带下划线
        setBackground(null);

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        setLeftSelectDeleteIconVisible(hasFocus(),hasFocus()&&text.length()>0);

    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setLeftSelectDeleteIconVisible(focused,focused && length() > 0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                Drawable drawable = ic_delete;
                if (drawable != null && event.getX() <= (getWidth()-getPaddingRight()) && event.getX() >= (getWidth() - getPaddingRight() - drawable.getBounds().width()) ){
                    setText("");
                    return true;
                }
        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(color);
        setTextColor(color);
        // 绘制分割线
        // 需要考虑：当输入长度超过输入框时，所画的线需要跟随着延伸
        // 解决方案：线的长度 = 控件长度 + 延伸后的长度
        int x=this.getScrollX(); // 获取延伸后的长度
        int w=this.getMeasuredWidth(); // 获取控件长度

        // 传入参数时，线的长度 = 控件长度 + 延伸后的长度
        canvas.drawLine(0, this.getMeasuredHeight()- linePosition, w+x,
                this.getMeasuredHeight() - linePosition, mPaint);
    }

    private void setLeftSelectDeleteIconVisible(boolean leftSelect, boolean deleteVisible){
        setCompoundDrawables(leftSelect?ic_left_click:ic_left_unclick,null,deleteVisible?ic_delete:null,null);
        color = leftSelect?lineColor_click:lineColor_unclick;
        invalidate();
    }


}
