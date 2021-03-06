package com.crell.core.util;

import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by crell on 2016/3/23.
 */
public class CommonUtil {

    public static boolean isContainsChinese(String str)
    {
        return Pattern.compile("[\\u4e00-\\u9fa5]").matcher(str).find();
    }

    public static boolean checkDateValid(String time) {
        Date date = new Date();
        long nowTime = date.getTime();
        if(nowTime < Long.parseLong(time)){
            return true;
        }
        return false;
    }
}
