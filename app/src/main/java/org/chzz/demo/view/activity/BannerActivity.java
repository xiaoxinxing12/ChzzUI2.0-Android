package org.chzz.demo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.chzz.banner.CHZZBanner;
import org.chzz.banner.transformer.TransitionEffect;
import org.chzz.demo.App;
import org.chzz.demo.R;
import org.chzz.demo.engine.Engine;
import org.chzz.demo.model.BannerModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BannerActivity extends Activity implements CHZZBanner.OnItemClickListener, CHZZBanner.Adapter {
    private CHZZBanner mDefaultBanner;
    private CHZZBanner mCubeBanner;
    private CHZZBanner mAccordionBanner;
    private CHZZBanner mFlipBanner;
    private CHZZBanner mRotateBanner;
    private CHZZBanner mAlphaBanner;
    private CHZZBanner mZoomFadeBanner;
    private CHZZBanner mFadeBanner;
    private CHZZBanner mZoomCenterBanner;
    private CHZZBanner mZoomBanner;
    private CHZZBanner mStackBanner;
    private CHZZBanner mZoomStackBanner;
    private CHZZBanner mDepthBanner;

    private Engine mEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        mEngine = App.getInstance().getEngine();

        initView();
        setListener();
        loadData();
    }

    private void initView() {
        mDefaultBanner = (CHZZBanner) findViewById(R.id.banner_main_default);
        mCubeBanner = (CHZZBanner) findViewById(R.id.banner_main_cube);
        mAccordionBanner = (CHZZBanner) findViewById(R.id.banner_main_accordion);
        mFlipBanner = (CHZZBanner) findViewById(R.id.banner_main_flip);
        mRotateBanner = (CHZZBanner) findViewById(R.id.banner_main_rotate);
        mAlphaBanner = (CHZZBanner) findViewById(R.id.banner_main_alpha);
        mZoomFadeBanner = (CHZZBanner) findViewById(R.id.banner_main_zoomFade);
        mFadeBanner = (CHZZBanner) findViewById(R.id.banner_main_fade);
        mZoomCenterBanner = (CHZZBanner) findViewById(R.id.banner_main_zoomCenter);
        mZoomBanner = (CHZZBanner) findViewById(R.id.banner_main_zoom);
        mStackBanner = (CHZZBanner) findViewById(R.id.banner_main_stack);
        mZoomStackBanner = (CHZZBanner) findViewById(R.id.banner_main_zoomStack);
        mDepthBanner = (CHZZBanner) findViewById(R.id.banner_main_depth);
    }

    private void setListener() {
        mDefaultBanner.setOnItemClickListener(this);
        mCubeBanner.setOnItemClickListener(this);
    }

    private void loadData() {
        loadData(mDefaultBanner, 1);
        loadData(mCubeBanner, 2);
        loadData(mAccordionBanner, 3);
        loadData(mFlipBanner, 4);
        loadData(mRotateBanner, 5);
        loadData(mAlphaBanner, 6);
        loadData(mZoomFadeBanner, 3);
        loadData(mFadeBanner, 4);
        loadData(mZoomCenterBanner, 5);
        loadData(mZoomBanner, 6);
        loadData(mStackBanner, 3);
        loadData(mZoomStackBanner, 4);
        loadData(mDepthBanner, 5);
    }

    private void loadData(final CHZZBanner banner, int count) {
        mEngine.fetchItemsWithItemCount(count).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();

                banner.setAdapter(BannerActivity.this);
                banner.setData(bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "网络数据加载失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBannerItemClick(CHZZBanner banner, View view, Object model, int position) {
        Toast.makeText(App.getInstance(), "点击了第" + (position + 1) + "页", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void fillBannerItem(CHZZBanner banner, View view, Object model, int position) {
        Glide.with(BannerActivity.this)
                .load(model)
                .placeholder(R.mipmap.holder)
                .error(R.mipmap.holder)
                .into((ImageView) view);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_select_page_one:
                mDefaultBanner.setCurrentItem(0);
                break;
            case R.id.tv_main_select_page_two:
                mDefaultBanner.setCurrentItem(1);
                break;
            case R.id.tv_main_select_page_three:
                mDefaultBanner.setCurrentItem(2);
                break;
            case R.id.tv_main_select_page_four:
                mDefaultBanner.setCurrentItem(3);
                break;
            case R.id.tv_main_select_page_five:
                mDefaultBanner.setCurrentItem(4);
                break;
            case R.id.tv_main_get_item_count:
                Toast.makeText(App.getInstance(), "广告条总页数为 " + mDefaultBanner.getItemCount(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_main_get_current_item:
                Toast.makeText(App.getInstance(), "广告当前索引位置为 " + mDefaultBanner.getCurrentItem(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_main_load_one_item:
                loadData(mDefaultBanner, 1);
                break;
            case R.id.tv_main_load_two_item:
                loadData(mDefaultBanner, 2);
                break;
            case R.id.tv_main_load_three_item:
                loadData(mDefaultBanner, 3);
                break;
            case R.id.tv_main_load_five_item:
                loadData(mDefaultBanner, 5);
                break;
            case R.id.tv_main_cube:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Cube);
                break;
            case R.id.tv_main_depth:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Depth);
                break;
            case R.id.tv_main_flip:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Flip);
                break;
            case R.id.tv_main_rotate:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Rotate);
                break;
            case R.id.tv_main_alpha:
                mDefaultBanner.setTransitionEffect(TransitionEffect.Alpha);
                break;
            case R.id.tv_main_listview_demo:
                startActivity(new Intent(this, ListViewDemoActivity.class));
                break;
            default:
                break;
        }
    }
}
