package org.chzz.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.chzz.adapter.CHZZCommonRecyclerViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.demo.R;
import org.chzz.demo.model.RefreshModel;

;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/22 16:31
 * 描述:
 */
public class NormalRecyclerViewAdapter extends CHZZCommonRecyclerViewAdapter<RefreshModel> {
    private boolean ignoreChange;

    public NormalRecyclerViewAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_normal);
    }

    public NormalRecyclerViewAdapter(RecyclerView recyclerView, View head, View foot) {
        super(recyclerView, R.layout.item_normal, head, foot);
    }

    @Override
    public void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
    }

    @Override
    public void fillData(CHZZViewHolderHelper viewHolderHelper, int position, RefreshModel model) {
        viewHolderHelper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);
    }

    public boolean isIgnoreChange() {
        return ignoreChange;
    }
}