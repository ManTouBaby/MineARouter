package com.hrw.book.bk.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.bk.repository.BKRankRepository;
import com.hrw.book.entity.BookList;
import com.hrw.book.service.IBKType;
import com.hrw.common.baseMVVM.BaseViewModel;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/13 11:29
 * @desc:
 */
public class BKHomeRankModel extends BaseViewModel<BKRankRepository> {
    MutableLiveData<BookList> mBMutableLiveData;


    /**
     * 获取图书列表
     *
     * @param sex  性别
     * @param type 种类
     * @param time 时间分类
     * @param page 页码
     * @return
     */
    public MutableLiveData<BookList> getBookList(@IBKType.IBKReaderSex String sex, @IBKType.IBKListType String type,
                                                 @IBKType.IBKListByTime String time, int page) {
        mBMutableLiveData = mRepository.getBookList(sex, type, time, page);
        return mBMutableLiveData;
    }

    @Override
    protected BKRankRepository createRepository() {
        return new BKRankRepository();
    }
}
