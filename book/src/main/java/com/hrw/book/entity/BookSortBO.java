package com.hrw.book.entity;

/**
 * @author:MtBaby
 * @date:2018/11/19 20:46
 * @desc:分类
 */
public class BookSortBO {

    /**
     * ListId : 10
     * UserName : 风一样的男人
     * ForMan : true
     * Cover : buxiufanren.jpg
     * Title : 一段又一段的仙侠传说，时间流逝了那些仙侠情怀。
     * Description : 什么是真实，什么又是虚幻:这是我的现实，就不代表不是你的梦境
     * BookCount : 21
     * CommendImage : https://image.zsdfm.com/shudan/images/10.jpg
     * CollectionCount : 1138
     * CommendCount : 550
     * IsCheck : true
     * AddTime : 2017/11/22 23:39:50
     * UpdateTime : 2017/12/01 01:20:25
     */

    private int ListId;
    private String UserName;
    private boolean ForMan;
    private String Cover;
    private String Title;
    private String Description;
    private int BookCount;
    private String CommendImage;
    private int CollectionCount;
    private int CommendCount;
    private boolean IsCheck;
    private String AddTime;
    private String UpdateTime;

    public int getListId() {
        return ListId;
    }

    public void setListId(int ListId) {
        this.ListId = ListId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public boolean isForMan() {
        return ForMan;
    }

    public void setForMan(boolean ForMan) {
        this.ForMan = ForMan;
    }

    public String getCover() {
        return Cover;
    }

    public void setCover(String Cover) {
        this.Cover = Cover;
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

    public int getBookCount() {
        return BookCount;
    }

    public void setBookCount(int BookCount) {
        this.BookCount = BookCount;
    }

    public String getCommendImage() {
        return CommendImage;
    }

    public void setCommendImage(String CommendImage) {
        this.CommendImage = CommendImage;
    }

    public int getCollectionCount() {
        return CollectionCount;
    }

    public void setCollectionCount(int CollectionCount) {
        this.CollectionCount = CollectionCount;
    }

    public int getCommendCount() {
        return CommendCount;
    }

    public void setCommendCount(int CommendCount) {
        this.CommendCount = CommendCount;
    }

    public boolean isIsCheck() {
        return IsCheck;
    }

    public void setIsCheck(boolean IsCheck) {
        this.IsCheck = IsCheck;
    }

    public String getAddTime() {
        return AddTime;
    }

    public void setAddTime(String AddTime) {
        this.AddTime = AddTime;
    }

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String UpdateTime) {
        this.UpdateTime = UpdateTime;
    }
}
