package com.hrw.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/10/18 11:13
 * @desc:
 */
public class MtSharedPreferences {
    private static Context mContext;
    private static SharedPreferences.Editor mEditor;
    private static SharedPreferences mPreferences;


    public static void init(Context context, String sharedPreferencesFileName) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(sharedPreferencesFileName, Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();
    }

    public static void init(Context context, String sharedPreferencesFileName, int mode) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(sharedPreferencesFileName, mode);
        mEditor = mPreferences.edit();
    }

    public static void putInt(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    public static void putLong(String key, long value) {
        mEditor.putLong(key, value);
        mEditor.commit();
    }

    public static void putFloat(String key, float value) {
        mEditor.putFloat(key, value);
        mEditor.commit();
    }

    public static void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    public static void putString(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    public static void putStringSet(String key, Set<String> values) {
        mEditor.putStringSet(key, values);
        mEditor.commit();
    }


    public static int getInt(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }

    public static long getLong(String key, long defValue) {
        return mPreferences.getLong(key, defValue);
    }

    public static float getFloat(String key, float defValue) {
        return mPreferences.getFloat(key, defValue);
    }

    public static boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    public static String getString(String key, String value) {
        return mPreferences.getString(key, value);
    }

    public static Set<String> getStringSet(String key, Set<String> values) {
        return mPreferences.getStringSet(key, values);
    }
}
