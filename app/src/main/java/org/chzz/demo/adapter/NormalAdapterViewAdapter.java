package org.chzz.demo.adapter;

import android.content.Context;

import org.chzz.adapter.CHZZAdapterViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.demo.R;
import org.chzz.demo.model.RefreshModel;


/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/21 上午12:39
 * 描述:
 */
public class NormalAdapterViewAdapter extends CHZZAdapterViewAdapter<RefreshModel> {

    public NormalAdapterViewAdapter(Context context) {
        super(context, R.layout.item_normal);
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_normal_delete);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_normal_delete);
    }

    @Override
    public void fillData(CHZZViewHolderHelper viewHolderHelper, int position, RefreshModel model) {
        viewHolderHelper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);
    }
}