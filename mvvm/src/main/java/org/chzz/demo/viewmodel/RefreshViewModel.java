package org.chzz.demo.viewmodel;

import android.content.Context;
import android.view.View;

import org.chzz.demo.model.RefreshModel;
import org.chzz.demo.util.RxSchedulers;
import org.chzz.demo.util.ToastUtil;

import java.util.List;
import java.util.Map;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/11/5
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/11/5--15:25
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/

public class RefreshViewModel implements BaseViewModel {

    private DataListener dataListener;
    private Context context;

    public RefreshViewModel(Context context, DataListener dataListener) {
        this.context = context;
        this.dataListener = dataListener;
    }

    @Override
    public void onUserVisible(Map<String, Object> data) {
        mEngine.loadNewData(1)
                .compose(RxSchedulers.io_main())
                .subscribe(list -> {
                    dataListener.onRepositoriesChanged(list);
                }, t -> {
                });
    }

    @Override
    public void destroy() {

    }

    public void loadData(View view) {
        mEngine.loadNewData(2)
                .compose(RxSchedulers.io_main())
                .subscribe(list -> {
                    dataListener.onRepositoriesChanged(list);
                }, t -> {
                    ToastUtil.show(t.getLocalizedMessage());
                });
    }

    public void onItemClicks(View view) {
        ToastUtil.show("Textview");
    }

    public void onFootClicks(View view) {
        ToastUtil.show("onFootClicks");
    }

    public interface DataListener {
        void onRepositoriesChanged(List<RefreshModel> refreshModels);
    }
}
