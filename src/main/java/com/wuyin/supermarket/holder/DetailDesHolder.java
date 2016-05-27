package com.wuyin.supermarket.holder;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.wuyin.supermarket.R;
import com.wuyin.supermarket.holder.base.BaseHolder;
import com.wuyin.supermarket.model.AppInfo;
import com.wuyin.supermarket.utils.UIUtils;

/**
 * Created by yinlong on 2016/5/11.
 */
public class DetailDesHolder extends BaseHolder<AppInfo> implements View.OnClickListener {


    private TextView des_content;
    private TextView des_author;
    private ImageView des_arrow;
    private RelativeLayout des_layout;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.detail_des);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        des_content = (TextView) view.findViewById(R.id.des_content);
        des_author = (TextView) view.findViewById(R.id.des_author);
        des_arrow = (ImageView) view.findViewById(R.id.des_arrow);
        des_layout = (RelativeLayout) view.findViewById(R.id.des_layout);
    }

    @Override
    public void refreshData(AppInfo data) {
        des_content.setText(data.getDes());
        des_author.setText("作者:" + data.getAuthor());
        des_layout.setOnClickListener(this);

        //des_content 起始高度7行的高度
        ViewGroup.LayoutParams layoutParams = des_content.getLayoutParams();
        layoutParams.height = getShortMeasureHeight();
        des_content.setLayoutParams(layoutParams);
        des_arrow.setImageResource(R.mipmap.arrow_down);
    }

    /**
     * 获取7行的高度
     *
     * @return
     */
    public int getShortMeasureHeight() {
        // 复制一个新的TextView 用来测量,最好不要在之前的TextView测量 有可能影响其它代码执行
        TextView textView = new TextView(UIUtils.getContext());
        textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14);//设置字体大小14dp
        textView.setMaxLines(7);
        textView.setLines(7);// 强制有7行
        int width = des_content.getMeasuredWidth(); // 开始宽度

        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, width);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.AT_MOST, 1000);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }

    /**
     * 获取TextView 自己本身的高度
     *
     * @return
     */
    public int getLongMeasureHeight() {
        int width = des_content.getMeasuredWidth(); // 开始宽度
        des_content.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;// 高度包裹内容


        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.EXACTLY, width);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(MeasureSpec.AT_MOST, 1000);
        des_content.measure(widthMeasureSpec, heightMeasureSpec);//
        return des_content.getMeasuredHeight();
    }

    boolean flag;// true展开了 false 没有展开

    @Override
    public void onClick(View v) {
        expand();
    }

    ScrollView scrollView;
//	scrollView.scrollTo(0, scrollView.getMeasuredHeight())

    /**
     * 获取到界面的ScollView
     */
    public ScrollView getScrollView(View view) {
        ViewParent parent = view.getParent();
        if (parent instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) parent;
            if (group instanceof ScrollView) {
                return (ScrollView) group;
            } else {
                return getScrollView(group);
            }

        } else {
            return null;
        }

    }

    private void expand() {
        scrollView = getScrollView(des_layout);
        int startHeight;
        int targetHeight;
        if (!flag) {
            flag = true;
            startHeight = getShortMeasureHeight();
            targetHeight = getLongMeasureHeight();
        } else {
            flag = false;
            startHeight = getLongMeasureHeight();
            targetHeight = getShortMeasureHeight();
        }
        final ViewGroup.LayoutParams layoutParams = des_content.getLayoutParams();
        ValueAnimator animator = ValueAnimator.ofInt(startHeight, targetHeight);
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (Integer) animation.getAnimatedValue();
                layoutParams.height = value;
                des_content.setLayoutParams(layoutParams);
                scrollView.scrollTo(0, scrollView.getMeasuredHeight());// 让scrollView 移动到最下面
            }
        });
        animator.addListener(new AnimatorListener() {  // 监听动画执行
            //当动画开始执行的时候调用
            @Override
            public void onAnimationStart(Animator arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationRepeat(Animator arg0) {

            }

            @Override
            public void onAnimationEnd(Animator arg0) {
                if (flag) {
                    des_arrow.setImageResource(R.mipmap.arrow_down);
                } else {
                    des_arrow.setImageResource(R.mipmap.arrow_up);
                }
            }

            @Override
            public void onAnimationCancel(Animator arg0) {

            }
        });
        animator.setDuration(500);//设置动画持续时间
        animator.start();
    }
}
