package com.hrw.book.bk.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Observable;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hrw.book.R;
import com.hrw.book.adapter.BKHOmeListAdapter;
import com.hrw.book.adapter.BKHomeBannerAdapter;
import com.hrw.book.adapter.BKHomePageTrans;
import com.hrw.book.bk.viewmodel.BKHomePageModel;
import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.common.baseMVVM.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ACBKHome extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout mSRLayout;
    //    @BindView(R.id.rl_book_home_item_show)
    RecyclerView mRecyclerView;
    ViewPager mViewPager;

    BKHOmeListAdapter mBkhOmeListAdapter;
    BKHomeBannerAdapter mBannerAdapter;
    BKHomePageModel mBkHomePageModel;

    @Override
    protected void initView() {
        initRecyclerView();
        initViewPage();
        mSRLayout = findViewById(R.id.swl_book_home_container);
        mSRLayout.setOnRefreshListener(this);
        listenerData();

    }

    private void listenerData() {
        mBkHomePageModel = ViewModelProviders.of(this).get(BKHomePageModel.class);
        mBkHomePageModel.getHomeChoiceData().observe(this, new Observer<List<HomeChoiceBO>>() {
            @Override
            public void onChanged(@Nullable List<HomeChoiceBO> homeChoiceBOS) {
                mBkhOmeListAdapter.setDate(homeChoiceBOS);
            }
        });

        mBkHomePageModel.getHomeChoiceBannerData().observe(this, new Observer<List<HomeChoiceBannerBO>>() {
            @Override
            public void onChanged(@Nullable List<HomeChoiceBannerBO> homeChoiceBannerBOS) {
                List<View> views = new ArrayList<>();
                for (HomeChoiceBannerBO bannerBO : homeChoiceBannerBOS) {
                    View view = LayoutInflater.from(getBaseContext()).inflate(R.layout.banner_ads_bk_show, null);
                    ImageView imageView = view.findViewById(R.id.iv_ads_bk_bg);
                    Glide.with(getBaseContext()).load(bannerBO.getImgurl()).into(imageView);
                    views.add(view);
                }
                mBannerAdapter.setViews(views);
            }
        });
        mBkHomePageModel.getIsOnRefresh().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (!mBkHomePageModel.getIsOnRefresh().get()) {
                    mSRLayout.setRefreshing(false);
                }
            }
        });
    }

    private void initViewPage() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        mViewPager.setPageMargin((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 25, metrics));
        mBannerAdapter = new BKHomeBannerAdapter();
        mViewPager.setAdapter(mBannerAdapter);
        mViewPager.setPageTransformer(true, new BKHomePageTrans());

    }

    private void initRecyclerView() {
        View headerBanner = LayoutInflater.from(this).inflate(R.layout.header_book_home_banner, null);
        mViewPager = headerBanner.findViewById(R.id.vp_banner);
        mRecyclerView = findViewById(R.id.rl_book_home_item_show);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mBkhOmeListAdapter = new BKHOmeListAdapter(this, R.layout.item_book_home_type5);
        mRecyclerView.setAdapter(mBkhOmeListAdapter);
        mBkhOmeListAdapter.setHeaderView(headerBanner);
        mBkhOmeListAdapter.setItemType(1, R.layout.item_book_home_type1);
        mBkhOmeListAdapter.setItemType(4, R.layout.item_book_home_type4);
        mBkhOmeListAdapter.setItemType(5, R.layout.item_book_home_type5);
        mBkhOmeListAdapter.setItemType(6, R.layout.item_book_home_type6);
        mBkhOmeListAdapter.setItemType(7, R.layout.item_book_home_type7);
        mBkhOmeListAdapter.setItemType(12, R.layout.item_book_home_type12);
    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_home;
    }

    @Override
    public void onRefresh() {
        mBkHomePageModel.getHomeChoiceBannerData();
        mBkHomePageModel.getHomeChoiceData();
    }
}
