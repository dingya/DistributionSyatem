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
 * �Զ�����ɾ����ť�����Text
 * @author xumin
 *
 */
public class ClearEditText extends EditText implements OnFocusChangeListener,
		TextWatcher {
	private Drawable mClearDrawable;
	private Drawable currentDrawable;

	private int distanceLeft;
	private int scaleLeft;
	private int[] res = new int[2];// ���ͼƬ����Դ

	public ClearEditText(Context context) {
		this(context, null);
	}

	public ClearEditText(Context context, AttributeSet attrs) {
		// ���ﹹ�췽��Ҳ����Ҫ����������ܶ����Բ�����XML���涨��
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
		// ��ȡEditText��DrawableRight,����û���������Ǿ�ʹ��Ĭ�ϵ�ͼƬ
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
	 * ��Ϊ���ǲ���ֱ�Ӹ�EditText���õ���¼������������ü�ס���ǰ��µ�λ����ģ�����¼� �����ǰ��µ�λ�� �� EditText�Ŀ�� -
	 * ͼ�굽�ؼ��ұߵļ�� - ͼ��Ŀ�� �� EditText�Ŀ�� - ͼ�굽�ؼ��ұߵļ��֮�����Ǿ�������ͼ�꣬��ֱ����û�п���
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
	 * ��ClearEditText���㷢���仯��ʱ���ж������ַ��������������ͼ�����ʾ������
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
	 * �������ͼ�����ʾ�����أ�����setCompoundDrawablesΪEditText������ȥ
	 * 
	 * @param visible
	 */
	protected void setClearIconVisible(boolean visible) {
		Drawable right = visible ? mClearDrawable : null;
		setCompoundDrawables(currentDrawable, getCompoundDrawables()[1], right,
				getCompoundDrawables()[3]);
	}

	/**
	 * ��������������ݷ����仯��ʱ��ص��ķ���
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
