package org.chzz.demo.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.chzz.adapter.CHZZDataBindingAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.demo.databinding.ItemRepoBinding;
import org.chzz.demo.model.RefreshModel;
import org.chzz.demo.viewmodel.ItemRefreshAdapterViewMode;

;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/22 16:31
 * 描述:
 */
public class NormalRecyclerViewAdapter extends CHZZDataBindingAdapter<RefreshModel> {
    private boolean ignoreChange;
    private IFillDataListener dataListener;

    public NormalRecyclerViewAdapter(RecyclerView recyclerView, int item, IFillDataListener listener) {
        super(recyclerView, item, true);
        dataListener = listener;
    }

    public NormalRecyclerViewAdapter(RecyclerView recyclerView, int item, View head, View foot, IFillDataListener listener) {
        super(recyclerView, item, head, foot, true);
        dataListener = listener;
    }

    @Override
    public void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {
        // viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
        //viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
    }

    @Override
    public void fillData(CHZZViewHolderHelper viewHolderHelper, int position, RefreshModel model) {
        // viewHolderHelper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);
        ItemRepoBinding binding1 = (ItemRepoBinding) viewHolderHelper.getmBinding();
        if (binding1.getViewModel() == null) {
            binding1.setViewModel(new ItemRefreshAdapterViewMode(mItem.getContext(), model));
        } else {
            binding1.getViewModel().setRefreshModel(model);
        }
    }

    @Override
    public void setBindingItem(ViewGroup parent) {
        mBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                mItemLayoutId, parent,
                false);
        dataListener.setBingItem(mBinding);

    }

    public boolean isIgnoreChange() {
        return ignoreChange;
    }

    public interface IFillDataListener {
        /**
         * 给item设置数据
         *
         * @param
         * @param
         * @param
         */

        //public void setFillDataListener(CHZZViewHolderHelper chzzViewHolderHelper, int i, RefreshModel t);
        public void setBingItem(ViewDataBinding mBinding);
    }
}