package com.yy.dome.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.dome.R;


public class PreferenceItem extends LinearLayout {
	
	private LayoutInflater mLayoutInflater;
	private TextView mTitle;
	private TextView mWhole;
	private ImageView mInto;
	private ImageView imageView;
	private CheckBox checkBox;
	private View view;
	private LinearLayout linearLayout;
	public PreferenceItem(Context context) {
		this(context, null);
	}
	public PreferenceItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	private void init() {
		setOrientation(HORIZONTAL);
		mLayoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mLayoutInflater.inflate(R.layout.widget_preference_item, this, true);
		imageView=(ImageView) findViewById(R.id.my_item_img);
		mTitle = (TextView) findViewById(R.id.my_item_title);
		mWhole = (TextView) findViewById(R.id.my_item_whole);
		mInto=(ImageView) findViewById(R.id.my_item_into);
		checkBox=(CheckBox)findViewById(R.id.checkbox);
		view = findViewById(R.id.prference_item_view);
		linearLayout = (LinearLayout)findViewById(R.id.preference_item);
	}
	public void setTitle(String title) {
		mTitle.setText(title);
	}
	public void setTitle(int title) {
		mTitle.setText(title);
	}
	public String getTitle(){
		return mTitle.getText().toString();
	}
	public void setContent(String content) {
		mWhole.setText(content);
	}
	public void setContent(int content) {
		mWhole.setText(content);
	}
	public String getContent(){
		return mWhole.getText().toString();
	}
	public void setImageView(int image){
		imageView.setImageResource(image);
	}
	public void setVisibility(int view)
	{
		imageView.setVisibility(view);
	}
	public void setFunctionMulti(String title,String content,int viewn){
		ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
		layoutParams.height=ViewGroup.LayoutParams.WRAP_CONTENT;
		mTitle.setText(title);
		mWhole.setText(content);
		imageView.setVisibility(viewn);
		view.setVisibility(viewn);
	}
	public void setFunctionMultin(String title,String content,int viewn){
		mTitle.setText(title);
		mWhole.setText(content);
		imageView.setVisibility(viewn);
		view.setVisibility(viewn);
	}

	public void setFunctionMultiPayment(String title,String content,int viewn){
		ViewGroup.LayoutParams layoutParams = linearLayout.getLayoutParams();
		layoutParams.height=ViewGroup.LayoutParams.WRAP_CONTENT;
		mTitle.setText(title);
		mWhole.setText(content);
		imageView.setVisibility(viewn);
		view.setVisibility(viewn);
		mInto.setVisibility(viewn);
	}
	public void setFunctionMultiPaymentMethod(int title){
		mTitle.setText(title);
		checkBox.setVisibility(VISIBLE);
	}

	public void setCheckBox(boolean isFlang){
		checkBox.setChecked(isFlang);
	}

	public boolean isChecked(){
		return checkBox.isChecked();
	}


	public void setModelVisibility(){
		linearLayout.setVisibility(GONE);
	}

}
