package com.ybj.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期转换工具类
 */
public class ConvertDate {
    static String[] type = {
            "yyyy-MM-dd HH:mm:ss",//0
            "yyyy-MM-dd",
            "HH:mm:ss",
            "yyyy/MM/dd HH:mm:ss",
            "yyyy/MM/dd",//4
            "yyyy年MM月dd日 HH时mm分ss秒",};
    /**
     * 字符串转换为日期
     *
     * @param str   字符串
     * @param index 转换格式
     * @return 日期
     */
    public static Date strToDate(String str, int index) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(type[index]);
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换为字符串
     *
     * @param date  日期
     * @param index 转换格式
     * @return 字符串
     */
    public static String dateToStr(Date date, int index) {
        SimpleDateFormat sdf = new SimpleDateFormat(type[index]);
        return sdf.format(date);
    }

//    public static void main(String[] args){
//        Date date = new Date();
//        String str1 = dateToStr(date, 0);
//        System.out.println("dateToStr1 = " + str1);
//
//        String str2 = "2022-05-02";
//        Date date2;
//        String str3 = dateToStr(strToDate(str2, 1), 1);
//        System.out.println("dateToStr2 = " + str3);
//    }

}




