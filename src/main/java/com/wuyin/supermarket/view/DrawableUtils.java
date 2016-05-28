package com.wuyin.supermarket.view;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by wuyin on 2016/5/28.
 */
public class DrawableUtils {

    /**
     * 创建弧度背景
     * @param color  背景颜色
     * @return  弧度图片
     */
    public static GradientDrawable createShape(int color) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(UIUtils.dip2px(5));  //设置4个角的弧度
        drawable.setColor(color);
        return drawable;
    }

    /**
     * 创建状态选择器
     *
     * @param pressDrawable  按下图片
     * @param normalDrawable 正常图片
     * @return  选择器
     */
    public static StateListDrawable createSelectorDrawable(Drawable pressDrawable, Drawable normalDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed}, pressDrawable);//按下显示的图片
        stateListDrawable.addState(new int[]{}, normalDrawable); //默认显示的图片
        return stateListDrawable;
    }
}
