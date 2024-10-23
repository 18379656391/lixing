package com.lixing.lixingdemo.controller.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2023/12/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueExportFileDTO {

    @JSONField(name = "fund_code")
    @JsonProperty(value = "fund_code")
    private String fundCode;

    @JSONField(name = "business_date")
    @JsonProperty(value = "business_date")
    private String businessDate;

    /**
     * 自定义科目层级
     */
    @JSONField(name = "subject_level")
    @JsonProperty(value = "subject_level")
    private String subjectLevel;

    @JSONField(name = "rule_list")
    @JsonProperty(value = "rule_list")
    private List<ValueExportRuleDTO> ruleList;

    @JSONField(name = "desense_item")
    @JsonProperty(value = "desense_item")
    private String desenseItem;

    @JSONField(name = "desense_subjct")
    @JsonProperty(value = "desense_subjct")
    private String desenseSubject;


}
