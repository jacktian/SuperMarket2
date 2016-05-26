package com.wuyin.supermarket.holder;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;

import java.util.List;

/**
 * Created by yinlong on 2016/5/11.
 */
public class DetailScreenHolder extends BaseHolder<AppInfo> {

    private ImageView[] ivs;
    @Override
    public View initView() {
        View view= UIUtils.inflate(R.layout.detail_screen);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        ivs=new ImageView[5];
        ivs[0]=(ImageView) view.findViewById(R.id.screen_1);
        ivs[1]=(ImageView) view.findViewById(R.id.screen_2);
        ivs[2]=(ImageView) view.findViewById(R.id.screen_3);
        ivs[3]=(ImageView) view.findViewById(R.id.screen_4);
        ivs[4]=(ImageView) view.findViewById(R.id.screen_5);
    }

    @Override
    public void refreshData(AppInfo data) {
        List<String> screen = data.getScreen(); // 集合的大小有可能小于5
        for(int i=0;i<5;i++){
            if(i<screen.size()){
                ivs[i].setVisibility(View.VISIBLE);
                Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL+screen.get(i)).error(R.mipmap.ic_default).into(ivs[i]);
                //bitmapUtils.display(ivs[i], HttpHelper.URL+"image?name="+screen.get(i));
            }else{
                ivs[i].setVisibility(View.GONE);
            }

        }
    }
}
