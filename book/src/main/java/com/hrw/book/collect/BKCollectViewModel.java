package com.hrw.book.collect;

import com.hrw.common.baseMVVM.BaseViewModel;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/12/20 18:02
 * @desc:
 */
public class BKCollectViewModel extends BaseViewModel<BKCollectRepository> {
    @Override
    protected BKCollectRepository createRepository() {
        return new BKCollectRepository();
    }
}
