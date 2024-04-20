package com.whkj.spikes.util;


import java.security.SecureRandom;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class UtilAll {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final AtomicLong ATOMIC = new AtomicLong();

    private UtilAll() {

    }

    public static String generateTaskId() {
        long increment = ATOMIC.getAndIncrement();
        String time = DateTimeUtils.format(new Date(), "yyyyMMdd_HHmm");
        return time + "_" + increment;
    }

    private static String formatCompact4Digits(double value) {
        return String.format(Locale.ROOT, "%.4g", value);
    }

    public static String toPrettyString(long milliseconds) {
        long nanos = milliseconds * 1000000L;

        TimeUnit unit = chooseUnit(nanos);
        double value = (double) nanos / (double) TimeUnit.NANOSECONDS.convert(1L, unit);

        // Too bad this functionality is not exposed as a regular method call
        return formatCompact4Digits(value) + " " + abbreviate(unit);
    }

    private static TimeUnit chooseUnit(long nanos) {
        if (TimeUnit.DAYS.convert(nanos, TimeUnit.NANOSECONDS) > 0L) {
            return TimeUnit.DAYS;
        }
        if (TimeUnit.HOURS.convert(nanos, TimeUnit.NANOSECONDS) > 0L) {
            return TimeUnit.HOURS;
        }
        if (TimeUnit.MINUTES.convert(nanos, TimeUnit.NANOSECONDS) > 0L) {
            return TimeUnit.MINUTES;
        }
        if (TimeUnit.SECONDS.convert(nanos, TimeUnit.NANOSECONDS) > 0L) {
            return TimeUnit.SECONDS;
        }
        if (TimeUnit.MILLISECONDS.convert(nanos, TimeUnit.NANOSECONDS) > 0L) {
            return TimeUnit.MILLISECONDS;
        }
        if (TimeUnit.MICROSECONDS.convert(nanos, TimeUnit.NANOSECONDS) > 0L) {
            return TimeUnit.MICROSECONDS;
        }
        return TimeUnit.NANOSECONDS;
    }

    private static String abbreviate(TimeUnit unit) {
        switch (unit) {
            case NANOSECONDS:
                return "ns";
            case MICROSECONDS:
                return "μs";
            case MILLISECONDS:
                return "ms";
            case SECONDS:
                return "s";
            case MINUTES:
                return "min";
            case HOURS:
                return "h";
            case DAYS:
                return "d";
            default:
                throw new AssertionError();
        }
    }


    /**
     * 返回随机ID.
     */
    public static long randomId() {
        return SECURE_RANDOM.nextLong();
    }

    /**
     * 返回随机名称, prefix字符串+5位随机数字.
     */
    public static String randomName(final String prefix) {
        return prefix + SECURE_RANDOM.nextInt(10000);
    }

    /**
     * 从输入list中随机返回一个对象.
     */
    public static <T> T randomOne(List<T> list) {
        return randomSome(list, 1).get(0);
    }

    /**
     * 从输入list中随机返回随机个对象.
     */
    public static <T> List<T> randomSome(List<T> list) {
        return randomSome(list, SECURE_RANDOM.nextInt(list.size()));
    }

    /**
     * 从输入list中随机返回count个对象.
     */
    public static <T> List<T> randomSome(List<T> list, int count) {
        Collections.shuffle(list);
        return list.subList(0, count);
    }

    public static String formatFriendlyTimeSpanByNow(long timeStampMillis) {
        long now = ClockUtil.currentTimeMillis();
        long span = now - timeStampMillis;
        if (span < 0L) {
            // 'c' 日期和时间，被格式化为 "%ta %tb %td %tT %tZ %tY"，例如 "Sun Jul 20 16:17:00 EDT 1969"。
            return String.format("%tc", timeStampMillis);
        }
        if (span < DateTimeUtils.MILLIS_PER_SECOND) {
            return "刚刚";
        } else if (span < DateTimeUtils.MILLIS_PER_MINUTE) {
            return String.format("%d秒前", span / DateTimeUtils.MILLIS_PER_SECOND);
        } else if (span < DateTimeUtils.MILLIS_PER_HOUR) {
            return String.format("%d分钟前", span / DateTimeUtils.MILLIS_PER_MINUTE);
        }
        // 获取当天00:00
        long wee = DateTimeUtils.beginOfDate(new Date(now)).getTime();
        if (timeStampMillis >= wee) {
            // 'R' 24 小时制的时间，被格式化为 "%tH:%tM"
            return String.format("今天%tR", timeStampMillis);
        } else if (timeStampMillis >= wee - DateTimeUtils.MILLIS_PER_DAY) {
            return String.format("昨天%tR", timeStampMillis);
        } else {
            // 'F' ISO 8601 格式的完整日期，被格式化为 "%tY-%tm-%td"。
            return String.format("%tF %<tT", timeStampMillis);
        }
    }

}
