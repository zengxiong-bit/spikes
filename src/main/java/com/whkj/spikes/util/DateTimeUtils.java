package com.whkj.spikes.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateTimeUtils {

    /**
     * 每秒毫秒数
     */
    public static final long MILLIS_PER_SECOND = 1000;

    /**
     * 每分毫秒数 60*1000
     */
    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

    /**
     * 每小时毫秒数 36*60*1000
     */
    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

    /**
     * 每天毫秒数 24*60*60*1000;
     */
    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;


    private static final int[] MONTH_LENGTH = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    //////// 日期比较 ///////////

    /**
     * 是否同一天.
     *
     * @see DateUtils#isSameDay(Date, Date)
     */
    public static boolean isSameDay(final Date date1,final Date date2) {
        return DateUtils.isSameDay(date1, date2);
    }

    /**
     * 是否同一时刻.
     */
    public static boolean isSameTime( final Date date1,  final Date date2) {
        // date.getMillisOf() 比date.getTime()快
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断日期是否在范围内，包含相等的日期
     */
    public static boolean isBetween( final Date date,  final Date start,  final Date end) {
        if (date == null || start == null || end == null || start.after(end)) {
            throw new IllegalArgumentException("some date parameters is null or dateBein after dateEnd");
        }
        return !date.before(start) && !date.after(end);
    }

    //////////// 往前往后滚动时间//////////////

    /**
     * 加一月
     */
    public static Date addMonths( final Date date, int amount) {
        return DateUtils.addMonths(date, amount);
    }

    /**
     * 减一月
     */
    public static Date subMonths( final Date date, int amount) {
        return DateUtils.addMonths(date, -amount);
    }

    /**
     * 加一周
     */
    public static Date addWeeks( final Date date, int amount) {
        return DateUtils.addWeeks(date, amount);
    }

    /**
     * 减一周
     */
    public static Date subWeeks( final Date date, int amount) {
        return DateUtils.addWeeks(date, -amount);
    }

    /**
     * 加一天
     */
    public static Date addDays( final Date date, final int amount) {
        return DateUtils.addDays(date, amount);
    }

    /**
     * 减一天
     */
    public static Date subDays( final Date date, int amount) {
        return DateUtils.addDays(date, -amount);
    }

    /**
     * 加一小时
     */
    public static Date addHours( final Date date, int amount) {
        return DateUtils.addHours(date, amount);
    }

    /**
     * 减一小时
     */
    public static Date subHours( final Date date, int amount) {
        return DateUtils.addHours(date, -amount);
    }

    /**
     * 加一分钟
     */
    public static Date addMinutes( final Date date, int amount) {
        return DateUtils.addMinutes(date, amount);
    }

    /**
     * 减一分钟
     */
    public static Date subMinutes( final Date date, int amount) {
        return DateUtils.addMinutes(date, -amount);
    }

    /**
     * 终于到了，续一秒.
     */
    public static Date addSeconds( final Date date, int amount) {
        return DateUtils.addSeconds(date, amount);
    }

    /**
     * 减一秒.
     */
    public static Date subSeconds( final Date date, int amount) {
        return DateUtils.addSeconds(date, -amount);
    }

    //////////// 直接设置时间//////////////

    /**
     * 设置年份, 公元纪年.
     */
    public static Date setYears( final Date date, int amount) {
        return DateUtils.setYears(date, amount);
    }

    /**
     * 设置月份, 0-11.
     */
    public static Date setMonths( final Date date, int amount) {
        return DateUtils.setMonths(date, amount);
    }

    /**
     * 设置日期, 1-31.
     */
    public static Date setDays( final Date date, int amount) {
        return DateUtils.setDays(date, amount);
    }

    /**
     * 设置小时, 0-23.
     */
    public static Date setHours( final Date date, int amount) {
        return DateUtils.setHours(date, amount);
    }

    /**
     * 设置分钟, 0-59.
     */
    public static Date setMinutes( final Date date, int amount) {
        return DateUtils.setMinutes(date, amount);
    }

    /**
     * 设置秒, 0-59.
     */
    public static Date setSeconds( final Date date, int amount) {
        return DateUtils.setSeconds(date, amount);
    }

    /**
     * 设置毫秒.
     */
    public static Date setMilliseconds( final Date date, int amount) {
        return DateUtils.setMilliseconds(date, amount);
    }

    ///// 获取日期的位置//////

    /**
     * 获得日期是一周的第几天. 已改为中国习惯，1 是Monday，而不是Sundays.
     */
    public static int getDayOfWeek( final Date date) {
        int result = get(date, Calendar.DAY_OF_WEEK);
        return result == 1 ? 7 : result - 1;
    }

    /**
     * 获取一个月的当前天
     */
    public static int getDayOfMonth( final Date date) {
        return get(date, Calendar.DAY_OF_MONTH);
    }


    /**
     *  获得月份，从0开始计数
     *
     */
    public static int getMonth( final Date date) {
        return get(date, Calendar.MONTH);
    }

    /**
     * 获得日期是一年的第几天，返回值从1开始
     */
    public static int getDayOfYear( final Date date) {
        return get(date, Calendar.DAY_OF_YEAR);
    }

    public static int getYear( final Date date) {
        return get(date, Calendar.YEAR);
    }

    /**
     * 获得日期是一月的第几周，返回值从1开始.
     * <p>
     * 开始的一周，只要有一天在那个月里都算. 已改为中国习惯，1 是Monday，而不是Sunday
     */
    public static int getWeekOfMonth( final Date date) {
        return getWithMondayFirst(date, Calendar.WEEK_OF_MONTH);
    }

    /**
     * 获得日期是一年的第几周，返回值从1开始.
     * <p>
     * 开始的一周，只要有一天在那一年里都算.已改为中国习惯，1 是Monday，而不是Sunday
     */
    public static int getWeekOfYear( final Date date) {
        return getWithMondayFirst(date, Calendar.WEEK_OF_YEAR);
    }

    private static int get(final Date date, int field) {
        Validate.notNull(date, "The date must not be null");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);

        return cal.get(field);
    }

    private static int getWithMondayFirst(final Date date, int field) {
        Validate.notNull(date, "The date must not be null");
        Calendar cal = Calendar.getInstance();
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(date);
        return cal.get(field);
    }

    ///// 获得往前往后的日期//////

    /**
     * 2016-11-10 07:33:23, 则返回2016-1-1 00:00:00
     */
    public static Date beginOfYear( final Date date) {
        return DateUtils.truncate(date, Calendar.YEAR);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-12-31 23:59:59.999
     */
    public static Date endOfYear( final Date date) {
        return new Date(nextYear(date).getTime() - 1);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2017-1-1 00:00:00
     */
    public static Date nextYear( final Date date) {
        return DateUtils.ceiling(date, Calendar.YEAR);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-11-1 00:00:00
     */
    public static Date beginOfMonth( final Date date) {
        return DateUtils.truncate(date, Calendar.MONTH);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-11-30 23:59:59.999
     */
    public static Date endOfMonth( final Date date) {
        return new Date(nextMonth(date).getTime() - 1);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-12-1 00:00:00
     */
    public static Date nextMonth( final Date date) {
        return DateUtils.ceiling(date, Calendar.MONTH);
    }

    /**
     * 2017-1-20 07:33:23, 则返回2017-1-16 00:00:00
     */
    public static Date beginOfWeek( final Date date) {
        return DateUtils.truncate(subDays(date, getDayOfWeek(date) - 1), Calendar.DATE);
    }

    /**
     * 2017-1-20 07:33:23, 则返回2017-1-22 23:59:59.999
     */
    public static Date endOfWeek( final Date date) {
        return new Date(nextWeek(date).getTime() - 1);
    }

    /**
     * 2017-1-23 07:33:23, 则返回2017-1-22 00:00:00
     */
    public static Date nextWeek( final Date date) {
        return DateUtils.truncate(addDays(date, 8 - getDayOfWeek(date)), Calendar.DATE);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-11-10 00:00:00
     */
    public static Date beginOfDate( final Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }

    /**
     * 2017-1-23 07:33:23, 则返回2017-1-23 23:59:59.999
     */
    public static Date endOfDate( final Date date) {
        return new Date(nextDate(date).getTime() - 1);
    }

    /**
     * 2016-11-10 07:33:23, 则返回2016-11-11 00:00:00
     */
    public static Date nextDate( final Date date) {
        return DateUtils.ceiling(date, Calendar.DATE);
    }

    /**
     * 2016-12-10 07:33:23, 则返回2016-12-10 07:00:00
     */
    public static Date beginOfHour( final Date date) {
        return DateUtils.truncate(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 2017-1-23 07:33:23, 则返回2017-1-23 07:59:59.999
     */
    public static Date endOfHour( final Date date) {
        return new Date(nextHour(date).getTime() - 1);
    }

    /**
     * 2016-12-10 07:33:23, 则返回2016-12-10 08:00:00
     */
    public static Date nextHour( final Date date) {
        return DateUtils.ceiling(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 2016-12-10 07:33:23, 则返回2016-12-10 07:33:00
     */
    public static Date beginOfMinute( final Date date) {
        return DateUtils.truncate(date, Calendar.MINUTE);
    }

    /**
     * 2017-1-23 07:33:23, 则返回2017-1-23 07:33:59.999
     */
    public static Date endOfMinute( final Date date) {
        return new Date(nextMinute(date).getTime() - 1);
    }

    /**
     * 2016-12-10 07:33:23, 则返回2016-12-10 07:34:00
     */
    public static Date nextMinute( final Date date) {
        return DateUtils.ceiling(date, Calendar.MINUTE);
    }

    ////// 闰年及每月天数///////

    /**
     * 是否闰年.
     */
    public static boolean isLeapYear( final Date date) {
        return isLeapYear(get(date, Calendar.YEAR));
    }

    /**
     * 是否闰年，移植Jodd Core的TimeUtil
     * <p>
     * 参数是公元计数, 如2016
     */
    public static boolean isLeapYear(int y) {
        boolean result = false;

        if (((y % 4) == 0) && // must be divisible by 4...
            ((y < 1582) || // and either before reform year...
                ((y % 100) != 0) || // or not a century...
                ((y % 400) == 0))) { // or a multiple of 400...
            result = true; // for leap year.
        }
        return result;
    }

    /**
     * 获取某个月有多少天, 考虑闰年等因数, 移植Jodd Core的TimeUtil
     */
    public static int getMonthLength( final Date date) {
        int year = get(date, Calendar.YEAR);
        int month = get(date, Calendar.MONTH)+1;
        return getMonthLength(year, month);
    }

    /**
     * 获取某个月有多少天, 考虑闰年等因数, 移植Jodd Core的TimeUtil
     */
    public static int getMonthLength(int year, int month) {

        if ((month < 1) || (month > 12)) {
            throw new IllegalArgumentException("Invalid month: " + month);
        }
        if (month == 2) {
            return isLeapYear(year) ? 29 : 28;
        }

        return MONTH_LENGTH[month];
    }


    /*
     * 时间样式参考　　 EEE:星期 aa:上午/下午等 0-yyyy-MM-dd<br> 1-yyyy-MM-dd HH<br>
     * 2-yyyy-MM-dd HH:mm<br> 3-yyyy-MM-dd HH:mm:ss<br> 4-yyyy年MM月dd日 HH:mm:ss
     * 5-MM/dd/yyyy hh:mm:ss aa 6- yy年MM月dd日 7-yyyy/MM/dd HH:mm:s 8-yyyy-MM-dd
     * HH:mm:ss SSS (毫秒)
     */
    private DateTimeUtils() {
    }

    /**
     * @param timeStr       时间字符串
     * @param formatPattern 时间格式
     * @return Date
     * @throws RuntimeException
     * @Title: parse
     * @Description:时间字符串根据指定格式转化为Date
     */
    public static Date parse(String timeStr, String formatPattern) {
        try {
            return new SimpleDateFormat(formatPattern).parse(timeStr);
        } catch (ParseException e) {
            throw new IllegalArgumentException("could not parse date: " + timeStr
                + " LEGACY_FORMAT = "
                + new SimpleDateFormat(formatPattern).toPattern(), e);
        }
    }

    /**
     * @param date          　　时间
     * @param formatPattern 　时间格式 如："yyyy-MM-dd HH:mm:ss"
     * @return String
     * @Title: format
     * @Description:　时间转化为指定的格式的时间字符串
     */
    public static String format(Date date, String formatPattern) {
        if (date == null || StringUtils.isBlank(formatPattern)) {
            throw new IllegalArgumentException("待格式化的时间或时间格式化模式串为空");
        }
        return new SimpleDateFormat(formatPattern).format(date);
    }

    public static String format(Date date) {
        return format(date,DatePattern.COMMON_DATE_AND_TIME);
    }

    public static String format(long milliseconds) {
        return format( new Date(milliseconds),DatePattern.COMMON_DATE_AND_TIME);
    }

    /**
     * @param date
     * @return int
     * @Title: getMonthOfDays
     * @Description: 返回给定时间所在月份的天数
     */
    public static int getMaxDayOfMonth(Date date) {
        return new DateTime(date).dayOfMonth().withMaximumValue()
            .getDayOfMonth();
    }

    /**
     * @param date
     * @return Date 当月最后一天的23:59:59
     * @Title: getLastDayOfMonth
     * @Description: 返回给定时间所在月份的最后一天
     */
    public static Date getLastDayOfMonth(Date date) {
        return new DateTime(date).dayOfMonth().withMaximumValue().millisOfDay()
            .withMaximumValue().toDate();
    }

    /**
     * @param date
     * @return Date 当月第一天的00:00:00
     * @Title: getfirstdayofmonth
     * @Description: 返回给定时间所在月份的第一天
     */
    public static Date getFirstDayOfMonth(Date date) {
        return new DateTime(date).dayOfMonth().withMinimumValue()
            .withTimeAtStartOfDay().toDate();
    }



    /**
     * 计算两个日期之间相差的天数，不足一天向上取整
     *
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static Integer daysBetweenUp(Date smdate, Date bdate) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        BigDecimal time1 = BigDecimal.valueOf(cal.getTimeInMillis());
        cal.setTime(bdate);
        BigDecimal time2 = BigDecimal.valueOf(cal.getTimeInMillis());
        BigDecimal betweenDays = time2.subtract(time1).divide(new BigDecimal(MILLIS_PER_DAY),BigDecimal.ROUND_UP);
        return betweenDays.intValue();
    }




    /**
     * 计算两个日期之间相差的天数，不足一天向下取整
     *
     * @param smdate
     *            较小的时间
     * @param bdate
     *            较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static Integer daysBetweenDown(Date smdate, Date bdate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        BigDecimal time1 = BigDecimal.valueOf(cal.getTimeInMillis());
        cal.setTime(bdate);
        BigDecimal time2 = BigDecimal.valueOf(cal.getTimeInMillis());
        BigDecimal betweenDays = time2.subtract(time1).divide(new BigDecimal(MILLIS_PER_DAY),BigDecimal.ROUND_DOWN);
        return betweenDays.intValue();
    }

    //UNIX时间戳转换日期:方法一
    //方法一，可能会报Long未定义错误;[未知，若有知道的，请留下言]
     /* @param formatPattern 　时间格式 如："yyyy-MM-dd HH:mm:ss"
    //当然可以转换成 str=unix_time.format(new Date(Long.parseLong(nowtime+"000")));
    */
    public static String getTimestampDate(Long timestamp,String formatPattern){
        String str;
        SimpleDateFormat unixTime=new SimpleDateFormat(formatPattern);
        str=unixTime.format(new Date(timestamp*1000));
        //此处增加"000"是因为Date类中，传入的时间单位为毫秒。
        return str;
    }


    /**
     * 加一天工作日
     */
    public static Date addWorkDays( final Date date, final int days) {
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        int i=0;
        while(i<days){
            calendar.add(Calendar.DATE,1);//整数往后推日期,负数往前推日期
            i++;
            if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY ||
                    calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
                i--;
            }
        }
        return calendar.getTime();
    }


    /**
     * 格式化开始时间(天)
     */
    public static Date formatDateStart( final Date date) {
        return new DateTime(date).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0).withMillisOfSecond(0).toDate();
    }

    /**
     * 格式化结束时间(天)
     */
    public static Date formatDateEnd( final Date date) {
        return new DateTime(date).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59).withMillisOfSecond(0).toDate();
    }
}
