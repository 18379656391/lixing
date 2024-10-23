package com.lixing.lixingdemo.controller.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lixing.lixingdemo.annotation.CommaFormat;
import com.lixing.lixingdemo.annotation.CommaJsonFormat;
import com.lixing.lixingdemo.construct.formatter.CustomJsonFieldDeserializer;
import com.lixing.lixingdemo.construct.formatter.CustomJsonFieldSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/9/22
 * Spring内饰的一些Formatter<T>
 *     1.DateFormatter          String和Date的转换
 *     2.InetAddressFormatter   String和InetAddress的转换
 *     3.PercentStyleFormatter  百分数格式化
 *     4.NumberFormatter String和Number的转换
 * @DateTimeFormat、@NumberFormat 注解就是通过Formatter<T>解析String类型的值，并转换为指定类型的值
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class TestDTO implements Serializable{
    // 为了避免在类的结构变化时出现序列化版本不匹配的情况，通常建议显式定义 serialVersionUID。这是因为序列化框架会根据 serialVersionUID 来判断两个序列化对象是否属于同一个类的不同实例。
    // 如果不指定 serialVersionUID，Java 运行时环境会根据类的内部表示自动生成一个 serialVersionUID，但是这个值可能会随着类的内部结构的变化而变化，从而导致序列化版本不匹配的异常（InvalidClassException）。
    private static final long serialVersionUID = -2105046476227433587L;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    // Number是一个抽象基类，是Byte、Short、Integer、Long、Float、Double、BigInteger、BigDecimal的父类
    @NumberFormat(pattern = "#.##")
    private Number number;

    @CommaJsonFormat("_")
    private List<String> jsonStr;

    @CommaJsonFormat("&")
    private List<String> jsonStr1;

}
