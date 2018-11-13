package com.hrw.book.entity;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/13 11:24
 * @desc:
 */
public class BookList {

    /**
     * BookList : [{"Id":270436,"Name":"明朝败家子","Author":"上山打老虎额","Img":"mingchaobaijiazi.jpg","Desc":"\u201c好了，好了，我承认，我不是人，我是败家子，我卑鄙，我无耻，我卖了家业，我愧对祖先，我还四处沾花惹草，恶贯满盈。爹，有话好好说，可以把你的大刀放下好吗？\u201d","CName":"历史军事","Score":8.8}]
     * Page : 1
     * HasNext : true
     */

    private int Page;
    private boolean HasNext;
    private List<BKListItemBO> BookList;

    public int getPage() {
        return Page;
    }

    public void setPage(int Page) {
        this.Page = Page;
    }

    public boolean isHasNext() {
        return HasNext;
    }

    public void setHasNext(boolean HasNext) {
        this.HasNext = HasNext;
    }

    public List<BKListItemBO> getBookList() {
        return BookList;
    }

    public void setBookList(List<BKListItemBO> BookList) {
        this.BookList = BookList;
    }


}
