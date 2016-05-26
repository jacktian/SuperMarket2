package com.wuyin.supermarket.utils;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by wuyin on 2016/5/2.
 */
public class ViewUtils {

    public static void remoteParent(View v) {
        //先找到parent，然后通过parent移除child
        ViewParent parent = v.getParent();
        //所有的控件都有parent，一般情况下就是ViewGroup
        if (parent instanceof ViewGroup){
            ViewGroup group = (ViewGroup) parent;
            group.removeView(v);
        }
    }
}
