package com.example.englishplay.bookrecycle.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * 侧滑菜单栏
 */
public class SlidingMenu extends HorizontalScrollView {

    /**
     * 在代码中使用new时会调用此方法
     * @param context
     */
    public SlidingMenu(Context context) {
        this(context, null);
    }

    /**
     * 未使用自定义属性时默认调用
     * @param context
     * @param attrs
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        //调用三个参数的构造方法
        this(context, attrs, 0);

    }

    /**
     * 当使用了自定义属性时会调用此方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
