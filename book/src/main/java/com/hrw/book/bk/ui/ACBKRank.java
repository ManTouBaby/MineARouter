package com.hrw.book.bk.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hrw.book.R;
import com.hrw.book.bk.viewmodel.BKHomeRankModel;
import com.hrw.book.bkdetails.ui.ACBKDetails;
import com.hrw.book.entity.BooKBO;
import com.hrw.book.entity.BookList;
import com.hrw.book.service.IBKType;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.utils.MtGlideUtils;
import com.hrw.smartrecyclerviewlibrary.OnSmartItemClickListener;
import com.hrw.smartrecyclerviewlibrary.SmartAdapter;
import com.hrw.smartrecyclerviewlibrary.SmartVH;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static com.hrw.common.servicePath.BKInterface.ROOT_BOOK_IMG;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/13 11:22
 * @desc:
 */
public class ACBKRank extends BaseActivity implements RadioGroup.OnCheckedChangeListener, OnSmartItemClickListener<BooKBO> {
    RecyclerView mRecyclerView;
    RadioGroup mRgSex;
    RadioGroup mRgType;
    RadioGroup mRgTimeType;
    TextView tvCurrentSelect;

    BKHomeRankModel mBkHomeRankModel;

    SmartAdapter<BooKBO> mSmartAdapter;
    private boolean mHasNext;


    String sex = IBKType.SEX_MAN;
    String type = IBKType.TYPE_HOT;
    String timeType = IBKType.TIME_TYPE_WEEK;
    int page = 1;

    @Override
    protected void initView() {
        mBkHomeRankModel = ViewModelProviders.of(this).get(BKHomeRankModel.class);
        initRecyclerView();
        tvCurrentSelect = findViewById(R.id.tv_current_select);
        ((RadioButton) mRgSex.getChildAt(0)).setChecked(true);
        ((RadioButton) mRgType.getChildAt(0)).setChecked(true);
        ((RadioButton) mRgTimeType.getChildAt(0)).setChecked(true);
    }

    @Override
    protected void initListener() {
        mRgSex.setOnCheckedChangeListener(this);
        mRgType.setOnCheckedChangeListener(this);
        mRgTimeType.setOnCheckedChangeListener(this);

        mBkHomeRankModel.getBookList(sex, type, timeType, page).observe(this, new Observer<BookList>() {
            @Override
            public void onChanged(@Nullable BookList bookList) {
                mHasNext = bookList.isHasNext();
                if (bookList.getPage() == 1) {
                    mSmartAdapter.setDate(bookList.getBookList());
                } else {
                    mSmartAdapter.addDate(bookList.getBookList());
                }
            }
        });
    }

    private void initRecyclerView() {
        mSmartAdapter = new SmartAdapter<BooKBO>(R.layout.item_book_show2) {
            @Override
            protected void bindView(SmartVH smartVH, BooKBO bkListItemBO, int i) {
                smartVH.getText(R.id.tv_book_name).setText(bkListItemBO.getName());
                smartVH.getText(R.id.tv_book_type).setText(bkListItemBO.getCName());
                smartVH.getText(R.id.tv_book_author).setText(bkListItemBO.getAuthor());
                smartVH.getText(R.id.tv_book_desc).setText(bkListItemBO.getDesc());
                smartVH.getText(R.id.tv_book_score).setText(bkListItemBO.getScore() + "分");
                ImageView imageView = smartVH.getImage(R.id.iv_book_bg);
                MtGlideUtils.bindIMG(getBaseContext(), ROOT_BOOK_IMG + bkListItemBO.getImg(), imageView);
            }
        };
        final View header = LayoutInflater.from(this).inflate(R.layout.header_book_rank_select, null);
        mRgSex = header.findViewById(R.id.rg_book_reader_sex);
        mRgType = header.findViewById(R.id.rg_book_type);
        mRgTimeType = header.findViewById(R.id.rg_book_time_type);

        mRecyclerView = findViewById(R.id.rv_book_rank_list_show);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mSmartAdapter.setHeaderView(header);
        mSmartAdapter.setOnSmartItemClickListener(this);
        mRecyclerView.setAdapter(mSmartAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                float headerHeight = header.getMeasuredHeight() / getResources().getDisplayMetrics().density;
                float offset = mRecyclerView.computeVerticalScrollOffset();
                int lastIndex = layoutManager.findLastVisibleItemPosition();
                int count = layoutManager.getItemCount();
                if (offset <= headerHeight + 2 && lastIndex < count - 1) {
                    float alpha = offset / headerHeight;
                    tvCurrentSelect.setAlpha(alpha < 1 ? alpha : 1f);
                } else if (offset > headerHeight) {
                    tvCurrentSelect.setAlpha(1);
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int firstIndex = layoutManager.findFirstVisibleItemPosition();
                int count = layoutManager.getItemCount();
                int visibleItemCount = recyclerView.getChildCount();
                if (count - visibleItemCount <= firstIndex && mHasNext && SCROLL_STATE_DRAGGING == newState) {
                    page++;
                    mBkHomeRankModel.getBookList(sex, type, timeType, page);
                }
            }
        });

    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_rank;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_book_reader_sex_man:
                if (sex.equals(IBKType.SEX_MAN)) return;
                sex = IBKType.SEX_MAN;
                break;
            case R.id.rb_book_reader_sex_lady:
                if (sex.equals(IBKType.SEX_LADY)) return;
                sex = IBKType.SEX_LADY;
                break;
            case R.id.rb_book_type_hot:
                if (type.equals(IBKType.TYPE_HOT)) return;
                type = IBKType.TYPE_HOT;
                break;
            case R.id.rb_book_type_commend:
                if (type.equals(IBKType.TYPE_COMMEND)) return;
                type = IBKType.TYPE_COMMEND;
                break;
            case R.id.rb_book_type_over:
                if (type.equals(IBKType.TYPE_OVER)) return;
                type = IBKType.TYPE_OVER;
                break;
            case R.id.rb_book_type_collect:
                if (type.equals(IBKType.TYPE_COLLECT)) return;
                type = IBKType.TYPE_COLLECT;
                break;
            case R.id.rb_book_type_new:
                if (type.equals(IBKType.TYPE_NEW)) return;
                type = IBKType.TYPE_NEW;
                break;
            case R.id.rb_book_type_vote:
                if (type.equals(IBKType.TYPE_VOTE)) return;
                type = IBKType.TYPE_VOTE;
                break;
            case R.id.rb_book_time_type_week:
                if (type.equals(IBKType.TIME_TYPE_WEEK)) return;
                timeType = IBKType.TIME_TYPE_WEEK;
                break;
            case R.id.rb_book_time_type_month:
                if (type.equals(IBKType.TIME_TYPE_MONTH)) return;
                timeType = IBKType.TIME_TYPE_MONTH;
                break;
            case R.id.rb_book_time_type_total:
                if (type.equals(IBKType.TIME_TYPE_TOTAL)) return;
                timeType = IBKType.TIME_TYPE_TOTAL;
                break;
        }
        page = 1;
        mBkHomeRankModel.getBookList(sex, type, timeType, page);
        StringBuffer buffer = new StringBuffer();
        switch (sex) {
            case IBKType.SEX_LADY:
                buffer.append("女生");
                break;
            case IBKType.SEX_MAN:
                buffer.append("男生");
                break;
        }
        buffer.append(" · ");
        switch (type) {
            case IBKType.TYPE_HOT:
                buffer.append("最热");
                break;
            case IBKType.TYPE_COMMEND:
                buffer.append("推荐");
                break;
            case IBKType.TYPE_OVER:
                buffer.append("完结");
                break;
            case IBKType.TYPE_COLLECT:
                buffer.append("收藏");
                break;
            case IBKType.TYPE_NEW:
                buffer.append("新书");
                break;
            case IBKType.TYPE_VOTE:
                buffer.append("评分");
                break;
        }
        buffer.append(" · ");
        switch (timeType) {
            case IBKType.TIME_TYPE_WEEK:
                buffer.append("周榜");
                break;
            case IBKType.TIME_TYPE_MONTH:
                buffer.append("月榜");
                break;
            case IBKType.TIME_TYPE_TOTAL:
                buffer.append("总榜");
                break;
        }
        tvCurrentSelect.setText(buffer.toString());
    }


    @Override
    public void onSmartItemClick(BooKBO bkListItemBO, int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("bookId", bkListItemBO.getId());
        gotoActivity(ACBKDetails.class, bundle);
    }
}
