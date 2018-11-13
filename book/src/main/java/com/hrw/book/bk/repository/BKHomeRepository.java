package com.hrw.book.bk.repository;

import android.arch.lifecycle.MutableLiveData;

import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.book.service.IBKService;
import com.hrw.book.service.IBKType;
import com.hrw.common.baseMVVM.BaseRepository;
import com.hrw.common.net.MtObserver;
import com.hrw.common.net.MtResultBean1;
import com.hrw.common.net.MtRetrofitHelper;

import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:44
 * @desc:
 */
public class BKHomeRepository extends BaseRepository {
    IBKService mIbkHomePage;


    MutableLiveData<List<HomeChoiceBO>> mHomeChoiceBOS = new MutableLiveData<>();
    MutableLiveData<List<HomeChoiceBannerBO>> mHomeChoiceBannerBOS = new MutableLiveData<>();

    String[] mHomeItemTypeSort = {"重磅推荐", "火热新书", "分类导航", "热门连载", "重推书单", "完本精选"};

    public BKHomeRepository() {
        mIbkHomePage = MtRetrofitHelper.getRetrofit().createClass(IBKService.class);
    }

    public MutableLiveData<List<HomeChoiceBannerBO>> getHomeChoiceBannerData(@IBKType.IBKReaderSex String sex) {
        subscribe(mIbkHomePage.getHomeChoiceBannerData(sex), new MtObserver<MtResultBean1<List<HomeChoiceBannerBO>>>() {
            @Override
            public void onLoadError(Throwable throwable) {
            }

            @Override
            public void onLoadSuccess(MtResultBean1<List<HomeChoiceBannerBO>> o) {
                if (o.getStatus() == 1) {
                    mHomeChoiceBannerBOS.setValue(o.getData());
                }
            }
        });
        return mHomeChoiceBannerBOS;
    }

    public MutableLiveData<List<HomeChoiceBO>> getHomeChoiceData(@IBKType.IBKReaderSex String sex) {
        mIsOnLoadData.set(true);
        mIsOnRefresh.set(true);
        subscribe(mIbkHomePage.getHomeChoiceData(sex), new MtObserver<MtResultBean1<List<HomeChoiceBO>>>() {
            @Override
            public void onLoadError(Throwable throwable) {
                mIsOnLoadData.set(false);
                mIsOnRefresh.set(false);
            }

            @Override
            public void onLoadSuccess(MtResultBean1<List<HomeChoiceBO>> o) {
                mIsOnLoadData.set(false);
                mIsOnRefresh.set(false);
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


}
