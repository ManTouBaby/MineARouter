package com.hrw.book.collect;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hrw.book.R;
import com.hrw.common.baseMVVM.BaseActivity;
import com.hrw.common.servicePath.BKInterface;
import com.hrw.common.utils.MtGlideUtils;
import com.hrw.common.utils.collect.AppDataBase;
import com.hrw.common.utils.collect.CollectBO;
import com.hrw.common.utils.collect.CollectType;
import com.hrw.common.utils.collect.CollectViewModel;
import com.hrw.smartrecyclerviewlibrary.SmartAdapter;
import com.hrw.smartrecyclerviewlibrary.SmartVH;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/12/20 18:03
 * @desc:
 */
public class ACBKCollect extends BaseActivity {
    RecyclerView rvShowCollect;

    CollectViewModel mCollectViewModel;
    SmartAdapter<CollectBO> smartAdapter;

    @Override
    protected void initView() {
        mCollectViewModel = ViewModelProviders.of(this).get(CollectViewModel.class);
        rvShowCollect = findViewById(R.id.rl_book_collect_item_show);
        rvShowCollect.setLayoutManager(new LinearLayoutManager(this));
        rvShowCollect.setHasFixedSize(true);
        rvShowCollect.setAdapter(smartAdapter = new SmartAdapter<CollectBO>(R.layout.item_book_show) {
            @Override
            protected void bindView(SmartVH smartVH, CollectBO collectBO, int i) {
                MtGlideUtils.bindIMG(getBaseContext(), BKInterface.ROOT_BOOK_IMG + collectBO.getCollectImg(), smartVH.getImage(R.id.iv_book_bg));
                smartVH.getText(R.id.tv_book_name).setText(collectBO.getCollectName());
                smartVH.getText(R.id.tv_book_new).setText(collectBO.getCollectLastChapter());
                smartVH.getText(R.id.tv_book_author).setText(collectBO.getCollectAuthor());
                smartVH.getText(R.id.tv_book_score).setText(collectBO.getCollectScore() + "分");
            }
        });
    }

    @Override
    protected void initListener() {
        mCollectViewModel.getAllCollectByType(AppDataBase.instance(this), CollectType.BOOK)
                .observe(this, new Observer<List<CollectBO>>() {
                    @Override
                    public void onChanged(@Nullable List<CollectBO> collectBOS) {
                        smartAdapter.setDate(collectBOS);
                    }
                });
    }

    @Override
    protected int createLayout() {
        return R.layout.ac_book_collect;
    }
}
