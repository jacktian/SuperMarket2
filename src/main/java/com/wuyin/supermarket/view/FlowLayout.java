package com.wuyin.supermarket.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wuyin.supermarket.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuyin on 2016/5/28.
 */
public class FlowLayout extends ViewGroup {
    int padding = UIUtils.dip2px(13);
    private int horizontolSpacing = padding;
    private int verticalSpacing = padding;

    List<Line> mLines = new ArrayList<>();
    private int width;
    private int height;

    public FlowLayout(Context context) {
        super(context);
    }

    /**
     * 分配每个孩子的位置
     *
     * @param changed 改变的view
     * @param l       左边
     * @param t       上边
     * @param r       右边
     * @param b       下边
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        l+=getPaddingLeft();
        t+=getPaddingTop();
        for (int i = 0; i < mLines.size(); i++) {
            Line line = mLines.get(i);
            line.layout(l, t);   //交给每一行去分配
            t += line.getHeight() + verticalSpacing;
        }
    }

    /**
     * 新的一行
     */
    public void newLine() {
        mLines.add(currentLine);  //记录之前的行
        currentLine = new Line();  //创建新的一行
        useWidth = 0;
    }

    private Line currentLine;  //当前行
    private int useWidth; //当前行使用的宽度

    class Line {

        int lineWidth = 0;
        int height = 0;//当前行的高度

        private List<View> children = new ArrayList<>();

        public void addChild(View child) {
            children.add(child);
            if (child.getMeasuredHeight() > height) {
                height = child.getMeasuredHeight();
            }
            lineWidth += child.getMeasuredWidth();

        }

        public int getChildCount() {
            return children.size();
        }

        public int getHeight() {

            return height;
        }

        public void layout(int l, int t) {
            lineWidth += horizontolSpacing * (children.size() - 1);
            int surplusChild = 0;
            int surplus = width - lineWidth;
            if (surplus > 0) {
                if (!(children.size() == 0)) {
                    surplusChild = surplus / children.size();
                }
            }
            for (int i = 0; i < children.size(); i++) {
                View child = children.get(i);
                //  getMeasuredWidth()   控件实际的大小
                // getWidth()  控件显示的大小
                child.layout(l, t, l + child.getMeasuredWidth() + surplusChild, t + child.getMeasuredHeight());
                l += child.getMeasuredWidth() + surplusChild;
                l += horizontolSpacing;
            }
        }
    }

    /**
     * 测量当前控件FlowLayout
     * 父类有义务测量每个孩子的
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        mLines.clear();
        currentLine = null;
        useWidth = 0;

        //获取模式
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //获取宽和高
        width = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        height = MeasureSpec.getSize(heightMeasureSpec) - getPaddingBottom() - getPaddingTop();

        //为了测量每个孩子，需要指定每个孩子的测量规则
        int childWidthMode;
        int childHeightMode;
        childWidthMode = widthMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : widthMode;
        childHeightMode = heightMode == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : heightMode;

        int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthMode, width);
        int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightMode, height);

        currentLine = new Line();
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            //测量每个孩子的宽和高
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            int measureWidth = child.getMeasuredWidth();

            useWidth += measureWidth; //让当前行加上使用的长度
            if (useWidth <= width) {
                currentLine.addChild(child);  //这时候证明当前的孩子是可以放进到这一行里
                useWidth += horizontolSpacing;
                if (useWidth > width) {
                    //换行
                    newLine();
                }
            } else {
                //换行
                if (currentLine.getChildCount() < 1) {
                    currentLine.addChild(child);  //保证当前行里最少有一个孩子
                }
                newLine();
            }
        }

        if (!mLines.contains(currentLine)) {
            mLines.add(currentLine);
        }

        int totalHeight = 0;
        for (Line line : mLines) {
            totalHeight += line.getHeight();
        }
        totalHeight += verticalSpacing * (mLines.size() - 1) +getPaddingTop()+getPaddingBottom();

        setMeasuredDimension(width+getPaddingRight()+getPaddingLeft(), resolveSize(totalHeight, heightMeasureSpec));
    }
}
