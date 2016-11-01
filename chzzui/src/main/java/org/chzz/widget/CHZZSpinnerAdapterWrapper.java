package org.chzz.widget;

import android.content.Context;
import android.widget.ListAdapter;

/**
 * @author angelo.marchesin
 */

public class CHZZSpinnerAdapterWrapper extends CHZZSpinnerBaseAdapter {

    private final ListAdapter mBaseAdapter;

    public CHZZSpinnerAdapterWrapper(Context context, ListAdapter toWrap, int textColor, int backgroundSelector, boolean isCheckBox, onCheckBoxChecked onClickCheckBox) {
        super(context, textColor, backgroundSelector,isCheckBox,onClickCheckBox);
        mBaseAdapter = toWrap;
    }

    @Override
    public int getCount() {
        return mBaseAdapter.getCount() - 1;
    }

    @Override
    public Object getItem(int position) {
        if (position >= mSelectedIndex) {
            return mBaseAdapter.getItem(position + 1);
        } else {
            return mBaseAdapter.getItem(position);
        }
    }

    @Override
    public Object getItemInDataset(int position) {
        return mBaseAdapter.getItem(position);
    }
}