package com.hrw.book.bk.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.hrw.book.R;
import com.hrw.book.bk.viewmodel.BKHomePageModel;
import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;

import java.util.List;

public class ACBKHome extends AppCompatActivity {
    BKHomePageModel mBkHomePageModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_book_home);
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
    }


}
