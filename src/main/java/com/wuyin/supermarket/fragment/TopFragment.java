package com.wuyin.supermarket.fragment;


import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.httpresult.TopHttpRequest;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.DrawableUtils;
import com.wuyin.supermarket.view.FlowLayout;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        FlowLayout layout = new FlowLayout(UIUtils.getContext());
        int padding  = UIUtils.dip2px(13);
        layout.setPadding(padding,padding,padding,padding);
        int backColor = 0xFFCECECE;
        Drawable pressDrawable = DrawableUtils.createShape(backColor);


        for (int i = 0; i < datas.size(); i++) {
            Random random = new Random();
            int red = random.nextInt(200)+20;
            int green = random.nextInt(200)+30;
            int blue = random.nextInt(200)+40;
            int color = Color.rgb(red,green,blue);
            GradientDrawable drawable = DrawableUtils.createShape(color);  //默认显示图片
            StateListDrawable selectorDrawable = DrawableUtils.createSelectorDrawable(pressDrawable, drawable);
            int textPaddingV = UIUtils.dip2px(4);
            int textPaddingH = UIUtils.dip2px(7);
            TextView textView = new TextView(UIUtils.getContext());
            textView.setTextColor(Color.WHITE);
            textView.setText(datas.get(i));
            textView.setBackgroundDrawable(selectorDrawable);
            textView.setTextSize(UIUtils.dip2px(10));
            textView.setPadding(textPaddingH,textPaddingV,textPaddingH,textPaddingV);
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(UIUtils.getContext(), datas.get(finalI), Toast.LENGTH_SHORT).show();
                }
            });
            layout.addView(textView,new LinearLayout.LayoutParams(-2,-2));
        }
        scrollView.addView(layout);
        return scrollView;
    }
}
