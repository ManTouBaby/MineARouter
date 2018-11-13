package com.hrw.book.bk.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.hrw.book.bk.repository.BKHomeRepository;
import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.book.service.IBKType;
import com.hrw.common.baseMVVM.BaseViewModel;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:48
 * @desc:
 */
public class BKHomePageModel extends BaseViewModel {
    BKHomeRepository mBkRepository;

    ObservableBoolean isOnRefresh;
    ObservableBoolean isOnLoadData;

    MutableLiveData<List<HomeChoiceBO>> mHomeChoiceBOS;
    MutableLiveData<List<HomeChoiceBannerBO>> mHomeChoiceBannerBOS;


    public BKHomePageModel() {
        mBkRepository = new BKHomeRepository();
    }

    public MutableLiveData<List<HomeChoiceBannerBO>> getHomeChoiceBannerData() {
        mHomeChoiceBannerBOS = mBkRepository.getHomeChoiceBannerData(IBKType.SEX_MAN);
        return mHomeChoiceBannerBOS;
    }

    public MutableLiveData<List<HomeChoiceBO>> getHomeChoiceData() {
        mHomeChoiceBOS = mBkRepository.getHomeChoiceData(IBKType.SEX_MAN);
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
