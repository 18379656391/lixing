package com.TestClass;

import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.lixing.lixingdemo.initialDemo.Car;
import com.lixing.lixingdemo.pojo.Student;
import com.util.DateUtils;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-07-18 23:34:58
 */
public class TestClass {

    @SneakyThrows
    public static void main(String[] args) {
        System.out.println(DateUtils.offsetTime("20240516", "yyyyMMdd", -15, ChronoUnit.DAYS));
        StringBuffer s=new StringBuffer("20190305").insert(4,"-").insert(7,"-");
        System.out.println(s.toString());
        System.out.println(0L > 0L);
        System.out.println(0 > 0L);
        String camelString = "cJ2";
        System.out.println("驼峰转下划线:" + StrUtil.toUnderlineCase(camelString));
        List<String> testListSort = new ArrayList<>();
        testListSort.add("20220101");
        testListSort.add("202201201");
        testListSort.add("20210502");
        testListSort.add("20240308");
        testListSort.add("20220901");
        System.out.println(testListSort);
        System.out.println(testListSort.stream().sorted().collect(Collectors.toList()));
//        File originFile = new File("C:\\Users\\hspcadmin\\Desktop\\log.txt");
//        File newFile = new File("newLog.txt");
//        writeToFile(newFile, new FileInputStream(originFile), true);
//        System.out.println(newFile.getName());
        //newFile.deleteOnExit();
        System.out.println(null == null);
        String[] strStr = new String[]{"1", "3", "5", "2", "4"};
        System.out.println(Arrays.toString(strStr));
        String strStrList = Stream.of(strStr).sorted().collect(Collectors.joining());
        System.out.println(strStrList);

        Month endMonth = Month.of(Integer.parseInt("04") * 3);
        YearMonth yearMonth = YearMonth.of(Integer.parseInt("2022"), endMonth);
        String finalCycle = yearMonth.format(DateTimeFormatter.ofPattern("yyyyMM"));
        System.out.println(finalCycle);

        Map<String, String> collect = new IdentityHashMap<>();
        collect.put(new String("111"), "value1");
        collect.put(new String("111"), "value2");
        collect.put(new String("111"), "value3");

        Car car = new Car();
        System.out.println(Objects.isNull(car));
        System.out.println(car == null);

        List<String> list = new ArrayList<>();
        list.add("资产托管");
        list.add("份额登记");
        list.add("估值核算");
        list.add("募集监督");
        list.add("资产托管");
        list.add("估值核算");
        String str = list.stream().distinct().collect(Collectors.joining(","));
        System.out.println(str);


        List<Student> stuList = new ArrayList<>();
        stuList.add(new Student(1111L, "1", 1));
        stuList.add(new Student(1111L, "3", 3));
        stuList.add(new Student(1111L, "4", 2));
        stuList.add(new Student(1111L, "5", 5));
        stuList.add(new Student(1111L, "2", 6));
        stuList.add(new Student(1112L, "8", 6));
        stuList.add(new Student(1112L, "9", 4));
        stuList.add(new Student(1112L, "0", 111));
        stuList.add(new Student(1115L, "7", 111));
        stuList.add(new Student(1115L, "6", 111));
        System.out.println(JSON.toJSONString(stuList));

        stuList = stuList.stream().sorted(Comparator.comparing( o -> ((Student)o).getName())).collect(Collectors.toCollection(ArrayList::new));
        System.out.println("sort--"+JSON.toJSONString(stuList));

        String stuStr = stuList.stream().map(Student::getName).filter(StringUtils::isNotBlank).distinct().collect(Collectors.joining(","));
        System.out.println(stuStr);

        // 分组，去重，合并 Collectors.mapping(Student::getName, Collectors.joining(","))
        Map<Long, Set<String>> collect2 = stuList.stream().collect(Collectors.groupingBy(Student::getStuNo, Collectors.mapping(Student::getName, Collectors.toSet())));
        System.out.println(JSON.toJSONString(collect2));

        List<Student> collect3 = stuList.stream().collect(
                Collectors.collectingAndThen(
                        Collectors.toCollection(
                                () -> new TreeSet<>(Comparator.comparing(Student::getStuNo))),ArrayList::new));
        System.out.println(JSON.toJSONString(collect3));

        List<String> strList = new ArrayList<>();
        for (int i = 1; i <=63 ; i++) {
            strList.add(String.valueOf(i));
        }
        System.out.println(strList.stream().collect(Collectors.joining(",")));

        String num = "55879.9999";
        System.out.println((int)Double.parseDouble(num));
        Date effectiveDate = DateUtil.getJavaDate(Double.parseDouble(
                String.valueOf((int)Double.parseDouble(num))));
        System.out.println(effectiveDate);

        getEndDayOfQuarter("2020", "1");
        getEndDayOfQuarter("2020", "2");
        getEndDayOfQuarter("2020", "3");
        getEndDayOfQuarter("2020", "4");

        String cycle = "202";
        System.out.println(String.valueOf(cycle.charAt(cycle.length() - 1)));

        List<String> strings = Arrays.asList("1");
        List<String> StringList = new ArrayList<>(strings);
        StringList.remove("1");
        System.out.println(StringList.stream().collect(Collectors.joining(",")));

        System.out.println(decimalFormat3("1000000"));
        System.out.println(decimalFormat2("1321.3251"));
        System.out.println(decimalFormat2("1321.3000"));
        System.out.println(decimalFormat2("0.1"));
        System.out.println(decimalFormat2("1"));

        String testStr = "'123456'";
        System.out.println(testStr.substring(1, testStr.length()-1));
        System.out.println(secToTime("61"));

        DecimalFormat decimalFormat = new DecimalFormat("##0.00%");
        //return decimalFormat.format((float)playEndCount / (float) playCount);
        System.out.println(decimalFormat.format(0));
        System.out.println(calculatePlayEndRate(0, 0));
        System.out.println(calculatePlayEndRate(5, 10));
        System.out.println(calculatePlayEndRate(9, 11));


        System.out.println(intervalDesense("中国建材　　　　"));
        syncValuationInvestTarget();

        Calendar now = Calendar.getInstance();
        now.set(Calendar.MONTH, 5);
        System.out.println(now.getTime());
        int currentYear = now.get(Calendar.YEAR);
        // 当前季度
        int currentPerd = now.get(Calendar.MONTH) / 3 + 1;
        int lastPerd = currentPerd - 1;
        if (lastPerd == 0) {
            lastPerd = 4;
            currentYear = currentYear - 1;
        }
        String queryPerd = currentYear + "Q" + lastPerd;
        System.out.println("lastPerd:" +queryPerd);
    }

    public static String calculatePlayEndRate(int playEndCount, int playCount) {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00%");
        return decimalFormat.format((float)playEndCount / (float) playCount);
    }

    private static String getEndDayOfQuarter(String year, String quarter) {
        Month endMonth = Month.of(Integer.parseInt(quarter) * 3);
        LocalDate tempDay = LocalDate.of(Integer.parseInt(year), endMonth, endMonth.length(IsoChronology.INSTANCE.isLeapYear(Long.parseLong(year))));
        System.out.println(JSON.toJSONString(tempDay));
        return tempDay.toString();
    }

    public static void writeToFile(File file, InputStream inputStream, boolean closeInputStream) {
        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[8192];

            int bytesRead;
            while((bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (Exception var16) {
            throw new ExcelAnalysisException("Can not create temporary file!", var16);
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException var15) {
                    throw new ExcelAnalysisException("Can not close 'outputStream'!", var15);
                }
            }

            if (inputStream != null && closeInputStream) {
                try {
                    inputStream.close();
                } catch (IOException var14) {
                    throw new ExcelAnalysisException("Can not close 'inputStream'", var14);
                }
            }

        }

    }

    public static String decimalFormat(String originValue) {
        return new DecimalFormat("##0.00").format(((new BigDecimal(originValue).divide(new BigDecimal(10000)))
                .doubleValue()));
    }

    public static String decimalFormat2(String originValue) {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        decimalFormat.setRoundingMode(RoundingMode.HALF_UP);
        return decimalFormat.format(new BigDecimal(originValue));
    }

    public static String decimalFormat3(String originValue) {
        DecimalFormat decimalFormat = new DecimalFormat(",##0.00");
        return decimalFormat.format(new BigDecimal(originValue));
    }

    public static String generateVideoTime(String seconds) {
        Integer secondValue = Integer.parseInt(seconds);
        int hour = secondValue / 60 / 60;
        int minute = secondValue / 60 % 60;
        int second = secondValue % 60;
        return hour + ":" + minute + ":" + second;
    }

    public static String secToTime(String timeStr) {
        int time = Integer.parseInt(timeStr);
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0) {
            return "00:00";
        } else {
            if (time >= 3600) {
                hour = time / 3600;
                time = time % 3600;
            }

            if (time >= 60) {
                minute = time / 60;
                second = time % 60;
            }
            return timeFormat(hour) + ":" + timeFormat(minute) + ":" + timeFormat(second);
        }
    }



    public static String timeFormat(int num) {
        String retStr = null;
        if (num >= 0 && num < 10) {
            retStr = "0" + num;
        } else {
            retStr = "" + num;
        }
        return retStr;
    }

    public static String intervalDesense(String originStr) {
        if (StringUtils.isBlank(originStr)) {
            return originStr;
        }
        // 间隔脱敏
        // 去掉中文空格
        String trimStr = originStr.replace((char) 12288, ' ').trim();
        StringBuilder sb = new StringBuilder(trimStr);
        for (int i = 0; i < sb.length(); i = i + 2) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    public static final List<String> KEY_SET = Arrays.asList("私募", "创业", "资管", "资产管理", "资本管理",
            "理财", "信托", "专户", "保险", "QDII", "RQDII", "QFII", "ETF", "债", "存款");
    public static void syncValuationInvestTarget() {
        // todo 调用估值接口查询所有投资标的
        List<String> itemList = Arrays.asList("周立军测试私募股权类FOF基金","易方达测试私募股权类FOF基金", "中国人寿测试保险公司及其子公司资产管理计划产品", "测试脱敏债券","恒生电子", "E测试QFII");
        for (String s : itemList) {
            if (StringUtils.isBlank(s)) {
                continue;
            }
            //去掉中文空格
            String tempString = s.replace((char) 12288, ' ').trim();
            int desenseEndIndex = tempString.length();
            for (String key : KEY_SET) {
                if (tempString.contains(key)) {
                    int index = tempString.indexOf(key);
                    desenseEndIndex = Math.min(index, desenseEndIndex);
                }
            }
            // 间隔脱敏
            StringBuilder sb = new StringBuilder(tempString);
            for (int i = 0; i < desenseEndIndex; i = i + 2) {
                sb.setCharAt(i, '*');
            }
            System.out.println("desenseResult:" + sb.toString());
        }


    }
}
