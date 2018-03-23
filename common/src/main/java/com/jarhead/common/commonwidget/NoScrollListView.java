package com.jarhead.common.commonwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * 不滚动的listview
 * Created by jiming1 on 2016/10/21.
 */
public class NoScrollListView extends ListView {

    public NoScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //设置listview不滚动
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
