package com.hrw.book.bkread.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.bkread.repository.BKReadRepository;
import com.hrw.book.entity.BKChapterContentBO;
import com.hrw.common.baseMVVM.BaseViewModel;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:14
 * @desc:
 */
public class BKReadViewModel extends BaseViewModel<BKReadRepository> {
    @Override
    protected BKReadRepository createRepository() {
        return new BKReadRepository();
    }

    public MutableLiveData<BKChapterContentBO> getReaderItem(int bookId, int bookPage) {
        return mRepository.getReaderItem(bookId, bookPage);
    }
}
