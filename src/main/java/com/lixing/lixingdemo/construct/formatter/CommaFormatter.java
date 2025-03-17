package com.lixing.lixingdemo.construct.formatter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/10/10
 * 只适用于表单提交数据或者url传参，比如硬件编码，json格式的请求参数处理使用的是CommaJson*的配置
 */
public class CommaFormatter implements Formatter<ArrayList<String>> {
    // 用于接受注解的参数（指定分隔符）
    private String separator;

    @Override
    public String print(ArrayList<String> object, Locale locale) {
        return object.toString();
    }

    @Override
    public ArrayList<String> parse(String text, Locale locale) throws ParseException {
        ArrayList<String> returnList = new ArrayList<>();
        if (StringUtils.isNotBlank(text) && text.contains(separator)) {
            returnList.addAll(Arrays.asList(text.split(separator)));
        } else {
            returnList.add(text);
        }
        return returnList;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }
}
