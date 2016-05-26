package com.wuyin.supermarket.holder;

import android.text.format.Formatter;
import android.transition.CircularPropagation;
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
public class DetailInfoHolder  extends BaseHolder<AppInfo>{

    private ImageView item_icon;
    private TextView item_title;
    private RatingBar item_rating;
    private TextView item_download;
    private TextView item_version;
    private TextView item_date;
    private TextView item_size;
    @Override
    public View initView() {
        View view= UIUtils.inflate(R.layout.detail_app_info);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        item_icon = (ImageView) view.findViewById(R.id.item_icon);
        item_title = (TextView) view.findViewById(R.id.item_title);
        item_rating = (RatingBar) view.findViewById(R.id.item_rating);
        item_download = (TextView) view.findViewById(R.id.item_download);
        item_version = (TextView) view.findViewById(R.id.item_version);
        item_date = (TextView) view.findViewById(R.id.item_date);
        item_size = (TextView) view.findViewById(R.id.item_size);
    }

    @Override
    public void refreshData(AppInfo data) {
        Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL+data.getIconUrl()).into(item_icon);
        item_title.setText(data.getName());
        item_rating.setRating(data.getStars());
        item_download.setText("下载:"+data.getDownloadNum());
        item_version.setText("版本:"+data.getVersion());
        item_date.setText("时间:"+data.getDate());
        item_size.setText("大小:"+ Formatter.formatFileSize(UIUtils.getContext(), data.getSize()));

    }
}
