package com.wuyin.supermarket.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.DetailBottomHolder;
import com.wuyin.supermarket.holder.DetailDesHolder;
import com.wuyin.supermarket.holder.DetailInfoHolder;
import com.wuyin.supermarket.holder.DetailSafeHolder;
import com.wuyin.supermarket.holder.DetailScreenHolder;
import com.wuyin.supermarket.httpresult.DetailHttpRequest;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.ui.base.BaseActivity;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;

public class DetailActivity extends BaseActivity {

    private String packageName;
    private AppInfo appInfo;

    @Override
    protected void initView() {
        LoadingPage loadingPage = new LoadingPage(this) {
            @Override
            public LoadResult load() {
                return DetailActivity.this.load();
            }

            @Override
            public View createSuccessView() {

                return DetailActivity.this.createSuccessView();
            }
        };
        //loadingPage.load();
        setContentView(loadingPage);
    }

    private FrameLayout bottom_layout, detail_info, detail_safe, detail_des;
    private HorizontalScrollView detail_screen;
    private DetailInfoHolder detailInfoHolder;
    private DetailScreenHolder screenHolder;
    private DetailSafeHolder safeHolder;
    private DetailDesHolder desHolder;
    private DetailBottomHolder bottomHolder;

    /**
     * 加载成功的解码
     *
     * @return
     */
    private View createSuccessView() {
        View view = UIUtils.inflate(R.layout.activity_detail);
        // 添加信息区域
        bottom_layout = (FrameLayout) view.findViewById(R.id.bottom_layout);
        bottomHolder = new DetailBottomHolder();
        bottomHolder.setData(appInfo);
        bottom_layout.addView(bottomHolder.getContentView());

        // 操作 应用程序信息
        detail_info = (FrameLayout) view.findViewById(R.id.detail_info);
        detailInfoHolder = new DetailInfoHolder();
        detailInfoHolder.setData(appInfo);
        detail_info.addView(detailInfoHolder.getContentView());

        //安全标记
        detail_safe = (FrameLayout) view.findViewById(R.id.detail_safe);
        safeHolder = new DetailSafeHolder();
        safeHolder.setData(appInfo);
        detail_safe.addView(safeHolder.getContentView());

        //应用详细描述
        detail_des = (FrameLayout) view.findViewById(R.id.detail_des);
        desHolder = new DetailDesHolder();
        desHolder.setData(appInfo);
        detail_des.addView(desHolder.getContentView());


        // 中间5张图片
        detail_screen = (HorizontalScrollView) view.findViewById(R.id.detail_screen);
        screenHolder = new DetailScreenHolder();
        screenHolder.setData(appInfo);
        detail_screen.addView(screenHolder.getContentView());
        return view;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        packageName = getIntent().getStringExtra("packageName");
        super.onCreate(savedInstanceState);
    }

    /**
     * 请求服务器加载数据
     *
     * @return
     */
    private LoadingPage.LoadResult load() {
        DetailHttpRequest request = new DetailHttpRequest(packageName);
        appInfo = request.load(0);
        if (appInfo == null) {
            return LoadingPage.LoadResult.error;
        } else {
            return LoadingPage.LoadResult.success;
        }
    }

    @Override
    protected void initActionBar() {
        super.initActionBar();
        ActionBar actionBar = getSupportActionBar();
        //显示按钮
        // actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
