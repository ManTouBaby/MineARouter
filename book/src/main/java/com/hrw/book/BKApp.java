package com.hrw.book;

import android.app.Application;

import com.hrw.common.net.MtRetrofitHelper;
import com.hrw.common.servicePath.BKInterface;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:28
 * @desc:
 */
public class BKApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MtRetrofitHelper.init(BKInterface.ROOT);
    }
}
