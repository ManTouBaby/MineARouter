package com.hrw.book.bk.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Observable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hrw.book.R;
import com.hrw.book.bk.viewmodel.BKHomePageModel;
import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.butterknife.BindView;

import java.util.List;

public class ACBKHome extends BaseActivity {
    @BindView(R.id.swl_book_home_container)
    SwipeRefreshLayout mSRLayout;
    @BindView(R.id.rl_book_home_item_show)
    RecyclerView mRecyclerView;


    BKHomePageModel mBkHomePageModel;

    @Override
    protected void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);


        mBkHomePageModel = ViewModelProviders.of(this).get(BKHomePageModel.class);
        mBkHomePageModel.getHomeChoiceData().observe(this, new Observer<List<HomeChoiceBO>>() {
            @Override
            public void onChanged(@Nullable List<HomeChoiceBO> homeChoiceBOS) {
                System.out.println("HomeChoiceBO数据改变了");
            }
        });

        mBkHomePageModel.getHomeChoiceBannerData().observe(this, new Observer<List<HomeChoiceBannerBO>>() {
            @Override
            public void onChanged(@Nullable List<HomeChoiceBannerBO> homeChoiceBannerBOS) {
                System.out.println("HomeChoiceBannerBO数据改变了");
            }
        });

        mBkHomePageModel.getIsOnLoadData().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {

            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_home;
    }


}
