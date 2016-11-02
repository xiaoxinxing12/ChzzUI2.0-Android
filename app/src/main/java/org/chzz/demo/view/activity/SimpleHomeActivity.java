package org.chzz.demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.chzz.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class SimpleHomeActivity extends BaseActivity {
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.tv_SlidingTab)
    TextView mSlidingTab;
    @Bind(R.id.tv_CommonTab)
    TextView mCommonTab;
    @Bind(R.id.tv_SegmentTab)
    TextView mSegmentTab;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tablayout);
        ButterKnife.bind(this);
        tvTitle.setText("TabLayout");
    }

    @Override
    protected void setListener() {
        mSlidingTab.setOnClickListener(this);
        mCommonTab.setOnClickListener(this);
        mSegmentTab.setOnClickListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_SlidingTab:
                intent = new Intent(this, SlidingTabActivity.class);
                break;
            case R.id.tv_CommonTab:
                intent = new Intent(this, CommonTabActivity.class);
                break;
            case R.id.tv_SegmentTab:
                intent = new Intent(this, SegmentTabActivity.class);
                break;
        }
        if (intent == null)
            return;
        startActivity(intent);
    }
}
