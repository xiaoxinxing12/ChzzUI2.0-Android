package org.chzz.demo.view.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.chzz.adapter.CHZZOnRVItemClickListener;
import org.chzz.demo.R;
import org.chzz.demo.adapter.NormalRecyclerViewAdapter;
import org.chzz.demo.model.RefreshModel;
import org.chzz.demo.util.RxSchedulers;
import org.chzz.demo.util.ThreadUtil;
import org.chzz.demo.util.ToastUtil;
import org.chzz.refresh.CHZZMoocStyleRefreshViewHolder;
import org.chzz.refresh.CHZZRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements CHZZRefreshLayout.CHZZRefreshLayoutDelegate, CHZZOnRVItemClickListener {
    Button mTest;
    RecyclerView mRvTest, mData;
    NormalRecyclerViewAdapter adapter;
    private CHZZRefreshLayout mRefreshLayout;
    int index = 1;
    View foot;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mTest = (Button) findViewById(R.id.but_refresh);
        mRvTest = (RecyclerView) findViewById(R.id.rv_test);
        mRefreshLayout = getViewById(R.id.rl_recyclerview);
        mData = getViewById(R.id.rv_recyclerview_data);
        foot = View.inflate(MainActivity.this, R.layout.item_foot, null);
        TextView textView = (TextView) foot.findViewById(R.id.tv_foot);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("点击了底部");
            }
        });
        getData(1);
    }

    @Override
    protected void setListener() {

        mTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEngine.loadNewData(1)
                        .compose(RxSchedulers.io_main())
                        .subscribe(list -> {
                            result(list);
                        }, t -> {
                            showError(t.getLocalizedMessage());
                        });
            }
        });


    }

    private void getData(int i) {
        mEngine.loadNewData(i)
                .compose(RxSchedulers.io_main())
                .subscribe(list -> {
                    mRefreshLayout.endRefreshing();
                    mRefreshLayout.endLoadingMore();
                    if (adapter.getData().size() == 0) {
                        adapter.setData(list);
                        adapter.addFootView(foot);
                    } else
                        adapter.addMoreData(list);
                    //
                }, t -> {
                    showError(t.getLocalizedMessage());
                });
    }

    private void init() {
        List<View> head = new ArrayList<>();
        head.add(View.inflate(this, R.layout.item_head, null));
        head.add(View.inflate(this, R.layout.item_head, null));
        List<View> footView = new ArrayList<>();
        //  footView.add(View.inflate(this, R.layout.item_foot, null));
        // footView.add(View.inflate(this, R.layout.item_foot, null));


        mRvTest.setLayoutManager(new GridLayoutManager(mApp, 1, GridLayoutManager.VERTICAL, false));
        mRvTest.setAdapter(adapter);
        List<RefreshModel> refreshModels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            refreshModels.add(new RefreshModel());
        }
        adapter.setData(refreshModels);
        adapter.notifyDataSetChanged();
    }


    private void result(List<RefreshModel> list) {
        adapter.addItem(adapter.getData().size(), list.get(0));
    }

    private void showError(String msg) {
        ToastUtil.show(msg);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        View head = View.inflate(this, R.layout.item_head, null);
        TextView h = (TextView) head.findViewById(R.id.tv_head);
        List<View> footView = new ArrayList<>();
        footView.add(View.inflate(this, R.layout.item_foot, null));
        adapter = new NormalRecyclerViewAdapter(mRvTest,head,null);
      //  adapter.addHeadView(head);
        mRefreshLayout.setDelegate(this);
        CHZZMoocStyleRefreshViewHolder leftRefreshViewHolder = new CHZZMoocStyleRefreshViewHolder(mApp, false);
        leftRefreshViewHolder.setSpringDistanceScale(2);
        //刷新图标
        leftRefreshViewHolder.setOriginalImage(R.mipmap.ic_launcher);
        //刷新头背景色
        leftRefreshViewHolder.setUltimateColor(R.color.white);
        mRefreshLayout.setRefreshViewHolder(leftRefreshViewHolder);

        // mRefreshLayout.setCustomHeaderView(DataEngine.getCustomHeaderView(mApp), true);
        mData.setLayoutManager(new GridLayoutManager(mApp, 1, GridLayoutManager.VERTICAL, false));
        mData.setAdapter(adapter);
        adapter.setOnRVItemClickListener(this);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.show("点击了头部");
            }
        });
    }

    @Override
    public void onCHZZRefreshLayoutBeginRefreshing(CHZZRefreshLayout refreshLayout) {
        adapter.getData().clear();
        getData(1);
    }

    @Override
    public boolean onCHZZRefreshLayoutBeginLoadingMore(CHZZRefreshLayout refreshLayout) {

        if (index <= 4) {
            index = index + 1;
            ThreadUtil.runInUIThread(new Runnable() {
                @Override
                public void run() {
                    getData(index);
                }
            }, 1000);
        } else {
            return false;
        }
        return true;
    }

    @Override
    public void onRVItemClick(ViewGroup parent, View itemView, int position) {
        ToastUtil.show("点击第" + (position - 1));
    }
}
