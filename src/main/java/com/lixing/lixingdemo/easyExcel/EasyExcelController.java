package com.lixing.lixingdemo.easyExcel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.converters.string.StringStringConverter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StringContent;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.List;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-10 23:43
 * 读取数据流程： 框架读取一行数据 -> 执行转换器(转换数据类型或者格式) -> 执行监听器回调方法（校验） -> 转换异常调用监听器OnException方法
 *
 */
@RestController
@ResponseBody
public class EasyExcelController {

    // 读取excel中的数据
    @PostMapping("/import")
    public void importCustomerDaily(@RequestBody MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<ReqCustomerDailyImport> customerDailyImportList = EasyExcel.read(inputStream)
                .head(ReqCustomerDailyImport.class)
                .sheet()
                // 设置标题所在行数
                .headRowNumber(2)
                .doReadSync();
    }

    // 校验数据
    @PostMapping("/checkExcel")
    public void checkExcel(@RequestBody MultipartFile file) throws Exception {
        EasyExcel.read(file.getInputStream())
                // 这个转换是成全局的， 所有java为string,excel为string的都会用这个转换器。
                // 如果就想单个字段使用请使用@ExcelProperty 指定converter
                .registerConverter(new StringStringConverter())
                // 注册监听器，监听器内做字段校验
                .registerReadListener(new CustomerDailyImportListener())
                .head(ReqCustomerDailyImport.class)
                .sheet()
                .headRowNumber(2)
                .doReadSync();
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException{
        // 生成数据
        List<RespCustomerDailyImport> respList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            RespCustomerDailyImport dailyImport = RespCustomerDailyImport.builder()
                    .misCode(String.valueOf(i))
                    .customerName("customerName" + i)
                    .monthlyQuota(String.valueOf(i))
                    .accountReceivableQuota(new BigDecimal(String.valueOf(i)))
                    .dailyInterestRate(new BigDecimal(String.valueOf(i)))
                    .build();
            respList.add(dailyImport);
        }

        // response.setContentType("application/vnd.ms-excel");
        response.addHeader("Content-Type", "application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Language", "zh-CN,zh;q=0.9,en-XA;q=0.8,en-US;q=0.7,en;q=0.6");
        // response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("导出", "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), RespCustomerDailyImport.class)
                .sheet("sheet0")
                // 设置字段宽度为自动调整
                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .doWrite(respList);
    }


}
