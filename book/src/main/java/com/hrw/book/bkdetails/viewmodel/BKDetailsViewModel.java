package com.hrw.book.bkdetails.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.bkdetails.repository.BKDetailRepository;
import com.hrw.book.entity.BKDetailBO;
import com.hrw.common.baseMVVM.BaseViewModel;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:12
 * @desc:
 */
public class BKDetailsViewModel extends BaseViewModel<BKDetailRepository> {
    MutableLiveData<BKDetailBO> mBookDetails;

    public BKDetailsViewModel() {
    }

    @Override
    protected BKDetailRepository createRepository() {
        return new BKDetailRepository();
    }

    public MutableLiveData<BKDetailBO> getBookDetails(int bookId) {
        mBookDetails = mRepository.getBookDetail(bookId);
        return mBookDetails;
    }


}
