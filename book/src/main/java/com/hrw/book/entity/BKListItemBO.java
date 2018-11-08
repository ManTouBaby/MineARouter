package com.hrw.book.entity;

/**
 * @author:MtBaby
 * @date:2018/11/07 22:54
 * @desc:
 */
public class BKListItemBO {
    /**
     * Id : 339970
     * Name : 妖孽动物园
     * Author : 光暗之心
     * Img : yaoniedongwuyuan.jpg
     * Desc : 失踪五年的夏青山回归都市，子承父业做了一名动物园饲养员。
     * <p>
     * 很快夏青山就发现，这个地球已经变得与往日不同了。
     * <p>
     * 这个动物园，似乎也很不简单。
     * CName : 都市言情
     * Score : 6.0
     */

    private int Id;
    private String Name;
    private String Author;
    private String Img;
    private String Desc;
    private String CName;
    private String Score;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String Score) {
        this.Score = Score;
    }
}
