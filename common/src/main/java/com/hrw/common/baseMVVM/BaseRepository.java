package com.hrw.common.baseMVVM;

import android.databinding.ObservableBoolean;

import com.hrw.common.net.MtResultBean1;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
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

    protected <T extends MtResultBean1> void subscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public ObservableBoolean getIsOnLoadData() {
        return mIsOnLoadData;
    }

    public ObservableBoolean getIsOnRefresh() {
        return mIsOnRefresh;
    }
}
