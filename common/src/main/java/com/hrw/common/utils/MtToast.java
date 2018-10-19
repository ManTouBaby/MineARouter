package com.hrw.common.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 17:04
 * @desc:
 */
public class MtToast {
    private static Context mContent;

    private static void init(Context context) {
        mContent = context;
    }

    public static void toastShortMSG(String label) {
        if (mContent == null) return;
        Toast.makeText(mContent, label, Toast.LENGTH_SHORT).show();
    }

    public static void toastLongMSG(String label) {
        if (mContent == null) return;
        Toast.makeText(mContent, label, Toast.LENGTH_LONG).show();
    }
}
