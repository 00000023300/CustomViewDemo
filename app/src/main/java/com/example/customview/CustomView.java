package com.example.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

public class CustomView  extends View {
    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // 获取到手指处的横坐标和纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // 计算移动的距离
                int offsetX = x - lastX;
                int offsetY = y - lastY;

                // 使用LayoutParams
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) getLayoutParams();
                params.leftMargin = getLeft() + offsetX;
                params.topMargin = getTop() + offsetY;
                setLayoutParams(params);

                // 使用scrollBy
//                ((View)getParent()).scrollBy(-offsetX,-offsetY);

                // 使用offset方法
//                offsetLeftAndRight(offsetX);
//                offsetTopAndBottom(offsetY);

                //调用layout方法来重新放置它的位置
//                layout(getLeft()+offsetX, getTop()+offsetY,
//                       getRight()+offsetX , getBottom()+offsetY);
                break;
            case MotionEvent.ACTION_UP:
                performClick();
                break;
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void smoothScrollTo(int destX, int destY) {
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScroller.startScroll(scrollX, 0, delta, 0, 2000);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScroller.computeScrollOffset()) {
            ((View)getParent()).scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            // 通过不断重绘，不断地调用computeScroll()
            invalidate();
        }
    }
}
