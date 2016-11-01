package org.chzz.demo.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import org.chzz.adapter.CHZZOnItemChildClickListener;
import org.chzz.adapter.CHZZOnItemChildLongClickListener;
import org.chzz.demo.R;
import org.chzz.demo.adapter.NormalAdapterViewAdapter;
import org.chzz.demo.model.RefreshModel;
import org.chzz.demo.ui.activity.RefreshActivity;
import org.chzz.demo.util.ThreadUtil;
import org.chzz.refresh.CHZZMoocStyleRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/10 17:45
 * 描述:
 */
public class RefreshGridViewFragment extends BaseFragment implements CHZZRefreshLayout.CHZZRefreshLayoutDelegate, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, CHZZOnItemChildClickListener, CHZZOnItemChildLongClickListener, View.OnClickListener {
    private CHZZRefreshLayout mRefreshLayout;
    private GridView mDataGv;
    private NormalAdapterViewAdapter mAdapter;
    private int mNewPageNumber = 0;
    private int mMorePageNumber = 0;

    private boolean mIsNetworkEnabled = false;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_gridview_refresh);
        mRefreshLayout = getViewById(R.id.rl_gridview_refresh);
        mDataGv = getViewById(R.id.lv_gridview_data);
    }

    @Override
    protected void setListener() {
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshScaleDelegate(new CHZZRefreshLayout.CHZZRefreshScaleDelegate() {
            @Override
            public void onRefreshScaleChanged(float scale, int moveYDistance) {
                Log.i(TAG, "scale:" + scale + " moveYDistance:" + moveYDistance);
            }
        });

        mDataGv.setOnItemClickListener(this);
        mDataGv.setOnItemLongClickListener(this);
        mDataGv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.i(TAG, "滚动状态变化");
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                Log.i(TAG, "正在滚动");
            }
        });

        mAdapter = new NormalAdapterViewAdapter(mApp);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);

        getViewById(R.id.beginRefreshing).setOnClickListener(this);
        getViewById(R.id.beginLoadingMore).setOnClickListener(this);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        CHZZMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new CHZZMoocStyleRefreshViewHolder(mApp, true);
        moocStyleRefreshViewHolder.setOriginalImage(R.mipmap.custom_mooc_icon);
        moocStyleRefreshViewHolder.setUltimateColor(R.color.imoocstyle);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);

        mDataGv.setAdapter(mAdapter);
    }

    @Override
    protected void onFirstUserVisible() {
        mNewPageNumber = 0;
        mMorePageNumber = 0;
        mEngine.loadInitDatas().enqueue(new Callback<List<RefreshModel>>() {
            @Override
            public void onResponse(Call<List<RefreshModel>> call, Response<List<RefreshModel>> response) {
                mAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
            }
        });
    }

    @Override
    public void onCHZZRefreshLayoutBeginRefreshing(final CHZZRefreshLayout refreshLayout) {
        if (mIsNetworkEnabled) {
            // 如果网络可用，则加载网络数据

            mNewPageNumber++;
            if (mNewPageNumber > 4) {
                mRefreshLayout.endRefreshing();
                showToast("没有最新数据了");
                return;
            }
            mEngine.loadNewData(mNewPageNumber).enqueue(new Callback<List<RefreshModel>>() {
                @Override
                public void onResponse(Call<List<RefreshModel>> call, final Response<List<RefreshModel>> response) {
                    // 测试数据放在七牛云上的比较快，这里加载完数据后模拟延时查看动画效果
                    ThreadUtil.runInUIThread(new Runnable() {
                        @Override
                        public void run() {
                            mRefreshLayout.endRefreshing();
                            mAdapter.addNewData(response.body());
                        }
                    }, RefreshActivity.LOADING_DURATION);
                }

                @Override
                public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
                    mRefreshLayout.endRefreshing();
                }
            });
        } else {
            // 网络不可用，结束下拉刷新
            showToast("网络不可用");
            mRefreshLayout.endRefreshing();
        }
        // 模拟网络可用不可用
        mIsNetworkEnabled = !mIsNetworkEnabled;
    }

    @Override
    public boolean onCHZZRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {
        Log.i(TAG, "开始加载更多");

        if (mIsNetworkEnabled) {
            // 如果网络可用，则异步加载网络数据，并返回true，显示正在加载更多
            mMorePageNumber++;
            if (mMorePageNumber > 4) {
                mRefreshLayout.endLoadingMore();
                showToast("没有更多数据了");
                return false;
            }
            mEngine.loadMoreData(mMorePageNumber).enqueue(new Callback<List<RefreshModel>>() {
                @Override
                public void onResponse(Call<List<RefreshModel>> call, final Response<List<RefreshModel>> response) {
                    // 测试数据放在七牛云上的比较快，这里加载完数据后模拟延时查看动画效果
                    ThreadUtil.runInUIThread(new Runnable() {
                        @Override
                        public void run() {
                            mRefreshLayout.endLoadingMore();
                            mAdapter.addMoreData(response.body());
                        }
                    }, RefreshActivity.LOADING_DURATION);
                }

                @Override
                public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
                    mRefreshLayout.endLoadingMore();
                }
            });
            // 模拟网络可用不可用
            mIsNetworkEnabled = !mIsNetworkEnabled;
            return true;
        } else {
            // 模拟网络可用不可用
            mIsNetworkEnabled = !mIsNetworkEnabled;

            // 网络不可用，返回false，不显示正在加载更多
            showToast("网络不可用");
            return false;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showToast("点击了条目 " + mAdapter.getItem(position).title);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        showToast("长按了" + mAdapter.getItem(position).title);
        return true;
    }

    @Override
    public void onItemChildClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_item_normal_delete) {
            mAdapter.removeItem(position);
        }
    }

    @Override
    public boolean onItemChildLongClick(ViewGroup parent, View childView, int position) {
        if (childView.getId() == R.id.tv_item_normal_delete) {
            showToast("长按了删除 " + mAdapter.getItem(position).title);
            return true;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.beginRefreshing) {
            mRefreshLayout.beginRefreshing();
        } else if (v.getId() == R.id.beginLoadingMore) {
            mRefreshLayout.beginLoadingMore();
        }
    }
}