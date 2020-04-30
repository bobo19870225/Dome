package com.yy.dome.widget;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.yy.dome.R;


public class CommonEditText extends LinearLayout implements View.OnClickListener, OnFocusChangeListener {
	
	private LayoutInflater mLayoutInflater;
	private EditText mEditText;
	private Button mClearButton;
	private View mRoot;
	private float mDensity;
	private TextWatcher mTextWatcher;

	public CommonEditText(Context context) {
		super(context);
		init();
	}
	
	public CommonEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init() {
		mDensity = getResources().getDisplayMetrics().density;
		mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayoutInflater.inflate(R.layout.widget_common_edit_text_layout, this, true);
		
		mEditText = (EditText) findViewById(R.id.common_edit_text);
		mClearButton = (Button) findViewById(R.id.clear_button);
		mRoot = findViewById(R.id.common_edit_text_root);
		
		mClearButton.setVisibility(TextUtils.isEmpty(mEditText.getText().toString()) ? View.GONE : View.VISIBLE);
		
		mClearButton.setOnClickListener(this);
		setOnFocusChangeListener(this);
		mRoot.setOnFocusChangeListener(this);
		mEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				if (hasFocus) {
					postDelayed(new Runnable() {
						
						@Override
						public void run() {
							//Utils.showKeyboard(mEditText);
						}
					}, 500);
					mClearButton.setVisibility(TextUtils.isEmpty(mEditText.getText().toString()) ? View.GONE : View.VISIBLE);
				} else {
					mClearButton.setVisibility(View.GONE);
				}
			}
		});
		
		mEditText.addTextChangedListener(new android.text.TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (mTextWatcher != null) {
					mTextWatcher.onTextChanged(s, start, before, count);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				if (mTextWatcher != null) {
					mTextWatcher.beforeTextChanged(s, start, count, after);
				}
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				mClearButton.setVisibility(!TextUtils.isEmpty(s) ? View.VISIBLE : View.GONE);
				if (mTextWatcher != null) {
					mTextWatcher.afterTextChanged(s);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.clear_button:
			mEditText.setText("");
			mEditText.setError(null);
			break;
		}

		mEditText.requestFocus();
	}

	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		if (hasFocus) {
			mEditText.requestFocus();	
			mClearButton.setVisibility(TextUtils.isEmpty(mEditText.getText().toString()) ? View.GONE : View.VISIBLE);
		} else {
			mClearButton.setVisibility(View.GONE);
		}
	}
	
	public void setText(CharSequence text) {
		mEditText.setText(text);
	}

	public void setText(int resid) {
		mEditText.setText(resid);
	}

	public Editable getText() {
		return mEditText.getText();
	}

	public void setSingleLine(boolean singleLine) {
		mEditText.setSingleLine(singleLine);
	}

	public void setSelection(int index) {
		mEditText.setSelection(index);
	}

	public void setError(CharSequence error) {
		mEditText.setError(error);
	}

	public void setHint(CharSequence hint) {
		mEditText.setHint(hint);
	}

	public void setHint(int resid) {
		mEditText.setHint(resid);
	}

	public void setInputType(int inputType) {
		mEditText.setInputType(inputType);
	}

	public void setTextSize(int dip) {
		mEditText.setTextSize(dip * mDensity);
	}

	public void setTextColor(int color) {
		mEditText.setTextColor(color);
	}

	public void setHintTextColor(int color) {
		mEditText.setHintTextColor(color);
	}

	public void setMaxTextLength(int length, String msg) {
	}

	public EditText getInputView() {
		return mEditText;
	}

	public void setDrawable(Drawable left, Drawable top, Drawable right, Drawable bottom) {
		mEditText.setCompoundDrawables(left, top, right, bottom);
	}

	public void setDrawable(int left, int top, int right, int bottom) {
		mEditText.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
	}

	public void addTextChangedListener(TextWatcher textWatcher) {
		mTextWatcher = textWatcher;
	}
	
	/**
	 * When an object of a type is attached to an Editable, its methods will
	 * be called when the text is changed.
	 */
	public interface TextWatcher {
		
		/**
	     * This method is called to notify you that, within <code>s</code>,
	     * the <code>count</code> characters beginning at <code>start</code>
	     * are about to be replaced by new text with length <code>after</code>.
	     * It is an error to attempt to make changes to <code>s</code> from
	     * this callback.
	     */
	    public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after);
	    /**
	     * This method is called to notify you that, within <code>s</code>,
	     * the <code>count</code> characters beginning at <code>start</code>
	     * have just replaced old text that had length <code>before</code>.
	     * It is an error to attempt to make changes to <code>s</code> from
	     * this callback.
	     */
	    public void onTextChanged(CharSequence s, int start, int before, int count);

	    /**
	     * This method is called to notify you that, somewhere within
	     * <code>s</code>, the text has been changed.
	     * It is legitimate to make further changes to <code>s</code> from
	     * this callback, but be careful not to get yourself into an infinite
	     * loop, because any changes you make will cause this method to be
	     * called again recursively.
	     * (You are not told where the change took place because other
	     * afterTextChanged() methods may already have made other changes
	     * and invalidated the offsets.  But if you need to know here,
	     * you can use {@link Spannable#setSpan} in {@link #onTextChanged}
	     * to mark your place and then look up from here where the span
	     * ended up.
	     */
	    public void afterTextChanged(Editable s);
		
	}

}

