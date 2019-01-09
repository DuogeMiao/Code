package com.tsingshan.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 时间工具类
 * 
 * @author 拖板孩
 */
public class DateUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

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

    /**
     * 假设传入的日期格式是yyyy-MM-dd HH:mm:ss, 也可以传入yyyy-MM-dd，如2018-1-1或者2018-01-01格式
     * @param strDate
     * @return
     */
    public static boolean isValidDate(final String strDate,final String datePattern) {
        SimpleDateFormat format = new SimpleDateFormat(datePattern);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01
            format.setLenient(false);
            Date date = format.parse(strDate);
            //判断传入的yyyy年-MM月-dd日 字符串是否为数字
            String[] sArray = strDate.split("-");
            for (String s : sArray) {
                boolean isNum = s.matches("[0-9]+");
                //+表示1个或多个（如"3"或"225"），*表示0个或多个（[0-9]*）（如""或"1"或"22"），?表示0个或1个([0-9]?)(如""或"7")
                if (!isNum) {
                    return false;
                }
            }
        } catch (Exception e) {
            // e.printStackTrace();
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
        return true;
    }


    /**
     * 判断是否是对应的格式的日期字符串
     * @param strDate
     * @param datePattern
     * @return true/false
     */
    public static boolean isRightDateStr(final String strDate,final String datePattern){
        DateFormat dateFormat  = new SimpleDateFormat(datePattern);
        try {
            //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
            dateFormat.setLenient(false);
            dateFormat.parse(strDate);
            Date date = (Date)dateFormat.parse(strDate);
            //重复比对一下，防止类似 “2018-5-15” 类型的字符串通过
            String newDateStr = dateFormat.format(date);
            if(strDate.equals(newDateStr)){
                return true;
            }else {
                LOGGER.error("字符串dateStr:{}， 不是严格的 datePattern:{} 格式的字符串",strDate,datePattern);
                return false;
            }
        } catch (ParseException e) {
            LOGGER.error("字符串dateStr:{}，不能按照 datePattern:{} 样式转换",strDate,datePattern);
            return false;
        }
    }



    //*****************************日期运算开始************************************//

    /**
     * 时间相减得到天数
     * @param beginDateStr
     * @param endDateStr
     * @param datePattern 日期格式
     * @return long 天数
     */
    public static long getDaySub(final String beginDateStr, final String endDateStr, final String datePattern) {
        long day = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
            Date beginDate = sdf.parse(beginDateStr);
            Date endDate = sdf.parse(endDateStr);
            day = (endDate.getTime() - beginDate.getTime()) / (24*60*60*1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     *  日期的年份 加/减  几年  2018-12-26 ==> 2021-12-26
     * @param yearNum 为正数 => 加   为负数 => 减
     * @param dateDate 日期
     * @return Date 日期
     */
    public static Date dateYear(final Integer yearNum, final Date dateDate) {
        Calendar calendar = calendar(dateDate);
        // 年份加 yearNum
        calendar.add(Calendar.YEAR, yearNum);
        // 结果
        return calendar.getTime();
    }

    public static String dateYear(final Integer yearNum, final String dateStr, final String dateFormat) {
        Date date = dateTime(dateFormat, dateStr);
        Calendar calendar = calendar(date);
        // 年份加 yearNum
        calendar.add(Calendar.YEAR, yearNum);
        // 结果
        return parseDateToStr(dateFormat,calendar.getTime());
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

    /**
     * 把日期的 年月日转换为 long
     * @param date
     * @return long日期
     * @throws Exception
     */
    public static Long getDataToData(Date date) throws Exception{
        //日期格式，精确到日
        DateFormat df = DateFormat.getDateInstance();
        return dateToLong(df,date);
    }
    public static Long getDataToSecond(Date date) throws Exception{
        //可以精确到时分秒
        DateFormat df = DateFormat.getDateTimeInstance();
        return dateToLong(df,date);
    }
    private static Long dateToLong(DateFormat df,Date date) throws Exception{
        String format = df.format(date);
        Date parse = df.parse(format);
        return parse.getTime();
    }

//    public static void main(String[] args) throws Exception{
//        String value = "2018-08-09 23:10:32";
//        if (DateUtils.isRightDateStr(value,"yyyy-MM-dd")) {
//            System.out.println("1: " +DateUtils.dateTime("yyyy-MM-dd",value));
//        } else if (DateUtils.isRightDateStr(value,"yyyy-MM-dd HH:mm:ss")) {
//            System.out.println("2: " +DateUtils.dateTime("yyyy-MM-dd HH:mm:ss",value));
//        } else if (DateUtils.isRightDateStr(value,"yyyyMMdd")) {
//            System.out.println("3: " +DateUtils.dateTime("yyyyMMdd",value));
//        }
//
//    }

}
