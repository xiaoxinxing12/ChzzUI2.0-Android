package org.chzz.demo.engine;

import rx.Subscriber;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/11/2
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/11/2--13:56
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/

public class SubscriberCall<T> extends Subscriber<T> {
    private DataCallback dataCallback;

    public SubscriberCall(DataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(T t) {
        dataCallback.call(t);
    }

    public interface DataCallback<T> {

        void call(T v);
    }
}
