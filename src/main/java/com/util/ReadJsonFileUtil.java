package com.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/5/8
 */
public class ReadJsonFileUtil {

    public static JSONArray readJsonFile(String filePath) {
        try {
            filePath = "/Users/lixing/Documents/需求/大模型项目资料/jsonFile/" + filePath;
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(new File(filePath), JSONArray.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
