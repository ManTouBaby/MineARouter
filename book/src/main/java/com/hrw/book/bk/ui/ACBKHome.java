package com.hrw.book.bk.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Observable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hrw.book.R;
import com.hrw.book.bk.viewmodel.BKHomePageModel;
import com.hrw.book.entity.BKListItemBO;
import com.hrw.book.entity.HomeChoiceBO;
import com.hrw.book.entity.HomeChoiceBannerBO;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.butterknife.BindView;
import com.hrw.smartrecyclerviewlibrary.SmartAdapter;
import com.hrw.smartrecyclerviewlibrary.SmartVH;

import java.util.List;

import static com.hrw.common.servicePath.BKInterface.ROOT_BOOK_IMG;

public class ACBKHome extends BaseActivity {
    @BindView(R.id.swl_book_home_container)
    SwipeRefreshLayout mSRLayout;
    //    @BindView(R.id.rl_book_home_item_show)
    RecyclerView mRecyclerView;

    SmartAdapter<HomeChoiceBO> mSmartAdapter;
    BKHomePageModel mBkHomePageModel;

    @Override
    protected void initView() {
        initRecyclerView();
        mBkHomePageModel = ViewModelProviders.of(this).get(BKHomePageModel.class);
        mBkHomePageModel.getHomeChoiceData().observe(this, new Observer<List<HomeChoiceBO>>() {
            @Override
            public void onChanged(@Nullable List<HomeChoiceBO> homeChoiceBOS) {
                System.out.println("HomeChoiceBO数据改变了");
                mSmartAdapter.setDate(homeChoiceBOS);
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

    private void initRecyclerView() {
        mRecyclerView = findViewById(R.id.rl_book_home_item_show);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mSmartAdapter = new SmartAdapter<HomeChoiceBO>(R.layout.item_book_home_type5) {
            @Override
            protected void bindView(SmartVH smartVH, HomeChoiceBO homeChoiceBO, int i) {
                LinearLayout llVertical;
                LinearLayout llHorizontal;
                LinearLayout llHorizontal2;
                TextView tvShowItemType;
                switch (homeChoiceBO.getItemType()) {
                    case 1:
                        smartVH.getText(R.id.tv_book_name).setText(homeChoiceBO.getBookList().getTitle());
                        smartVH.getText(R.id.tv_book_decr).setText(homeChoiceBO.getBookList().getDescription());
                        Glide.with(getBaseContext()).load(homeChoiceBO.getBookList().getImgUrl()).into(smartVH.getImage(R.id.iv_book_bg));
                        break;
                    case 4:
                        smartVH.getText(R.id.tv_book_type1).setText(homeChoiceBO.getCategories().get(0).getCategoryName());
                        smartVH.getText(R.id.tv_book_type2).setText(homeChoiceBO.getCategories().get(1).getCategoryName());
                        smartVH.getText(R.id.tv_book_type3).setText(homeChoiceBO.getCategories().get(2).getCategoryName());
                        smartVH.getText(R.id.tv_book_type4).setText(homeChoiceBO.getCategories().get(3).getCategoryName());
                        break;
                    case 5:
                        tvShowItemType = smartVH.getText(R.id.tv_item_type_name);
                        llVertical = smartVH.getViewById(R.id.ll_vertical_container);
                        llHorizontal = smartVH.getViewById(R.id.ll_horizontal_container);
                        llVertical.addView(getViewShow1(homeChoiceBO.getBooks().get(0)));
                        llVertical.addView(getViewShow1(homeChoiceBO.getBooks().get(1)));
                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(2)), 0);
                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(3)), 2);
                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(4)), 4);

                        tvShowItemType.setText(homeChoiceBO.getCategory());
                        break;
                    case 6:
                        tvShowItemType = smartVH.getText(R.id.tv_item_type_name);
                        llHorizontal = smartVH.getViewById(R.id.ll_horizontal_container);
                        llHorizontal2 = smartVH.getViewById(R.id.ll_horizontal_container2);
                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(0)), 0);
                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(1)), 2);
                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(2)), 4);
                        llHorizontal2.addView(getViewShow3(homeChoiceBO.getBooks().get(3)), 0);
                        llHorizontal2.addView(getViewShow3(homeChoiceBO.getBooks().get(4)), 2);
                        llHorizontal2.addView(getViewShow3(homeChoiceBO.getBooks().get(5)), 4);

                        tvShowItemType.setText(homeChoiceBO.getCategory());
                        break;
                    case 7:
                        tvShowItemType = smartVH.getText(R.id.tv_item_type_name);
                        llVertical = smartVH.getViewById(R.id.ll_vertical_container);
                        llHorizontal = smartVH.getViewById(R.id.ll_horizontal_container);
                        llHorizontal2 = smartVH.getViewById(R.id.ll_horizontal_container2);
                        llVertical.addView(getViewShow1(homeChoiceBO.getBooks().get(0)));

                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(1)), 0);
                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(2)), 2);
                        llHorizontal.addView(getViewShow3(homeChoiceBO.getBooks().get(3)), 4);

                        llHorizontal2.addView(getViewShow3(homeChoiceBO.getBooks().get(4)), 0);
                        llHorizontal2.addView(getViewShow3(homeChoiceBO.getBooks().get(5)), 2);
                        llHorizontal2.addView(getViewShow3(homeChoiceBO.getBooks().get(6)), 4);
                        tvShowItemType.setText(homeChoiceBO.getCategory());
                        break;
                    case 12:
                        tvShowItemType = smartVH.getText(R.id.tv_item_type_name);

                        tvShowItemType.setText(homeChoiceBO.getCategory());
                        break;

                }
            }
        };

        View headerBanner = LayoutInflater.from(this).inflate(R.layout.header_book_home_banner, null);
//        View headerMenu = LayoutInflater.from(this).inflate(R.layout.header_book_home_menu, null);
        mRecyclerView.setAdapter(mSmartAdapter);
        mSmartAdapter.setHeaderView(headerBanner);
        mSmartAdapter.setItemType(1, R.layout.item_book_home_type1);
        mSmartAdapter.setItemType(4, R.layout.item_book_home_type4);
        mSmartAdapter.setItemType(5, R.layout.item_book_home_type5);
        mSmartAdapter.setItemType(6, R.layout.item_book_home_type6);
        mSmartAdapter.setItemType(7, R.layout.item_book_home_type7);
        mSmartAdapter.setItemType(12, R.layout.item_book_home_type12);
    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_home;
    }

    private View getViewShow1(BKListItemBO listItemBO) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_book_show1, null);
        ImageView ivBookBG = view.findViewById(R.id.iv_book_bg);
        TextView tvBookName = view.findViewById(R.id.tv_book_name);
        TextView tvBookDesc = view.findViewById(R.id.tv_book_desc);
        TextView tvBookAuthor = view.findViewById(R.id.tv_book_author);
        TextView tvBookType = view.findViewById(R.id.tv_book_type);
        Glide.with(getBaseContext()).load(ROOT_BOOK_IMG + listItemBO.getImg()).into(ivBookBG);
        tvBookName.setText(listItemBO.getName());
        tvBookDesc.setText(listItemBO.getDesc());
        tvBookAuthor.setText(listItemBO.getAuthor());
        tvBookType.setText(listItemBO.getCName());
        return view;
    }

    private View getViewShow3(BKListItemBO listItemBO) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_book_show3, null);
        ImageView ivBookBG = view.findViewById(R.id.iv_book_bg);
        TextView tvBookName = view.findViewById(R.id.tv_book_name);
        TextView tvBookAuthor = view.findViewById(R.id.tv_book_author);
        Glide.with(getBaseContext()).load(ROOT_BOOK_IMG + listItemBO.getImg()).into(ivBookBG);
        tvBookName.setText(listItemBO.getName());
        tvBookAuthor.setText(listItemBO.getAuthor());
        return view;
    }

    private View getViewShow4(BKListItemBO listItemBO) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_book_show4, null);
        ImageView ivBookBG = view.findViewById(R.id.iv_book_bg);
        TextView tvBookName = view.findViewById(R.id.tv_book_name);
        Glide.with(getBaseContext()).load(ROOT_BOOK_IMG + listItemBO.getImg()).into(ivBookBG);
        tvBookName.setText(listItemBO.getName());
        return view;
    }

}
