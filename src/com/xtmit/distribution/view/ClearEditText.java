package com.xtmit.distribution.view;

import com.xtmit.distributionsystem.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;

 
/**
 * 自定义有删除按钮的清空Text
 * @author xumin
 *
 */
public class ClearEditText extends EditText implements OnFocusChangeListener,
		TextWatcher {
	private Drawable mClearDrawable;
	private Drawable currentDrawable;

	private int distanceLeft;
	private int scaleLeft;
	private int[] res = new int[2];// 左边图片的资源

	public ClearEditText(Context context) {
		this(context, null);
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		// 这里构造方法也很重要，不加这个很多属性不能再XML里面定义
		this(context, attrs, android.R.attr.editTextStyle);
	}

	public ClearEditText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	private void init(AttributeSet attrs) {
		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs,R.styleable.editText);
			res[0] = a.getResourceId(R.styleable.editText_drawableLeftFormal,
					-1);
			res[1] = a
					.getResourceId(R.styleable.editText_drawableLeftFouse, -1);
			distanceLeft = a.getInteger(R.styleable.editText_distanceLeft, -1);
			scaleLeft = a.getInteger(R.styleable.editText_scaleLeft, -1);
		}
		// 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
		mClearDrawable = getCompoundDrawables()[2];

		if (mClearDrawable == null) {
			mClearDrawable = getResources().getDrawable(
					R.drawable.emotionstore_progresscancelbtn);
		}

		if (currentDrawable == null) {
			setLeftDrawable(res[0]);
		}
		
		mClearDrawable.setBounds(0, 0, mClearDrawable.getIntrinsicWidth(),
				mClearDrawable.getIntrinsicHeight());
		setClearIconVisible(false);
		setOnFocusChangeListener(this);
		addTextChangedListener(this);
	}

	/**
	 * 因为我们不能直接给EditText设置点击事件，所以我们用记住我们按下的位置来模拟点击事件 当我们按下的位置 在 EditText的宽度 -
	 * 图标到控件右边的间距 - 图标的宽度 和 EditText的宽度 - 图标到控件右边的间距之间我们就算点击了图标，竖直方向没有考虑
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (getCompoundDrawables()[2] != null) {
			if (event.getAction() == MotionEvent.ACTION_UP) {
				boolean touchable = event.getX() > (getWidth()
						- getPaddingRight() - mClearDrawable
							.getIntrinsicWidth())
						&& (event.getX() < ((getWidth() - getPaddingRight())));
				if (touchable) {
					this.setText("");
				}
			}
		}

		return super.onTouchEvent(event);
	}

	/**
	 * 当ClearEditText焦点发生变化的时候，判断里面字符串长度设置清除图标的显示与隐藏
	 */
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			setLeftDrawable(res[1]);
			setClearIconVisible(getText().length() > 0);
		} else {
			setLeftDrawable(res[0]);
			setClearIconVisible(false);
		}
	}

	public void setLeftDrawable(int res) {
		if (res != -1) {
			currentDrawable =  getResources().getDrawable(res);
			currentDrawable.setBounds(-distanceLeft, 0,
					(currentDrawable.getIntrinsicWidth() * scaleLeft) - distanceLeft,
					currentDrawable.getIntrinsicHeight() * scaleLeft);
		}
	}

	/**
	 * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
	 * 
	 * @param visible
	 */
	protected void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mClearDrawable : null;
		setCompoundDrawables(currentDrawable, getCompoundDrawables()[1], right,
				getCompoundDrawables()[3]);
	}

	/**
	 * 当输入框里面内容发生变化的时候回调的方法
	 */
	@Override
	public void onTextChanged(CharSequence s, int start, int count, int after) {
		setClearIconVisible(s.length() > 0);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {}

	@Override
	public void afterTextChanged(Editable s) {}
}
