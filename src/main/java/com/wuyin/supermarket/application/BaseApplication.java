package com.wuyin.supermarket.application;

import android.app.Application;
import android.os.Handler;

/**
 * Created by wuyin on 2016/5/2.
 */
public class BaseApplication extends Application {

    private static BaseApplication mApplication;
    private static int mainTid;
    private static Handler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        mainTid = android.os.Process.myTid();
        mHandler = new Handler();
    }

    public static BaseApplication getmApplication() {
        return mApplication;
    }

    public static int getMainTid(){
        return mainTid;
    }

    public static Handler getmHandler(){
        return mHandler;
    }

}
