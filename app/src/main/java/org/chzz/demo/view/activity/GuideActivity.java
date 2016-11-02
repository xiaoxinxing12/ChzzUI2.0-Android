package org.chzz.demo.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.chzz.banner.CHZZBanner;
import org.chzz.banner.CHZZBannerUtil;
import org.chzz.demo.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GuideActivity extends Activity implements View.OnClickListener {
    private static final String TAG = GuideActivity.class.getSimpleName();
    private TextView mSkipTv;
    private Button mEnterBtn;
    private CHZZBanner mBackgroundBanner;
    private CHZZBanner mForegroundBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
        setListener();
        processLogic();
    }

    private void initView() {
        setContentView(R.layout.activity_guide);
        mSkipTv = (TextView) findViewById(R.id.tv_guide_skip);
        mEnterBtn = (Button) findViewById(R.id.btn_guide_enter);
        mBackgroundBanner = (CHZZBanner) findViewById(R.id.banner_guide_background);
        mForegroundBanner = (CHZZBanner) findViewById(R.id.banner_guide_foreground);
    }

    private void setListener() {
        mSkipTv.setOnClickListener(this);
        mEnterBtn.setOnClickListener(this);

        // 监听页码切换事件，控制「跳过按钮」和「进入主界面的显示与隐藏」
        mForegroundBanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == mForegroundBanner.getItemCount() - 2) {
                    ViewCompat.setAlpha(mEnterBtn, positionOffset);
                    ViewCompat.setAlpha(mSkipTv, 1.0f - positionOffset);

                    if (positionOffset > 0.5f) {
                        mEnterBtn.setVisibility(View.VISIBLE);
                        mSkipTv.setVisibility(View.GONE);
                    } else {
                        mEnterBtn.setVisibility(View.GONE);
                        mSkipTv.setVisibility(View.VISIBLE);
                    }
                } else if (position == mForegroundBanner.getItemCount() - 1) {
                    mSkipTv.setVisibility(View.GONE);
                    mEnterBtn.setVisibility(View.VISIBLE);
                    ViewCompat.setAlpha(mEnterBtn, 1.0f);
                } else {
                    mSkipTv.setVisibility(View.VISIBLE);
                    ViewCompat.setAlpha(mSkipTv, 1.0f);
                    mEnterBtn.setVisibility(View.GONE);
                }
            }
        });
    }

    private void processLogic() {
        mBackgroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mForegroundBanner.setOverScrollMode(View.OVER_SCROLL_NEVER);

        // 初始化方式1：通过传入数据模型并结合Adapter的方式初始化
        mBackgroundBanner.setAdapter(new CHZZBanner.Adapter() {
            @Override
            public void fillBannerItem(CHZZBanner banner, View view, Object model, int position) {
                ((ImageView) view).setImageResource((int) model);
            }
        });
        mBackgroundBanner.setData(Arrays.asList(R.mipmap.uoko_guide_background_1, R.mipmap.uoko_guide_background_2, R.mipmap.uoko_guide_background_3), null);


        // 初始化方式2：通过直接传入视图集合的方式初始化
        List<View> views = new ArrayList<>();
        views.add(CHZZBannerUtil.getItemImageView(this, R.mipmap.uoko_guide_foreground_1));
        views.add(CHZZBannerUtil.getItemImageView(this, R.mipmap.uoko_guide_foreground_2));
        views.add(CHZZBannerUtil.getItemImageView(this, R.mipmap.uoko_guide_foreground_3));
        mForegroundBanner.setData(views);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_guide_enter || view.getId() == R.id.tv_guide_skip) {
            startActivity(new Intent(GuideActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 在界面可见时给背景Banner设置一个白色背景，避免滑动过程中两个Banner都设置透明度后能看到Launcher
        mBackgroundBanner.setBackgroundResource(android.R.color.white);
    }
}