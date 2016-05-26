package com.wuyin.supermarket.holder.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by yinlong on 2016/5/11.
 */
public class ListBaseHolder extends BaseHolder<AppInfo> {
    ImageView image_item;
    TextView item_title, item_size, item_bottom;
    RatingBar item_rating;

    private View contentView;



    public View getContentView() {
        return contentView;
    }


    @Override
    public View initView() {
        contentView = View.inflate(UIUtils.getContext(), R.layout.home_item, null);
        image_item = (ImageView) contentView.findViewById(R.id.item_icon);
        item_title = (TextView) contentView.findViewById(R.id.item_title);
        item_size = (TextView) contentView.findViewById(R.id.item_size);
        item_bottom = (TextView) contentView.findViewById(R.id.item_bottom);
        item_rating = (RatingBar) contentView.findViewById(R.id.item_rating);
        contentView.setTag(this);
        return contentView;
    }

    @Override
    public void refreshData(AppInfo data) {
        //加载图片
        Glide.with(UIUtils.getContext())
                .load(Constants.IMAGE_URL + data.getIconUrl())
                .error(R.mipmap.ic_default)
                .into(image_item);

        item_title.setText(data.getName());

        //得到app的大小
        String size = android.text.format.Formatter.formatFileSize(UIUtils.getContext(), data.getSize());
        item_size.setText(size);
        item_bottom.setText(data.getDes());

        //设置ratingBar
        float stars = data.getStars();
        item_rating.setRating(stars);
    }

}
