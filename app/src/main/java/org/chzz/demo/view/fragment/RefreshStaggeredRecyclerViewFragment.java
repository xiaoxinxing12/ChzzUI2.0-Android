package org.chzz.demo.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.adapter.CHZZOnRVItemClickListener;
import org.chzz.adapter.CHZZOnRVItemLongClickListener;
import org.chzz.demo.R;
import org.chzz.demo.adapter.StaggeredRecyclerViewAdapter;
import org.chzz.demo.engine.DataEngine;
import org.chzz.demo.model.StaggeredModel;
import org.chzz.demo.view.activity.RefreshActivity;
import org.chzz.demo.util.ThreadUtil;
import org.chzz.refresh.CHZZNormalRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/22 10:06
 * 描述:
 */
public class RefreshStaggeredRecyclerViewFragment extends BaseFragment implements CHZZRefreshLayout.CHZZRefreshLayoutDelegate, CHZZOnRVItemClickListener, CHZZOnRVItemLongClickListener {
    private static final String TAG = RefreshStaggeredRecyclerViewFragment.class.getSimpleName();
    private StaggeredRecyclerViewAdapter mAdapter;
    private CHZZRefreshLayout mRefreshLayout;
    private RecyclerView mDataRv;
    private int mNewPageNumber = 0;
    private int mMorePageNumber = 0;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_recyclerview_refresh);
        mRefreshLayout = getViewById(R.id.rl_recyclerview_refresh);
        mDataRv = getViewById(R.id.rv_recyclerview_data);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);

        mAdapter = new StaggeredRecyclerViewAdapter(mDataRv);
        mAdapter.setOnRVItemClickListener(this);
        mAdapter.setOnRVItemLongClickListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mRefreshLayout.setCustomHeaderView(DataEngine.getCustomHeaderView(mApp), true);
        mRefreshLayout.setRefreshViewHolder(new CHZZNormalRefreshViewHolder(mApp, true));

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        mDataRv.setLayoutManager(layoutManager);

        mDataRv.setAdapter(mAdapter);
    }

    @Override
    protected void onFirstUserVisible() {
        mNewPageNumber = 0;
        mMorePageNumber = 0;
        mEngine.loadDefaultStaggeredData().enqueue(new Callback<List<StaggeredModel>>() {
            @Override
            public void onResponse(Call<List<StaggeredModel>> call, Response<List<StaggeredModel>> response) {
                mAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<StaggeredModel>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onCHZZRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        mNewPageNumber++;
        if (mNewPageNumber > 4) {
            mRefreshLayout.endRefreshing();
            showToast("没有最新数据了");
            return;
        }
        mEngine.loadNewStaggeredData(mNewPageNumber).enqueue(new Callback<List<StaggeredModel>>() {
            @Override
            public void onResponse(Call<List<StaggeredModel>> call, final Response<List<StaggeredModel>> response) {
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.endRefreshing();
                        mAdapter.addNewData(response.body());
                        mDataRv.smoothScrollToPosition(0);
                    }
                }, RefreshActivity.LOADING_DURATION);
            }

            @Override
            public void onFailure(Call<List<StaggeredModel>> call, Throwable t) {
                mRefreshLayout.endRefreshing();
            }
        });
    }

    @Override
    public boolean onCHZZRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        mMorePageNumber++;
        if (mMorePageNumber > 5) {
            mRefreshLayout.endLoadingMore();
            showToast("没有更多数据了");
            return false;
        }
        mEngine.loadMoreStaggeredData(mMorePageNumber).enqueue(new Callback<List<StaggeredModel>>() {
            @Override
            public void onResponse(Call<List<StaggeredModel>> call, final Response<List<StaggeredModel>> response) {
                ThreadUtil.runInUIThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.endLoadingMore();
                        mAdapter.addMoreData(response.body());
                    }
                }, RefreshActivity.LOADING_DURATION);
            }

            @Override
            public void onFailure(Call<List<StaggeredModel>> call, Throwable t) {
                mRefreshLayout.endLoadingMore();
            }
        });
        return true;
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        showToast("点击了条目 " + mAdapter.getItem(position).desc);
    }

    @Override
    public boolean onRVItemLongClick(ViewGroup parent, View itemView, int position) {
        showToast("长按了条目 " + mAdapter.getItem(position).desc);
        return true;
    }
}