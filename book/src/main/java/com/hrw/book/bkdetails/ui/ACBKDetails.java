package com.hrw.book.bkdetails.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Observable;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hrw.book.R;
import com.hrw.book.bkdetails.viewmodel.BKDetailsViewModel;
import com.hrw.book.bkread.ui.ACBKRead;
import com.hrw.book.collect.ACBKCollect;
import com.hrw.book.entity.BKDetailBO;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.servicePath.BKInterface;
import com.hrw.common.utils.MtGlideUtils;
import com.hrw.common.utils.MtStatusBarHelper;
import com.hrw.common.utils.collect.AppDataBase;
import com.hrw.common.utils.collect.CollectBO;
import com.hrw.common.utils.collect.CollectType;
import com.hrw.common.utils.collect.CollectViewModel;
import com.hrw.smartview.adapter.SmartAdapter;
import com.hrw.smartview.adapter.SmartVH;
import com.hrw.smartview.listener.OnSmartItemClickListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:10
 * @desc:小说详细页面
 */
public class ACBKDetails extends BaseActivity implements NestedScrollView.OnScrollChangeListener, View.OnClickListener, OnSmartItemClickListener<BKDetailBO.SameUserBooksBean> {
    RecyclerView mRvAuthorOtherBooks;

    ImageView mIvBookBG;
    ImageView mIvTopBG;
    ImageView mIvBack;
    TextView mTvRead;
    TextView mTvCollect;
    TextView mTvCollectHome;

    TextView mTvBookChange;
    TextView mTvBookName;
    TextView mTvBookAuthor;
    TextView mTvBookType;
    TextView mTvBookStatus;
    TextView mTvBookScore;
    TextView mTvBookDesc;
    TextView mTvBookUPTime;
    TextView mTvBookUPContent;
    TextView mAuthorSameBooksTitle;
    ConstraintLayout mClHeader;
    ConstraintLayout mClToolBar;

    View mVOtherSameBooks;
    View mIvOtherSameBook1;
    View mIvOtherSameBook2;
    View mIvOtherSameBook3;
    View mIvOtherSameBook4;

    View mVHeader;
    BKDetailsViewModel mDetailsViewModel;
    CollectViewModel mCollectViewModel;
    SmartAdapter<BKDetailBO.SameUserBooksBean> mSmartAdapter;
    List<BKDetailBO.SameCategoryBooksBean> mCategoryBooksBeans;
    BKDetailBO mBkDetailBO;
    int bookId;
    boolean isCollect;


    Map<View, Map<Integer, View>> sameBKView = new HashMap();

    @Override
    protected void initView() {
        MtStatusBarHelper.instance(this).setBGColor(Color.TRANSPARENT).setFullScreen(true);
        mDetailsViewModel = ViewModelProviders.of(this).get(BKDetailsViewModel.class);
        mCollectViewModel = ViewModelProviders.of(this).get(CollectViewModel.class);
        Bundle bundle = getIntent().getExtras();
        bookId = bundle.getInt("bookId");

        initRecyclerView();

        mIvBack = findViewById(R.id.iv_back);
        mTvRead = findViewById(R.id.tv_read_book);
        mClToolBar = findViewById(R.id.cl_toolBar_container);
        mTvCollect = findViewById(R.id.tv_my_book_collect);
        mTvCollectHome = findViewById(R.id.tv_book_collect_home);
        mIvBookBG = mVHeader.findViewById(R.id.iv_book_bg);
        mIvTopBG = mVHeader.findViewById(R.id.iv_top_bg);
        mTvBookName = mVHeader.findViewById(R.id.tv_book_name);
        mTvBookAuthor = mVHeader.findViewById(R.id.tv_book_author);
        mTvBookType = mVHeader.findViewById(R.id.tv_book_type);
        mTvBookStatus = mVHeader.findViewById(R.id.tv_book_status);
        mTvBookScore = mVHeader.findViewById(R.id.tv_book_score);
        mTvBookDesc = mVHeader.findViewById(R.id.tv_book_desc);
        mTvBookUPTime = mVHeader.findViewById(R.id.tv_book_update_time);
        mTvBookUPContent = mVHeader.findViewById(R.id.tv_book_update_content);
        mAuthorSameBooksTitle = mVHeader.findViewById(R.id.tv_author_same_books);
        mClHeader = mVHeader.findViewById(R.id.mine_cl_title_container);

    }

    private void initRecyclerView() {
        initBottom();
        mVHeader = LayoutInflater.from(this).inflate(R.layout.header_book_details, null);
        mRvAuthorOtherBooks = findViewById(R.id.rv_author_other_books);
        mRvAuthorOtherBooks.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvAuthorOtherBooks.setLayoutManager(layoutManager);

        mRvAuthorOtherBooks.setAdapter(mSmartAdapter = new SmartAdapter<BKDetailBO.SameUserBooksBean>(R.layout.item_book_show) {
            @Override
            protected void bindView(SmartVH smartVH, BKDetailBO.SameUserBooksBean sameUserBooksBean, int i) {
                MtGlideUtils.bindIMG(getBaseContext(), BKInterface.ROOT_BOOK_IMG + sameUserBooksBean.getImg(), smartVH.getImage(R.id.iv_book_bg));
                smartVH.getText(R.id.tv_book_name).setText(sameUserBooksBean.getName());
                smartVH.getText(R.id.tv_book_new).setText(sameUserBooksBean.getLastChapter());
                smartVH.getText(R.id.tv_book_author).setText(sameUserBooksBean.getAuthor());
                smartVH.getText(R.id.tv_book_score).setText(sameUserBooksBean.getScore() + "分");
            }
        }.setHeaderView(mVHeader)
                .setFooterView(mVOtherSameBooks)
                .setOnSmartItemClickListener(this));

        mRvAuthorOtherBooks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int count = layoutManager.getItemCount();
                float offset = mRvAuthorOtherBooks.computeVerticalScrollOffset();
                float mClHeaderHeight = mClHeader.getHeight() / getResources().getDisplayMetrics().density;
                int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                if (offset <= mClHeaderHeight + 4 && lastVisiblePosition < count - 1) {
                    float mm = offset / mClHeaderHeight;
                    int alpha = mm < 1 ? (int) (255 * mm) : 255;
                    mClToolBar.setBackgroundColor(Color.argb(alpha, 85, 68, 85));
                } else if (offset >= mClHeaderHeight) {
                    mClToolBar.setBackgroundColor(Color.argb(255, 85, 68, 85));
                }

            }

        });

    }

    private void initBottom() {
        mVOtherSameBooks = LayoutInflater.from(this).inflate(R.layout.bottom_book_details, null);
        mTvBookChange = mVOtherSameBooks.findViewById(R.id.tv_same_book_change);
        mIvOtherSameBook1 = mVOtherSameBooks.findViewById(R.id.i_horizontal_1);
        mIvOtherSameBook2 = mVOtherSameBooks.findViewById(R.id.i_horizontal_2);
        mIvOtherSameBook3 = mVOtherSameBooks.findViewById(R.id.i_horizontal_3);
        mIvOtherSameBook4 = mVOtherSameBooks.findViewById(R.id.i_horizontal_4);
        mTvBookChange.setOnClickListener(this);
    }


    @Override
    protected void initListener() {
        mTvRead.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mTvCollect.setOnClickListener(this);
        mTvCollectHome.setOnClickListener(this);

        mDetailsViewModel.getBookDetails(bookId).observe(this, new Observer<BKDetailBO>() {
            @Override
            public void onChanged(@Nullable BKDetailBO bkDetailBO) {
                mBkDetailBO = bkDetailBO;
                MtGlideUtils.bindIMG(getBaseContext(), BKInterface.ROOT_BOOK_IMG + bkDetailBO.getImg(), mIvBookBG);
                MtGlideUtils.bindIMG(getBaseContext(), BKInterface.ROOT_BOOK_IMG + bkDetailBO.getImg(), mIvTopBG);
                mTvBookName.setText(bkDetailBO.getName());

                mTvBookAuthor.setText(bkDetailBO.getAuthor());
                mTvBookType.setText(bkDetailBO.getCName());
                mTvBookStatus.setText(bkDetailBO.getBookStatus());
                mTvBookScore.setText(bkDetailBO.getBookVote().getScore() + "分");
                mTvBookDesc.setText(bkDetailBO.getDesc());
                mTvBookUPTime.setText(bkDetailBO.getLastTime());
                mTvBookUPContent.setText(bkDetailBO.getLastChapter());
                if (bkDetailBO.getSameUserBooks() != null && bkDetailBO.getSameUserBooks().size() > 0) {
                    mAuthorSameBooksTitle.setVisibility(View.VISIBLE);
                    mSmartAdapter.setDate(bkDetailBO.getSameUserBooks());
                } else {
                    mAuthorSameBooksTitle.setVisibility(View.GONE);
                }
                if (bkDetailBO.getSameCategoryBooks() != null && bkDetailBO.getSameCategoryBooks().size() > 0) {
                    mVOtherSameBooks.setVisibility(View.VISIBLE);
                }

                mCategoryBooksBeans = bkDetailBO.getSameCategoryBooks();
                getRandomDate();
            }
        });

        mCollectViewModel.getCollectById(AppDataBase.instance(this), bookId).observe(this, new Observer<CollectBO>() {
            @Override
            public void onChanged(@Nullable CollectBO collectBO) {
                if (collectBO == null) {
                    mTvCollect.setSelected(false);
                    isCollect = false;
                } else {
                    mTvCollect.setSelected(true);
                    isCollect = true;
                }
            }
        });

        mDetailsViewModel.getIsOnLoadData().addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                if (mDetailsViewModel.isOnLoadData.get()) {

                } else {

                }
            }
        });
    }

    private void getRandomDate() {
        List<Integer> integers = new ArrayList<>();
        if (mCategoryBooksBeans.size() <= 4) {
            for (int i = 0; i < mCategoryBooksBeans.size(); i++) {
                integers.add(i);
            }
        } else {
            while (integers.size() <= 4) {
                int random = (int) (Math.random() * mCategoryBooksBeans.size());
                if (!integers.contains(random)) {
                    integers.add(random);
                }
            }
            setSameBookDate(mIvOtherSameBook1, mCategoryBooksBeans.get(integers.get(0)));
            setSameBookDate(mIvOtherSameBook2, mCategoryBooksBeans.get(integers.get(1)));
            setSameBookDate(mIvOtherSameBook3, mCategoryBooksBeans.get(integers.get(2)));
            setSameBookDate(mIvOtherSameBook4, mCategoryBooksBeans.get(integers.get(3)));
        }
    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_details;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
    }

    private void setSameBookDate(View sameBook, final BKDetailBO.SameCategoryBooksBean booksBean) {
        ImageView ivBookBG;
        TextView tvBookName;
        Map<Integer, View> viewMap = sameBKView.get(sameBook);
        if (viewMap == null) {
            viewMap = new HashMap<>();
            ivBookBG = sameBook.findViewById(R.id.iv_book_bg);
            tvBookName = sameBook.findViewById(R.id.tv_book_name);
            viewMap.put(R.id.iv_book_bg, ivBookBG);
            viewMap.put(R.id.tv_book_name, tvBookName);
            sameBKView.put(sameBook, viewMap);
        } else {
            ivBookBG = (ImageView) viewMap.get(R.id.iv_book_bg);
            tvBookName = (TextView) viewMap.get(R.id.tv_book_name);
        }

        MtGlideUtils.bindIMG(getBaseContext(), BKInterface.ROOT_BOOK_IMG + booksBean.getImg(), ivBookBG);
        tvBookName.setText(booksBean.getName());
        sameBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("bookId", booksBean.getId());
                gotoActivity(ACBKDetails.class, bundle);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_same_book_change:
                getRandomDate();
                break;
            case R.id.tv_my_book_collect:
                CollectBO collectBO = new CollectBO();
                collectBO.setCollectId(mBkDetailBO.getId());
                collectBO.setCollectAuthor(mBkDetailBO.getAuthor());
                collectBO.setCollectFirstChapterId(mBkDetailBO.getFirstChapterId() + "");
                collectBO.setCollectLastChapter(mBkDetailBO.getLastChapter());
                collectBO.setCollectLastChapterId(mBkDetailBO.getLastChapterId() + "");
                collectBO.setCollectImg(mBkDetailBO.getImg());
                collectBO.setCollectInfo(mBkDetailBO.getDesc());
                collectBO.setCollectLastTime(mBkDetailBO.getLastTime());
                collectBO.setCollectName(mBkDetailBO.getName());
                collectBO.setCollectScore(mBkDetailBO.getBookVote().getScore());
                collectBO.setCollectType(CollectType.BOOK);
                if (isCollect) {
                    mCollectViewModel.deleteCollect(AppDataBase.instance(this), collectBO);
                } else {
                    mCollectViewModel.insertCollect(AppDataBase.instance(this), collectBO);
                }
//                mTvCollect.setSelected(true);
                break;
            case R.id.tv_book_collect_home:
                gotoActivity(ACBKCollect.class);
                break;
            case R.id.tv_read_book:
                Bundle bundle = new Bundle();
                bundle.putInt("bookId", mBkDetailBO.getId());
                bundle.putInt("bookPage", mBkDetailBO.getFirstChapterId());
                gotoActivity(ACBKRead.class, bundle);
                break;
        }
    }

    @Override
    public void onSmartItemClick(BKDetailBO.SameUserBooksBean sameUserBooksBean, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("bookId", sameUserBooksBean.getId());
        gotoActivity(ACBKDetails.class, bundle);
    }
}
