package com.hrw.common.utils.database;

import android.database.sqlite.SQLiteDatabase;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/10/19 9:38
 * @desc:
 */
public class MtDBManager {
    private static MtDBManager mtDBManager;
    MtDBHelper mMtDBHelper;
    private SQLiteDatabase mSqLiteDatabase;


    private MtDBManager() {
        mMtDBHelper = MtDBHelper.getInstance();
        mSqLiteDatabase = mMtDBHelper.getWritableDatabase();
    }

    public static MtDBManager init() {
        if (mtDBManager == null)
            mtDBManager = new MtDBManager();
        return mtDBManager;
    }

    public void close() {
        if (mtDBManager != null) {
            mtDBManager = null;
        }
        if (mMtDBHelper != null) {
            mMtDBHelper.close();
            mMtDBHelper = null;
        }
        if (mSqLiteDatabase.isOpen()) {
            mSqLiteDatabase.close();
            mSqLiteDatabase = null;
        }
    }
}
