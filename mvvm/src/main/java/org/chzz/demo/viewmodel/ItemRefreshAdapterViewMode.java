package org.chzz.demo.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import org.chzz.demo.model.RefreshModel;
import org.chzz.demo.util.ToastUtil;

import java.util.Map;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/11/5
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/11/5--15:56
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/

public class ItemRefreshAdapterViewMode extends BaseObservable implements BaseViewModel {
    Context context;
    public RefreshModel refreshModel;

    public ItemRefreshAdapterViewMode(Context context, RefreshModel refreshModel) {
        this.context = context;
        this.refreshModel = refreshModel;
    }

    public void setRefreshModel(RefreshModel refreshModel) {
        this.refreshModel = refreshModel;
        notifyChange();
    }

    @Override
    public void onUserVisible(Map<String, Object> data) {

    }

    @Override
    public void destroy() {

    }

    public void onItemClick(View view) {
        ToastUtil.show("onItemClick" + refreshModel.getTitle());
    }

    public void onItemClicks(View view) {
        ToastUtil.show("Textview" + refreshModel.getTitle());
    }
}
