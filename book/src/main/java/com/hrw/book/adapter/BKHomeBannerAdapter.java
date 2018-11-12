package com.hrw.book.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/12 21:57
 * @desc:
 */
public class BKHomeBannerAdapter extends PagerAdapter {
    List<View> mViews = new ArrayList<>();

    public void setViews(List<View> views) {
        mViews = views;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(mViews.get(position));
        return mViews.get(position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(mViews.get(position));
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}
