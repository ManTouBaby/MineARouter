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
import com.hrw.book.entity.BKDetailBO;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.servicePath.BKInterface;
import com.hrw.common.utils.MtGlideUtils;
import com.hrw.common.utils.MtStatusBarHelper;
import com.hrw.smartrecyclerviewlibrary.SmartAdapter;
import com.hrw.smartrecyclerviewlibrary.SmartVH;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:10
 * @desc:
 */
public class ACBKDetails extends BaseActivity implements NestedScrollView.OnScrollChangeListener, View.OnClickListener {
    RecyclerView mRvAuthorOtherBooks;

    ImageView mIvBookBG;
    ImageView mIvTopBG;
    ImageView mIvBack;

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
    View mVHeader;

    BKDetailsViewModel mDetailsViewModel;
    SmartAdapter<BKDetailBO.SameUserBooksBean> mSmartAdapter;
    int bookId;

    @Override
    protected void initView() {
        MtStatusBarHelper.instance(this).setBGColor(Color.TRANSPARENT).setFullScreen(true);
        mDetailsViewModel = ViewModelProviders.of(this).get(BKDetailsViewModel.class);
        Bundle bundle = getIntent().getExtras();
        bookId = bundle.getInt("bookId");

        initRecyclerView();

        mIvBack = findViewById(R.id.iv_back);
        mClToolBar = findViewById(R.id.cl_toolBar_container);
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
        mRvAuthorOtherBooks = findViewById(R.id.rv_author_other_books);
        mVOtherSameBooks = LayoutInflater.from(this).inflate(R.layout.bottom_book_details, null);
        mVHeader = LayoutInflater.from(this).inflate(R.layout.header_book_details, null);
        mRvAuthorOtherBooks.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRvAuthorOtherBooks.setLayoutManager(layoutManager);
        mSmartAdapter = new SmartAdapter<BKDetailBO.SameUserBooksBean>(R.layout.item_book_show) {
            @Override
            protected void bindView(SmartVH smartVH, BKDetailBO.SameUserBooksBean sameUserBooksBean, int i) {
                MtGlideUtils.bindIMG(getBaseContext(), BKInterface.ROOT_BOOK_IMG + sameUserBooksBean.getImg(), smartVH.getImage(R.id.iv_book_bg));
                smartVH.getText(R.id.tv_book_name).setText(sameUserBooksBean.getName());
                smartVH.getText(R.id.tv_book_new).setText(sameUserBooksBean.getLastChapter());
                smartVH.getText(R.id.tv_book_author).setText(sameUserBooksBean.getAuthor());
                smartVH.getText(R.id.tv_book_score).setText(sameUserBooksBean.getScore() + "分");
            }
        };
        mSmartAdapter.setHeaderView(mVHeader);
        mSmartAdapter.setFooterView(mVOtherSameBooks);
        mRvAuthorOtherBooks.setAdapter(mSmartAdapter);
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
//                    System.out.println("mClHeaderHeight:" + mClHeaderHeight + "  offset:" + offset + "  mm:" + mm + "  lastVisiblePosition:" + lastVisiblePosition);
                } else if (offset >= mClHeaderHeight) {
                    mClToolBar.setBackgroundColor(Color.argb(255, 85, 68, 85));
                }

            }

        });

    }

    @Override
    protected void initListener() {
        mIvBack.setOnClickListener(this);

        mDetailsViewModel.getBookDetails(bookId).observe(this, new Observer<BKDetailBO>() {
            @Override
            public void onChanged(@Nullable BKDetailBO bkDetailBO) {
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
                }
                if (bkDetailBO.getSameCategoryBooks() != null && bkDetailBO.getSameCategoryBooks().size() > 0) {
                    mVOtherSameBooks.setVisibility(View.VISIBLE);
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

    @Override
    protected int createLayout() {
        return R.layout.ac_book_details;
    }

    @Override
    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
        System.out.println("scrollX:" + scrollX + "  scrollY:" + scrollY + "  oldScrollX:" + oldScrollX + "  oldScrollY:" + oldScrollY);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
