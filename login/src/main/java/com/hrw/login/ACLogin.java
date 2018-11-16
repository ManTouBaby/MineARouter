package com.hrw.login;

import android.graphics.Color;
import android.os.Bundle;

import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.utils.MtStatusBarHelper;

public class ACLogin extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MtStatusBarHelper.instance(this)
                .setBGColor(Color.TRANSPARENT)
                .setFullScreen(true);
    }


    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int createLayout() {
        return R.layout.activity_aclogin;
    }
}
