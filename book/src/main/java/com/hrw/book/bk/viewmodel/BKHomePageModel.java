package com.hrw.book.bk.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.hrw.book.bk.repository.BKRepository;
import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.common.baseMVVM.BaseViewModel;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:48
 * @desc:
 */
public class BKHomePageModel extends BaseViewModel {
    BKRepository mBkRepository;

    ObservableBoolean isOnRefresh;
    ObservableBoolean isOnLoadData;

    MutableLiveData<List<HomeChoiceBO>> mHomeChoiceBOS;
    MutableLiveData<List<HomeChoiceBannerBO>> mHomeChoiceBannerBOS;


    public BKHomePageModel() {
        mBkRepository = new BKRepository();
    }

    public MutableLiveData<List<HomeChoiceBannerBO>> getHomeChoiceBannerData() {
//        isOnRefresh.set(true);
        mHomeChoiceBannerBOS = mBkRepository.getHomeChoiceBannerData();
        return mHomeChoiceBannerBOS;
    }

    public MutableLiveData<List<HomeChoiceBO>> getHomeChoiceData() {
//        isOnRefresh.set(true);
        mHomeChoiceBOS = mBkRepository.getHomeChoiceData();
        return mHomeChoiceBOS;
    }


    public ObservableBoolean getIsOnLoadData() {
        isOnLoadData = mBkRepository.getIsOnLoadData();
        return isOnLoadData;
    }

    public ObservableBoolean getIsOnRefresh() {
        isOnRefresh = mBkRepository.getIsOnRefresh();
        return isOnRefresh;
    }


}
