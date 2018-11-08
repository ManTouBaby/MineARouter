package com.hrw.common.net;

import io.reactivex.functions.Function;

/**
 * @author:MtBaby
 * @date:2018/11/08 20:37
 * @desc:
 */
public class MtFunction1<T extends MtResultBean1<R>, R> implements Function<T, R> {

    @Override
    public R apply(T t) throws NullPointerException {
        if (t.getInfo().equals("success") && t.getStatus() == 1) {
            return t.getData();
        }
        return null;
    }
}
