package com.hrw.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @version 1.0.0
 * @author:hrw
 * @date:2018/10/22 17:15
 * @desc:
 */
public class MtDateUtil {

    public static String getTimeString() {
        return getTimeString(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getTimeString(String formatString) {
        return getTimeString(new Date(), formatString);
    }


    public static String getTimeString(Date date, String formatString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        return dateFormat.format(date);
    }

    public static String getTimeString(long milliseconds, String formatString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        return dateFormat.format(milliseconds);
    }


}
