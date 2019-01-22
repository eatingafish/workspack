package com.example.buyaolian;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

public class MyView extends View {

    private Paint paint;
    private int r=0;
    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    private void init(){
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(10,250);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                r= (int) valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(-1);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.start();

    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAlpha(255-(int)(r*(255.0/250)));
        canvas.drawCircle(getWidth()/2,getHeight()/2,r,paint);
        canvas.drawCircle(getWidth()/2,getHeight()/2,r*(r/250.f),paint);

    }
}
