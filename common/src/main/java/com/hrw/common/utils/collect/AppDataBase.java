package com.hrw.common.utils.collect;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/26 17:06
 * @desc:
 */

@Database(entities = {CollectBO.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract CollectDAO collectDAO();

    private static AppDataBase mDataBase;

    public static AppDataBase instance(Context context) {
        if (mDataBase == null) {
            synchronized (AppDataBase.class) {
                if (mDataBase == null) {
                    mDataBase = Room.databaseBuilder(context, AppDataBase.class, "mineARouter").build();
                }
            }
        }
        return mDataBase;
    }

}
