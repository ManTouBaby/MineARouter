package com.hrw.common.utils.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/10/18 11:48
 * @desc:
 */
public class MtDBHelper extends SQLiteOpenHelper {
    List<String> sqlString = new ArrayList<>();
    List<Map<String, MtDBType>> mMapList = new ArrayList<>();
    private static MtDBHelper dbHelper;

    public static void instance(Context context, String name, @NonNull List<Map<String, MtDBType>> maps, int version) {
        if (dbHelper == null) {
            dbHelper = new MtDBHelper(context, name, maps, version);
        }
    }

    public static void instance(Context context, String name, @NonNull List<Map<String, MtDBType>> maps, SQLiteDatabase.CursorFactory factory, int version) {
        if (dbHelper == null) {
            dbHelper = new MtDBHelper(context, name, maps, factory, version);
        }
    }

    public static void instance(Context context, String name, @NonNull List<Map<String, MtDBType>> maps, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        if (dbHelper == null) {
            dbHelper = new MtDBHelper(context, name, maps, factory, version, errorHandler);
        }
    }

    public static MtDBHelper getInstance() {
        if (dbHelper != null) {
            return dbHelper;
        } else {
            new Throwable("must instance the MtDBHelper");
        }
        return null;
    }

    private MtDBHelper(Context context, String name, @NonNull List<Map<String, MtDBType>> maps, int version) {
        this(context, name + ".db", maps, null, version);
    }

    private MtDBHelper(Context context, String name, @NonNull List<Map<String, MtDBType>> maps, SQLiteDatabase.CursorFactory factory, int version) {
        this(context, name, maps, factory, version, null);
    }

    private MtDBHelper(Context context, String name, @NonNull List<Map<String, MtDBType>> maps, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        mMapList = maps;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (mMapList == null || mMapList.size() < 1) new Throwable("参数大小不能小于1");
        for (Map<String, MtDBType> dbTypeMap : mMapList) {
            String tableName = null;
            String primaryKey = null;
            Set<Map.Entry<String, MtDBType>> entries = dbTypeMap.entrySet();
            for (Map.Entry<String, MtDBType> typeEntry : entries) {
                if (typeEntry.getValue() == MtDBType.TABLE_NAME) {
                    tableName = typeEntry.getKey();
                    sqlString.add(tableName);
                }
                if (typeEntry.getValue() == MtDBType.TABLE_TYPE_PRIMARY) {
                    primaryKey = typeEntry.getKey();
                }
                if (tableName != null && primaryKey != null) {
                    break;
                }
            }

            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("CREATE TABLE " + tableName + " (" + primaryKey + " " + MtDBType.TABLE_TYPE_TEXT.getValue() + " primary key autoincrement,");

            Iterator<Map.Entry<String, MtDBType>> entryIterable = entries.iterator();
            while (entryIterable.hasNext()) {
                Map.Entry<String, MtDBType> typeEntry = entryIterable.next();
                if (typeEntry.getValue() == MtDBType.TABLE_NAME || typeEntry.getValue() == MtDBType.TABLE_TYPE_PRIMARY) {
                    continue;
                }
                if (entryIterable.hasNext()) {
                    stringBuffer.append(typeEntry.getKey() + " " + typeEntry.getValue().getValue() + ",");
                } else {
                    stringBuffer.append(typeEntry.getKey() + " " + typeEntry.getValue().getValue());
                }

            }
            stringBuffer.append(");");
            System.out.println("执行SQL:" + stringBuffer);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            for (String tableName : sqlString) {
                db.execSQL("DROP TABLE IF EXISTS " + tableName);
            }
        }
        onCreate(db);
    }
}
