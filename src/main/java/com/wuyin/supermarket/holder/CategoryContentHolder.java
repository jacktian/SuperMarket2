package com.wuyin.supermarket.holder;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.CategoryInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by wuyin on 2016/5/27.
 */
public class CategoryContentHolder extends BaseHolder<CategoryInfo> {

    ImageView[] ivs;
    TextView[] tvs;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.item_category_content);
        ivs = new ImageView[3];
        ivs[0] = (ImageView) view.findViewById(R.id.iv_1);
        ivs[1] = (ImageView) view.findViewById(R.id.iv_2);
        ivs[2] = (ImageView) view.findViewById(R.id.iv_3);

        tvs = new TextView[3];
        tvs[0] = (TextView) view.findViewById(R.id.tv_1);
        tvs[1] = (TextView) view.findViewById(R.id.tv_2);
        tvs[2] = (TextView) view.findViewById(R.id.tv_3);


        return view;
    }

    @Override
    public void refreshData(CategoryInfo data) {
// 第一块
        if (!TextUtils.isEmpty(data.getName1()) && !TextUtils.isEmpty(data.getUrl1())) {
            tvs[0].setText(data.getName1());
            Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL + data.getUrl1()).into(ivs[0]);
            //bitmapUtils.display(ivs[0], HttpHelper.URL+"image?name="+data.getUrl1());
            tvs[0].setVisibility(View.VISIBLE);
            ivs[0].setVisibility(View.VISIBLE);
        } else {
            tvs[0].setVisibility(View.INVISIBLE);
            ivs[0].setVisibility(View.INVISIBLE);
        }
        // 第二块
        if (!TextUtils.isEmpty(data.getName2()) && !TextUtils.isEmpty(data.getUrl2())) {
            tvs[1].setText(data.getName2());
            Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL + data.getUrl2()).into(ivs[1]);

            //bitmapUtils.display(ivs[1], HttpHelper.URL+"image?name="+data.getUrl2());
            tvs[1].setVisibility(View.VISIBLE);
            ivs[1].setVisibility(View.VISIBLE);
        } else {
            tvs[1].setVisibility(View.INVISIBLE);
            ivs[1].setVisibility(View.INVISIBLE);
        }
        //第三块
        if (!TextUtils.isEmpty(data.getName3()) && !TextUtils.isEmpty(data.getUrl3())) {
            tvs[2].setText(data.getName3());
            Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL + data.getUrl3()).into(ivs[2]);

            // bitmapUtils.display(ivs[2], HttpHelper.URL+"image?name="+data.getUrl3());
            tvs[2].setVisibility(View.VISIBLE);
            ivs[2].setVisibility(View.VISIBLE);
        } else {
            tvs[2].setVisibility(View.INVISIBLE);
            ivs[2].setVisibility(View.INVISIBLE);
        }
    }
}
