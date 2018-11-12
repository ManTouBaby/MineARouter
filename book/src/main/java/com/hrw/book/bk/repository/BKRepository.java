package com.hrw.book.bk.repository;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.book.service.IBKHomePage;
import com.hrw.common.baseMVVM.BaseRepository;
import com.hrw.common.net.MtBaseObserver;
import com.hrw.common.net.MtResultBean1;
import com.hrw.common.net.MtRetrofitHelper;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:44
 * @desc:
 */
public class BKRepository extends BaseRepository {
    IBKHomePage mIbkHomePage;
    ObservableBoolean isOnRefresh = new ObservableBoolean();
    ObservableBoolean isOnLoadData = new ObservableBoolean();

    MutableLiveData<List<HomeChoiceBO>> mHomeChoiceBOS = new MutableLiveData<>();
    MutableLiveData<List<HomeChoiceBannerBO>> mHomeChoiceBannerBOS = new MutableLiveData<>();

    String[] mHomeItemTypeSort = {"重磅推荐", "火热新书", "分类导航", "热门连载", "重推书单", "完本精选"};

    public BKRepository() {
        mIbkHomePage = MtRetrofitHelper.getRetrofit().createClass(IBKHomePage.class);
    }

    public MutableLiveData<List<HomeChoiceBannerBO>> getHomeChoiceBannerData() {
        isOnLoadData.set(true);
        isOnRefresh.set(true);
        subscribe(mIbkHomePage.getHomeChoiceBannerData(), new MtBaseObserver<MtResultBean1<List<HomeChoiceBannerBO>>>() {
            @Override
            public void onLoadError(Throwable throwable) {
                isOnLoadData.set(false);
                isOnRefresh.set(false);
            }

            @Override
            public void onLoadSuccess(MtResultBean1<List<HomeChoiceBannerBO>> o) {
                isOnLoadData.set(false);
                isOnRefresh.set(false);
                if (o.getStatus() == 1) {
                    mHomeChoiceBannerBOS.setValue(o.getData());
                }
            }
        });
        return mHomeChoiceBannerBOS;
    }

    public MutableLiveData<List<HomeChoiceBO>> getHomeChoiceData() {
        isOnLoadData.set(true);
        isOnRefresh.set(true);
        subscribe(mIbkHomePage.getHomeChoiceData(), new MtBaseObserver<MtResultBean1<List<HomeChoiceBO>>>() {
            @Override
            public void onLoadError(Throwable throwable) {
                isOnLoadData.set(false);
                isOnRefresh.set(false);
            }

            @Override
            public void onLoadSuccess(MtResultBean1<List<HomeChoiceBO>> o) {
                isOnLoadData.set(false);
                isOnRefresh.set(false);
                if (o.getStatus() == 1) {
                    int index = -1;
                    for (int i = 0; i < mHomeItemTypeSort.length; i++) {
                        for (int j = i; j < o.getData().size(); j++) {
                            HomeChoiceBO choiceBO = o.getData().get(j);
                            if (choiceBO.getCategory().equals(mHomeItemTypeSort[i])) {
                                index++;
                                o.getData().remove(j);
                                o.getData().add(index, choiceBO);
                                break;
                            }
                        }
                    }
                    for (HomeChoiceBO homeChoiceBO : o.getData()) {
                        if ("火热新书".equals(homeChoiceBO.getCategory())) {
                            homeChoiceBO.setItemType(5);
                        } else if ("热门连载".equals(homeChoiceBO.getCategory())) {
                            homeChoiceBO.setItemType(7);
                        } else if ("分类导航".equals(homeChoiceBO.getCategory())) {
                            homeChoiceBO.setItemType(4);
                        } else if ("重推书单".equals(homeChoiceBO.getCategory())) {
                            homeChoiceBO.setItemType(1);
                        } else if ("完本精选".equals(homeChoiceBO.getCategory())) {
                            homeChoiceBO.setItemType(6);
                        } else {
                            homeChoiceBO.setItemType(12);
                        }
                    }
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
