package com.hrw.common.net;

import com.hrw.common.utils.ToastUtils;

import io.reactivex.functions.Consumer;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 17:54
 * @desc:
 */
public abstract class MineConsumer<K> implements Consumer<ResultBean<K>> {

    @Override
    public void accept(ResultBean<K> tResultBean) throws Exception {
        if (tResultBean.isFlag() && tResultBean.getCode() == 200) {
            acceptSuccess(tResultBean.getData());
        } else {
            acceptFail(tResultBean.getMsg());
            ToastUtils.toastLongMSG(tResultBean.getMsg());
        }
    }

    protected abstract void acceptSuccess(K date);

    protected abstract void acceptFail(String msg);

}
