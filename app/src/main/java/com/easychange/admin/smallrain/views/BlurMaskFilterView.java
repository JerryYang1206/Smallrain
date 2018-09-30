package com.easychange.admin.smallrain.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.easychange.admin.smallrain.R;

public class BlurMaskFilterView extends View {
    private Paint mPaint;
    private RectF rectF;

    public BlurMaskFilterView(Context context) {
        this(context, null);
    }

    public BlurMaskFilterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BlurMaskFilterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BlurMaskFilterView);
        int marginLeft = (int) typedArray.getDimension(R.styleable.BlurMaskFilterView_blur_marginLeft, dp2px(context,0));
        int marginTop = (int) typedArray.getDimension(R.styleable.BlurMaskFilterView_blur_marginTop, dp2px(context,0));
        int marginRight = (int) typedArray.getDimension(R.styleable.BlurMaskFilterView_blur_marginRight, dp2px(context,0));
        int marginBottom = (int) typedArray.getDimension(R.styleable.BlurMaskFilterView_blur_marginBottom, dp2px(context,0));
        typedArray.recycle();
        //先画一个矩形
        rectF = new RectF(marginLeft, marginTop, marginRight, marginBottom);
        //创建画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#fefbf6"));
    }

    public void faguang() {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        /*float radius：用来定义模糊半径，同样是高斯模糊算法。
        Blur style：发光样式，有内发光、外发光、和内外发光，
        分别对应：Blur.INNER(内发光)、Blur.SOLID(外发光)、Blur.NORMAL(内外发光)、Blur.OUTER(仅发光部分可见)*/
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
        invalidate();
    }

    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //根据提供的矩形为四个角画弧线，（其中的数字：第一个表示X轴方向大小，第二个Y轴方向大小。可以改成其他的，你可以自己体验），最后添加画笔。
        canvas.drawRoundRect(rectF, 30, 30, mPaint);
    }
}
