package com.hrw.book.bk.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.book.service.IBKHomePage;
import com.hrw.common.baseMVVM.BaseViewModel;
import com.hrw.common.net.MtBaseObserver;
import com.hrw.common.net.MtResultBean1;
import com.hrw.common.net.MtRetrofitHelper;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:48
 * @desc:
 */
public class BKHomePageModel extends BaseViewModel {
    ObservableBoolean isOnRefresh = new ObservableBoolean();
    ObservableBoolean isOnLoadData = new ObservableBoolean();

    MutableLiveData<List<HomeChoiceBO>> mHomeChoiceBOS = new MutableLiveData<>();
    MutableLiveData<List<HomeChoiceBannerBO>> mHomeChoiceBannerBOS = new MutableLiveData<>();

    IBKHomePage mIbkHomePage;


    public BKHomePageModel() {
        mIbkHomePage = MtRetrofitHelper.getRetrofit().createClass(IBKHomePage.class);
    }

    public MutableLiveData<List<HomeChoiceBannerBO>> getHomeChoiceBannerData() {
        isOnLoadData.set(true);
        subscribe(mIbkHomePage.getHomeChoiceBannerData(), new MtBaseObserver<MtResultBean1<List<HomeChoiceBannerBO>>>() {
            @Override
            public void onLoadError(Throwable throwable) {

            }

            @Override
            public void onLoadSuccess(MtResultBean1<List<HomeChoiceBannerBO>> o) {
                isOnLoadData.set(false);
                if (o.getStatus() == 1) {
                    mHomeChoiceBannerBOS.setValue(o.getData());
                }
            }
        });
        return mHomeChoiceBannerBOS;
    }

    public MutableLiveData<List<HomeChoiceBO>> getHomeChoiceData() {
        subscribe(mIbkHomePage.getHomeChoiceData(), new MtBaseObserver<MtResultBean1<List<HomeChoiceBO>>>() {
            @Override
            public void onLoadError(Throwable throwable) {

            }

            @Override
            public void onLoadSuccess(MtResultBean1<List<HomeChoiceBO>> o) {
                if (o.getStatus() == 1) {
                    mHomeChoiceBOS.setValue(o.getData());
                }
            }
        });
        return mHomeChoiceBOS;
    }


    public ObservableBoolean getIsOnLoadData() {
        return isOnLoadData;
    }

    public ObservableBoolean getIsOnRefresh() {
        return isOnRefresh;
    }
}
