package com.wuyin.supermarket.holder;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Encoder;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yinlong on 2016/5/11.
 */
public class HomePictureHolder extends BaseHolder<List<String>> {

    private ViewPager viewPager;
    //轮播数据
    List<String> datas = new ArrayList<>();

    //当创建的时候调用
    @Override
    public View initView() {
        viewPager = new ViewPager(UIUtils.getContext());
        //在这里指定具体的高度
        viewPager.setLayoutParams(new AbsListView.LayoutParams(
                AbsListView.LayoutParams.MATCH_PARENT, UIUtils
                .getDimens(R.dimen.home_picture_height)));
        return viewPager;
    }

    /**
     * 当设置data的时候调用
     *
     * @param data
     */
    @Override
    public void refreshData(List<String> data) {
        this.datas = data;
        viewPager.setAdapter(new HomeAdapter());
        //设置一个中间值
        viewPager.setCurrentItem(1000 * data.size());
        final AutoRunTask task = new AutoRunTask();
        /**
         * 监听viewpager按下滑动的事件
         */
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        task.stop();
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        task.start();
                        break;
                }
               /* if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    task.stop();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    task.start();
                }*/
                return false;   //不用传递true   如果是true，那么左右不能滑动
            }
        });

        task.start();    //任务开启
    }

    boolean flag;

    public class AutoRunTask implements Runnable {

        @Override
        public void run() {
            if (flag) {
                UIUtils.cancel(this);
                int currentItem = viewPager.getCurrentItem();
                currentItem++;
                viewPager.setCurrentItem(currentItem);
                UIUtils.postDelayed(this, 5000);   //递归调用
            }
        }

        /**
         * 开启的方法
         */
        public void start() {
            if (!flag) {
                UIUtils.cancel(this);
                flag = true;
                UIUtils.postDelayed(this, 5000);
            }
        }

        /**
         * 停止的方法
         */
        public void stop() {
            if (flag) {
                flag = false;
                UIUtils.cancel(this);
            }
        }
    }

    class HomeAdapter extends PagerAdapter {

        LinkedList<ImageView> convertView = new LinkedList<>();

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        /*判断当前显示的view对象*/
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            int index = position % datas.size();
            ImageView view = null;
            if (convertView.size() > 0) {
                view = convertView.remove(0);
            } else {
                view = new ImageView(UIUtils.getContext());
            }
            Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL + datas.get(index))
                    .error(R.mipmap.ic_default)
                    .into(view);
            //添加进去imageview
            container.addView(view);
            return view;   //返回的对象ing
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView view = (ImageView) object;
            convertView.add(view);// 把移除的对象 添加到缓存集合中
            container.removeView(view);
        }
    }
}
