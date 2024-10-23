package com.lixing.lixingdemo.easyExcel;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

/*
import cn.hutool.core.date.DateUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.lixing.lixingdemo.controller.dto.RequestDTO;
import com.lixing.lixingdemo.controller.dto.ValueExportFileDTO;
import com.lixing.lixingdemo.controller.dto.ValueExportRuleDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.hundsun.amc.constants.CommonConstant;
import com.hundsun.amc.model.RequestDTO;
import com.hundsun.amc.support.FstoreSupport;
import com.hundsun.amc.vo.FstoreVO;
import com.hundsun.amop.fafacade.api.api.WbfaApiService;
import com.hundsun.amop.fafacade.api.bean.ResponseData;
import com.hundsun.amos.queryrpt.api.bean.AutoVerifySealRecordInputDTO;
import com.hundsun.amos.queryrpt.api.service.IReportSealBatchService;
import com.hundsun.doc.api.bean.FileStorageDTO;
import com.hundsun.doc.api.service.IFstoreService;
import com.hundsun.jrescloud.common.exception.BaseBizException;
import com.hundsun.jrescloud.rpc.annotation.CloudReference;
import com.hundsun.jrescloud.rpc.result.RpcResultDTO;
import com.hundsun.ppos.product.api.service.IFundInfoApiService;
import com.hundsun.ppos.thriddata.api.bean.ValueExportFileDTO;
import com.hundsun.ppos.thriddata.api.bean.ValueExportRuleDTO;
import com.hundsun.ppos.thriddata.server.atom.FundInfoAtom;
import com.hundsun.ppos.thriddata.server.service.dto.FundCodeAndNameDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/1/3
 */
@Component
@Slf4j
public class ValuationExportByRuleSupport {

    /*
    @CloudReference(group = "${app.reference.30facade.group}", version = "${app.reference.30facade.version}")
    private WbfaApiService wbfaApiService;

    @CloudReference(group = "${app.reference.product.group}", version = "${app.reference.product.version}")
    private IFundInfoApiService iFundInfoApiService;

    @CloudReference(group = "amosQueryrptGroup")
    private IReportSealBatchService iReportSealBatchService;

    @Autowired
    private FundInfoAtom fundInfoAtom;

    @Autowired
    private FstoreSupport fstoreSupport;


    @CloudReference(group = "${app.reference.doc.group}")
    private IFstoreService fstoreService; */


    public static final String FLAG = "*";

    private static final Map<String, String> TRANS_MAP = new HashMap() {{
        put("2", "quantity");
        put("3", "unitCost");
        put("4", "cost");
        put("5", "costValue");
        put("6", "quotationValue");
        put("7", "marketValue");
        put("8", "percentMarket");
        put("9", "valueIncrease");
        put("10", "suspensionNote");
    }};

    public static final List<String> FILTER_LIST = Arrays.asList("quantity", "unitCost", "cost", "costValue", "quotationValue", "marketValue", "percentMarket", "valueIncrease");


    /*public JSONObject exportValuationByRule(RequestDTO<ValueExportFileDTO> requestDTO, Map<String, FundCodeAndNameDTO> fundMap) {
        ValueExportFileDTO param = requestDTO.getParam();
        String fundCode = param.getFundCode();
        String businessDate = param.getBusinessDate();
        String subjectLevel = param.getSetSubjectLevel();
        if (StringUtils.isBlank(subjectLevel)) {
            throw new BaseBizException("9999", "设置科目级别不能为空");
        }
        List<ValueExportRuleDTO> ruleList = requestDTO.getParam().getRuleList();
        // 过滤0值
        ValueFilter filter = (Object o, String s, Object o1) -> {
            if (FILTER_LIST.contains(s) && CommonConstant.STR_ZERO.equals(String.valueOf(o1))) {
                return "";
            } else {
                return o1;
            }
        };
        // 扩展- key值驼峰转下划线
         NameFilter nameFilter = (o, k, v) -> StrUtil.toUnderlineCase(k);
        // ResponseData valuationDetail = wbfaApiService.getValuationDetailWb(fundCode, businessDate, subjectLevel, subjectCode);
        // List<JSONObject> levelList = JSONObject.parseArray(JSON.toJSONString(valuationDetail.getData(), filter), JSONObject.class);

        // 四位数字开头的科目，过滤非底级统计科目
        String regex = "^\\d{4}\\S*$";
        Pattern compile = Pattern.compile(regex);

        // 取全量数据-4级科目数据
        // 调用估值存过获取估值科目信息
        log.info("调用估值存过入参fundCode:{},businessDate:{}", fundCode, businessDate);
        ResponseData valuationDetailTotal = wbfaApiService.getValuationDetailWb(fundCode, businessDate, null, null);
        List<JSONObject> totalList = JSONArray.parseArray(JSON.toJSONString(valuationDetailTotal.getData(), filter), JSONObject.class);
        if (CollectionUtils.isEmpty(totalList)) {
            throw new BaseBizException("-1", "未找到对应的估值数据");
        }
        // 底级科目指标数据
        List<JSONObject> targetDataList = new ArrayList<>();
        // 过滤底级统计科目,将科目数据按照1，2，3，4科目分组
        Map<String, List<JSONObject>> levelGroupData = new HashMap<>();
        Map<String, JSONObject> getDataMap = new HashMap<>();
        List<JSONObject> levelList = new ArrayList<>();
        for (JSONObject o : totalList) {
            getDataMap.put(o.getString("subjectCode"), o);
            if (compile.matcher(o.getString("subjectCode")).matches()) {
                if (Integer.parseInt(subjectLevel) >= Integer.parseInt(o.getString("subjectLevel"))) {
                    levelList.add(o);
                }
                if (!levelGroupData.containsKey(o.getString("subjectLevel"))) {
                    levelGroupData.put(o.getString("subjectLevel"), new ArrayList<>());
                }
                levelGroupData.get(o.getString("subjectLevel")).add(o);
            }else {
                // 底级科目
                targetDataList.add(o);
            }
        }
        levelList.addAll(targetDataList);
        if (CollectionUtils.isNotEmpty(param.getRuleList())) {
            // 校验规则代码和设置级别,只能选择当前科目代码科目层级及之后的层级
            checkSubjectRule(param.getRuleList());
            List<String> ruleCodeList = ruleList.stream().map(ValueExportRuleDTO::getSubjectNo).collect(Collectors.toList());
            log.info("ruleCodeList:{}", JSON.toJSONString(ruleCodeList));
            // 先去重,同样的科目设置代码，取科目展示层级大的
            List<ValueExportRuleDTO> distinctRuleList = new ArrayList<>(ruleList.stream().collect(Collectors.groupingBy(ValueExportRuleDTO::getSubjectNo,
                    Collectors.collectingAndThen(Collectors.reducing((a, b) ->
                            Integer.parseInt(a.getDisplayLevel()) > Integer.parseInt(b.getDisplayLevel()) ? a : b
                    ), Optional::get))).values());
            // 科目设置计算
            // 不分组
            // 排序
            List<ValueExportRuleDTO> ruleSortedList = sorted(distinctRuleList);
            // 家庭集合
            List<Set<ValueExportRuleDTO>> familyGroup = new ArrayList<>();
            Set<ValueExportRuleDTO> allFamilySet = new HashSet<>();
            // 计算子孙关系，子科目层级小于父科目层级求交集，其他求并集
            for (int i = 0; i < ruleSortedList.size(); i++) {
                Set<ValueExportRuleDTO> familySet = null;
                for (int j = i + 1; j < ruleSortedList.size(); j++) {
                    if (ruleSortedList.get(j).getSubjectNo().startsWith(ruleSortedList.get(i).getSubjectNo()) &&
                            Integer.parseInt(ruleSortedList.get(j).getDisplayLevel()) < Integer.parseInt(ruleSortedList.get(i).getDisplayLevel())) {
                        // 子孙关系成立,且子科目层级小于母科目层级，取交集
                        if (null == familySet) {
                            familySet = new LinkedHashSet<>();
                        }
                        // 加入自己
                        familySet.add(ruleSortedList.get(i));
                        familySet.add(ruleSortedList.get(j));
                        allFamilySet.addAll(familySet);
                        familyGroup.add(familySet);
                    }
                }
            }
            // 求差集得到需要求并集的集合的设置
            ruleSortedList.removeAll(allFamilySet);
            // log.info("singleList:{}", JSON.toJSONString(ruleSortedList));
            // log.info("familyGroup:{}", JSON.toJSONString(familyGroup));
            // 结果集取并集
            Set<JSONObject> singleResult = calculateData(getDataMap, levelGroupData, ruleSortedList, Boolean.FALSE);
            Set<JSONObject> familyResult = new HashSet<>();
            familyGroup.forEach(item -> {
                familyResult.addAll(calculateData(getDataMap, levelGroupData, new ArrayList<>(item), Boolean.TRUE));
            });
            // log.info("singleResult:{}", JSON.toJSONString(singleResult));
            // log.info("familyResult:{}", JSON.toJSONString(familyResult));
            // 所有设置规则算出的汇总数据
            singleResult.addAll(familyResult);
            // 汇总数据拼接计算结果数据，先删除设置的规则数据，在拼接计算记过
            Set<JSONObject> finalSet = intersectCollection(levelList, ruleCodeList, singleResult);
            // 最后按照numberNo升序排序
            levelList = finalSet.stream().sorted(new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    return o1.getIntValue("numberNo") - o2.getIntValue("numberNo");
                }
            }).collect(Collectors.toList());
            // log.info("finalList:{}", JSON.toJSONString(levelList));
        }
        List<Integer> boldRowNum = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(levelList)) {
            // 加粗一级科目数据
            List<JSONObject> oneSubjectList = levelGroupData.get(CommonConstant.STR_ONE);
            for (int i = 0; i < levelList.size(); i++) {
                if (oneSubjectList.contains(levelList.get(i))) {
                    boldRowNum.add(i);
                }
            }
        }
        log.info("boldRowNumList:{}", JSON.toJSONString(boldRowNum));
        // 脱敏
        if (StringUtils.isNotEmpty(param.getDesenseItem())) {
            levelList = levelList.stream().map(o -> desenseObject(param, o, compile)).collect(Collectors.toList());
        }
        return this.generateFile(boldRowNum, fundMap, requestDTO, levelList, getDataMap);
    }

    private JSONObject desenseObject(ValueExportFileDTO param, JSONObject originObject, Pattern compile) {
        // 脱敏处理 ？？ 是否可以放在生成文件中处理---否
        Stream.of(param.getDesenseItem().split(",")).forEach(item -> {
            // 科目代码名称特殊处理，对填写的科目代码对应的四级科目名称进行脱敏处理，科目代码不填写，默认脱敏所有四级科目名称
            if (CommonConstant.STR_ONE.equals(item)) {
                if (compile.matcher(originObject.getString("subjectCode")).matches()) {
                    if (StringUtils.isBlank(param.getDesenseSubject())) {
                        // 科目代码为空，脱敏所有四级科目名称,不脱敏底级科目
                        if (CommonConstant.STR_FOUR.equals(originObject.getString("subjectLevel"))) {
                            originObject.put("subjectName", intervalDesense(originObject.getString("subjectName")));
                        }
                    } else {
                        // 科目代码不为空,脱敏科目代码对应的四级科目科目名称
                        Arrays.asList(param.getDesenseSubject().split(",")).forEach(code -> {
                            if (CommonConstant.STR_FOUR.equals(originObject.getString("subjectLevel")) && originObject.getString("subjectCode").startsWith(code)) {
                                originObject.put("subjectName", intervalDesense(originObject.getString("subjectName")));
                            }
                        });
                    }
                }
            } else {
                if (null != originObject.get(TRANS_MAP.get(item)) && StringUtils.isNotBlank(String.valueOf(originObject.get(TRANS_MAP.get(item))))) {
                    originObject.put(TRANS_MAP.get(item), FLAG);
                }
            }
        });
        return originObject;
    }

    public static final List<String> KEY_SET = Arrays.asList("私募", "创业", "资管", "资产管理", "资本管理",
            "理财", "信托", "专户", "保险", "QDII", "RQDII", "QFII", "ETF", "债", "存款");

    *//**
     * 间隔脱敏
     * @param originStr
     * @return
     *//*
    private String intervalDesense(String originStr) {
        if (StringUtils.isBlank(originStr)) {
            return originStr;
        }
        // 估值二期，按照指定规则脱敏
        // 去掉中文空格
        String trimStr = originStr.replace((char) 12288, ' ').trim();
        int desenseEndIndex = trimStr.length();
        for (String key : KEY_SET) {
            if (trimStr.contains(key)) {
                int index = trimStr.indexOf(key);
                desenseEndIndex = Math.min(index, desenseEndIndex);
            }
        }
        // 间隔脱敏
        StringBuilder sb = new StringBuilder(trimStr);
        for (int i = 0; i < desenseEndIndex; i = i + 2) {
            sb.setCharAt(i, '*');
        }
        return sb.toString();
    }

    public static final String VALUATE_TEMPLATE_PATH = "./templates/valuateTemplate.xlsx";

    private JSONObject generateFile(List<Integer> boldRowNumList, Map<String, FundCodeAndNameDTO> fundMap, RequestDTO<ValueExportFileDTO> requestDTO, List<JSONObject> levelList, Map<String, JSONObject> getDataMap) {
        ValueExportFileDTO paramDto = requestDTO.getParam();
        String fileType = paramDto.getFileType();
        String fundCode = paramDto.getFundCode();
        String fundId = fundMap.get(fundCode).getFundId();
        String fundName = fundMap.get(fundCode).getFundName();
        ClassPathResource resource = new ClassPathResource(VALUATE_TEMPLATE_PATH);
        if (!resource.exists()) {
            throw new BaseBizException("9999", "未找到估值表模板路径");
        }
        // 填充表头
        Map<String, Object> headerMap = new HashMap<>();
        if (CollectionUtils.isNotEmpty(levelList) && !getDataMap.isEmpty()) {
            headerMap.put("instName", levelList.get(0).getString("managerName"));
            headerMap.put("fundName", levelList.get(0).getString("fundName"));
            headerMap.put("businessDate", DateUtil.format(DateUtil.parse(levelList.get(0).getString("workDate"))));
            JSONObject netObject = getDataMap.get("基金单位净值：");
            headerMap.put("netValue", null == netObject ? "" : netObject.getString("subjectName"));
            log.info("headerMap:{}", JSON.toJSONString(headerMap));
        }
        JSONObject resultObject = new JSONObject();
        ExcelWriter writer = null;
        try (InputStream templateStream = resource.getInputStream(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            writer = EasyExcel.write(out).registerWriteHandler(new CellWriteHandler() {
                private Integer firstRowNum = null;
                @Override
                public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row, Head head, Integer integer, Integer integer1, Boolean aBoolean) {
                }

                @Override
                public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell, Head head, Integer integer, Boolean aBoolean) {

                }

                @Override
                public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

                }

                @Override
                public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, List<CellData> list, Cell cell, Head head, Integer integer, Boolean aBoolean) {
                    Row row = cell.getRow();
                    if (!aBoolean && null == firstRowNum) {
                        firstRowNum = row.getRowNum();
                        log.info("firstRowNum:{}", firstRowNum);
                    }
                    if (boldRowNumList.contains(row.getRowNum() - firstRowNum)) {
                        Sheet sheet = writeSheetHolder.getSheet();
                        Workbook workbook = sheet.getWorkbook();
                        CellStyle cellStyle = workbook.createCellStyle();
                        cellStyle.cloneStyleFrom(cell.getCellStyle());
                        Font font = workbook.createFont();
                        font.setBold(Boolean.TRUE);
                        font.setFontName("宋体");
                        font.setFontHeightInPoints((short) 10);
                        font.setColor(IndexedColors.BLACK.getIndex());
                        cellStyle.setFont(font);
                        // cellStyle.setBorderBottom(BorderStyle.THIN);
                        // cellStyle.setBorderTop(BorderStyle.THIN);
                        // cellStyle.setBorderLeft(BorderStyle.THIN);
                        // cellStyle.setBorderRight(BorderStyle.THIN);
                        // cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
                        // cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                        // cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                        // cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
                        // 水平对齐
                        // cellStyle.setAlignment(HorizontalAlignment.CENTER);
                        // 垂直对齐
                        // cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                        cell.setCellStyle(cellStyle);
                    }
                }
            }).withTemplate(templateStream).build();
            WriteSheet sheet = EasyExcel.writerSheet().build();
            FillConfig fillConfig = FillConfig.builder().forceNewRow(Boolean.TRUE).build();
            writer.fill(levelList, fillConfig, sheet);
            writer.fill(headerMap, sheet);
            writer.finish();
            String excelName = "估值表日报" + "-" + fundCode + "-" + fundName + "-" + paramDto.getSetSubjectLevel() + "-" + paramDto.getBusinessDate() + ".xlsx";
            String businessDateSplit = paramDto.getBusinessDate();
            if (Objects.nonNull(paramDto.getBusinessDate()) && paramDto.getBusinessDate().length() == 8) {
                businessDateSplit = paramDto.getBusinessDate().substring(0, 4) + "-" + paramDto.getBusinessDate().substring(4, 6) + "-" + paramDto.getBusinessDate().substring(6, 8);
            }
            String pdfName = "证券投资基金估值表" + "_" + fundCode + "_" + fundName + "_" +paramDto.getSetSubjectLevel() + "_" +
                    businessDateSplit + ".pdf";
            if (StringUtils.isNotBlank(requestDTO.getParam().getFileNamePrefix())) {
                excelName = requestDTO.getParam().getFileNamePrefix() + excelName;
                pdfName = requestDTO.getParam().getFileNamePrefix() + pdfName;
            }
            String excelFileId = uploadFstoreFile(out.toByteArray(), excelName);
            if (fileType.contains(CommonConstant.STR_ONE)) {
                RequestDTO<AutoVerifySealRecordInputDTO> param = new RequestDTO<>();
                param.setOperatorCode(requestDTO.getOperatorCode());
                param.setSystemFlag(requestDTO.getSystemFlag());
                param.setUserName(requestDTO.getUserName());
                AutoVerifySealRecordInputDTO in = new AutoVerifySealRecordInputDTO();
                in.setFileId(excelFileId);
                in.setReportId("02003");
                in.setPrintType(paramDto.getPrintType());
                String dateStr = DateUtil.format(DateUtil.parse(paramDto.getBusinessDate()));
                in.setBeginDate(dateStr);
                in.setEndDate(dateStr);
                in.setFundId(fundId);
                in.setSubjectLevel(paramDto.getSetSubjectLevel());
                param.setParam(in);
                log.info("thrddata调用用印入参-----param:{}", JSON.toJSONString(param));
                RpcResultDTO<Map<String, Object>> outDTO = iReportSealBatchService.sealValuationByFile(param);
                log.info("调用返回-----outDTO:{}", JSON.toJSONString(outDTO));
                Map<String, Object> result = outDTO.getResult();
                String pdfFileId = String.valueOf(result.get("fileIds"));
                resultObject.put("pdfFileId", pdfFileId);
                resultObject.put("pdfFileName", pdfName);
            }
            resultObject.put("excelFileId", excelFileId);
            log.info("support调用返回-----{}", JSON.toJSONString(resultObject));
            return resultObject;
        } catch (IOException e) {
            log.error("加载估值表模板失败", e);
            throw new BaseBizException("9999", "加载估值表模板失败");
        } finally {
            if (null != writer) {
                writer.finish();
            }
        }
    }

    private String uploadFstoreFile(byte[] fileByte, String fileName) {
        try {
            InputStream in = new ByteArrayInputStream(fileByte);
            int length = in.available();
            FstoreVO uploadStoreFile = fstoreSupport.uploadStoreFile(fileName, length, in, "group1");
            FileStorageDTO fileDto = new FileStorageDTO();
            BeanUtils.copyProperties(uploadStoreFile, fileDto);
            fileDto.setFileSize(new BigDecimal(uploadStoreFile.getFileSize()));
            fstoreService.storeFile(fileDto, "group1");
            return uploadStoreFile.getFileId();
        } catch (Exception e) {
            throw new BaseBizException("9999", "文件上传失败");
        }
    }

    private byte[] downloadFstoreFile(String fileId) {
        FileStorageDTO fileStorageDTO = new FileStorageDTO();
        fileStorageDTO.setFileId(fileId);
        FileStorageDTO excelFile = fstoreService.getStoreFile(fileStorageDTO);
        if (null == excelFile) {
            throw new BaseBizException("9999", "文件下载失败");
        }
        return excelFile.getFileData();
    }

    private String groupMethod(ValueExportRuleDTO dto) {
        return dto.getSubjectNo().substring(0, 4);
    }

    private void checkSubjectRule(List<ValueExportRuleDTO> ruleList) {
        ruleList.forEach(rule -> {
            String originLevel = judgeCurrentLevel(rule.getSubjectNo());
            String setLevel = rule.getDisplayLevel();
            if (Integer.parseInt(originLevel) > Integer.parseInt(setLevel)) {
                throw new BaseBizException("9999", "科目代码规则设置有误，设置科目级别只能选择当前科目代码层级及之后的层级,错误科目代码:" + rule.getSubjectNo());
            }
        });
    }

    private List<ValueExportRuleDTO> sorted(List<ValueExportRuleDTO> list) {
        return list.stream().sorted(new Comparator<ValueExportRuleDTO>() {
            @Override
            public int compare(ValueExportRuleDTO o1, ValueExportRuleDTO o2) {
                return o1.getSubjectNo().length() - o2.getSubjectNo().length();
            }
        }).collect(Collectors.toList());
    }

    *//**
     * @param levelGroupData 汇总分组数据
     * @param ruleList       规则列表（必须按照科目等级从小到大排序）
     * @param mixed          是否求交集 true:求交集 false:求并集
     * @return
     *//*
    private Set<JSONObject> calculateData(Map<String, JSONObject> getDataMap, Map<String, List<JSONObject>> levelGroupData, List<ValueExportRuleDTO> ruleList, Boolean mixed) {
        Set<JSONObject> totalSet = new HashSet<>();

        Set<JSONObject> childSet = new HashSet<>();
        List<JSONObject> mainList = new ArrayList<>();
        List<String> setList = new ArrayList<>();
        for (int i = 0; i < ruleList.size(); i++) {
            ValueExportRuleDTO rule = ruleList.get(i);
            List<JSONObject> list = new ArrayList<>();
            // 拿规则进行结果集计算
            JSONObject subjectNoObject = getDataMap.get(rule.getSubjectNo());
            if (Objects.isNull(subjectNoObject)) {
                log.info("未找到的科目信息:{},忽略----", rule.getSubjectNo());
                continue;
            }
            // 判断科目层级是不是从1开始的，不是的把底级科目补回去 1-4,2-6,3-8,>8
            for (int j = 0; j < Integer.parseInt(judgeCurrentLevel(rule.getSubjectNo())) - 1; j++) {
                // 4 + (2*j)
                int endIndex = 4 + (2 * j);
                list.add(getDataMap.get(rule.getSubjectNo().substring(0, endIndex)));
            }
            cal(levelGroupData, subjectNoObject, rule.getDisplayLevel(), list);
            // log.info("rule:{},----calculate data:{}", JSON.toJSONString(rule), JSON.toJSONString(list));
            if (!mixed || ruleList.size() == 1) {
                totalSet.addAll(list);
            } else {
                // 求交集(删除交集部分，再合并)
                // 存在子规则
                if (i == 0) {
                    mainList = list;
                } else {
                    childSet.addAll(list);
                    setList.add(rule.getSubjectNo());
                }
            }
        }
        if (childSet.size() > 0) {
            // 交集返回
            return intersectCollection(mainList, setList, childSet);
        } else {
            // 并集返回
            return totalSet;
        }
    }

    *//**
     * @param mainList 主集合
     * @param setList  设置的科目集合
     * @param childSet 设置的科目算出的结果
     * @return
     *//*
    private Set<JSONObject> intersectCollection(List<JSONObject> mainList, List<String> setList, Set<JSONObject> childSet) {
        // 循环主集合，删除以设置科目代码开头的数据，然后拼接科目代码计算得到的数据
        for (String s : setList) {
            mainList.removeIf(item -> item.getString("subjectCode").startsWith(s));
        }
        mainList.addAll(childSet);
        return new HashSet<>(mainList);
    }

    private void cal(Map<String, List<JSONObject>> levelGroupData, JSONObject subjectNoObject, String displayLevel, List<JSONObject> allData) {
        allData.add(subjectNoObject);
        String subjectNo = subjectNoObject.getString("subjectCode");
        String currentLevel = String.valueOf(Integer.parseInt(judgeCurrentLevel(subjectNo)) + 1);
        if (Integer.parseInt(currentLevel) > Integer.parseInt(displayLevel)) {
            return;
        }
        // 递归调用
        levelGroupData.get(currentLevel).stream()
                .filter(item -> item.getString("subjectCode").startsWith(subjectNo))
                .peek(m -> cal(levelGroupData, m, displayLevel, allData))
                .collect(Collectors.toList());
    }

    private String judgeCurrentLevel(@NotBlank String subjectNo) {
        switch (subjectNo.length()) {
            case 4:
                return CommonConstant.STR_ONE;
            case 6:
                return CommonConstant.STR_TWO;
            case 8:
                return CommonConstant.STR_THREE;
            default:
                return CommonConstant.STR_FOUR;
        }
    }*/

}
