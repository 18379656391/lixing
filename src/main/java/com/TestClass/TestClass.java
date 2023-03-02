package com.TestClass;

import com.alibaba.excel.exception.ExcelAnalysisException;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.lixing.lixingdemo.initialDemo.Car;
import com.lixing.lixingdemo.pojo.Student;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.DateUtil;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
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
        stuList.add(new Student(1111L, "资产托管", 111));
        stuList.add(new Student(1112L, "份额登记", 111));
        stuList.add(new Student(1113L, "估值核算", 111));
        stuList.add(new Student(1114L, "募集监督", 111));
        stuList.add(new Student(1115L, "资产托管", 111));
        stuList.add(new Student(1116L, "估值核算", 111));

        String stuStr = stuList.stream().map(Student::getName).filter(StringUtils::isNotBlank).distinct().collect(Collectors.joining(","));
        System.out.println(stuStr);

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

}
