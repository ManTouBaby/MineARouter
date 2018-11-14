package com.hrw.common.net;

import com.hrw.common.utils.MtLog;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * @author:MtBaby
 * @date:2018/11/09 23:08
 * @desc:
 */
public abstract class MtObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onNext(T o) {
        onLoadSuccess(o);
    }

    @Override
    public void onError(Throwable throwable) {
        MtLog.d(throwable.toString());
        onLoadError(throwable);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onLoadError(Throwable throwable);

    public abstract void onLoadSuccess(T o);

}
