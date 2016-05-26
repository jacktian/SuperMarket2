package com.wuyin.supermarket.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.wuyin.supermarket.adapter.base.ListBaseAdapter;
import com.wuyin.supermarket.holder.HomePictureHolder;
import com.wuyin.supermarket.httpresult.HomeHttpRequest;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.ui.DetailActivity;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    List<AppInfo> appInfos = new ArrayList<>();
    private List<String> pics = new ArrayList<>();  //顶部view显示界面的数据

    /**
     * 当fragment挂载到activity中的时候调用，要不然有个问题
     * 就是当我们第一次进入到app的时候，因为第一个首先加载的是homefragment
     * 所以我们要去加载之前去加载一下
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }


    public LoadingPage.LoadResult load() {

        HomeHttpRequest httpRequest = new HomeHttpRequest();
        appInfos = httpRequest.load(0);
        pics = httpRequest.getPics();
        return checkLoad(appInfos);
    }


    @Override
    public View createSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());

        HomePictureHolder holder=new HomePictureHolder();
        holder.setData(pics);
        View contentView = holder.getContentView(); // 得到holder里面管理的view对象
        //contentView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        listView.addHeaderView(contentView); // 把holder里的view对象 添加到listView的上面



        listView.setAdapter(new ListBaseAdapter(appInfos,listView) {
            @Override
            protected List<AppInfo> onLoad() {
                HomeHttpRequest request = new HomeHttpRequest();
                List<AppInfo> load = request.load(appInfos.size());

                appInfos.addAll(load);
                return load;
            }

            @Override
            public void onInnerItemClick(int position) {
                super.onInnerItemClick(position);
                //Toast.makeText(UIUtils.getContext(), "position" + position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UIUtils.getContext(), DetailActivity.class);
                intent.putExtra("packageName",appInfos.get(position).getPackageName());
                startActivity(intent);
            }
        });
        return listView;
    }

   /* *//**
     * adapter
     *//*
    class HomeAdapter extends DefaultAdapter<AppInfo> {

        public HomeAdapter(List<AppInfo> datas) {
            super(datas);
        }



        @Override
        public BaseHolder<AppInfo> getHolder() {

            return new ListBaseHolder();
        }
    }
*/



}
