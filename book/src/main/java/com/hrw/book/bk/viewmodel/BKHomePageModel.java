package com.hrw.book.bk.viewmodel;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.book.service.IBKHomePage;
import com.hrw.common.baseMVVM.BaseViewModel;
import com.hrw.common.net.MtRetrofitHelper;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:48
 * @desc:
 */
public class BKHomePageModel extends BaseViewModel {
    MutableLiveData<List<HomeChoiceBO>> mHomeChoiceBOS = new MutableLiveData<>();
    MutableLiveData<List<HomeChoiceBannerBO>> mHomeChoiceBannerBOS = new MutableLiveData<>();

    IBKHomePage mIbkHomePage;

    public BKHomePageModel() {
        mIbkHomePage = MtRetrofitHelper.getRetrofit().createClass(IBKHomePage.class);
    }

    public MutableLiveData<List<HomeChoiceBannerBO>> getHomeChoiceBannerData() {
        subscribe(mIbkHomePage.getHomeChoiceBannerData(), mHomeChoiceBannerBOS);
        return mHomeChoiceBannerBOS;
    }

    public MutableLiveData<List<HomeChoiceBO>> getHomeChoiceData() {
        subscribe(mIbkHomePage.getHomeChoiceData(), mHomeChoiceBOS);
        return mHomeChoiceBOS;
    }


}
