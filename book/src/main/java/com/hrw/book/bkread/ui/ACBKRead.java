package com.hrw.book.bkread.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;

import com.hrw.book.R;
import com.hrw.book.bkread.viewmodel.BKReadViewModel;
import com.hrw.book.entity.BKChapterBO;
import com.hrw.book.entity.BKChapterContentBO;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.utils.MtToast;
import com.hrw.read.widget.ReaderView;

import java.util.ArrayList;
import java.util.List;

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
    private BKChapterContentBO mBkChapterContentBO;
    private ReaderView.Adapter<BKChapterBO.ListBeanX.ListBean, BKChapterContentBO> mAdapter;

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
        mReaderView.setAdapter(mAdapter = new ReaderView.Adapter<BKChapterBO.ListBeanX.ListBean, BKChapterContentBO>() {

            @Override
            public BKChapterContentBO downLoad(BKChapterBO.ListBeanX.ListBean bkChapterBO) {
                mReadViewModel.getReaderContent(mBookId, bkChapterBO.getId());
                return mBkChapterContentBO;
            }

            @Override
            public String obtainCacheKey(BKChapterBO.ListBeanX.ListBean bkChapterBO) {
                return bkChapterBO.getId() + "";
            }

            @Override
            public String obtainChapterName(BKChapterBO.ListBeanX.ListBean bkChapterBO) {
                return bkChapterBO.getName();
            }

            @Override
            public String obtainChapterContent(BKChapterContentBO bkChapterContentBO) {
                return bkChapterContentBO.getContent();
            }
        });


    }

    @Override
    protected void initListener() {
        mReadViewModel.getChapterItem(mBookId).observe(this, new Observer<BKChapterBO>() {
            @Override
            public void onChanged(@Nullable BKChapterBO bkChapterBO) {
                List<BKChapterBO.ListBeanX.ListBean> list = new ArrayList<>();
                for (BKChapterBO.ListBeanX beanX : bkChapterBO.getList()) {
                    if (beanX != null) list.addAll(beanX.getList());
                }
                mAdapter.setChapterList(list);
                mAdapter.notifyDataSetChanged();

            }
        });

        mReadViewModel.getReaderContent(mBookId, mBookPage).observe(this, new Observer<BKChapterContentBO>() {
            @Override
            public void onChanged(@Nullable BKChapterContentBO bkChapterContentBO) {
                mBkChapterContentBO = bkChapterContentBO;
            }
        });
    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_read;
    }
}
