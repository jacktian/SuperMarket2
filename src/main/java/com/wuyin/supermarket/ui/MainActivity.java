package com.wuyin.supermarket.ui;


import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.wuyin.supermarket.fragment.base.BaseFragment;
import com.wuyin.supermarket.fragment.factory.FragmentFactory;
import com.wuyin.supermarket.holder.MenuHolder;
import com.wuyin.supermarket.utils.UIUtils;
import com.wuyin.supermarket.adapter.MyViewPagerAdapter;
import com.wuyin.supermarket.R;
import com.wuyin.supermarket.ui.base.BaseActivity;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener, ActionBar.TabListener {


    private DrawerLayout mDrawerLayout;
    private ActionBar mActionBar;
    private ActionBarDrawerToggle mDrawerToggle;
    private PagerTabStrip mPagerTabStrip;
    private FrameLayout frameLayout;   //菜单的根布局


    private ViewPager mViewPager;
    private String[] mStrings;

    /* @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);



         //initViews();


         //addTab();

         //initViewPager();


         //drawerToggle();

     }
 */
    @Override
    protected void initView() {
        super.init();
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);
        //设置标签下划线的颜色
        mPagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));
        frameLayout = (FrameLayout) findViewById(R.id.fl_menu);
        //frameLayout.addView();
        MenuHolder menuHolder = new MenuHolder();
        frameLayout.addView(menuHolder.getContentView());
    }

    @Override
    protected void init() {
        super.init();
        mStrings = UIUtils.getStringArray(R.array.tab_names);

    }

    @Override
    protected void initActionBar() {
        super.initActionBar();

        mActionBar = getSupportActionBar();
        //显示原始的箭头的
        // mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);

         /*
         *	1）显示Navigation Drawer的 Activity 对象
			2） DrawerLayout 对象
			3）一个用来指示Navigation Drawer的 drawable资源
			4）一个用来描述打开Navigation Drawer的文本 (用于支持可访问性)。
			5）一个用来描述关闭Navigation Drawer的文本(用于支持可访问性).
		 */
        mDrawerToggle =
                new ActionBarDrawerToggle(this,
                        mDrawerLayout,
                        R.mipmap.ic_drawer_am,
                        R.string.open_drawer,
                        R.string.close_drawer) {

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        Toast.makeText(MainActivity.this, "抽屉关闭", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        Toast.makeText(MainActivity.this, "抽屉打开", Toast.LENGTH_SHORT).show();
                    }
                };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //让开关和actionBar建立关系
        mDrawerToggle.syncState();

        initViewPager();

        //sendBroadcast(new Intent("com.wuyin.supermarket.killAll"));
    }

    /**
     * 处理ViewPager的有关设置
     */
    private void initViewPager() {
        mViewPager.setAdapter(new MyViewPagerAdapter(getSupportFragmentManager(),mStrings));

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                //当翻ViewPager的时候给actionBar的位置切换
               // getSupportActionBar().setSelectedNavigationItem(position);

                //通过fragment的工厂类来获取当前显示的fragment
                BaseFragment fragment = FragmentFactory.createFragment(position);
                fragment.show();//当切换界面的时候，重新请求服务器
            }
        });
    }
/*

    */
/**
     * 初始化布局控件
     *//*

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.draw_layout);

        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);
        //设置标签下划线的颜色
        mPagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.indicatorcolor));
    }
*/

    private void drawerToggle() {
        /*
         *	1）显示Navigation Drawer的 Activity 对象
			2） DrawerLayout 对象
			3）一个用来指示Navigation Drawer的 drawable资源
			4）一个用来描述打开Navigation Drawer的文本 (用于支持可访问性)。
			5）一个用来描述关闭Navigation Drawer的文本(用于支持可访问性).
		 */
        mDrawerToggle =
                new ActionBarDrawerToggle(this,
                        mDrawerLayout,
                        R.mipmap.ic_drawer_am,
                        R.string.open_drawer,
                        R.string.close_drawer) {

                    @Override
                    public void onDrawerOpened(View drawerView) {
                        super.onDrawerOpened(drawerView);
                        Toast.makeText(MainActivity.this, "抽屉关闭", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onDrawerClosed(View drawerView) {
                        super.onDrawerClosed(drawerView);
                        Toast.makeText(MainActivity.this, "抽屉打开", Toast.LENGTH_SHORT).show();
                    }
                };

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        //让开关和actionBar建立关系
        mDrawerToggle.syncState();
    }

    /**
     * 添加tab标签
     */
    private void addTab() {
        mActionBar = getSupportActionBar();
        //显示原始的箭头的
        // mActionBar.setDisplayShowHomeEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeButtonEnabled(true);
        //mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
/*
        ActionBar.Tab tab1 = mActionBar.newTab().setText("标签一")
                .setTabListener(this);
        mActionBar.addTab(tab1);
        ActionBar.Tab tab2 = mActionBar.newTab().setText("标签二")
                .setTabListener(this);
        mActionBar.addTab(tab2);
        ActionBar.Tab tab3 = mActionBar.newTab().setText("标签三")
                .setTabListener(this);
        mActionBar.addTab(tab3);
        ActionBar.Tab tab4 = mActionBar.newTab().setText("标签四")
                .setTabListener(this);
        mActionBar.addTab(tab4);*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        if (Build.VERSION.SDK_INT > 11) {
            SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                    .getActionView();
            searchView.setOnQueryTextListener(this);
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                break;
           /* case R.id.action_settings:
                openSettings();
                break;*/
           /* case android.R.id.home:
                startActivity(new Intent(MainActivity.this,DetailActivity.class));
                break;*/
            default:
                break;
        }

        return mDrawerToggle.onOptionsItemSelected(item) | super.onOptionsItemSelected(item);
    }


    /**
     * 打开搜索方法
     */
    private void openSearch() {
        // Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
    }

    /**
     * 搜索text提交的时候调用的方法
     *
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        //Toast.makeText(this, query, Toast.LENGTH_SHORT).show();
        return true;
    }

    /**
     * 搜索框中字符变化的时候调用
     *
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        //Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();
        return true;
    }

    /**
     * 当Tab被选中的时候，ViewPager切换到指定的位置
     *
     * @param tab
     * @param ft
     */
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }


}
