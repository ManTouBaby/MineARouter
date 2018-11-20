package com.hrw.book.bk.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hrw.book.R;
import com.hrw.common.baseMVVM.BaseActivity;

/**
 * @author:MtBaby
 * @date:2018/11/19 21:00
 * @desc:
 */
public class ACBKSort extends BaseActivity {
    RecyclerView mRvBookSort;

    @Override
    protected void initView() {
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRvBookSort = findViewById(R.id.rv_book_sort_show);
        mRvBookSort.setLayoutManager(new LinearLayoutManager(this));
        
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_sort;
    }
}
