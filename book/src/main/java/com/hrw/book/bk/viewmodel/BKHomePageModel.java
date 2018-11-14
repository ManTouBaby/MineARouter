package com.hrw.book.bk.viewmodel;

import android.arch.lifecycle.MutableLiveData;

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
public class BKHomePageModel extends BaseViewModel<BKHomeRepository> {
    MutableLiveData<List<HomeChoiceBO>> mHomeChoiceBOS;
    MutableLiveData<List<HomeChoiceBannerBO>> mHomeChoiceBannerBOS;

    @Override
    protected BKHomeRepository createRepository() {
        return new BKHomeRepository();
    }

    public MutableLiveData<List<HomeChoiceBannerBO>> getHomeChoiceBannerData() {
        mHomeChoiceBannerBOS = mRepository.getHomeChoiceBannerData(IBKType.SEX_MAN);
        return mHomeChoiceBannerBOS;
    }

    public MutableLiveData<List<HomeChoiceBO>> getHomeChoiceData() {
        mHomeChoiceBOS = mRepository.getHomeChoiceData(IBKType.SEX_MAN);
        return mHomeChoiceBOS;
    }


}
