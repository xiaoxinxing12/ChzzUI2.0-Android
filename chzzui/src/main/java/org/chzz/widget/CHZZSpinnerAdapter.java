package org.chzz.widget;

import android.content.Context;

import java.util.List;

/**
 * @author angelo.marchesin
 */

public class CHZZSpinnerAdapter<T> extends CHZZSpinnerBaseAdapter {

    private final List<T> mItems;

    public CHZZSpinnerAdapter(Context context, List<T> items, int textColor, int backgroundSelector, boolean isCheckBox, onCheckBoxChecked onClickCheckBox) {
        super(context, textColor, backgroundSelector,isCheckBox,onClickCheckBox);
        mItems = items;
    }

    @Override
    public int getCount() {
        return mItems.size() - 1;
    }

    @Override
    public T getItem(int position) {
        if (position >= mSelectedIndex) {
            return mItems.get(position + 1);
        } else {
            return mItems.get(position);
        }
    }

    @Override
    public T getItemInDataset(int position) {
        return mItems.get(position);
    }
}