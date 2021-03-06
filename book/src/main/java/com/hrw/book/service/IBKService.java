package com.hrw.book.service;


import com.hrw.book.entity.BKChapterBO;
import com.hrw.book.entity.BKChapterContentBO;
import com.hrw.book.entity.BKDetailBO;
import com.hrw.book.entity.BookList;
import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.common.net.MtResultBean1;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:42
 * @desc:
 */
public interface IBKService {

    /**
     * 获取首页接口
     *
     * @param sex
     * @return
     */
    @GET("v5/base/{sex}" + ".html")
    Observable<MtResultBean1<List<HomeChoiceBO>>> getHomeChoiceData(@Path("sex") @IBKType.IBKReaderSex String sex);

    /**
     * 获取首页Banner接口
     *
     * @param sex
     * @return
     */
    @GET("v5/base/banner_{sex}" + ".html")
    Observable<MtResultBean1<List<HomeChoiceBannerBO>>> getHomeChoiceBannerData(@Path("sex") @IBKType.IBKReaderSex String sex);

    /**
     * 获取榜单接口
     *
     * @param sex
     * @param type
     * @param time
     * @param page
     * @return
     */
    @GET("top/{sex}/top/{type}/{time}/{page}.html")
    Observable<MtResultBean1<BookList>> getTopBookList(@Path("sex") @IBKType.IBKReaderSex String sex, @Path("type") @IBKType.IBKListType String type,
                                                       @Path("time") @IBKType.IBKListByTime String time, @Path("page") int page);

    /**
     * 获取书单
     *
     * @param sex
     * @param type
     * @param page
     * @return
     */
    @GET("shudan/{sex}/all/{type}/{page}.html")
    Observable<MtResultBean1> getBookList(@Path("sex") @IBKType.IBKReaderSex String sex, @Path("type") @IBKType.IBKListType String type, @Path("page") int page);

    /**
     * 获取图书详细信息
     *
     * @param bookId
     * @return
     */
    @GET("info/{bookId}.html")
    Observable<MtResultBean1<BKDetailBO>> getBookDetail(@Path("bookId") int bookId);

    /**
     * 获取小说内容
     *
     * @return
     */
    @GET("book/{bookId}/{bookPage}.html")
    Observable<MtResultBean1<BKChapterContentBO>> getReadContent(@Path("bookId") int bookId, @Path("bookPage") int bookPage);

    /**
     * 获取小说章节
     *
     * @return
     */
    @GET("book/{bookId}/")
    Observable<MtResultBean1<BKChapterBO>> getChapterItem(@Path("bookId") int bookId);
}
