package com.lixing.lixingdemo.mybatis;

import com.alibaba.fastjson.JSON;
import com.lixing.lixingdemo.mybatis.po.PposBaseManginstPo;
import lombok.SneakyThrows;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2023-03-07 15:39:23
 */
public class mybatisMain {


    @SneakyThrows
    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = factory.openSession();

        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("instId", "100002215");
        List<PposBaseManginstPo> manginstPoList = sqlSession.selectList("getManginst", queryParam);


        System.out.println(JSON.toJSONString(manginstPoList));


    }
}
