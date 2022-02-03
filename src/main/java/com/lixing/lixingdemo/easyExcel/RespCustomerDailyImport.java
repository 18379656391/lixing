package com.lixing.lixingdemo.easyExcel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-11 17:06
 */
@Data
@Builder
public class RespCustomerDailyImport implements Serializable {

    private static final long serialVersionUID = 3162083483218342780L;
    /**
     * 客户名称
     */
    @ExcelProperty("客户名称")
    private String customerName;
    /**
     * MIS编码
     */
    @ExcelProperty("MIS编码")
    private String misCode;
    /**
     * 月度滚动额
     */
    @ExcelProperty("月度滚动额")
    private String monthlyQuota;
    /**
     * 最新应收账款余额
     */
    @ExcelProperty("最新应收账款余额")
    private BigDecimal accountReceivableQuota;
    /**
     * 本月利率
     */
    @NumberFormat("#.##%")
    @ExcelProperty("本月利率")
    private BigDecimal dailyInterestRate;
}
