package com.hrw.book.entity;

import java.util.List;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/11/29 14:41
 * @desc:
 */
public class BKChapterBO {
    /**
     * id : 343551
     * name : 盛唐幻夜
     * list : [{"name":"正文","list":[{"id":1819136,"name":"二 4 黑衣人","hasContent":1},{"id":1869002,"name":"十六 1 讨要佛珠","hasContent":1}]}]
     */

    private int id;
    private String name;
    private List<ListBeanX> list;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ListBeanX> getList() {
        return list;
    }

    public void setList(List<ListBeanX> list) {
        this.list = list;
    }

    public static class ListBeanX {
        /**
         * name : 正文
         * list : [{"id":1819136,"name":"二 4 黑衣人","hasContent":1},{"id":1869002,"name":"十六 1 讨要佛珠","hasContent":1}]
         */

        private String name;
        private List<ListBean> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 1819136
             * name : 二 4 黑衣人
             * hasContent : 1
             */

            private int id;
            private String name;
            private int hasContent;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getHasContent() {
                return hasContent;
            }

            public void setHasContent(int hasContent) {
                this.hasContent = hasContent;
            }
        }
    }
}
