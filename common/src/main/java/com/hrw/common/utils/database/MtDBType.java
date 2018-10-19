package com.hrw.common.utils.database;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/10/18 14:51
 * @desc:
 */
public enum MtDBType {
    TABLE_NAME("table_name", 0),
    TABLE_TYPE_PRIMARY("primary", 1),
    TABLE_TYPE_TEXT("text", 2),
    TABLE_TYPE_INTEGER("integer", 3);

    MtDBType(String value, int index) {
        this.value = value;
        this.index = index;
    }

    public static String getName(int index) {
        for (MtDBType dbType : MtDBType.values()) {
            if (dbType.getIndex() == index) {
                return dbType.value;
            }
        }
        return null;
    }

    private String value;
    private int index;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
