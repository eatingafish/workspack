package com.example.weibohuati;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEditText extends EditText {

	public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public MyEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyEditText(Context context) {
		super(context);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	public void insertTopic(String topic){
		this.getText().delete(this.getText().length() - 1, this.getText().length());
		SpannableString ss = new SpannableString("[" + topic + "]");
		TopicDrawable td = new TopicDrawable(topic);
		td.setBounds(0, 0, td.getIntrinsicWidth(), td.getIntrinsicHeight());
		System.out.println(td.getIntrinsicHeight() + " " + td.getIntrinsicWidth());
		ImageSpan span = new ImageSpan(td, ImageSpan.ALIGN_BASELINE);
		ss.setSpan(span, 0, topic.length()+2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		int start = this.getSelectionStart();
		int end = this.getSelectionEnd();
		if(start == end){
			//System.out.println(et.getText().length());
			this.getText().insert(start, ss);
		}else{
			this.getText().replace(start, end, ss);
		}
		//System.out.println(start + ss.length());
		//System.out.println(getText().length());
		this.setSelection(start + ss.length());
	}

}
