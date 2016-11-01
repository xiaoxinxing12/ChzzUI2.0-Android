package org.chzz.demo.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import org.chzz.demo.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ContentFragment extends BaseFragment {
    @Bind(R.id.tv_content_tag)
    TextView mTagTv;
    public int mPosition;

    public static ContentFragment newInstance(int position) {
        ContentFragment contentFragment = new ContentFragment();
        contentFragment.mPosition = position;
        return contentFragment;
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_content);
        ButterKnife.bind(this, mContentView);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mTagTv.setText("第" + mPosition + "页");
    }
}
