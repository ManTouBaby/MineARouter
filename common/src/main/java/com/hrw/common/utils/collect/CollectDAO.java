package com.hrw.common.utils.collect;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/26 17:03
 * @desc:
 */
@Dao
public interface CollectDAO {

    @Query("select * from collectHome")
    List<CollectBO> getAllCollect();

    @Query("select * from collectHome where CollectType = :collectType")
    List<CollectBO> getAllCollectByType(int collectType);

    @Query("select * from collectHome where CollectId = :collectId")
    CollectBO getCollect(String collectId);

    @Insert
    void insertAll(CollectBO... collectBOS);

    @Delete()
    void deleteCollect(CollectBO... collectBOS);
}
