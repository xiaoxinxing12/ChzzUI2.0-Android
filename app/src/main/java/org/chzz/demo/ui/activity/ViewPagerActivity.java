package org.chzz.demo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.chzz.banner.CHZZBanner;
import org.chzz.demo.App;
import org.chzz.demo.R;
import org.chzz.demo.model.BannerModel;
import org.chzz.demo.ui.fragment.StickyNavListViewFragment;
import org.chzz.demo.ui.fragment.StickyNavRecyclerViewFragment;
import org.chzz.demo.ui.fragment.StickyNavScrollViewFragment;
import org.chzz.demo.ui.fragment.StickyNavWebViewFragment;
import org.chzz.refresh.CHZZNormalRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import cn.bingoogolapple.bgaindicator.BGAFixedIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewPagerActivity extends BaseActivity implements CHZZRefreshLayout.CHZZRefreshLayoutDelegate {
    private CHZZRefreshLayout mRefreshLayout;
    private CHZZBanner mBanner;
    private BGAFixedIndicator mIndicator;
    private ViewPager mContentVp;

    private Fragment[] mFragments;
    private String[] mTitles;
    private StickyNavRecyclerViewFragment mRecyclerViewFragment;
    private StickyNavListViewFragment mListViewFragment;
    private StickyNavScrollViewFragment mScrollViewFragment;
    private StickyNavWebViewFragment mWebViewFragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_viewpager);
        mRefreshLayout = getViewById(R.id.refreshLayout);
        mBanner = getViewById(R.id.banner);
        mIndicator = getViewById(R.id.indicator);
        mContentVp = getViewById(R.id.vp_viewpager_content);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mRefreshLayout.setRefreshViewHolder(new CHZZNormalRefreshViewHolder(mApp, true));

        initBanner();

        mFragments = new Fragment[4];
        mFragments[0] = mRecyclerViewFragment = new StickyNavRecyclerViewFragment();
        mFragments[1] = mListViewFragment = new StickyNavListViewFragment();
        mFragments[2] = mScrollViewFragment = new StickyNavScrollViewFragment();
        mFragments[3] = mWebViewFragment = new StickyNavWebViewFragment();

        mTitles = new String[4];
        mTitles[0] = "RecyclerView";
        mTitles[1] = "ListView";
        mTitles[2] = "ScrollView";
        mTitles[3] = "WebView";
        mContentVp.setAdapter(new ContentViewPagerAdapter(getSupportFragmentManager()));
        mIndicator.initData(0, mContentVp);
    }

    private void initBanner() {
        mBanner.setAdapter(new CHZZBanner.Adapter() {
            @Override
            public void fillBannerItem(CHZZBanner banner, View view, Object model, int position) {
                Glide.with(banner.getContext()).load(model).placeholder(R.mipmap.holder).error(R.mipmap.holder).dontAnimate().thumbnail(0.1f).into((ImageView) view);
            }
        });

        App.getInstance().getEngine().getBannerModel().enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                mBanner.setData(R.layout.view_image, bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
            }
        });
    }

    @Override
    public void onCHZZRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        switch (mContentVp.getCurrentItem()) {
            case 0:
                mRecyclerViewFragment.onCHZZRefreshLayoutBeginRefreshing(refreshLayout);
                break;
            case 1:
                mListViewFragment.onCHZZRefreshLayoutBeginRefreshing(refreshLayout);
                break;
            case 2:
                mScrollViewFragment.onCHZZRefreshLayoutBeginRefreshing(refreshLayout);
                break;
            case 3:
                mWebViewFragment.onCHZZRefreshLayoutBeginRefreshing(refreshLayout);
                break;
        }
    }

    @Override
    public boolean onCHZZRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        switch (mContentVp.getCurrentItem()) {
            case 0:
                return mRecyclerViewFragment.onCHZZRefreshLayoutBeginLoadingMore(refreshLayout);
            case 1:
                return mListViewFragment.onCHZZRefreshLayoutBeginLoadingMore(refreshLayout);
            case 2:
                return mScrollViewFragment.onCHZZRefreshLayoutBeginLoadingMore(refreshLayout);
            case 3:
                return mWebViewFragment.onCHZZRefreshLayoutBeginLoadingMore(refreshLayout);
            default:
                return false;
        }
    }

    public void endRefreshing() {
        mRefreshLayout.endRefreshing();
    }

    public void endLoadingMore() {
        mRefreshLayout.endLoadingMore();
    }

    class ContentViewPagerAdapter extends FragmentPagerAdapter {

        public ContentViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
    }

}