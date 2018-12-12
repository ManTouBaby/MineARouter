package com.hrw.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/12/12 14:22
 * @desc:
 */
public class MtGraphicMixedUtils {
    public static Spanned setSource(final Context context, final TextView textView, String source) {
        //获取全屏大小
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        //我的textview有左右留边  margin
        float paddingLeft = textView.getPaddingLeft();
        float paddingRight = textView.getPaddingRight();
        final float actX = metrics.widthPixels - paddingLeft - paddingRight;
        final float actY = metrics.heightPixels;

        Spanned label = Html.fromHtml(source, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                final URLDrawable urlDrawable = new URLDrawable();
                Glide.with(context).load(source).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                        float x = bitmap.getWidth();
                        float y = bitmap.getHeight();

                        float temp = actX / x;
                        //进行等比例缩放程序
                        Matrix matrix = new Matrix();
                        matrix.postScale(temp, temp);
                        //长和宽放大缩小的比例
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, (int) x, (int) y, matrix, true);
                        urlDrawable.bitmap = bitmap;
                        urlDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight() + 40);//+40设置画板空间高度
                        textView.invalidate();
                        textView.setText(textView.getText()); // 解决图文重叠
                    }
                });
                return urlDrawable;

            }
        }, null);
        return label;
    }

    public static class URLDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 20, getPaint());
            }
        }
    }

}
