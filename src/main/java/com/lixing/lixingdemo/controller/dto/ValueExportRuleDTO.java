package com.lixing.lixingdemo.controller.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2023/12/22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValueExportRuleDTO {

    /**
     * 自定义科目代码
     */
    @JSONField(name = "subject_no")
    @JsonProperty(value = "subject_no")
    private String subjectNo;

    /**
     * 展示层级
     */
    @JSONField(name = "display_level")
    @JsonProperty(value = "display_level")
    private String displayLevel;


}
