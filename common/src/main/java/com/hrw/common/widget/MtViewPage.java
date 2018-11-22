package com.hrw.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hrw.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:MtBaby
 * @date:2018/11/20 20:34
 * @desc:
 */
public class MtViewPage extends RelativeLayout implements ViewPager.OnPageChangeListener {
    List<View> mViews = new ArrayList<>();
    MtViewPageAdapter mtViewPageAdapter;
    LinearLayout mllPointContainer;
    ViewPager mViewPager;
    Context mContext;

    Drawable mDefaultDrawableRes;
    Drawable mSelectDrawableRes;
    boolean isShowTagPoint = true;
    boolean isOpenAutoCycle = true;
    boolean isClose = true;
    int mTagWidth = 20;
    int mTagHeight = 20;
    float dpi;
    long mCyclePeriod = 1000 * 5;
    int mCurrentPage = -1;
    ImageView oldSelect;
    ImageView nowSelect;


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            mCurrentPage++;
            mCurrentPage = mCurrentPage % mViews.size();
            setCurrentPage(mCurrentPage);
        }
    };

    public MtViewPage(@NonNull Context context) {
        this(context, null);
    }

    public MtViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        dpi = context.getResources().getDisplayMetrics().densityDpi;
        mContext = context;
        mDefaultDrawableRes = context.getResources().getDrawable(R.drawable.icon_tag_point_default);
        mSelectDrawableRes = context.getResources().getDrawable(R.drawable.icon_tag_point_select);

        mViewPager = new ViewPager(context);
        mViewPager.setLayoutParams(new ViewPager.LayoutParams());
        mtViewPageAdapter = new MtViewPageAdapter();
        mViewPager.setAdapter(mtViewPageAdapter);
        mViewPager.addOnPageChangeListener(this);

        addView(mViewPager);
        if (isShowTagPoint) {
            mllPointContainer = new LinearLayout(context);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.CENTER_HORIZONTAL);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            params.setMargins(16, 8, 16, 8);
            mllPointContainer.setLayoutParams(params);
            addView(mllPointContainer);
        }

    }

    public void setShowTagPoint(boolean showTagPoint) {
        isShowTagPoint = showTagPoint;
    }

    public void setOpenAutoCycle(boolean openAutoCycle) {
        isOpenAutoCycle = openAutoCycle;
    }

    public void setDate(List<View> views) {
        mViews = views;
        mllPointContainer.removeAllViews();
        if (isShowTagPoint) for (int i = 0; i < views.size(); i++) {
            createImagePoint();
        }
        mtViewPageAdapter.notifyDataSetChanged();
    }

    private void setCurrentPage(int position) {
        mViewPager.setCurrentItem(position);
    }

    private ImageView createImagePoint() {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageDrawable(mDefaultDrawableRes);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mTagWidth, mTagHeight);
        params.setMargins(8, 0, 8, 0);
        imageView.setLayoutParams(params);

        mllPointContainer.addView(imageView);
        return imageView;
    }

    private void setSelectTagPoint(int position) {
        if (oldSelect == null && nowSelect == null) {
            oldSelect = (ImageView) mllPointContainer.getChildAt(position);
        } else {
            oldSelect = nowSelect;
        }
        nowSelect = (ImageView) mllPointContainer.getChildAt(position);
        if (oldSelect != null) oldSelect.setImageDrawable(mDefaultDrawableRes);
        if (nowSelect != null) nowSelect.setImageDrawable(mSelectDrawableRes);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
//        System.out.println("onVisibilityChanged:" + visibility);
        if (visibility == View.VISIBLE) {
            isClose = true;
            if (isOpenAutoCycle) {
                openAuto();
            }
        }
        if (visibility == INVISIBLE) {
            isClose = false;
        }

    }


    private void openAuto() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isClose) {
                    try {
                        Thread.sleep(mCyclePeriod);
                        Message message = new Message();
                        message.arg1 = 1;
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setSelectTagPoint(getRealIndex(position));
        mCurrentPage = getRealIndex(position);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    private class MtViewPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (mViews.size() < 1) {
                return 0;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            if (mViews.size() > 0) {
//                View view = mViews.get(getRealIndex(position));
//                ViewParent parent = view.getParent();
//                if (parent != null) {
//                    ViewGroup group = (ViewGroup) parent;
//                    group.removeView(view);
//                }
//                container.addView(view);
//                return view;
//            } else {
//                return null;
//            }
            View view = mViews.get(getRealIndex(position));
            ViewParent parent = view.getParent();
            if (parent != null) {
                ViewGroup group = (ViewGroup) parent;
                group.removeView(view);
            }
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mViews.get(position));
        }
    }


    private int getRealIndex(int position) {
        if (mViews.size() > 0) {
            return position % mViews.size();
        } else {
            return position;
        }
    }
}
