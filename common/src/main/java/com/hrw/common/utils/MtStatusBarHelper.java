package com.hrw.common.utils;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/01 11:14
 * @desc:
 */
public class MtStatusBarHelper {
    private static Window window;
    private static Activity mActivity;
    private static MtStatusBarHelper mStatusHelper;
    private static Map<Class<?>, View> mClassView = new HashMap<>();

    private MtStatusBarHelper() {
    }

    public static MtStatusBarHelper instance(Activity activity) {
        mActivity = activity;
        window = activity.getWindow();
        if (mStatusHelper == null) {
            mStatusHelper = new MtStatusBarHelper();
        }
        return mStatusHelper;
    }

    public MtStatusBarHelper setBGColor(@ColorInt int bgColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(bgColor);
        } else {
            //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                ViewGroup contentView = window.getDecorView().findViewById(android.R.id.content);
                View childAt = contentView.getChildAt(0);
                if (childAt != null) {
                    //将侧边栏顶部延伸至status bar
                    childAt.setFitsSystemWindows(true);
                    //将主页面顶部延伸至status bar;虽默认为false,但经测试,DrawerLayout需显示设置
//                    contentView.setClipToPadding(false);
                }
                if (mClassView.containsKey(mActivity.getClass()) && mClassView.get(mActivity.getClass()) != null) {
                    mClassView.get(mActivity.getClass()).setBackgroundColor(bgColor);
                } else {
                    View view = new View(mActivity);
                    view.setVisibility(View.VISIBLE);
                    mClassView.put(mActivity.getClass(), view);
                    //获取到状态栏的高度
                    int statusHeight = getStatusBarHeight();
                    //动态的设置隐藏布局的高度
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusHeight);
                    view.setBackgroundColor(bgColor);
                    contentView.addView(view, params);
                }
            }
        }
        return mStatusHelper;
    }

    /**
     * 设置Activity是否占用全屏
     *
     * @param isFull
     */
    public MtStatusBarHelper setFullScreen(boolean isFull) {
        if (isFull) {
            //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                ViewGroup contentView = window.getDecorView().findViewById(android.R.id.content);
                View childAt = contentView.getChildAt(0);
                if (childAt != null) {
                    //将侧边栏顶部延伸至status bar
                    childAt.setFitsSystemWindows(false);
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
        }

        return mStatusHelper;
    }

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    private static int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return mActivity.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
