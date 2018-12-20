package com.hrw.common.utils.collect;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.common.baseMVVM.BaseViewModel;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/12/20 17:19
 * @desc:
 */
public class CollectViewModel extends BaseViewModel<CollectRepository> {
    @Override
    protected CollectRepository createRepository() {
        return new CollectRepository();
    }

    public MutableLiveData<List<CollectBO>> getAllCollect(AppDataBase dataBase) {
        return mRepository.getAllCollect(dataBase);
    }

    public MutableLiveData<List<CollectBO>> getAllCollectByType(AppDataBase dataBase, @CollectType int collectType) {
        return mRepository.getAllCollectByType(dataBase, collectType);
    }

    public MutableLiveData<CollectBO> getCollectById(AppDataBase dataBase, int bookId) {
        return mRepository.getCollectById(dataBase, bookId);
    }

    public void insertCollect(AppDataBase dataBase, CollectBO... collectBOS) {
        mRepository.insertCollect(dataBase, collectBOS);
    }

    public void deleteCollect(AppDataBase dataBase, CollectBO... collectBOS) {
        mRepository.deleteCollect(dataBase, collectBOS);
    }
}
