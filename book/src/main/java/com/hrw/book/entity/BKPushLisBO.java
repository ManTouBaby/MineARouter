package com.hrw.book.entity;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/09 9:23
 * @desc:
 */
public class BKPushLisBO {
    /**
     * ImgUrl : https://image.zsdfm.com/shudan/images/5.jpg
     * ListId : 5
     * Title : 都市草根崛起
     * Description : 你还是一个人在吃着狗粮感觉恋爱的酸臭气息吗？来看都市草根崛起之路，体验成为强者的快感。
     */

    private String ImgUrl;
    private int ListId;
    private String Title;
    private String Description;

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String ImgUrl) {
        this.ImgUrl = ImgUrl;
    }

    public int getListId() {
        return ListId;
    }

    public void setListId(int ListId) {
        this.ListId = ListId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}
