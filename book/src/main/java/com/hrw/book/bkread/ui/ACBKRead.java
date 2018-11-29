package com.hrw.book.bkread.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.hrw.book.R;
import com.hrw.book.bkread.viewmodel.BKReadViewModel;
import com.hrw.book.entity.BKChapterBO;
import com.hrw.book.entity.BKChapterContentBO;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.utils.MtToast;
import com.hrw.read.TurnStatus;
import com.hrw.read.widget.PageChangedCallback;
import com.hrw.read.widget.ReaderView;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/14 16:13
 * @desc:
 */
public class ACBKRead extends BaseActivity {
    private ReaderView mReaderView;

    BKReadViewModel mReadViewModel;

    private int mBookId;
    private int mBookPage;
    private ReaderView.Adapter<BKChapterBO, BKChapterContentBO> mAdapter;

    @Override
    protected void initView() {
        mReadViewModel = ViewModelProviders.of(this).get(BKReadViewModel.class);

        mBookId = getIntent().getExtras().getInt("bookId", -1);
        mBookPage = getIntent().getExtras().getInt("bookPage", -1);
        if (mBookId == -1 || mBookPage == -1) {
            MtToast.toastShortMSG("图书加载失败...");
            return;
        }
        initReader();

    }

    private void initReader() {
        mReaderView = findViewById(R.id.normal_reader_view);
        final ReaderView.ReaderManager readerManager = new ReaderView.ReaderManager();
        mReaderView.setReaderManager(readerManager);

        mAdapter = new ReaderView.Adapter<BKChapterBO, BKChapterContentBO>() {

            @Override
            public BKChapterContentBO downLoad(BKChapterBO bkChapterBO) {
                final BKChapterContentBO[] mBkReadLabelBO = new BKChapterContentBO[1];
                mReadViewModel.getReaderItem(mBookId, mBookPage).observe(ACBKRead.this, new Observer<BKChapterContentBO>() {
                    @Override
                    public void onChanged(@Nullable BKChapterContentBO bkReadLabelBO) {
                        mBkReadLabelBO[0] = bkReadLabelBO;
                    }
                });
                return mBkReadLabelBO[0];
            }

            @Override
            public String obtainCacheKey(BKChapterBO bkChapterBO) {
                return bkChapterBO.getId() + "";
            }

            @Override
            public String obtainChapterName(BKChapterBO bkChapterBO) {
                return bkChapterBO.getName();
            }

            @Override
            public String obtainChapterContent(BKChapterContentBO bkChapterContentBO) {
                return bkChapterContentBO.getContent();
            }


        };

        mReaderView.setAdapter(mAdapter);

        mReaderView.setPageChangedCallback(new PageChangedCallback() {
            @Override
            public TurnStatus toPrevPage() {
                TurnStatus turnStatus = readerManager.toPrevPage();
                if (turnStatus == TurnStatus.NO_PREV_CHAPTER) {
                    Toast.makeText(ACBKRead.this, "没有上一页啦", Toast.LENGTH_SHORT).show();
                }
                return turnStatus;
            }

            @Override
            public TurnStatus toNextPage() {
                TurnStatus turnStatus = readerManager.toNextPage();
                if (turnStatus == TurnStatus.NO_NEXT_CHAPTER) {
                    Toast.makeText(ACBKRead.this, "没有下一页啦", Toast.LENGTH_SHORT).show();
                }
                return turnStatus;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_read;
    }
}
