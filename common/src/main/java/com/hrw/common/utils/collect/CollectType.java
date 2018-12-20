package com.hrw.common.utils.collect;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.hrw.common.utils.collect.CollectType.BOOK;
import static com.hrw.common.utils.collect.CollectType.MUSIC;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/12/20 14:26
 * @desc:
 */
@IntDef({
        BOOK,
        MUSIC,
})
@Retention(RetentionPolicy.SOURCE)
public @interface CollectType {
    int BOOK = 1;
    int MUSIC = 2;
}
