package com.hrw.common.baseMVVM;


import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hrw.common.net.MtResultBean1;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 11:33
 * @desc:
 */
public class BaseViewModel extends ViewModel {
    protected <T extends MtResultBean1<R>, R> void subscribe(Observable<T> observable, final MutableLiveData<R> mutableLiveData) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                    }

                    @Override
                    public void onNext(T t) {
                        if (t.getStatus() == 1) {
                            mutableLiveData.setValue(t.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
