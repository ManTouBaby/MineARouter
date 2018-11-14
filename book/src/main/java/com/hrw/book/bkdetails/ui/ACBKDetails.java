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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    NestedScrollView mScrollView;
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

    RecyclerView mRvAuthorOtherBooks;

    LinearLayout mllAuthorOtherBooks;
    ConstraintLayout mClOtherSameBooks;


    BKDetailsViewModel mDetailsViewModel;
    SmartAdapter<BKDetailBO.SameUserBooksBean> mSmartAdapter;
    int bookId;

    @Override
    protected void initView() {
        Bundle bundle = getIntent().getExtras();
        bookId = bundle.getInt("bookId");

        mScrollView = findViewById(R.id.mine_main_container);
        mScrollView.scrollTo(0, 0);
        mIvBookBG = findViewById(R.id.iv_book_bg);
        mIvTopBG = findViewById(R.id.iv_top_bg);
        mIvBack = findViewById(R.id.iv_back);
        mTvBookName = findViewById(R.id.tv_book_name);
        mTvBookAuthor = findViewById(R.id.tv_book_author);
        mTvBookType = findViewById(R.id.tv_book_type);
        mTvBookStatus = findViewById(R.id.tv_book_status);
        mTvBookScore = findViewById(R.id.tv_book_score);
        mTvBookDesc = findViewById(R.id.tv_book_desc);
        mTvBookUPTime = findViewById(R.id.tv_book_update_time);
        mTvBookUPContent = findViewById(R.id.tv_book_update_content);
        mllAuthorOtherBooks = findViewById(R.id.ll_author_other_books);
        mClOtherSameBooks = findViewById(R.id.cl_other_same_book);
        initRecyclerView();
        MtStatusBarHelper.instance(this).setBGColor(Color.TRANSPARENT).setFullScreen(true);
        mDetailsViewModel = ViewModelProviders.of(this).get(BKDetailsViewModel.class);
    }

    private void initRecyclerView() {
        mRvAuthorOtherBooks = findViewById(R.id.rv_author_other_books);
        mRvAuthorOtherBooks.setHasFixedSize(true);
        mRvAuthorOtherBooks.setLayoutManager(new LinearLayoutManager(this));
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
        mRvAuthorOtherBooks.setAdapter(mSmartAdapter);
    }

    @Override
    protected void initListener() {
        mScrollView.setOnScrollChangeListener(this);
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
                    mllAuthorOtherBooks.setVisibility(View.VISIBLE);
                    mSmartAdapter.setDate(bkDetailBO.getSameUserBooks());
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
