package com.hrw.book.bkdetails.ui;

import android.graphics.Color;

import com.hrw.book.R;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.utils.MtStatusBarHelper;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:10
 * @desc:
 */
public class ACBKDetails extends BaseActivity {
    @Override
    protected void initView() {
        MtStatusBarHelper.instance(this)
                .setBGColor(Color.parseColor("#554455"))
                .setFullScreen(false);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_details;
    }
}
