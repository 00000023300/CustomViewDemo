package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class SimpleImageView2 extends View {
//    画笔
    private Paint paint;
//    图片drawable
    private Drawable drawable;
    private int mwidth;
    private int mheight;

    public SimpleImageView2(Context context) {
        this(context,null);
    }

    public SimpleImageView2(Context context, AttributeSet attrs) {
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
//        宽度的模式和大小
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
//        高度的模式和大小
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
//        设置View的宽高
        setMeasuredDimension(measuredWidth(widthMode,width),
        measureHeight(heightMode,height));

    }

    private int measureHeight(int mode, int height) {
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mheight = height;
                break;
        }
        return height;

    }

    private int measuredWidth(int mode, int width) {
        switch (mode){
            case MeasureSpec.UNSPECIFIED:
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.EXACTLY:
                mwidth = width;
                break;
        }
        return width;
    }

private Bitmap bitmap;
    @Override
    protected void onDraw(Canvas canvas) {
        if(bitmap == null){
            bitmap = Bitmap.createScaledBitmap(ImageUtils.drawableToBitmap(drawable),
                    getMeasuredWidth(),getMeasuredHeight(),
                    true);
        }
//        绘制图片
        canvas.drawBitmap(bitmap,getLeft(),getTop(),paint);
//        保存画布
        canvas.save();
//        旋转90度
//        canvas.rotate(90);
//        绘制文字
        paint.setColor(Color.YELLOW);
        paint.setTextSize(100);
        canvas.drawText("鬼刀",getLeft()-1,getTop() + 200,paint);

//        恢复原来的状态
        canvas.restore();
    }


    public SimpleImageView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

