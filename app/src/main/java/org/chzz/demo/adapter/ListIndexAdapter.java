package org.chzz.demo.adapter;

import android.content.Context;
import android.view.View;

import org.chzz.adapter.CHZZAdapterViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.demo.R;
import org.chzz.demo.model.IndexModel;


/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/21 上午12:39
 * 描述:
 */
public class ListIndexAdapter extends CHZZAdapterViewAdapter<IndexModel> {

    public ListIndexAdapter(Context context) {
        super(context, R.layout.item_indexview);
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper helper) {
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
        for (int i = 0; i < getCount(); i++) {
            String sortStr = mData.get(i).topc;
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}