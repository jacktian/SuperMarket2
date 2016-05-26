package com.wuyin.supermarket.fragment.base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.utils.ViewUtils;
import com.wuyin.supermarket.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {

    private LoadingPage mFrameLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // View view = inflater.inflate(R.layout.fragment_home, container, false);
        //framelayout不为空的时候在去new
        if (mFrameLayout == null) {
            mFrameLayout = new LoadingPage(UIUtils.getContext()) {
                @Override
                public LoadResult load() {
                    return BaseFragment.this.load();
                }

                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }
            };
            // init();  //在FrameLayout中添加四种不同的布局
        } else {
            //先干掉之前的parent
            ViewUtils.remoteParent(mFrameLayout);

        }
        // showPage();//根据不同的状态显示不同的界面
        //show();//根据服务器的数据切换状态

        return mFrameLayout;
    }

    /**
     * 校验数据
     * @param datas
     * @return
     */
    public  LoadingPage.LoadResult checkLoad(List datas) {
        if (datas == null){
            return LoadingPage.LoadResult.error;  //解析失敗
        } else if (datas.size() == 0){
            return LoadingPage.LoadResult.empty;
        } else {
            return LoadingPage.LoadResult.success;
        }
    }


    /**
     * 请求服务器
     *
     * @return
     */
    public abstract LoadingPage.LoadResult load();

    /**
     * 创建成功的界面
     *
     * @return
     */
    public abstract View createSuccessView();


    public void show() {
        if (mFrameLayout != null) {
            mFrameLayout.show();
        }
    }


}
