package com.hrw.common.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
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
    Context mContext;

    ViewPager mViewPager;
    Drawable mDefaultDrawableRes;
    Drawable mSelectDrawableRes;
    boolean isShowTagPoint = true;//是否显示小圈圈
    boolean isOpenCycle = true;//是否可以无限滑动
    int mTagWidth = 16;//标志点宽度
    int mTagHeight = 16;//标志点高度
    int mTagMarginLeft = 4;//标志点左边间距
    int mTagMarginRight = 4;//标志点右边间距
    long mCyclePeriod = 1000 * 5;//自动滚动周期
    int mCurrentPage = -1;//当前显示界面

    private boolean isClose = true;
    ImageView oldSelect;
    ImageView nowSelect;
    float dpi;//相对密度


    Handler handler = new Handler();

    public MtViewPage(@NonNull Context context) {
        this(context, null);
    }

    public MtViewPage(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        dpi = context.getResources().getDisplayMetrics().density;
        mDefaultDrawableRes = context.getResources().getDrawable(R.drawable.icon_tag_point_default);
        mSelectDrawableRes = context.getResources().getDrawable(R.drawable.icon_tag_point_select);

        //初始化ViewPager
        mViewPager = new ViewPager(context);
        mViewPager.setLayoutParams(new ViewPager.LayoutParams());
        mtViewPageAdapter = new MtViewPageAdapter();
        mViewPager.setAdapter(mtViewPageAdapter);
        mViewPager.addOnPageChangeListener(this);
        addView(mViewPager);
        openAuto();

        //初始化PointTag
        mllPointContainer = new LinearLayout(mContext);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins((int) (16 * dpi), (int) (8 * dpi), (int) (16 * dpi), (int) (8 * dpi));
        mllPointContainer.setLayoutParams(params);
        addView(mllPointContainer);
    }

    public MtViewPage setShowTagPoint(boolean showTagPoint) {
        isShowTagPoint = showTagPoint;
        return this;
    }

    public MtViewPage setOpenAutoCycle(boolean openAutoCycle) {
        isClose = openAutoCycle;
        return this;
    }

    public MtViewPage setOpenCycle(boolean openCycle) {
        isOpenCycle = openCycle;
        return this;
    }

    public void setDate(List<View> views) {
        mViews = views;
        mllPointContainer.removeAllViews();
        if (isShowTagPoint) for (int i = 0; i < views.size(); i++) {
            createImagePoint();
        }

        setSelectTagPoint(getRealIndex(0));
        mCurrentPage = getRealIndex(0);
        mtViewPageAdapter.notifyDataSetChanged();
    }

    public MtViewPage setCurrentPage(int position) {
        mCurrentPage = position;
        mViewPager.setCurrentItem(position);
        return this;
    }

    private ImageView createImagePoint() {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageDrawable(mDefaultDrawableRes);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (mTagWidth * dpi), (int) (mTagHeight * dpi));
        params.setMargins((int) (mTagMarginLeft * dpi), 0, (int) (mTagMarginRight * dpi), 0);
        imageView.setLayoutParams(params);

        mllPointContainer.addView(imageView);
        return imageView;
    }

    private void setSelectTagPoint(int position) {
        if (!isShowTagPoint) return;
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
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        System.out.println("绑定到界面");
        isClose = true;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        System.out.println("解除绑定");
        isClose = false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        mCurrentPage = position;
        setSelectTagPoint(getRealIndex(position));
    }

    private class MtViewPageAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (isOpenCycle) {
                if (mViews.size() < 1) {
                    return 0;
                } else {
                    return Integer.MAX_VALUE;
                }
            } else {
                return mViews.size();
            }
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
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
            if (!isOpenCycle) container.removeView(mViews.get(position));
        }
    }


    private int getRealIndex(int position) {
        if (mViews.size() > 0) {
            return position % mViews.size();
        } else {
            return position;
        }
    }


    private void openAuto() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isClose) {
                    try {
                        Thread.sleep(mCyclePeriod);
                        System.out.println("线程存活");
                        handler.post(runnable);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            mCurrentPage++;
            setCurrentPage(mCurrentPage);
        }
    };
}
