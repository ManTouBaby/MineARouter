package com.hrw.common.utils.collect;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;
import android.support.annotation.NonNull;

import com.hrw.common.baseMVVM.BaseRepository;
import com.hrw.common.net.OnResultListener;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/12/20 17:19
 * @desc:
 */
public class CollectRepository extends BaseRepository {
    MutableLiveData<CollectBO> mCollectBO = new MutableLiveData<>();
    MutableLiveData<List<CollectBO>> mCollectBOS = new MutableLiveData<>();
    ObservableBoolean isInsertSuccess = new ObservableBoolean();

    /**
     * 获取所有收藏
     *
     * @param dataBase
     * @return
     */
    public MutableLiveData<List<CollectBO>> getAllCollect(final AppDataBase dataBase) {
        subscribe1(Observable.create(new ObservableOnSubscribe<List<CollectBO>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CollectBO>> emitter) throws Exception {
                emitter.onNext(dataBase.collectDAO().getAllCollect());
            }
        }), new OnResultListener<List<CollectBO>>() {
            @Override
            public void onLoadError(Throwable throwable) {

            }

            @Override
            public void onLoadSuccess(List<CollectBO> o) {
                mCollectBOS.postValue(o);
            }
        });
        return mCollectBOS;
    }

    public MutableLiveData<List<CollectBO>> getAllCollectByType(final AppDataBase dataBase, @NonNull @CollectType final int colectType) {
        subscribe1(Observable.create(new ObservableOnSubscribe<List<CollectBO>>() {
            @Override
            public void subscribe(ObservableEmitter<List<CollectBO>> emitter) throws Exception {
                emitter.onNext(dataBase.collectDAO().getAllCollectByType(colectType));
            }
        }), new OnResultListener<List<CollectBO>>() {
            @Override
            public void onLoadError(Throwable throwable) {

            }

            @Override
            public void onLoadSuccess(List<CollectBO> o) {
                mCollectBOS.postValue(o);
            }
        });
        return mCollectBOS;
    }

    /**
     * 按BookId查找
     *
     * @param dataBase
     * @param bookId
     * @return
     */
    public MutableLiveData<CollectBO> getCollectById(final AppDataBase dataBase, final int bookId) {
        subscribe1(Observable.create(new ObservableOnSubscribe<CollectBO>() {
            @Override
            public void subscribe(ObservableEmitter<CollectBO> emitter) throws Exception {
                emitter.onNext(dataBase.collectDAO().getCollect(bookId + ""));
            }
        }), new OnResultListener<CollectBO>() {
            @Override
            public void onLoadError(Throwable throwable) {

            }

            @Override
            public void onLoadSuccess(CollectBO o) {
                mCollectBO.postValue(o);
            }
        });
        return mCollectBO;
    }

    public ObservableBoolean insertCollect(final AppDataBase dataBase, final CollectBO... collectBO) {
        subscribe1(Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                dataBase.collectDAO().insertAll(collectBO);
                mCollectBO.postValue(collectBO[0]);
//                emitter.onNext();
            }
        }), new OnResultListener<Boolean>() {
            @Override
            public void onLoadError(Throwable throwable) {

            }

            @Override
            public void onLoadSuccess(Boolean o) {
                isInsertSuccess.set(o);
            }
        });
        return isInsertSuccess;
    }

    public ObservableBoolean deleteCollect(final AppDataBase dataBase, final CollectBO... collectBO) {
        subscribe1(Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> emitter) throws Exception {
                dataBase.collectDAO().deleteCollect(collectBO);
                mCollectBO.postValue(null);
//                emitter.onNext();
            }
        }), new OnResultListener<Boolean>() {
            @Override
            public void onLoadError(Throwable throwable) {

            }

            @Override
            public void onLoadSuccess(Boolean o) {
                isInsertSuccess.set(o);
            }
        });
        return isInsertSuccess;
    }
}
