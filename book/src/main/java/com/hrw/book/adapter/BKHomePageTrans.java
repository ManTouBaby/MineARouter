package com.hrw.book.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * @author:MtBaby
 * @date:2018/11/12 22:46
 * @desc:
 */
public class BKHomePageTrans implements ViewPager.PageTransformer {
    final float SCALE_MAX = 0.8f;
    final float ALPHA_MAX = 0.5f;
    @Override
    public void transformPage(@NonNull View page, float position) {
        float scale = (position < 0)
                ? ((1 - SCALE_MAX) * position + 1)
                : ((SCALE_MAX - 1) * position + 1);
        float alpha = (position < 0)
                ? ((1 - ALPHA_MAX) * position + 1)
                : ((ALPHA_MAX - 1) * position + 1);
        //为了滑动过程中，page间距不变，这里做了处理
        if(position < 0) {
            ViewCompat.setPivotX(page, page.getWidth());
            ViewCompat.setPivotY(page, page.getHeight() / 2);
        } else {
            ViewCompat.setPivotX(page, 0);
            ViewCompat.setPivotY(page, page.getHeight() / 2);
        }
        ViewCompat.setScaleX(page, scale);
        ViewCompat.setScaleY(page, scale);
        ViewCompat.setAlpha(page, Math.abs(alpha));

    }
}
