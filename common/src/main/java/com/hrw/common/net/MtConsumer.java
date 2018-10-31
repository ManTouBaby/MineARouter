package com.hrw.common.net;

import com.hrw.common.utils.MtToast;

import io.reactivex.functions.Consumer;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 17:54
 * @desc:
 */
public abstract class MtConsumer<K> implements Consumer<MtResultBean<K>> {

    @Override
    public void accept(MtResultBean<K> tResultBean) throws Exception {
        if (tResultBean.isFlag() && tResultBean.getCode() == 200) {
            acceptSuccess(tResultBean.getData());
        } else {
            acceptFail(tResultBean.getMsg());
            MtToast.toastLongMSG(tResultBean.getMsg());
        }
    }

    protected abstract void acceptSuccess(K date);

    protected abstract void acceptFail(String msg);

}