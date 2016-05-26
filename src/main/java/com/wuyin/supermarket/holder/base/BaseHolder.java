package com.wuyin.supermarket.holder.base;

import android.view.View;

/**
 * Created by yinlong on 2016/5/5.
 */
public abstract class BaseHolder<T> {

    private View contentView;

    private T data;



    public BaseHolder() {
        contentView = initView();
        contentView.setTag(this);
    }

    /**
     * 初始化的方法，留给子类去实现
     * @return
     */
    public abstract View initView();

    public void setData(T data) {
        this.data = data;
        refreshData(data);
    }

    /*刷新数据的方法*/
    public abstract void refreshData(T data) ;


    public View getContentView() {

        return contentView;
    }
}
