package com.hrw.common.baseMVVM;


import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 11:33
 * @desc:
 */
public abstract class BaseViewModel<T extends BaseRepository> extends ViewModel {
    public ObservableBoolean isOnRefresh;
    public ObservableBoolean isOnLoadData;
    protected T mRepository;

    public BaseViewModel() {
        mRepository = createRepository();
    }

    protected abstract T createRepository();

    public ObservableBoolean getIsOnLoadData() {
        isOnLoadData = mRepository.getIsOnLoadData();
        return isOnLoadData;
    }

    public ObservableBoolean getIsOnRefresh() {
        isOnRefresh = mRepository.getIsOnRefresh();
        return isOnRefresh;
    }
}
