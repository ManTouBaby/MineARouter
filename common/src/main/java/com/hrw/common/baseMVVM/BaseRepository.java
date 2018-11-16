package com.hrw.common.baseMVVM;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.hrw.common.net.MtResultBean1;
import com.hrw.common.net.OnResultListener;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/07 15:26
 * @desc:
 */
public class BaseRepository {
    protected ObservableBoolean mIsOnRefresh = new ObservableBoolean();
    protected ObservableBoolean mIsOnLoadData = new ObservableBoolean();
    protected MutableLiveData<ErrorResult> mErrorResult = new MutableLiveData<>();

    protected <T extends MtResultBean1> void subscribe(Observable<T> observable, final OnResultListener<T> onResultListener) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T t) {
                        onResultListener.onLoadSuccess(t);
                        if (t.getStatus() != 1) {
                            mErrorResult.setValue(new ErrorResult(t.getStatus(), t.getInfo()));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        onResultListener.onLoadError(e);
                        mErrorResult.setValue(new ErrorResult(0, e.getMessage()));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public MutableLiveData<ErrorResult> getErrorResult() {
        return mErrorResult;
    }

    public ObservableBoolean getIsOnLoadData() {
        return mIsOnLoadData;
    }

    public ObservableBoolean getIsOnRefresh() {
        return mIsOnRefresh;
    }
}
