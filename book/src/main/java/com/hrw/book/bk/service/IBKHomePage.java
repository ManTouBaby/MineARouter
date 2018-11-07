package com.hrw.book.bk.service;

import android.database.Observable;

import com.hrw.book.bk.entity.HomePageBO;

import retrofit2.http.POST;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:42
 * @desc:
 */
public interface IBKHomePage {
    @POST("/resource/homepage/check")
    Observable<HomePageBO> getHomePageData();

    
}
