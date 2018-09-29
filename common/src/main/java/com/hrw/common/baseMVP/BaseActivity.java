package com.hrw.common.baseMVP;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hrw.common.butterknife.ButterKnife;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 11:31
 * @desc:
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {
    P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.initBindView(this);

        mPresenter = createPresenter();
        setContentView(createLayout());
    }

    protected abstract int createLayout();

    protected abstract P createPresenter();

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDetach();
        }
        super.onDestroy();
    }
}
