package com.lixing.lixingdemo.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-06 15:03
 * 注解：
 * ExcelProperty: index指定当前字段对应excel中的列号，也可以用字符串指定列名
 * ExcelIgnore 默认所有字段都会和excel去匹配，加了这个注解会忽略该字段。
 * DateTimeFormat 日期转换，用String去接收excel日期格式的数据会调用这个注解。里面的value参照java.text.SimpleDateFormat。
 * NumberFormat 数字转换，用String去接收excel数字格式的数据会调用这个注解。里面的value参照java.text.DecimalFormat。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCustomerDailyImport {

    /**
     * 客户名称
     */
    @ExcelProperty(index = 0)
    private String customerName;
    /**
     * MIS编码
     */
    @ExcelProperty(index = 1)
    private String misCode;
    /**
     * 月度滚动额
     */
    @ExcelProperty(index = 3)
    private String monthlyQuota;
    /**
     * 最新应收账款余额
     */
    @ExcelProperty(index = 4)
    private BigDecimal accountReceivableQuota;
    /**
     * 本月利率
     */
    @ExcelProperty(index = 5)
    private BigDecimal dailyInterestRate;
}
