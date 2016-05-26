package com.wuyin.supermarket.utils;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.wuyin.supermarket.application.BaseApplication;

/**
 * Created by wuyin on 2016/5/2.
 */
public class UIUtils {

    /**
     * 获取到xml中的字符数组
     *
     * @param tab_names 字符数组的ID
     * @return
     */
    public static String[] getStringArray(int tab_names) {
        return getResources().getStringArray(tab_names);
    }

    /**
     * 获取getResources()
     *
     * @return
     */
    private static Resources getResources() {
        return BaseApplication.getmApplication().getResources();
    }


    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */

    public static int px2dip(int px) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        return BaseApplication.getmApplication();
    }

    /**
     * 把Runnable 方法提交到主线程运行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        // 在主线程运行
        if (android.os.Process.myTid() == BaseApplication.getMainTid()) {
            runnable.run();
        } else {
            //获取handler
            BaseApplication.getmHandler().post(runnable);
        }
    }

    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    public static Drawable getDrawalbe(int id) {
        return getResources().getDrawable(id);
    }

    public static int getDimens(int homePictureHeight) {
        return (int) getResources().getDimension(homePictureHeight);
    }
    /**
     * 延迟执行 任务
     * @param run   任务
     * @param time  延迟的时间
     */
    public static void postDelayed(Runnable run, int time) {
        BaseApplication.getmHandler().postDelayed(run, time); // 调用Runable里面的run方法
    }
    /**
     * 取消任务
     * @param auToRunTask
     */
    public static void cancel(Runnable auToRunTask) {
        BaseApplication.getmHandler().removeCallbacks(auToRunTask);
    }
}
