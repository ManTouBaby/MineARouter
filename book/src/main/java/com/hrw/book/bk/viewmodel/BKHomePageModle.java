package com.hrw.book.bk.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.common.baseMVVM.BaseViewModel;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:48
 * @desc:
 */
public class BKHomePageModle extends BaseViewModel {
    MutableLiveData<List<HomeChoiceBO>> homeChoiceBOS = new MutableLiveData<>();
    MutableLiveData<List<HomeChoiceBannerBO>> homeChoiceBannerBOS = new MutableLiveData<>();


}
