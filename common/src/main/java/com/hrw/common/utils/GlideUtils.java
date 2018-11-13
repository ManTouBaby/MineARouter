package com.hrw.common.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/13 16:07
 * @desc:
 */
public class GlideUtils {
    public static void bindIMG(Context mContext, String url, ImageView ivBookBG) {
        Glide.with(mContext).load(url).dontAnimate().into(ivBookBG);
    }
}
