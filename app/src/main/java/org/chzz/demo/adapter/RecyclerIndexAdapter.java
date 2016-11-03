package org.chzz.demo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.chzz.adapter.CHZZRecyclerViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.demo.R;
import org.chzz.demo.model.IndexModel;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/22 16:31
 * 描述:
 */
public class RecyclerIndexAdapter extends CHZZRecyclerViewAdapter<IndexModel> {
    public RecyclerIndexAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_indexview);
    }

    @Override
    public void setItemChildListener(CHZZViewHolderHelper helper) {
        helper.setItemChildClickListener(R.id.tv_item_indexview_name);
    }

    @Override
    public void fillData(CHZZViewHolderHelper helper, int position, IndexModel model) {
        int section = getSectionForPosition(position);
        if (position == getPositionForSection(section)) {
            helper.setVisibility(R.id.tv_item_indexview_catalog, View.VISIBLE);
            helper.setText(R.id.tv_item_indexview_catalog, model.topc);
        } else {
            helper.setVisibility(R.id.tv_item_indexview_catalog, View.GONE);
        }
        helper.setText(R.id.tv_item_indexview_name, model.name);
    }

    public int getSectionForPosition(int position) {
        return mData.get(position).topc.charAt(0);
    }

    public int getPositionForSection(int section) {
        for (int i = 0; i < getItemCount(); i++) {
            String sortStr = mData.get(i).topc;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}