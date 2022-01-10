package com.lixing.lixingdemo.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-06 15:03
 */
@Data
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
    private BigDecimal AccountReceivableQuota;
    /**
     * 本月利率
     */
    @ExcelProperty(index = 5)
    private BigDecimal dailyInterestRate;
}
