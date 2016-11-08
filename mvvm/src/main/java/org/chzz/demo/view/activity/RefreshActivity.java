package org.chzz.demo.view.activity;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.adapter.CHZZOnRVItemClickListener;
import org.chzz.demo.R;
import org.chzz.demo.adapter.NormalRecyclerViewAdapter;
import org.chzz.demo.databinding.ActivityRefreshBinding;
import org.chzz.demo.databinding.ItemFootBinding;
import org.chzz.demo.databinding.ItemRepoBinding;
import org.chzz.demo.model.RefreshModel;
import org.chzz.demo.util.ThreadUtil;
import org.chzz.demo.util.ToastUtil;
import org.chzz.demo.viewmodel.RefreshViewModel;
import org.chzz.refresh.CHZZMoocStyleRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import java.util.HashMap;
import java.util.List;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/11/2
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/11/2--15:01
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/

public class RefreshActivity extends BaseActivity implements CHZZRefreshLayout.CHZZRefreshLayoutDelegate, RefreshViewModel.DataListener, CHZZOnRVItemClickListener, NormalRecyclerViewAdapter.IFillDataListener {
    NormalRecyclerViewAdapter adapter;
    private ActivityRefreshBinding binding;
    private RefreshViewModel model;

    @Override
    protected void initView(Bundle savedInstanceState) {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_refresh);
        model = new RefreshViewModel(this, this);
        binding.setViewModel(model);

    }

    @Override
    protected void setListener() {
        View head = View.inflate(this, R.layout.item_head, null);
        ItemFootBinding bind = DataBindingUtil.bind(View.inflate(this, R.layout.item_foot, null));
        adapter = new NormalRecyclerViewAdapter(binding.rvRecyclerviewData, R.layout.item_repo, head, bind.foot, this);
        //  adapter.addHeadView(head);
        binding.rlRecyclerview.setDelegate(this);
        CHZZMoocStyleRefreshViewHolder leftRefreshViewHolder = new CHZZMoocStyleRefreshViewHolder(mApp, true);
        leftRefreshViewHolder.setSpringDistanceScale(2);
        //刷新图标
        leftRefreshViewHolder.setOriginalImage(R.mipmap.ic_launcher);
        //刷新头背景色
        leftRefreshViewHolder.setUltimateColor(R.color.white);
        binding.rlRecyclerview.setRefreshViewHolder(leftRefreshViewHolder);
        binding.rvRecyclerviewData.setLayoutManager(new GridLayoutManager(mApp, 1, GridLayoutManager.VERTICAL, false));
        binding.rvRecyclerviewData.setAdapter(adapter);
        adapter.setOnRVItemClickListener(this);
        model.onUserVisible(new HashMap<>());
        bind.tvFoot.setText("Im  foot");
        bind.setViewModel(model);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onCHZZRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {

    }

    @Override
    public boolean onCHZZRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        if (1 <= 10) {
            ThreadUtil.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    model.loadData(null);
                }
            }, 2000);
        } else {
            if (5 > 1)
                ToastUtil.show("暂无更多数据");
            return false;
        }
        return true;
    }

    @Override
    public void onRepositoriesChanged(List<RefreshModel> refreshModels) {
        binding.rlRecyclerview.endRefreshing();
        binding.rlRecyclerview.endLoadingMore();
        if (adapter.getData().size() == 0) {
            adapter.setData(refreshModels);
        } else
            adapter.addMoreData(refreshModels);

    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        ToastUtil.show("item");
    }

    @Override
    public void setBingItem(ViewDataBinding binding) {

        ItemRepoBinding b = (ItemRepoBinding) binding;
        adapter.setItem(b.cardView);
    }
}
