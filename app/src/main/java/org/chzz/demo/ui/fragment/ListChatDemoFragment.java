package org.chzz.demo.ui.fragment;

import android.os.Bundle;
import android.widget.ListView;

import org.chzz.demo.R;
import org.chzz.demo.adapter.ListChatAdapter;
import org.chzz.demo.engine.DataEngine;
import org.chzz.demo.model.ChatModel;

import java.util.List;


/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/22 10:06
 * 描述:
 */
public class ListChatDemoFragment extends BaseFragment {
    private static final String TAG = ListChatDemoFragment.class.getSimpleName();
    private List<ChatModel> mData;
    private ListView mDataLv;
    private ListChatAdapter mAdapter;

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_listview);
        mDataLv = getViewById(R.id.lv_listview_data);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mDataLv.setSelector(android.R.color.transparent);
        mDataLv.setDivider(null);
        mAdapter = new ListChatAdapter(mActivity);
        mData = DataEngine.loadChatModelData();
        mAdapter.setData(mData);
        mDataLv.setAdapter(mAdapter);
    }

}