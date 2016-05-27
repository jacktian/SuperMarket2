package com.wuyin.supermarket.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by wuyin on 2016/5/26.
 */
public class RatioLayout extends FrameLayout {

    //按照宽高比例去显示
    private float radio = 2.43f;  //比例值

    public RatioLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RatioLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public RatioLayout(Context context) {
        this(context,null);
    }

    //测量当前布局
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //widthMeasureSpec   宽度的规则，包含了两部分    模式 和 值
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);  //模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);  //宽度
        int width = widthSize - getPaddingLeft() - getPaddingRight();  //去掉左右两边的padding


        int heightMode = MeasureSpec.getMode(heightMeasureSpec);  //模式
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);  //宽度
        int height = heightSize - getPaddingTop() - getPaddingBottom();  //去掉左右两边的padding

        //宽度是已知的
        if (widthMode == MeasureSpec.EXACTLY && heightMode != MeasureSpec.EXACTLY) {
            //修正一下高度的值   让高度来重新按照宽度的值/比例  来计算
            height = (int) (width / radio + 0.5);
        } else if (widthMode != MeasureSpec.EXACTLY && heightMode == MeasureSpec.EXACTLY) {
            //高度是已知的   宽度随着高度的变化而变化
            width = (int) (height * (radio + 0.5));
        }

        //重新制作新的规则
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, width + getPaddingLeft() + getPaddingRight());
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, height + getPaddingBottom() + getPaddingTop());


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
