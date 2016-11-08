/**
 * Copyright 2015 bingoogolapple
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.chzz.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/28 上午7:28
 * 描述:适用于RecyclerView的item的ViewHolder
 */
public class CHZZDataBindingHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    protected Context mContext;
    protected CHZZOnRVItemClickListener mOnRVItemClickListener;
    protected CHZZOnRVItemLongClickListener mOnRVItemLongClickListener;
    protected CHZZViewHolderHelper mViewHolderHelper;
    protected RecyclerView mRecyclerView;
    protected ViewDataBinding mBinding;

    public CHZZDataBindingHolder(RecyclerView recyclerView, View itemView, CHZZOnRVItemClickListener onRVItemClickListener, CHZZOnRVItemLongClickListener onRVItemLongClickListener) {
        super(recyclerView);
        mRecyclerView = recyclerView;
        mContext = mRecyclerView.getContext();
        mOnRVItemClickListener = onRVItemClickListener;
        mOnRVItemLongClickListener = onRVItemLongClickListener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        mViewHolderHelper = new CHZZViewHolderHelper(mRecyclerView, this.itemView);
        mViewHolderHelper.setRecyclerViewHolder(this);
    }

    public CHZZDataBindingHolder(ViewDataBinding binding, View item) {
        super(item);
        mBinding = binding;
        mViewHolderHelper = new CHZZViewHolderHelper(mBinding, item);
        mViewHolderHelper.setRecyclerViewHolder(this);
    }


    public CHZZViewHolderHelper getViewHolderHelper() {
        return mViewHolderHelper;
    }

    public ViewDataBinding getViewDataBinding() {
        return mBinding;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == this.itemView.getId() && null != mOnRVItemClickListener) {
            mOnRVItemClickListener.onRVItemClick(mRecyclerView, v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v.getId() == this.itemView.getId() && null != mOnRVItemLongClickListener) {
            return mOnRVItemLongClickListener.onRVItemLongClick(mRecyclerView, v, getAdapterPosition());
        }
        return false;
    }

}