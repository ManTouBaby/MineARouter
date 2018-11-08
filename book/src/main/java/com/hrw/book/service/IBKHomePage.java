package com.hrw.book.service;


import com.hrw.book.entity.HomeChoiceBO;

import io.reactivex.Observable;
import retrofit2.http.POST;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:42
 * @desc:
 */
public interface IBKHomePage {
    @POST("/resource/homepage/check")
    Observable<HomeChoiceBO> getHomePageData();
}
