package com.hrw.common.utils;

import android.util.Log;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 17:03
 * @desc:
 */
public class MtLog {
    private static String mTag;
    private static boolean mIsOpen = false;

    public static void init(boolean isOpen) {

    }

    public static void init(String TAG, boolean isOpen) {
        mTag = TAG;
    }

    public static void d(String msg) {
        if (mIsOpen) Log.d("", msg);
    }

    public static void d(String tag, String msg) {
        if (mIsOpen) Log.d(tag, msg);
    }

    public static void e(String msg) {
        if (mIsOpen) Log.e("", msg);
    }

    public static void e(String tag, String msg) {
        if (mIsOpen) Log.e(tag, msg);
    }

    public static void i(String msg) {
        if (mIsOpen) Log.i("", msg);
    }

    public static void i(String tag, String msg) {
        if (mIsOpen) Log.i(tag, msg);
    }

    public static void v(String msg) {
        if (mIsOpen) Log.v("", msg);
    }

    public static void v(String tag, String msg) {
        if (mIsOpen) Log.v(tag, msg);
    }
}
