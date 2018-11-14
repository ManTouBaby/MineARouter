package com.hrw.book.bkdetails.repository;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.entity.BKDetailBO;
import com.hrw.book.service.IBKService;
import com.hrw.common.baseMVVM.BaseRepository;
import com.hrw.common.net.MtObserver;
import com.hrw.common.net.MtResultBean1;
import com.hrw.common.net.MtRetrofitHelper;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:10
 * @desc:
 */
public class BKDetailRepository extends BaseRepository {
    MutableLiveData<BKDetailBO> mBookDetails = new MutableLiveData<>();

    IBKService mIbkService;

    public BKDetailRepository() {
        mIbkService = MtRetrofitHelper.getRetrofit().createClass(IBKService.class);
    }

    public MutableLiveData<BKDetailBO> getBookDetail(int bookId) {
        mIsOnLoadData.set(true);
        mIsOnRefresh.set(true);
        subscribe(mIbkService.getBookDetail(bookId), new MtObserver<MtResultBean1<BKDetailBO>>() {
            @Override
            public void onLoadError(Throwable throwable) {
                mIsOnLoadData.set(false);
                mIsOnRefresh.set(false);
            }

            @Override
            public void onLoadSuccess(MtResultBean1<BKDetailBO> o) {
                mIsOnLoadData.set(false);
                mIsOnRefresh.set(false);
                if (o.getStatus() == 1) {
                    mBookDetails.setValue(o.getData());
                }
            }
        });
        return mBookDetails;
    }
}
