package com.wuyin.supermarket.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.httpresult.UserHttpRequest;
import com.wuyin.supermarket.manager.ThreadManager;
import com.wuyin.supermarket.model.UserInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by yinlong on 2016/5/11.
 */
public class MenuHolder extends BaseHolder<UserInfo> implements View.OnClickListener {

    private RelativeLayout photo_layout;
    private ImageView image_photo;
    private TextView user_name, user_email;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.menu_holder);
        initViews(view);
        photo_layout.setOnClickListener(this);
        return view;
    }

    /**
     * 初始化布局控件
     *
     * @param view
     */
    private void initViews(View view) {
        photo_layout = (RelativeLayout) view.findViewById(R.id.photo_layout);
        image_photo = (ImageView) view.findViewById(R.id.image_photo);
        user_name = (TextView) view.findViewById(R.id.user_name);
        user_email = (TextView) view.findViewById(R.id.user_email);
    }

    @Override
    public void refreshData(UserInfo data) {
        UserInfo userInfo = data;
        user_name.setText(userInfo.getName());
        user_email.setText(userInfo.getEmail());
        Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL + userInfo.getIcon()).into(image_photo);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_layout:
                //连接服务器去登录
                ThreadManager.getInstance().createShortPool(2, 5, 3000).execute(new Runnable() {
                    @Override
                    public void run() {
                        UserHttpRequest request = new UserHttpRequest();
                        final UserInfo load = request.load(0);
                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setData(load);  //当调用该方法的时候，就会去调用refreshData
                            }
                        });
                    }
                });
                break;
        }
    }
}
