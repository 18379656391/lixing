package com.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: fangxu
 * @Date: 2022/10/31 19:09
 * @Description:
 */
@Slf4j
public class DateUtils {

    public static final String DATE_TIME_LONG_14 = "yyyyMMddHHmmss";
    public static final String DATE_TIME_LONG_17 = "yyyyMMddHHmmssSSS";
    public static final String CURRENT_TIME_LONG_15 = "yyyyMMdd.HHmmss";
    /**
     * 显示年月日，例如 20220811
     */
    public static final String DATETIME_FORMAT_SHORT_8 = "yyyyMMdd";
    public static final String TIME_SHORT_8 = "HHmmss";
    public static final String DATETIME_FORMAT_SHORT_10 = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT_SHORT_10_1 = "yyyy/MM/dd";
    /**
     * 显示年月日时分秒，例如 2022-08-11
     */
    public static final String DATETIME_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
    public static final String POINT = ".";


    private static final String SDF1REG = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";

    private static final String SDF2REG = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2}$";

    private static final String SDF3REG = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}$";

    private static final String SDF4REG = "^\\d{2,4}\\/\\d{1,2}\\/\\d{1,2}$";

    private static final String SDF5REG = "^\\d{8}$";

    private static final String SDF6REG = "^\\d{2,4}\\-\\d{1,2}\\-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}\\.\\d{1,3}$";

    /**
     * 根据给定的格式返回日期
     *
     * @param pDate
     * @param format
     * @return
     */
    public static String formatDate(Date pDate, String format) {
        if (pDate == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(pDate);
    }

    public static Date parse(String pDate, String format) {
        if (StringUtils.isBlank(pDate)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(pDate);
        } catch (ParseException e) {
            log.error("DateUtils#parse() error", e);
            throw new RuntimeException("");
        }
    }



    public static Date getDateTimeByString(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            log.error("", e);
            return null;
        }
    }

    /**
     * 获取date天开始时间
     *
     * @param date
     * @return
     */
    public static Date getDayOfStartTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取date天结束时间
     *
     * @param date
     * @return
     */
    public static Date getDayOfEndTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 59);
        return calendar.getTime();
    }





    /**
     * 是否是每自然季度的首工作日
     * @param localDate
     * @param isFirst true：第一天；false：最后一天
     * @return
     */
    public static Boolean isQuarterlyFirstOrEndWorkDay(Date localDate, Boolean isFirst) {
        if (localDate == null) {
            return Boolean.FALSE;
        }
        LocalDate quarterLocalDate = getStartOrEndDayOfQuarter(localDate, isFirst);
        String localDay = formatDate(localDate, DATETIME_FORMAT_SHORT_8);
        String expectDay = quarterLocalDate.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_SHORT_8));
        return StringUtils.equals(localDay, expectDay);
    }

    /**
     * 是否是每半年度的首工作日
     * @param localDate
     * @param isFirst true：第一天；false：最后一天
     * @return
     */
    public static Boolean isHalfYearlyFirstOrEndWorkDay(Date localDate, Boolean isFirst) {
        if (localDate == null) {
            return Boolean.FALSE;
        }
        LocalDate halfYearLocalDate = getStartOrEndWorkDayOfHalfYear(localDate, isFirst);
        String localDay = formatDate(localDate, DATETIME_FORMAT_SHORT_8);
        String expectDay = halfYearLocalDate.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_SHORT_8));
        return StringUtils.equals(localDay, expectDay);
    }




    /**
     * 获取当前日期所在季度的开始日期和结束日期
     * 季度一年四季， 第一季度：1月-3月， 第二季度：4月-6月， 第三季度：7月-9月， 第四季度：10月-12月
     * @param isFirst  true表示查询本季度开始日期  false表示查询本季度结束日期
     * @return
     */
    public static LocalDate getStartOrEndDayOfQuarter(Date fileDate, Boolean isFirst){

//        LocalDate today = LocalDate.now();
        LocalDate fileDay = fileDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate resDate = LocalDate.now();
        if (fileDay == null) {
            fileDay = resDate;
        }
        Month month = fileDay.getMonth();
        Month firstMonthOfQuarter = month.firstMonthOfQuarter();
        Month endMonthOfQuarter = Month.of(firstMonthOfQuarter.getValue() + 2);
        if (isFirst) {
            resDate = LocalDate.of(fileDay.getYear(), firstMonthOfQuarter, 1);
        } else {
            resDate = LocalDate.of(fileDay.getYear(), endMonthOfQuarter, endMonthOfQuarter.length(fileDay.isLeapYear()));
        }

        // 如果为周末（开始日期顺延下一日，结束日期往前一天）
        while (resDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || resDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            if (isFirst) {
                resDate = resDate.plusDays(1);
            } else {
                resDate = resDate.minusDays(1);
            }
        }
        return resDate;
    }

    /**
     * 获取当前日期所在半年度的开始日期和结束日期
     * 半年 1-6 7-12
     * @param isFirst  true表示查询半年度开始日期  false表示查询半年度结束日期
     * @return
     */
    public static LocalDate getStartOrEndWorkDayOfHalfYear(Date fileDate, Boolean isFirst){
        //        LocalDate today = LocalDate.now();
        LocalDate today = fileDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate resDate = LocalDate.now();
        if (today == null) {
            today = resDate;
        }
        Month todayMonth = today.getMonth();
        Month firstMonthOfYear;
        Month endMonthOfYear;
        if (todayMonth.getValue() < Month.JULY.getValue()) {
            // 上半年
            firstMonthOfYear = Month.JANUARY;
            endMonthOfYear = Month.JUNE;
        } else {
            firstMonthOfYear = Month.JULY;
            endMonthOfYear = Month.DECEMBER;
        }

        if (isFirst) {
            resDate = LocalDate.of(today.getYear(), firstMonthOfYear, 1);
        } else {
            resDate = LocalDate.of(today.getYear(), endMonthOfYear, endMonthOfYear.length(today.isLeapYear()));
        }

        // 如果为周末（开始日期顺延下一日，结束日期往前一天）
        while (resDate.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || resDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
            if (isFirst) {
                resDate = resDate.plusDays(1);
            } else {
                resDate = resDate.minusDays(1);
            }
        }

        return resDate;
    }


    public static int getYear(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(Calendar.YEAR);
    }

    public static int getSysDate() {
        return Integer.parseInt(getDate());
    }

    public static int getSysTime() {
        return Integer.parseInt(getTime(), 10);
    }

    public static String getDate() {
        SimpleDateFormat dataFormat = new SimpleDateFormat(DATETIME_FORMAT_SHORT_8);
        Date date = new Date();
        return dataFormat.format(date);
    }
    public static String getTime() {
        SimpleDateFormat dataFormat = new SimpleDateFormat(TIME_SHORT_8);
        Date date = new Date();
        return  dataFormat.format(date);
    }

    public static BigDecimal getCurrentTime() {
        // SimpleDateFormat dataFormat = new SimpleDateFormat(CURRENT_TIME_LONG_15);
        // Date date = new Date();
        // String time = dataFormat.format(date);

        BigDecimal bigDecimal = new BigDecimal(formatLocalDateTime(LocalDateTime.now()));
        String format = new DecimalFormat("00000000.000000").format(bigDecimal);
        return new BigDecimal(format);
    }


    public static BigDecimal getBigDecimalTime(Date time) {
        return new BigDecimal(formatLocalDateTime(DateUtil.toLocalDateTime(time)));
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd.HHmmss");
    public static String formatLocalDateTime(LocalDateTime dateTime) {
        return dateTime == null ? "" : dateTime.format(DATE_TIME_FORMATTER);
    }

    public static boolean isDateFormatted(String str, String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            LocalDate.parse(str, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }


    public static void main(String[] args) {
        /*LocalDate localDate = getStartOrEndDayOfQuarter(false);
        String dateTimeStr = localDate.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_SHORT_8));
        System.out.println(dateTimeStr);

        LocalDate halfYearDate = getStartOrEndWorkDayOfHalfYear(true);
        String halfYearStr = halfYearDate.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_SHORT_8));
        System.out.println(halfYearStr);

        LocalDate halfEndYearDate = getStartOrEndWorkDayOfHalfYear(false);
        String halfEndYearStr = halfEndYearDate.format(DateTimeFormatter.ofPattern(DATETIME_FORMAT_SHORT_8));
        System.out.println(halfEndYearStr);*/

        // Date date = getDateTimeByString("20230403", DATETIME_FORMAT_SHORT_8);
        // Boolean enable = isQuarterlyFirstOrEndWorkDay(date, false);
        // System.out.println(enable);

        /*LocalDateTime localDateTime = LocalDateTime.of(2024, 1, 10, 10, 00, 20);
        String dateTime = formatLocalDateTime(localDateTime);
        System.out.println(dateTime);
        BigDecimal bigDecimal = new BigDecimal(dateTime);
        System.out.println(bigDecimal);
*/

        /*String time = "20240116.18301";
        BigDecimal currentTime = new BigDecimal(time);
        String format = new DecimalFormat("00000000.000000").format(currentTime);
        BigDecimal result = new BigDecimal(format);
        System.out.println(result);*/


        System.out.println(DateUtils.getFirstDayOfNextQuarter("20214"));
    }


    public static Date parse(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        Date date = null;
        Pattern p1 = Pattern.compile(SDF1REG);
        Matcher m1 = p1.matcher(str);
        Pattern p2 = Pattern.compile(SDF2REG);
        Matcher m2 = p2.matcher(str);
        Pattern p3 = Pattern.compile(SDF3REG);
        Matcher m3 = p3.matcher(str);
        Pattern p4 = Pattern.compile(SDF4REG);
        Matcher m4 = p4.matcher(str);
        Pattern p5 = Pattern.compile(SDF5REG);
        Matcher m5 = p5.matcher(str);
        Pattern p6 = Pattern.compile(SDF6REG);
        Matcher m6 = p6.matcher(str);
        try {
            if (m1.matches()) {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str);
            } else if (m2.matches()) {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
            } else if (m3.matches()) {
                date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(str);
            } else if (m4.matches()) {
                date = new SimpleDateFormat("yyyy/MM/dd").parse(str);
            } else if (m5.matches()) {
                date = new SimpleDateFormat("yyyyMMdd").parse(str);
            } else if (m6.matches()) {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(str);
            }
        } catch (ParseException e) {
            throw new RuntimeException( "非法日期字符串，解析失败：" + str );
        }

        return date;
    }

    /**
     *
     * @param inputDate
     * @param sourceFormat
     * @param targetFormat
     * @return
     */
    public static String dateFormatChange(String inputDate, String sourceFormat, String targetFormat) {
        if (Objects.isNull(inputDate) || StringUtils.isBlank(inputDate.trim())) {
            return "";
        }
        SimpleDateFormat inputFormat = new SimpleDateFormat(sourceFormat);
        SimpleDateFormat outputFormat = new SimpleDateFormat(targetFormat);

        try {
            Date date = inputFormat.parse(inputDate);
            String outputDate = outputFormat.format(date);

            return outputDate;
        } catch (ParseException e) {
            throw new RuntimeException( "非法日期字符串，解析失败：" + inputDate );
        }
    }


    /**
     * 时间偏移
     * @param dateString
     * @param offset
     * @param unit
     * @return
     */
    public static String offsetTime(String dateString, String returnFormat, long offset, ChronoUnit unit) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        try {
            LocalDateTime localDateTime = LocalDateTimeUtil.of(DateUtils.parse(dateString));
            // 日期偏移
            LocalDateTime offsetDateTime = LocalDateTimeUtil.offset(localDateTime, offset, unit);
            return LocalDateTimeUtil.format(offsetDateTime, returnFormat);
        } catch (Exception e) {
            log.error("", e);
        }

        return null;
    }

    public static String offsetMonth(String dateStr, long offsetMonth) {
        return offsetTime(dateStr, "yyyyMMdd", offsetMonth, ChronoUnit.MONTHS);
    }

    /**
     * 获取上一季度的开始日期和结束日期
     */
    public static Map<String, String> getPreQuarterlyDate(Calendar calendar) {
        // 创建一个Calendar实例，设置为当前时间
        if (Objects.isNull(calendar)) {
            return null;
        }

        // 获取上一季度的开始月份和结束月份
        int startMonth = (calendar.get(Calendar.MONTH) / 3 - 1) * 3;
        int endMonth = startMonth + 2;

        // 设置上一季度的开始日期
        calendar.set(Calendar.MONTH, startMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDate = sdf.format(calendar.getTime());

        // 设置上一季度的结束日期
        calendar.set(Calendar.MONTH, endMonth);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String endDate = sdf.format(calendar.getTime());

        // 输出上一季度的开始日期和结束日期
        Map<String, String> dateMap = new HashMap<>();
        dateMap.put("startDate", startDate);
        dateMap.put("endDate", endDate);

        return dateMap;
    }

    /**
     * 日期格式为202401（yyyyMM），获取他的下一个月的月初日期,譬如20240201
     * @param dateStr
     * @return
     */
    public static String getNextMonthFirstDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        try {
            Date date = sdf.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            String nextMonthFirstDay = sdf1.format(calendar.getTime());
            return nextMonthFirstDay;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * 20242表示2024年第二季度
     * 入参为20243，出参是20241001
     * @param input
     * @return
     */
    public static String getFirstDayOfNextQuarter(String input) {
        int year = Integer.parseInt(input.substring(0, 4));
        int quarterNum = Integer.parseInt(input.substring(4));
        int nextQuarterNum = (quarterNum % 4) + 1;
        int nextYear = year + (nextQuarterNum == 1 ? 1 : 0);
        int nextMonth = (nextQuarterNum - 1) * 3 + 1;
        return String.format("%d%02d01", nextYear, nextMonth);
    }
}
