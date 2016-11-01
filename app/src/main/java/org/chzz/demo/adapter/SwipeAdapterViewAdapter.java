package org.chzz.demo.adapter;

import android.content.Context;

import org.chzz.adapter.CHZZAdapterViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.demo.R;
import org.chzz.demo.model.RefreshModel;
import org.chzz.widget.CHZZSwipeItemLayout;

import java.util.ArrayList;
import java.util.List;




/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/5/21 上午12:39
 * 描述:
 */
public class SwipeAdapterViewAdapter extends CHZZAdapterViewAdapter<RefreshModel> {
    /**
     * 当前处于打开状态的item
     */
    private List<CHZZSwipeItemLayout> mOpenedSil = new ArrayList<>();

    public SwipeAdapterViewAdapter(Context context) {
        super(context, R.layout.item_swipe);
    }

    @Override
    protected void setItemChildListener(CHZZViewHolderHelper viewHolderHelper) {
        CHZZSwipeItemLayout swipeItemLayout = viewHolderHelper.getView(R.id.sil_item_swipe_root);
        swipeItemLayout.setDelegate(new CHZZSwipeItemLayout.CHZZSwipeItemLayoutDelegate() {
            @Override
            public void onCHZZSwipeItemLayoutOpened(CHZZSwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
                mOpenedSil.add(swipeItemLayout);
            }

            @Override
            public void onCHZZSwipeItemLayoutClosed(CHZZSwipeItemLayout swipeItemLayout) {
                mOpenedSil.remove(swipeItemLayout);
            }

            @Override
            public void onCHZZSwipeItemLayoutStartOpen(CHZZSwipeItemLayout swipeItemLayout) {
                closeOpenedSwipeItemLayoutWithAnim();
            }
        });
        viewHolderHelper.setItemChildClickListener(R.id.tv_item_swipe_delete);
        viewHolderHelper.setItemChildLongClickListener(R.id.tv_item_swipe_delete);
    }

    @Override
    public void fillData(CHZZViewHolderHelper viewHolderHelper, int position, RefreshModel model) {
        viewHolderHelper.setText(R.id.tv_item_swipe_title, model.title).setText(R.id.tv_item_swipe_detail, model.detail).setText(R.id.et_item_swipe_title, model.title);
    }

    public void closeOpenedSwipeItemLayoutWithAnim() {
        for (CHZZSwipeItemLayout sil : mOpenedSil) {
            sil.closeWithAnim();
        }
        mOpenedSil.clear();
    }

    public void closeOpenedSwipeItemLayout() {
        for (CHZZSwipeItemLayout sil : mOpenedSil) {
            sil.close();
        }
        mOpenedSil.clear();
    }

}