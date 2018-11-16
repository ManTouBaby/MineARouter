package com.hrw.common.net;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/16 9:10
 * @desc:
 */
public interface OnResultListener<T> {

    void onLoadError(Throwable throwable);

    void onLoadSuccess(T o);

}
