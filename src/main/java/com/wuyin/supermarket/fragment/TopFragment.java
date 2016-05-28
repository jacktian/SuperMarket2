package com.wuyin.supermarket.fragment;


import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.httpresult.TopHttpRequest;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TopFragment extends BaseFragment {

    List<String> datas = new ArrayList<>();

    @Override
    public LoadingPage.LoadResult load() {

        TopHttpRequest request = new TopHttpRequest();
        datas = request.load(0);

        return checkLoad(datas);
    }

    @Override
    public View createSuccessView() {
        ScrollView scrollView =new ScrollView(UIUtils.getContext());
        LinearLayout layout = new LinearLayout(UIUtils.getContext());
        layout.setOrientation(LinearLayout.VERTICAL);//设置线性布局的方向
        for (int i = 0; i < datas.size(); i++) {
            TextView textView = new TextView(UIUtils.getContext());
            textView.setTextColor(Color.BLUE);
            textView.setText(datas.get(i));
            layout.addView(textView);
        }
        scrollView.addView(layout);
        return scrollView;
    }
}
