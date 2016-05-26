package com.wuyin.supermarket.fragment.factory;

import android.support.v4.app.Fragment;

import com.wuyin.supermarket.fragment.AppFragment;
import com.wuyin.supermarket.fragment.CategoryFragment;
import com.wuyin.supermarket.fragment.GameFragment;
import com.wuyin.supermarket.fragment.HomeFragment;
import com.wuyin.supermarket.fragment.SubjectFragment;
import com.wuyin.supermarket.fragment.TopFragment;
import com.wuyin.supermarket.fragment.base.BaseFragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyin on 2016/5/2.
 */
public class FragmentFactory {

    private static Map<Integer,BaseFragment> mFragmentMaps = new HashMap<>();

    public static BaseFragment createFragment(int position){
        BaseFragment fragment = null;
        //重新取出来fragment
        fragment = mFragmentMaps.get(position);
        if (fragment == null) {  //如果在集合中没有

            switch (position) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new CategoryFragment();
                    break;
                case 5:
                    fragment = new TopFragment();
                    break;
            }
            if (fragment != null) {
                //创建好的fragment存放到集合中缓存起来
                mFragmentMaps.put(position, fragment);
            }
        }
        return fragment;
    }
}
