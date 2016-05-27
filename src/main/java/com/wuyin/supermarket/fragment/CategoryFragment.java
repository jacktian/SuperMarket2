package com.wuyin.supermarket.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wuyin.supermarket.adapter.DefaultAdapter;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.holder.CategoryContentHolder;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.httpresult.CategoryHttpRequest;
import com.wuyin.supermarket.model.CategoryInfo;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.BaseListView;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {
    List<CategoryInfo> datas;

    @Override
    public LoadingPage.LoadResult load() {
        CategoryHttpRequest request = new CategoryHttpRequest();
        datas = request.load(0);
        return checkLoad(datas);
    }

    //创建成功的界面
    @Override
    public View createSuccessView() {
        BaseListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new CategoryAdapter(datas, listView));
        return listView;
    }

    private class CategoryAdapter extends DefaultAdapter<CategoryInfo> {


        private int position;//当前条目的位置
        public final static int ITEM_TITLE = 2;

        public CategoryAdapter(List<CategoryInfo> datas, ListView lv) {
            super(datas, lv);
        }


        //实现每个条目的界面
        @Override
        public BaseHolder<CategoryInfo> getHolder() {
            if (!datas.get(position).isTitle()) {
                return new CategoryContentHolder();
            } else {
                return new CategoryTitleHolder();
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            this.position = position;
            return super.getView(position, convertView, parent);
        }

        @Override
        protected List<CategoryInfo> onLoad() {
            return null;
        }

        @Override
        protected boolean hasMore() {   //当前方法如果为false  onload就不会被调用
            return false;
        }

        //  集合 管理三个convertView
        @Override
        public int getViewTypeCount() {
            return super.getViewTypeCount() + 1;
        }

        @Override
        protected int getInnerItemType(int position) {

            if (datas.get(position).isTitle()) {
                return ITEM_TITLE;
            } else {
                return super.getInnerItemType(position);
            }

        }
    }
}
