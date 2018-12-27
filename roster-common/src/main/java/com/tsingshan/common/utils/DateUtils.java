package com.tsingshan.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 * 
 * @author 拖板孩
 */
public class DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static  String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static  String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static  String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static  String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static  String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static  Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static  String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static  String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    //*****************************日期运算开始************************************//

    /**
     *  日期的年份 加/减  几年  2018-12-26 ==> 2021-12-26
     * @param yearNum 为正数 => 加   为负数 => 减
     * @param date 日期
     * @return Date 日期
     */
    public static Date dateYear(final Integer yearNum, final Date date) {
        Calendar calendar = calendar(date);
        // 年份加 yearNum
        calendar.add(Calendar.YEAR, yearNum);
        // 结果
        return calendar.getTime();
    }


    /**
     *  日期的月份 加减  2018-12-26 ==> 2021-12-26
     * @param monthNum 为正数 => 加   为负数 => 减
     * @param date 日期
     * @return Date 日期
     */
    public static Date dateMonth(final Integer monthNum, final Date date) {
        Calendar calendar = calendar(date);
        // 月份加 yearNum
        calendar.add(Calendar.MONTH, monthNum);
        // 结果
        return calendar.getTime();
    }

    /**
     * 日期的 天数 加减  2018-12-26 ==> 2021-12-29
     * @param dateNum 天数
     * @param date 日期
     * @return 日期
     */
    public static Date dateDate(final Integer dateNum, final Date date) {
        Calendar calendar = calendar(date);
        // 天数加 yearNum
        calendar.add(Calendar.DATE, dateNum);
        // 结果
        return calendar.getTime();
    }

    public static Date dateHour(final Integer hourNum, final Date date) {
        Calendar calendar = calendar(date);
        // 小时加 yearNum
        calendar.add(Calendar.HOUR, hourNum);
        // 结果
        return calendar.getTime();
    }
    public static Date dateMinute(final Integer minuteNum, final Date date) {
        Calendar calendar = calendar(date);
        // 分钟加 yearNum
        calendar.add(Calendar.MINUTE, minuteNum);
        // 结果
        return calendar.getTime();
    }
    public static Date dateSecond(final Integer secondNum, final Date date) {
        Calendar calendar = calendar(date);
        // 秒 加 yearNum
        calendar.add(Calendar.SECOND, secondNum);
        // 结果
        return calendar.getTime();
    }
    public static Date dateMilliSecond(final Integer milliSecondNum, final Date date) {
        Calendar calendar = calendar(date);
        // 毫秒加 yearNum
        calendar.add(Calendar.MILLISECOND, milliSecondNum);
        // 结果
        return calendar.getTime();
    }
    //*****************************日期运算结束************************************//
    private static Calendar calendar (final Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

}
