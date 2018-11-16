package com.hrw.common.baseMVVM;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/16 9:04
 * @desc:
 */
public class ErrorResult {
    public int code;
    public String msg;

    public ErrorResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
