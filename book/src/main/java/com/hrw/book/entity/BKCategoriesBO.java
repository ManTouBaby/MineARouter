package com.hrw.book.entity;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/09 9:11
 * @desc:
 */
public class BKCategoriesBO {
    /**
     * CategoryName : 女生频道
     * CategoryId : 7
     */

    private String CategoryName;
    private int CategoryId;

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }
}
