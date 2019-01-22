package com.example.weibohuati;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class TopicDrawable extends Drawable {
 
	private String topic;
	private Paint paint;
	
	public TopicDrawable(String topic){
		this.topic = topic;
		paint = new Paint();
		paint.setColor(Color.BLUE);
		paint.setTextSize(36);
		
	}
	
	@Override
	public void draw(Canvas canvas) {
		canvas.drawText(topic, 0, 0, paint);
		
	}
 
	@Override
	public void setAlpha(int alpha) {
 
	}
 
	@Override
	public void setColorFilter(ColorFilter cf) {
 
	}
 
	@Override
	public int getOpacity() {
		return 0;
	}
 
	@Override
	public int getIntrinsicHeight() {
		return 0;
	}
	
	@Override
	public int getIntrinsicWidth() {
		return (int) paint.measureText(topic);
	}
}
