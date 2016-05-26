package com.wuyin.supermarket.holder;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by yinlong on 2016/5/11.
 */
public class DetailBottomHolder extends BaseHolder<AppInfo> implements View.OnClickListener {

    Button bottom_favorites,bottom_share,progress_btn;
    @Override
    public View initView() {

        View view= UIUtils.inflate(R.layout.detail_bottom);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        bottom_favorites = (Button) view.findViewById(R.id.bottom_favorites);
        bottom_favorites.setOnClickListener(this);
        bottom_share = (Button) view.findViewById(R.id.bottom_share);
        bottom_share.setOnClickListener(this);
        progress_btn = (Button) view.findViewById(R.id.progress_btn);
        progress_btn.setOnClickListener(this);
    }

    @Override
    public void refreshData(AppInfo data) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_favorites:
                Toast.makeText(UIUtils.getContext(), "收藏", 0).show();
                break;
            case R.id.bottom_share:
                Toast.makeText(UIUtils.getContext(), "分享", 0).show();
                break;
            case R.id.progress_btn:
                Toast.makeText(UIUtils.getContext(), "下载", 0).show();
                break;
        }
    }
}
