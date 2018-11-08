package com.hrw.common.net;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/09/29 17:47
 * @desc:
 */
public class MtResultBean1<T> {
    private int status;
    private String info;
    private T data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
