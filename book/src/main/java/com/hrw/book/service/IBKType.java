package com.hrw.book.service;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/13 15:36
 * @desc:
 */
public class IBKType {

    //    男生:man  女生:lady
    //    周榜:week 月榜:month 总榜:total   1表示当前页数
    //    最热榜单:hot  推荐榜单:commend 完结榜单:over 收藏榜单:collect  新书榜单:new  评分榜单:vote
    public static final String SEX_LADY = "lady";
    public static final String SEX_MAN = "man";

    public static final String TYPE_HOT = "hot";
    public static final String TYPE_COMMEND = "commend";
    public static final String TYPE_OVER = "over";
    public static final String TYPE_COLLECT = "collect";
    public static final String TYPE_NEW = "new";
    public static final String TYPE_VOTE = "vote";

    public static final String TIME_TYPE_WEEK = "week";
    public static final String TIME_TYPE_MONTH = "month";
    public static final String TIME_TYPE_TOTAL = "total";


    @StringDef({
            SEX_MAN,
            SEX_LADY
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface IBKReaderSex {
    }

    @StringDef({
            TYPE_HOT,
            TYPE_COMMEND,
            TYPE_OVER,
            TYPE_COLLECT,
            TYPE_NEW,
            TYPE_VOTE,
    })
    public @Retention(RetentionPolicy.SOURCE)
    @interface IBKListType {
    }

    @StringDef({
            TIME_TYPE_WEEK,
            TIME_TYPE_MONTH,
            TIME_TYPE_TOTAL
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface IBKListByTime {
    }
}
