package com.lixing.lixingdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lixing.lixingdemo.FunctionalInterface.FunctionUtils;
import com.lixing.lixingdemo.applicationListener.EmailEvent;
import com.lixing.lixingdemo.applicationListener.EventPublisher;
import com.parseObjectDemo.TestClassA;
import com.parseObjectDemo.TestClassB;
import com.spire.ms.System.Exception;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-22 15:12
 */
@SpringBootTest
public class LixingTest {

    @Test
    public void test() {
        String testStr1 = "";
        String testStr2 = "1,2";
        System.out.println(testStr1.split(",").toString());
        System.out.println(testStr2.split(",").toString());
        System.out.println(testStr1.split(",").length);
        System.out.println(testStr2.split(",").length);

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        System.out.println(format.format(date));

        TestClassB b = new TestClassB();
        b.setFiled1("1");
        b.setFiled2("2");
        b.setFiled3("3");
        String json = JSON.toJSONString(b);
        TestClassA a=JSONObject.parseObject(json, TestClassA.class);
        System.out.println(a);

    }

    @Test
    public void ExceptionTest() {
        FunctionUtils.isTrue(true).throwMessage("空指针异常");
    }

    @Test
    public void branchTest() {
        FunctionUtils.select(true).branchSelect(() -> {
            System.out.println("select true branch");
        }, () -> {
            System.out.println("select false branch");
        });
    }

    @Test
    public void EmptyTest() {
        FunctionUtils.emptyHandler("sss").emptyHandle(consumer -> System.out.println("字符串不为空，字符串被消费"),() -> System.out.println("字符串为空"));
    }

    @Test
    public void optionalTest(){
        String accountNo = "";
        Optional.ofNullable(accountNo).filter(acc -> acc.split(",").length <= 10).orElseThrow(() -> new Exception("联机查询一次账户数量不能超过10个"));
    }

    @Test
    public void listenerTest(){
        EmailEvent emailEvent = new EmailEvent("hello", "test@test.com", "test content");
        EventPublisher.publicEvent(emailEvent);
    }
}
