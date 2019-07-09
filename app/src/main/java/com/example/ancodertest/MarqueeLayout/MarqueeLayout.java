package com.example.ancodertest.MarqueeLayout;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Scroller;

import java.lang.ref.WeakReference;

public class MarqueeLayout extends ViewGroup {

    public static final int ORIENTATION_UP = 1;
    public static final int ORIENTATION_DOWN = 2;
    public static final int ORIENTATION_LEFT = 3;
    public static final int ORIENTATION_RIGHT = 4;

    private int mOrientation; //枚举 方向
    private int mItemCount;   //总个数
    private int mCurrentPosition; //当前位置
    private int mScrollDistance;  //每次滑动的距离
    private int mSwitchTime;  //停留的时间
    private int mScrollTime;  //动画的时间

    private Scroller mScroller;

    private boolean mIsStart;

    /**
     * 是否开启滚动时子View的缩放和透明度动画
     */
    private boolean mEnableAlphaAnim;
    private boolean mEnableScaleAnim;

    private MarqueeLayoutAdapter mAdapter;

    private boolean mVisible;
    private MarqueeLayoutHandler mHandler;


    public MarqueeLayout(Context context){
        super(context);
        init(context,null);
    }

    public MarqueeLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        init(context,attrs);
    }

    public MarqueeLayout(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
        init(context,attrs);
    }

    public MarqueeLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes){
        super(context,attrs,defStyleAttr,defStyleRes);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        mSwitchTime = 2500;
        mScrollTime = 1000;
        mOrientation = ORIENTATION_UP;
        mEnableAlphaAnim = false;
        mEnableScaleAnim = false;

        mScroller = new Scroller(context, new AccelerateDecelerateInterpolator());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 声明临时变量存储父容器的期望值
        // 3
        int parentDesireHeight = 0;
        int parentDesireWidth = 0;

        int maxWidth = 0;
        int maxHeight = 0;
        if (getChildCount() > 0){
            for (int i=0; i<getChildCount(); i++){
                final View child = getChildAt(i);

                final MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();

                measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,0);

                parentDesireWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                maxWidth = Math.max(maxWidth,parentDesireHeight);

                parentDesireHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                maxHeight = Math.max(maxHeight,parentDesireHeight);
            }
            parentDesireWidth = maxWidth;
            parentDesireHeight = maxHeight;
            parentDesireWidth = parentDesireWidth + getPaddingLeft() + getPaddingRight();
            parentDesireHeight = parentDesireWidth + getPaddingTop() + getPaddingBottom();
        }
        setMeasuredDimension(resolveSize(parentDesireWidth,widthMeasureSpec),resolveSize(parentDesireHeight,heightMeasureSpec));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //4
        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        if (mOrientation == ORIENTATION_UP || mOrientation == ORIENTATION_DOWN){
            final int height = getMeasuredHeight();
            mScrollDistance = height;
            int multiHeight = 0;
            // 垂直方向跑马灯
            for (int i=0;i<getChildCount();i++){
                final View child = getChildAt(i);
                MarginLayoutParams lp = (MarginLayoutParams)child.getLayoutParams();
                if (i == 0 && multiHeight == 0 && mOrientation == ORIENTATION_DOWN) {
                    multiHeight = -height;
                    mCurrentPosition = 1;
                }
                // 垂直方向的话，因为布局高度定死为子控件最大的高度，所以子控件一律位置垂直居中，paddingTop和marginTop均失效
                child.layout(paddingLeft + lp.leftMargin, (height - child.getMeasuredHeight()) / 2 + multiHeight, child.getMeasuredWidth() + paddingLeft + lp.leftMargin,
                        (height - child.getMeasuredHeight()) / 2 + child.getMeasuredHeight() + multiHeight);
                multiHeight += height;
            }
        }else {

        }
    }

    private void addChildView(MarqueeLayoutAdapter adapter) {
        mAdapter = adapter;

        removeAllViews();
        for (int i = 0; i < adapter.getCount(); i++) {
            final View child = adapter.getView(i, null, this);
            addView(child);
        }

        if (adapter.getCount() > 1) {
            switch (getOrientation()) {
                case MarqueeLayout.ORIENTATION_UP:
                case MarqueeLayout.ORIENTATION_LEFT:
                    // 首添加到尾
                    addView(adapter.getView(0, null, this), getChildCount());
                    break;
                case MarqueeLayout.ORIENTATION_DOWN:
                case MarqueeLayout.ORIENTATION_RIGHT:
                    // 尾添加到首
                    addView(adapter.getView(getChildCount() - 1, null, this), 0);
                    break;
            }
            mItemCount = adapter.getCount() + 1;
        } else {
            mItemCount = adapter.getCount();
        }

        if (mItemCount <= 1) {
            mScroller.forceFinished(true);
            scrollTo(0, 0);
        }

    }

    public int getOrientation() {
        return mOrientation;
    }

    // 生成默认的布局参数
    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    // 生成布局参数,将布局参数包装成我们的
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    // 生成布局参数,从属性配置中生成我们的布局参数
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //1
        return new MarginLayoutParams(getContext(), attrs);
    }

    // 查当前布局参数是否是我们定义的类型这在code声明布局参数时常常用到
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        //2
        return p instanceof MarginLayoutParams;
    }

    public void setAdapter(MarqueeLayoutAdapter adapter) {
        addChildView(adapter);
    }

    /**
     * 开始轮播
     */
    public void start(){
        if (getChildCount()<=1 || mHandler!=null){
            return;
        }
        mIsStart = true;
        mHandler = new MarqueeLayoutHandler(this);
        mHandler.sendEmptyMessageDelayed(100,mSwitchTime);
    }
    /**getCurrY
     * 从心开始
     */
    public void carryOn(){
        if (mIsStart && mHandler != null){
            mHandler.removeMessages(100);
            mHandler.sendEmptyMessageDelayed(100,mSwitchTime);
        }
    }
    /**
     * 暂停
     */
    public void pause(){
        if (mIsStart && mHandler != null){
            mHandler.removeMessages(100);
        }
    }

    private void smoothScroll(int distance) {
        if (mOrientation == ORIENTATION_DOWN || mOrientation == ORIENTATION_UP) {
            mScroller.startScroll(0, mScroller.getFinalY(), 0, distance, mScrollTime);
            // Log.e("MarqueeLayout", "246行-smoothScroll(): " + mScroller.getFinalY() + ";" + distance);
        } else {
            mScroller.startScroll(mScroller.getFinalX(), 0, distance, 0, mScrollTime);
        }
    }

    private void fastScroll(int distance){
        if (mOrientation == ORIENTATION_DOWN || mOrientation == ORIENTATION_UP){
            mScroller.startScroll(0, mScroller.getFinalY(), 0, distance, 0);
        }else {
            mScroller.startScroll(mScroller.getFinalX(), 0, distance, 0, 0);
        }
    }

    @Override
    public void computeScroll() {
        if (mItemCount == 0){
            return;
        }
        if (mScroller.computeScrollOffset()){
            //返回true时表示还移动还没有完成.
            if (mOrientation == ORIENTATION_DOWN || mOrientation == ORIENTATION_UP) {
                final int y = mScroller.getCurrY();
                Log.i("getCurrY",y+"");
                scrollTo(0, y);
            } else {
                final int x = mScroller.getCurrX();
                Log.i("getCurrX",x+"");
                scrollTo(x, 0);
            }
            invalidate();

        }else if (!mScroller.computeScrollOffset()){
            switch (mOrientation){
                case ORIENTATION_UP:
                    if (mCurrentPosition >= mItemCount-1){
                        fastScroll(-mCurrentPosition * mScrollDistance);
                        mCurrentPosition = 0;
                    }
                    break;
                case ORIENTATION_DOWN:
                    if (mCurrentPosition <= 0){
                        fastScroll((mItemCount - 1) * mScrollDistance);
                        mCurrentPosition = mItemCount - 1;
                    }
                    break;
                case ORIENTATION_LEFT:
                    if (mCurrentPosition >= mItemCount - 1) {
                        fastScroll(-mCurrentPosition * mScrollDistance);
                        mCurrentPosition = 0;
                    }
                    break;
                case ORIENTATION_RIGHT:
                    if (mCurrentPosition <= 0) {
                        fastScroll((mItemCount - 1) * mScrollDistance);
                        mCurrentPosition = mItemCount - 1;
                    }
                    break;
            }
            invalidate();
        }
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        //1
        super.onWindowVisibilityChanged(visibility);
        mVisible = visibility == VISIBLE;
        if (mVisible){
            carryOn();
        }else {
            pause();
        }
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        mVisible = visibility == VISIBLE;
        // Log.e("MarqueeLayout", "191行-onVisibilityChanged(): " + mVisible);
        if (visibility == VISIBLE) {
            carryOn();
        } else {
            pause();
        }
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mVisible = screenState == View.SCREEN_STATE_ON;
        } else {
            mVisible = screenState == 1;
        }
        if (mVisible) {
            pause();
        } else {
            carryOn();
        }
    }

    private static class MarqueeLayoutHandler extends Handler{
        private WeakReference<MarqueeLayout> mReference;
        MarqueeLayoutHandler (MarqueeLayout marqueeLayout){
            mReference = new WeakReference<>(marqueeLayout);
        }

        @Override
        public void handleMessage(Message msg) {
            MarqueeLayout marqueeLayout = mReference.get();
            if (marqueeLayout != null && marqueeLayout.mVisible){
                if (msg.what == 100){
                    switch (marqueeLayout.mOrientation) {
                        case ORIENTATION_UP:
                            marqueeLayout.mCurrentPosition++;
                            marqueeLayout.smoothScroll(marqueeLayout.mScrollDistance);
                            break;
                        case ORIENTATION_DOWN:
                            marqueeLayout.mCurrentPosition--;
                            marqueeLayout.smoothScroll(-marqueeLayout.mScrollDistance);
                            break;
                        case ORIENTATION_LEFT:
                            marqueeLayout.mCurrentPosition++;
                            marqueeLayout.smoothScroll(marqueeLayout.mScrollDistance);
                            break;
                        case ORIENTATION_RIGHT:
                            marqueeLayout.mCurrentPosition--;
                            marqueeLayout.smoothScroll(-marqueeLayout.mScrollDistance);
                            break;
                    }
                    marqueeLayout.postInvalidate();
                    sendEmptyMessageDelayed(100,marqueeLayout.mSwitchTime);
                }
            }
        }
    }
}
