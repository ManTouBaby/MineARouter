package com.hrw.login;

import com.hrw.common.baseMVP.BaseActivity;
import com.hrw.common.baseMVP.BasePresenter;
import com.hrw.common.net.MineConsumer;
import com.hrw.common.net.RetrofitHelper;

import io.reactivex.Observable;

public class LoginActivity extends BaseActivity implements LoginView {

    @Override
    protected void initView() {

        mPresenter.onSubscribe((Observable) RetrofitHelper.instance().createClass(LoginApi.class).doLogin(), new MineConsumer<LoginBean>() {
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

