package com.hrw.common.butterknife;

import android.app.Activity;
import android.view.View;


import java.lang.reflect.Field;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 17:36
 * @desc:
 */
public class ButterKnife {
    public ButterKnife() {
    }

    public static void initBindView(Object currentClass, View view) {
        Field[] fields = currentClass.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            Field[] var3 = fields;
            int var4 = fields.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Field field = var3[var5];
                BindView bindView = (BindView) field.getAnnotation(BindView.class);
                if (bindView != null) {
                    int viewId = bindView.value();

                    try {
                        field.setAccessible(true);
                        field.set(currentClass, view.findViewById(viewId));
                    } catch (Exception var10) {
                        var10.printStackTrace();
                    }
                }
            }
        }

    }

    public static void initBindView(Object currentClass) {
        Field[] fields = currentClass.getClass().getDeclaredFields();
        if (fields != null && fields.length > 0) {
            Field[] var2 = fields;
            int var3 = fields.length;

            for (int var4 = 0; var4 < var3; ++var4) {
                Field field = var2[var4];
                BindView bindView = field.getAnnotation(BindView.class);
                if (bindView != null) {
                    int viewId = bindView.value();

                    try {
                        field.setAccessible(true);
                        field.set(currentClass, ((Activity) currentClass).findViewById(viewId));
                    } catch (Exception var9) {
                        var9.printStackTrace();
                    }
                }
            }
        }

    }
}
