package com.tl.commonsdk.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


/**
 * Created by fangpenglin on 16/6/19.
 * 处理rxjava的数据回传
 */
public abstract class HttpDelegate {
    private CompositeDisposable mCompositeSubscription;

    public void addSubscription(Disposable disposable) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeDisposable();
        }
        mCompositeSubscription.add(disposable);
    }

    /**
     * RxJava取消注册，以避免内存泄露
     */
    public void onUnSubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.size()>0) {
            mCompositeSubscription.dispose();
        }
    }
}
