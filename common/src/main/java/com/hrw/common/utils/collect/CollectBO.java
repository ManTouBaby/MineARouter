package com.hrw.common.utils.collect;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/26 15:58
 * @desc:
 */
@Entity(tableName = Constance.COLLECT_HOME)
public class CollectBO {
    @PrimaryKey
    int CollectId;
    int CollectType;
    String CollectInfo;
    String CollectName;
    String CollectAuthor;
    String CollectImg;

    String CollectLastTime;
    String CollectFirstChapterId;
    String CollectLastChapter;
    String CollectLastChapterId;

    public int getCollectId() {
        return CollectId;
    }

    public void setCollectId(int collectId) {
        CollectId = collectId;
    }

    public int getCollectType() {
        return CollectType;
    }

    public void setCollectType(int collectType) {
        CollectType = collectType;
    }

    public String getCollectInfo() {
        return CollectInfo;
    }

    public void setCollectInfo(String collectInfo) {
        CollectInfo = collectInfo;
    }

    public String getCollectName() {
        return CollectName;
    }

    public void setCollectName(String collectName) {
        CollectName = collectName;
    }

    public String getCollectAuthor() {
        return CollectAuthor;
    }

    public void setCollectAuthor(String collectAuthor) {
        CollectAuthor = collectAuthor;
    }

    public String getCollectImg() {
        return CollectImg;
    }

    public void setCollectImg(String collectImg) {
        CollectImg = collectImg;
    }

    public String getCollectLastTime() {
        return CollectLastTime;
    }

    public void setCollectLastTime(String collectLastTime) {
        CollectLastTime = collectLastTime;
    }

    public String getCollectFirstChapterId() {
        return CollectFirstChapterId;
    }

    public void setCollectFirstChapterId(String collectFirstChapterId) {
        CollectFirstChapterId = collectFirstChapterId;
    }

    public String getCollectLastChapter() {
        return CollectLastChapter;
    }

    public void setCollectLastChapter(String collectLastChapter) {
        CollectLastChapter = collectLastChapter;
    }

    public String getCollectLastChapterId() {
        return CollectLastChapterId;
    }

    public void setCollectLastChapterId(String collectLastChapterId) {
        CollectLastChapterId = collectLastChapterId;
    }
}
