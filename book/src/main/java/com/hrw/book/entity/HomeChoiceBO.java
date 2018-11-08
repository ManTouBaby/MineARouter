package com.hrw.book.entity;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/08 17:24
 * @desc:
 */
public class HomeChoiceBO {

    /**
     * Category : 火热新书
     * Books : [{"Id":339970,"Name":"妖孽动物园","Author":"光暗之心","Img":"yaoniedongwuyuan.jpg","Desc":"失踪五年的夏青山回归都市，子承父业做了一名动物园饲养员。\r\n\r\n 很快夏青山就发现，这个地球已经变得与往日不同了。\r\n\r\n 这个动物园，似乎也很不简单。","CName":"都市言情","Score":"6.0"}]
     */

    private String Category;
    private List<BKListItemBO> Books;

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public List<BKListItemBO> getBooks() {
        return Books;
    }

    public void setBooks(List<BKListItemBO> Books) {
        this.Books = Books;
    }

}
