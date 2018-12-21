package com.hrw.book.bkread.repository;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.entity.BKChapterBO;
import com.hrw.book.entity.BKChapterContentBO;
import com.hrw.book.service.IBKService;
import com.hrw.common.baseMVVM.BaseRepository;
import com.hrw.common.baseMVVM.ErrorResult;
import com.hrw.common.net.MtResultBean1;
import com.hrw.common.net.MtRetrofitHelper;
import com.hrw.common.net.OnResultListener;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:13
 * @desc:
 */
public class BKReadRepository extends BaseRepository {
    private final IBKService mIbkHomePage;
    MutableLiveData<BKChapterContentBO> mBKReadLabelBO = new MutableLiveData<>();
    MutableLiveData<BKChapterBO> mChapterItems = new MutableLiveData<>();


    public BKReadRepository() {
        mIbkHomePage = MtRetrofitHelper.getRetrofit().createClass(IBKService.class);
    }

    public MutableLiveData<BKChapterContentBO> getReaderContent(int bookId, int bookPage) {
        mIsOnLoadData.set(true);
        subscribe(mIbkHomePage.getReadContent(bookId, bookPage), new OnResultListener<MtResultBean1<BKChapterContentBO>>() {
            @Override
            public void onLoadError(Throwable throwable) {
                mIsOnLoadData.set(false);
                mErrorResult.setValue(new ErrorResult(-1, throwable.getMessage()));
            }

            @Override
            public void onLoadSuccess(MtResultBean1<BKChapterContentBO> o) {
                mIsOnLoadData.set(false);
                if (o.getStatus() == 1) {
                    mBKReadLabelBO.setValue(o.getData());
                } else {
                    mErrorResult.setValue(new ErrorResult(o.getStatus(), o.getInfo()));
                }
            }
        });
        return mBKReadLabelBO;
    }

    public MutableLiveData<BKChapterBO> getChapterItem(int bookId) {
        mIsOnLoadData.set(true);
        subscribe(mIbkHomePage.getChapterItem(bookId), new OnResultListener<MtResultBean1<BKChapterBO>>() {
            @Override
            public void onLoadError(Throwable throwable) {
                mIsOnLoadData.set(false);
                mErrorResult.setValue(new ErrorResult(-1, throwable.getMessage()));
            }

            @Override
            public void onLoadSuccess(MtResultBean1<BKChapterBO> o) {
                mIsOnLoadData.set(false);
                if (o.getStatus() == 1) {
                    mChapterItems.setValue(o.getData());
                } else {
                    mErrorResult.setValue(new ErrorResult(o.getStatus(), o.getInfo()));
                }
            }
        });
        return mChapterItems;
    }
}
