package com.yunyisheng.app.yunys.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ExpandableListView;

/**
 * Created by liyalong on 2018/1/23.
 */

public class SuperExpandableListView extends ExpandableListView {
    public SuperExpandableListView(Context context) {
        super(context);
    }

    public SuperExpandableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SuperExpandableListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
