package org.chzz.demo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import org.chzz.demo.R;
import org.chzz.demo.ui.fragment.GridViewDemoFragment;
import org.chzz.demo.ui.fragment.ListChatDemoFragment;
import org.chzz.demo.ui.fragment.ListIndexViewDemoFragment;
import org.chzz.demo.ui.fragment.ListViewDemoFragment;
import org.chzz.demo.ui.fragment.RecyclerChatDemoFragment;
import org.chzz.demo.ui.fragment.RecyclerIndexDemoFragment;
import org.chzz.demo.ui.fragment.RecyclerViewDemoFragment;
import org.chzz.demo.util.SnackbarUtil;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/28 10:23
 * 描述:
 */
public class AdapterActivity extends BaseActivity {
    private Class[] mFragmentClasses = new Class[]{GridViewDemoFragment.class, ListViewDemoFragment.class, RecyclerViewDemoFragment.class, ListChatDemoFragment.class, RecyclerChatDemoFragment.class, ListIndexViewDemoFragment.class, RecyclerIndexDemoFragment.class};
    private CoordinatorLayout mCoordinatorLayout;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_adapter);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setIcon(R.mipmap.logo);

        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        ContentPagerAdapter contentPagerAdapter = new ContentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(contentPagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setTabsFromPagerAdapter(contentPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    public void showSnackbar(String msg) {
        SnackbarUtil.show(mCoordinatorLayout, msg);
    }

    private class ContentPagerAdapter extends FragmentStatePagerAdapter {

        public ContentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return Fragment.instantiate(AdapterActivity.this, mFragmentClasses[position].getName());
        }

        @Override
        public int getCount() {
            return mFragmentClasses.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentClasses[position].getSimpleName().replace("Fragment", "");
        }
    }

}