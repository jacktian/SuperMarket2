package com.wuyin.supermarket.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.CategoryInfo;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by wuyin on 2016/5/27.
 */
public class CategoryTitleHolder extends BaseHolder<CategoryInfo> {

    private TextView tv;

    @Override
    public View initView() {
        tv = new TextView(UIUtils.getContext());
        tv.setTextColor(Color.BLACK);
        tv.setBackgroundDrawable(UIUtils.getDrawalbe(R.drawable.grid_item_bg));
        return tv;
    }

    @Override
    public void refreshData(CategoryInfo data) {
        tv.setText(data.getTitle());
    }
}
