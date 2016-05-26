package com.wuyin.supermarket.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.adapter.DefaultAdapter;
import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.SubjectInfo;
import com.wuyin.supermarket.uri.Constants;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.view.LoadingPage;
import com.wuyin.supermarket.httpresult.SubjectHttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectFragment extends BaseFragment {

    List<SubjectInfo> subjectInfos = new ArrayList<>();

    @Override
    public LoadingPage.LoadResult load() {

        SubjectHttpRequest httpRequest = new SubjectHttpRequest();
        subjectInfos = httpRequest.load(0);

        return checkLoad(subjectInfos);
    }

    @Override
    public View createSuccessView() {

        ListView listView = new ListView(UIUtils.getContext());
        listView.setAdapter(new MyAdapter(subjectInfos,listView));

        return listView;
    }

    class ViewHolder extends BaseHolder<SubjectInfo> {
        ImageView item_img;
        TextView item_title;

        @Override
        public View initView() {
            View contentView = UIUtils.inflate(R.layout.subject_item);
            this.item_img = (ImageView) contentView.findViewById(R.id.item_img);
            this.item_title = (TextView) contentView.findViewById(R.id.item_title);
            return contentView;
        }

        @Override
        public void refreshData(SubjectInfo data) {
            this.item_title.setText(data.getDes());
            Glide.with(UIUtils.getContext()).load(Constants.IMAGE_URL + data.getUrl())
                    .error(R.mipmap.ic_default)
                    .into(this.item_img);
        }
    }

    class MyAdapter extends DefaultAdapter<SubjectInfo> {


        public MyAdapter(List<SubjectInfo> datas,ListView lv) {
            super(datas,lv);
        }


        @Override
        public BaseHolder<SubjectInfo> getHolder() {
            return new ViewHolder();
        }

        @Override
        protected List<SubjectInfo> onLoad() {
            SubjectHttpRequest request = new SubjectHttpRequest();
            List<SubjectInfo> load = request.load(subjectInfos.size());
            subjectInfos.addAll(load);
            return load;
        }
    }


}
