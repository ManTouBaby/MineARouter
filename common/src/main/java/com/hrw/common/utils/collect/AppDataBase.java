package com.hrw.common.utils.collect;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/26 17:06
 * @desc:
 */

@Database(entities = {CollectBO.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public static String collectHome="collectHome";
    public abstract CollectDAO collectDAO();
}
