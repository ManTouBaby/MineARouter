package com.hrw.book.entity;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/08 17:44
 * @desc:
 */
public class HomeChoiceBannerBO {

    /**
     * type : book
     * param : 248872
     * imgurl : https://image.zsdfm.com/shudan/images/248872.jpeg
     */

    private String type;
    private String param;
    private String imgurl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }
}
