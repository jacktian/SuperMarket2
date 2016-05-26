package com.wuyin.supermarket.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.holder.MoreHolder;
import com.wuyin.supermarket.manager.ThreadManager;
import com.wuyin.supermarket.utils.UIUtils;

import java.util.List;

/**
 * Created by yinlong on 2016/5/5.
 */
public abstract class DefaultAdapter<T> extends BaseAdapter implements AdapterView.OnItemClickListener {

    private static final int DEFAULT_ITEM = 0;
    private static final int MORE_ITEM = 1;
    private ListView lv;

    protected List<T> datas;

    public DefaultAdapter(List<T> datas, ListView lv) {
        this.datas = datas;
        this.lv = lv;
        //给item设置点击事件
        lv.setOnItemClickListener(this);
    }

    @Override
    public int getCount() {
        return datas.size() + 1;  //最后的一个加载更多的条目
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 当前lietview有几种view类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        if (position == datas.size()) {  //当前最后的一个条目
            return MORE_ITEM;
        }
        return getInnerItemType(position);
    }

    /**
     * @param position
     * @return
     */
    private int getInnerItemType(int position) {

        return DEFAULT_ITEM;
    }


    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /* BaseHolder holder = null;
        *//*if (position == datas.size()) {  //如果是最后一个就显示加载的view
            holder = getMoreHolder();
            return holder.getContentView();
        }*//*
        if (convertView == null) {
            if (getInnerItemType(position) == DEFAULT_ITEM) {
                holder = getHolder();  //普通条目的holder
            } else if(getInnerItemType(position) == MORE_ITEM){
                holder = getMoreHolder();
            }
        } else {
            if (getItemViewType(position) == DEFAULT_ITEM) {
                holder = (BaseHolder) convertView.getTag();
            } else {
                holder = getMoreHolder();
            }
        }

        if (getInnerItemType(position) == DEFAULT_ITEM) {
            T info = datas.get(position);
            holder.setData(info);
        }

        return holder.getContentView();*/
        BaseHolder holder = null;

        switch (getItemViewType(position)) {  // 判断当前条目时什么类型
            case MORE_ITEM:
                if (convertView == null) {
                    holder = getMoreHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                break;
            case DEFAULT_ITEM:
                if (convertView == null) {
                    holder = getHolder();
                } else {
                    holder = (BaseHolder) convertView.getTag();
                }
                if (position < datas.size()) {
                    holder.setData(datas.get(position));
                }
                break;
        }
        return holder.getContentView();  //  如果当前Holder 恰好是MoreHolder  证明MoreHOlder已经显示
    }

    private MoreHolder moreHolder;

    private BaseHolder getMoreHolder() {
        if (moreHolder != null) {
            return moreHolder;
        } else {
            moreHolder = new MoreHolder(this);
        }
        return moreHolder;
    }

    public abstract BaseHolder<T> getHolder();

    /**
     * 当加载更多条目显示的时候调用这个方法
     */
    public void loadMore() {
        ThreadManager.getInstance().createLongPool().execute(new Runnable() {
            @Override
            public void run() {
                //在子线程中加载更多
                final List<T> newData = onLoad();

                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newData == null) {
                            //// TODO: 2016/5/11 连接服务器失败
                            moreHolder.setData(MoreHolder.LOAD_ERROR);

                        } else if (newData.size() == 0) {
                            //// TODO: 2016/5/11 服务器没有更多数据
                            moreHolder.setData(MoreHolder.HAS_NO_MORE);

                        } else {
                            //成功了   给listview添加新的数据
                            moreHolder.setData(MoreHolder.HAS_MORE);
                            datas.addAll(newData);
                            //在主线程中刷新
                            notifyDataSetChanged();

                        }
                    }
                });

            }
        });
    }

    /**
     * 加载数据，让子类去加载
     */
    protected abstract List<T> onLoad();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       // Toast.makeText(UIUtils.getContext(), "position" + position, Toast.LENGTH_SHORT).show();
        position = position - lv.getHeaderViewsCount();  //获取到顶部条目的数量，修正position
        onInnerItemClick(position);
    }

    /**
     * 处理条目的点击事件
     * @param position
     */
    public void onInnerItemClick(int position) {

    }
}
