package org.chzz.demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import org.chzz.demo.R;
import org.chzz.demo.ui.fragment.ContentFragment;
import org.chzz.widget.CHZZFixedIndicator;

import butterknife.Bind;
import butterknife.ButterKnife;


public class IndicatorActivity extends BaseActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @Bind(R.id.indicator1)
     CHZZFixedIndicator mIndicator1;
    @Bind(R.id.pager1)
     ViewPager mPager1;

    @Bind(R.id.indicator2)
     CHZZFixedIndicator mIndicator2;
    @Bind(R.id.pager2)
     ViewPager mPager2;

    @Bind(R.id.indicator3)
     CHZZFixedIndicator mIndicator3;
    @Bind(R.id.pager3)
     ViewPager mPager3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicator);
        ButterKnife.bind(this);
        String[] titles1 = new String[3];
        for (int i = 0; i < titles1.length; i++) {
            titles1[i] = "标签" + i;
        }
        mPager1.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), titles1));
        mIndicator1.initData(0, mPager1);

        String[] titles2 = new String[5];
        for (int i = 0; i < titles2.length; i++) {
            titles2[i] = "标签" + i;
        }
        mPager2.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), titles2));
        mIndicator2.initData(1, mPager2);

        String[] titles3 = new String[5];
        for (int i = 0; i < titles3.length; i++) {
            titles3[i] = "标签" + i;
        }
        mPager3.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), titles2));
        mIndicator3.initData(1, mPager3);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private String[] mTitles;

        public MyPagerAdapter(FragmentManager fm, String[] titles) {
            super(fm);
            mTitles = titles;
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
            return ContentFragment.newInstance(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}