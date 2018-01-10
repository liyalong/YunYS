package com.yunyisheng.app.yunys.utils;


import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 作者：MarkMingShuai
 * 时间 2017-8-9 13:39
 * 邮箱：mark_mingshuai@163.com
 * 类的意图：自定义GridView
 */

public class MyGridView extends GridView {

	public MyGridView(Context context) {
		super(context);
	}
	public MyGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	public MyGridView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);

		super.onMeasure(widthMeasureSpec, expandSpec);
	}

}
