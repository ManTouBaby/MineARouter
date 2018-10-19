package com.hrw.main;

import com.hrw.common.baseMVP.BaseActivity;
import com.hrw.common.baseMVP.BasePresenter;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/10/18 18:02
 * @desc:
 */
public class ACMain extends BaseActivity{
    @Override
    protected void initView() {

    }

    @Override
    protected int createLayout() {
        return R.layout.ac_main_layout;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
