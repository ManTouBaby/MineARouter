package com.hrw.book.service;


import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.common.net.MtResultBean1;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:42
 * @desc:
 */
public interface IBKHomePage {
    @GET("/v5/base/man.html")
    Observable<MtResultBean1<List<HomeChoiceBO>>> getHomeChoiceData();

    @GET("/v5/base/banner_man.html")
    Observable<MtResultBean1<List<HomeChoiceBannerBO>>> getHomeChoiceBannerData();
}
