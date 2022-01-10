package com.lixing.lixingdemo.enumDemo;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-12-24 11:02
 * 获取方法：dictItem.DICT1.getName()
 */
public enum dictItem {
    DICT1("字典1", "value1", "code1"),
    DICT2("字典2","value2","code2");

    private final String name;
    private final String value;
    private final String code;

    dictItem(String name, String value, String code) {
        this.name = name;
        this.value = value;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getCode() {
        return code;
    }
}
