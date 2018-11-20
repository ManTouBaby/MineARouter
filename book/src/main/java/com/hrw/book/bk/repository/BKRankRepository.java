package com.hrw.book.bk.repository;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.entity.BookList;
import com.hrw.book.service.IBKService;
import com.hrw.book.service.IBKType;
import com.hrw.common.baseMVVM.BaseRepository;
import com.hrw.common.net.MtResultBean1;
import com.hrw.common.net.MtRetrofitHelper;
import com.hrw.common.net.OnResultListener;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/13 11:33
 * @desc:
 */
public class BKRankRepository extends BaseRepository {
    MutableLiveData<BookList> mBMutableLiveData = new MutableLiveData<>();

    IBKService mIbkService;

    public BKRankRepository() {
        mIbkService = MtRetrofitHelper.getRetrofit().createClass(IBKService.class);
    }

    public MutableLiveData<BookList> getBookList(@IBKType.IBKReaderSex String sex, @IBKType.IBKListType String type,
                                                 @IBKType.IBKListByTime String time, int page) {

        mIsOnLoadData.set(true);
        mIsOnRefresh.set(true);
        subscribe(mIbkService.getTopBookList(sex, type, time, page), new OnResultListener<MtResultBean1<BookList>>() {
            @Override
            public void onLoadError(Throwable throwable) {
                mIsOnLoadData.set(false);
                mIsOnRefresh.set(false);
            }

            @Override
            public void onLoadSuccess(MtResultBean1<BookList> o) {
                mIsOnLoadData.set(false);
                mIsOnRefresh.set(false);
                if (o.getStatus() == 1) {
                    mBMutableLiveData.setValue(o.getData());
                }
            }
        });
        return mBMutableLiveData;
    }
}
