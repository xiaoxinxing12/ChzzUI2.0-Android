package org.chzz.demo.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.GridView;

import org.chzz.adapter.CHZZOnItemChildCheckedChangeListener;
import org.chzz.adapter.CHZZOnItemChildClickListener;
import org.chzz.adapter.CHZZOnItemChildLongClickListener;
import org.chzz.demo.R;
import org.chzz.demo.adapter.NormalAdapterViewAdapter;
import org.chzz.demo.model.RefreshModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/6/28 下午12:34
 * 描述:
 */
public class GridViewDemoFragment extends BaseFragment implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener, CHZZOnItemChildClickListener, CHZZOnItemChildLongClickListener, CHZZOnItemChildCheckedChangeListener {
    private static final String TAG = GridViewDemoFragment.class.getSimpleName();
    private GridView mDataGv;
    private NormalAdapterViewAdapter mAdapter;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_gridview);
        mDataGv = getViewById(R.id.lv_gridview_data);
    }

    @Override
    protected void setListener() {
        mDataGv.setOnItemClickListener(this);
        mDataGv.setOnItemLongClickListener(this);

        mAdapter = new NormalAdapterViewAdapter(mActivity);
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnItemChildLongClickListener(this);
        mAdapter.setOnItemChildCheckedChangeListener(this);
    }

    @Override
    protected void onFirstUserVisible() {
        mEngine.getNormalModels().enqueue(new Callback<List<RefreshModel>>() {
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
    protected void processLogic(Bundle savedInstanceState) {
        mDataGv.setAdapter(mAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

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

            return true;
        }
        return false;
    }

    @Override
    public void onItemChildCheckedChanged(ViewGroup parent, CompoundButton childView, int position, boolean isChecked) {
        // 在填充数据列表时，忽略选中状态变化
//        if (!mAdapter.isIgnoreChange()) {
//            mAdapter.getItem(position).selected = isChecked;
//            if (isChecked) {
//
//            } else {
//            }
//        }
    }
}