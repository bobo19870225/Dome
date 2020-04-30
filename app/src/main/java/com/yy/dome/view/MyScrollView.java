package com.yy.dome.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView {
	ScrollViewLine scrollViewLine;
	boolean isup = false;

	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		scrollViewLine.onScrollChanged(l, t, oldl, oldt);
	}

	public ScrollViewLine getScrollViewLine() {
		return scrollViewLine;
	}

	public void setScrollViewLine(ScrollViewLine scrollViewLine) {
		this.scrollViewLine = scrollViewLine;
	}

	public interface ScrollViewLine {
		void onScrollChanged(int l, int t, int oldl, int oldt);
	}
}