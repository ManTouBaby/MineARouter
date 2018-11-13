package com.hrw.common.baseMVVM;

import android.content.Intent;
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
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.initBindView(this);
        setContentView(createLayout());
        initView();
        initListener();
    }

    protected abstract void initView();
    protected abstract void initListener();

    protected abstract int createLayout();

    protected void gotoActivity(Class<?> aClass) {
        Intent intent = new Intent(this, aClass);
        startActivity(intent);
    }

    protected void gotoActivity(Class<?> aClass, Bundle bundle) {
        Intent intent = new Intent(this, aClass);
        intent.putExtras(bundle);
        startActivity(intent);
    }


}
