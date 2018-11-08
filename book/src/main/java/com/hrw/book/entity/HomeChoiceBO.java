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
    private List<CategoriesBean> Categories;
    /**
     * BookList : {"ImgUrl":"https://image.zsdfm.com/shudan/images/5.jpg","ListId":5,"Title":"都市草根崛起","Description":"你还是一个人在吃着狗粮感觉恋爱的酸臭气息吗？来看都市草根崛起之路，体验成为强者的快感。"}
     */

    private BookListBean BookList;


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

    public List<CategoriesBean> getCategories() {
        return Categories;
    }

    public void setCategories(List<CategoriesBean> Categories) {
        this.Categories = Categories;
    }

    public BookListBean getBookList() {
        return BookList;
    }

    public void setBookList(BookListBean BookList) {
        this.BookList = BookList;
    }

    public static class CategoriesBean {
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

    public static class BookListBean {
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
}
