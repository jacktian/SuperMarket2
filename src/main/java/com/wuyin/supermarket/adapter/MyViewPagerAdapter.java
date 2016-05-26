package com.wuyin.supermarket.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wuyin.supermarket.fragment.AppFragment;
import com.wuyin.supermarket.fragment.CategoryFragment;
import com.wuyin.supermarket.fragment.GameFragment;
import com.wuyin.supermarket.fragment.HomeFragment;
import com.wuyin.supermarket.fragment.SubjectFragment;
import com.wuyin.supermarket.fragment.TopFragment;
import com.wuyin.supermarket.fragment.factory.FragmentFactory;

/**
 * Created by wuyin on 2016/5/2.
 */
public class MyViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] mTitles;

    public MyViewPagerAdapter(FragmentManager fm, String[] titles) {
        super(fm);
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        //通过Fragment工厂来生成fragment
       /* switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SubjectFragment();
            case 2:
                return new GameFragment();
            case 3:
                return new SubjectFragment();
            case 4:
                return new CategoryFragment();
            case 5:
                return new TopFragment();
        }*/
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
