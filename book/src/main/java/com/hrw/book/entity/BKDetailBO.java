package com.hrw.book.entity;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/08 14:28
 * @desc:
 */
public class BKDetailBO {

    /**
     * Id : 87451
     * Name : 天道图书馆
     * Img : tiandaotushuguan.jpg
     * Author : 横扫天涯
     * Desc : 张悬穿越异界，成了一名光荣的教师，脑海中多出了一个神秘的图书馆。
     * 　　 只要他看过的东西，无论人还是物，都能自动形成书籍，记录下对方各种各样的缺点，于是，他牛大了！
     * 　　 “昊天大帝，你怎么不喜欢穿内裤啊？堂堂大帝，能不能注意点形象？”
     * 　　 “玲珑仙子，你如果晚上再失眠，可以找我嘛，我这个人唱安眠曲很有一套的！”
     * 　　 “还有你，乾坤魔君，能不能少吃...
     * CId : 95
     * CName : 玄幻奇幻
     * LastTime : 11/8/2018 12:22:39 AM
     * FirstChapterId : 4696609
     * LastChapter : 第一千五百九十三章 兀臣的可怕
     * LastChapterId : 5229593
     * BookStatus : 连载
     * SameUserBooks : [{"Id":4105,"Name":"万界独尊","Author":"横扫天涯","Img":"wanjieduzun.jpg","LastChapterId":2838110,"LastChapter":"写在完本后的话.","Score":0}]
     * SameCategoryBooks : [{"Id":317749,"Name":"不科学游戏少女","Img":"bukexueyouxishaonv.jpg","Score":0}]
     * BookVote : {"BookId":87451,"TotalScore":133,"VoterCount":19,"Score":7}
     */

    private int Id;
    private String Name;
    private String Img;
    private String Author;
    private String Desc;
    private int CId;
    private String CName;
    private String LastTime;
    private int FirstChapterId;
    private String LastChapter;
    private int LastChapterId;
    private String BookStatus;
    private BookVoteBean BookVote;
    private List<SameUserBooksBean> SameUserBooks;
    private List<SameCategoryBooksBean> SameCategoryBooks;

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

    public String getImg() {
        return Img;
    }

    public void setImg(String Img) {
        this.Img = Img;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public int getCId() {
        return CId;
    }

    public void setCId(int CId) {
        this.CId = CId;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getLastTime() {
        return LastTime;
    }

    public void setLastTime(String LastTime) {
        this.LastTime = LastTime;
    }

    public int getFirstChapterId() {
        return FirstChapterId;
    }

    public void setFirstChapterId(int FirstChapterId) {
        this.FirstChapterId = FirstChapterId;
    }

    public String getLastChapter() {
        return LastChapter;
    }

    public void setLastChapter(String LastChapter) {
        this.LastChapter = LastChapter;
    }

    public int getLastChapterId() {
        return LastChapterId;
    }

    public void setLastChapterId(int LastChapterId) {
        this.LastChapterId = LastChapterId;
    }

    public String getBookStatus() {
        return BookStatus;
    }

    public void setBookStatus(String BookStatus) {
        this.BookStatus = BookStatus;
    }

    public BookVoteBean getBookVote() {
        return BookVote;
    }

    public void setBookVote(BookVoteBean BookVote) {
        this.BookVote = BookVote;
    }

    public List<SameUserBooksBean> getSameUserBooks() {
        return SameUserBooks;
    }

    public void setSameUserBooks(List<SameUserBooksBean> SameUserBooks) {
        this.SameUserBooks = SameUserBooks;
    }

    public List<SameCategoryBooksBean> getSameCategoryBooks() {
        return SameCategoryBooks;
    }

    public void setSameCategoryBooks(List<SameCategoryBooksBean> SameCategoryBooks) {
        this.SameCategoryBooks = SameCategoryBooks;
    }

    public static class BookVoteBean {
        /**
         * BookId : 87451
         * TotalScore : 133
         * VoterCount : 19
         * Score : 7
         */

        private int BookId;
        private int TotalScore;
        private int VoterCount;
        private String Score;

        public int getBookId() {
            return BookId;
        }

        public void setBookId(int BookId) {
            this.BookId = BookId;
        }

        public int getTotalScore() {
            return TotalScore;
        }

        public void setTotalScore(int TotalScore) {
            this.TotalScore = TotalScore;
        }

        public int getVoterCount() {
            return VoterCount;
        }

        public void setVoterCount(int VoterCount) {
            this.VoterCount = VoterCount;
        }

        public String getScore() {
            return Score;
        }

        public void setScore(String Score) {
            this.Score = Score;
        }
    }

    public static class SameUserBooksBean {
        /**
         * Id : 4105
         * Name : 万界独尊
         * Author : 横扫天涯
         * Img : wanjieduzun.jpg
         * LastChapterId : 2838110
         * LastChapter : 写在完本后的话.
         * Score : 0
         */

        private int Id;
        private String Name;
        private String Author;
        private String Img;
        private int LastChapterId;
        private String LastChapter;
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

        public int getLastChapterId() {
            return LastChapterId;
        }

        public void setLastChapterId(int LastChapterId) {
            this.LastChapterId = LastChapterId;
        }

        public String getLastChapter() {
            return LastChapter;
        }

        public void setLastChapter(String LastChapter) {
            this.LastChapter = LastChapter;
        }

        public String getScore() {
            return Score;
        }

        public void setScore(String Score) {
            this.Score = Score;
        }
    }

    public static class SameCategoryBooksBean {
        /**
         * Id : 317749
         * Name : 不科学游戏少女
         * Img : bukexueyouxishaonv.jpg
         * Score : 0
         */

        private int Id;
        private String Name;
        private String Img;
        private int Score;

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

        public String getImg() {
            return Img;
        }

        public void setImg(String Img) {
            this.Img = Img;
        }

        public int getScore() {
            return Score;
        }

        public void setScore(int Score) {
            this.Score = Score;
        }
    }
}
