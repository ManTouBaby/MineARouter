package com.hrw.main;

import com.hrw.common.baseMVP.BaseActivity;
import com.hrw.common.baseMVP.BasePresenter;

public class LoginActivity extends BaseActivity implements LoginView {

    @Override
    protected void initView() {

//        mPresenter.onSubscribe(RetrofitHelper.instance().createClass(LoginApi.class).doLogin(), new MineConsumer<LoginBean>() {
//            @Override
//            protected void acceptSuccess(LoginBean date) {
//
//            }
//
//            @Override
//            protected void acceptFail(String msg) {
//
//            }
//        });
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

