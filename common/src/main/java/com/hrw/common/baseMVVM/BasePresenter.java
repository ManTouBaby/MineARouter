package com.hrw.common.baseMVVM;


import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 11:33
 * @desc:
 */
public class BasePresenter<V> {
    V mView;

    CompositeDisposable mDisposable;

    public BasePresenter(V v) {
        onAttach(v);
    }

    public void onAttach(V v) {
        mView = v;
    }

    public void onDetach() {
        mView = null;
        onUnsubscribe();
    }

    public <K> void onSubscribe(@NonNull Observable<K> observable, @NonNull Consumer<K> consumer) {
        if (mDisposable == null) {
            mDisposable = new CompositeDisposable();
        }
        mDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer));


    }

    public void onUnsubscribe() {
        mDisposable.clear();
    }
}
