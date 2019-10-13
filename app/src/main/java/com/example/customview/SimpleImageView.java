package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class SimpleImageView extends View {
//    画笔
    private Paint paint;
//    图片drawable
    private Drawable drawable;
    private int mwidth;
    private int mheight;

    public SimpleImageView(Context context) {
        this(context,null);
    }

    public SimpleImageView(Context context,  AttributeSet attrs) {
        super(context, attrs);
//        根据属性初始化
        initAttrs(attrs);
//        初始化画笔
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = null;
            try {
//            获取定义属性
                array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
//            根据图片id获取drawable对象
                drawable = array.getDrawable(R.styleable.SimpleImageView_src);
//            测量drawable对象的宽高
                measureDrawable();
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }
    }

    private void measureDrawable() {
        if(drawable == null){
            throw new RuntimeException("drawable不能为空!");
        }
        mwidth = drawable.getIntrinsicWidth();
        mheight = drawable.getIntrinsicHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        设置View的宽和高为图片的宽和高
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(drawable == null){
            return;
        }
//        绘制图片
        canvas.drawBitmap(ImageUtils.drawableToBitmap(drawable),
       getLeft(),getTop(),paint);
    }


    public SimpleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

