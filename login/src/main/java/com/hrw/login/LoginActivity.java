package com.hrw.login;

import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.baseMVVM.BasePresenter;
import com.hrw.common.net.MtConsumer;
import com.hrw.common.net.MtRetrofitHelper;

import io.reactivex.Observable;

public class LoginActivity extends BaseActivity implements LoginView {

    @Override
    protected void initView() {
        mPresenter.onSubscribe((Observable) MtRetrofitHelper
                .getRetrofit()
                .createClass(LoginApi.class).doLogin(), new MtConsumer<LoginBean>() {
            @Override
            protected void acceptSuccess(LoginBean date) {

            }

            @Override
            protected void acceptFail(String msg) {

            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new LoginPresenter(this);
    }
}

