package com.hrw.login;

import com.hrw.common.net.ResultBean;

import io.reactivex.Observer;
import retrofit2.http.POST;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 18:14
 * @desc:
 */
public interface LoginApi {
    @POST("/login")
    Observer<ResultBean<LoginBean>> doLogin();
}
