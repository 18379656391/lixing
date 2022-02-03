package com.lixing.lixingdemo.fileExporter;

import com.lixing.lixingdemo.easyExcel.RespCustomerDailyImport;
import org.apache.commons.compress.utils.Lists;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-14 14:05
 */
@RestController
public class FileExporterController {

    @PostMapping("/export")
    @FileExporter(busiType = "文件导出测试", busiTypeSub = "测试列表导出")
    public FileExporterOutputDTO<RespCustomerDailyImport> export(FileExporterInputDTO inputDTO) throws IOException {
        FileExporterOutputDTO outputDTO = new FileExporterOutputDTO();
        Integer maxPageSize = 10;
        // 生成数据
        List<RespCustomerDailyImport> respList = Lists.newArrayList();
        for (int i = 0; i < inputDTO.getPageSize(); i++) {
            RespCustomerDailyImport dailyImport = RespCustomerDailyImport.builder()
                    .misCode(String.valueOf(i))
                    .customerName("customerName" + i)
                    .monthlyQuota(String.valueOf(i))
                    .accountReceivableQuota(new BigDecimal(String.valueOf(i)))
                    .dailyInterestRate(new BigDecimal(String.valueOf(i)))
                    .build();
            respList.add(dailyImport);
        }
        outputDTO.setTotal(maxPageSize);
        outputDTO.setResultList(respList.subList(0, inputDTO.getPageSize()));
        return outputDTO;
    }
}
