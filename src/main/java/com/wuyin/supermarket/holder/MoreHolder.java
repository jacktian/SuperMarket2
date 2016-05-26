package com.wuyin.supermarket.holder;

import android.view.View;
import android.widget.RelativeLayout;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.adapter.DefaultAdapter;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by yinlong on 2016/5/11.
 */
public class MoreHolder extends BaseHolder<Integer> {

    public static final int HAS_NO_MORE = 0;     //没有额外的数据
    public static final int LOAD_ERROR = 1;   //加载失败
    public static final int HAS_MORE = 2;   //有额外的数据

    private RelativeLayout rl_more_loading,rl_more_error;

    /**
     * 当holder显示的时候显示的样子
     *
     * @return
     */
    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.load_more);
        rl_more_loading = (RelativeLayout) view.findViewById(R.id.rl_more_loading);
        rl_more_error = (RelativeLayout) view.findViewById(R.id.rl_more_error);
        return view;
    }

    private DefaultAdapter adapter;

    public MoreHolder(DefaultAdapter adapter) {
        super();
        this.adapter = adapter;
    }

    /**
     * 根据接收到的数据进行页面的显示和隐藏
     * @param data
     */
    @Override
    public void refreshData(Integer data) {
       /* if (data == LOAD_ERROR){
            rk_more_error.setVisibility(View.VISIBLE);
        } else {
            rk_more_error.setVisibility(View.GONE);
        }*/
        rl_more_error.setVisibility(data==LOAD_ERROR?View.VISIBLE:View.GONE);
        rl_more_loading.setVisibility(data==HAS_MORE?View.VISIBLE:View.GONE);
    }

    @Override
    public View getContentView() {

        loadMore();

        return super.getContentView();
    }

    /**
     * 请求服务器加载更多的数据
     */
    private void loadMore() {

        //交给adapter，让其去处理加载更多数据
        adapter.loadMore();
    }
}
