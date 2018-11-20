package com.hrw.book.bkread.repository;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.entity.BKReadLabelBO;
import com.hrw.common.baseMVVM.BaseRepository;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:13
 * @desc:
 */
public class BKReadRepository extends BaseRepository {
    MutableLiveData<BKReadLabelBO> mBKReadLableBO = new MutableLiveData<>();


}
