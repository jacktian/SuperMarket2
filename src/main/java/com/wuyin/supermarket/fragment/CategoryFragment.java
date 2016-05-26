package com.wuyin.supermarket.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.view.LoadingPage;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseFragment {


    @Override
    public LoadingPage.LoadResult load() {
        return LoadingPage.LoadResult.error;
    }

    @Override
    public View createSuccessView() {
        return null;
    }
}
