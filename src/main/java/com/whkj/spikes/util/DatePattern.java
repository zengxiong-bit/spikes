package com.whkj.spikes.util;

/**
 * 常用的日期模式.
 */
public final class DatePattern {

    /**
     * 只有日期 年月日
     */
    public static final String COMMON_DATE = "yyyy-MM-dd";

    /**
     * 中文日期格式,年月日
     */
    public static final String CHINESE_COMMON_DATE = "yyyy年MM月dd日";

    /**
     * 只有时间
     */
    public static final String COMMON_TIME = "HH:mm:ss";

    /**
     * 只有时间且不带秒
     */
    public static final String COMMON_TIME_WITHOUT_SECOND = "HH:mm";

    /**
     */
    public static final String COMMON_DATE_AND_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 中文日期+时间格式
     */
    public static final String CHINESE_COMMON_DATE_AND_TIME = "yyyy年MM月dd日 HH:mm:ss";

    /**
     * 带毫秒的时间格式
     */
    public static final String COMMON_DATE_AND_TIME_WITH_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 不带秒
     */
    public static final String COMMON_DATE_AND_TIME_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";

    /**
     * 不带年 不带秒
     */
    public static final String COMMON_DATE_AND_TIME_WITHOUT_YEAR_AND_SECOND = "MM-dd HH:mm";

    // *****************************************************************************************************

    /**
     * 时间戳, 一般用于拼接文件名称
     */
    public static final String TIMESTAMP = "yyyyMMddHHmmss";

    /**
     * 带毫秒的时间戳
     */
    public static final String TIMESTAMP_WITH_MILLISECOND = "yyyyMMddHHmmssSSS";

    //******************************************************************************************************

    /**
     * 年月 带水平线,一般用于分类日志,将众多日志按月分类
     */
    public static final String YEAR_AND_MONTH = "yyyy-MM";

    /**
     * 月日
     */
    public static final String MONTH_AND_DAY = "MM-dd";

    /**
     * 月日带星期
     */
    public static final String MONTH_AND_DAY_WITH_WEEK = "MM-dd(E)";

    //**********************************************************************************

    public static final String ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";

    public static final String yy = "yy";

    public static final String yyyy = "yyyy";

    /**
     * MM月份
     */
    public static final String MM = "MM";

    public static final String yyyyMMdd = "yyyyMMdd";

    public static final String yyyyMM = "yyyyMM";

    public static final String mmss = "mmss";

    public static final String HH = "HH";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd#HH:mm:ss:SSS";

    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    private DatePattern() {
    }
}
