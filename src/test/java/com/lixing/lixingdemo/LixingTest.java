package com.lixing.lixingdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lixing.lixingdemo.FunctionalInterface.FunctionUtils;
import com.lixing.lixingdemo.applicationListener.EmailEvent;
import com.lixing.lixingdemo.applicationListener.EventPublisher;
import com.lixing.lixingdemo.parseObjectDemo.TestClassA;
import com.lixing.lixingdemo.parseObjectDemo.TestClassB;
import com.spire.ms.System.Exception;
import com.util.ReadJsonFileUtil;
import com.util.UniqueIdGenerator;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.text.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2021-11-22 15:12
 */
@SpringBootTest
public class LixingTest {

    @Test
    @SneakyThrows
    public void test() {
        System.out.println(new String("12").split(",").length);
        System.out.println("id generate---" + UniqueIdGenerator.getInstance().nextId().toString());
        JSONArray array = ReadJsonFileUtil.readJsonFile("api0703.json");
        JSONArray array1 = new JSONArray();
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsonObject = array.getJSONObject(i);
            JSONObject tempObj = new JSONObject();
            tempObj.put("description", jsonObject.getString("description"));
            tempObj.put("name", jsonObject.getString("name"));
            array1.add(tempObj);
        }
        System.out.println(array1.toJSONString());
        StringBuffer sb = new StringBuffer();
        sb.append("hello，");
        System.out.println(sb.replace(sb.length() - 1, sb.length(), "；"));
        String MSG2 = "客户$" + "{khmc} 产品$" + "{cpdm} $" + "{cpmc}，购买日期为$" + "{sqrq} 确认份额$" + "{qrfe},锁定期到$" + "{ktshsqr}。";
        Map<String,Object> replaceMap = new LinkedHashMap<>();
        replaceMap.put("khmc", "aaa");
        replaceMap.put("cpdm", "bbb");
        replaceMap.put("cpmc", "ccc");
        replaceMap.put("sqrq", "ddd");
        replaceMap.put("qrfe", "ccc");
        replaceMap.put("ktshsqr", null);
        // 记录}的位置
        int lastIndex = 0;
        for (Map.Entry<String, Object> entry : replaceMap.entrySet()) {
            String key = (String)entry.getKey();
            int currentIndex = MSG2.indexOf(key) + key.length();
            if (null == entry.getValue() || org.apache.commons.lang3.StringUtils.isBlank((String)String.valueOf(entry.getValue()))) {
                MSG2 = MSG2.replace(MSG2.substring(lastIndex + 1, currentIndex + 1), "");
            }
            lastIndex = currentIndex;
        }

        System.out.println(MSG2.replace(MSG2.substring(9, 19), ""));
        System.out.println(MSG2.indexOf("khmc"));
        System.out.println(MSG2.indexOf("cpdm"));
        System.out.println(null == JSONObject.parseObject("", genericTest.class));
        File originFile = new File("C:\\Users\\hspcadmin\\Desktop\\log.txt");
        originFile.renameTo(new File(originFile.getAbsolutePath() + "newName.txt"));
        System.out.println(originFile.getName());

        String testStr1 = "1";
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
        List list = Stream.of(testStr2.split(",")).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list.contains("3"));
        System.out.println(list.contains("2"));

        List<String> splitList = new ArrayList<>();
        splitList.add("");
        splitList.add("");
        splitList.add("");
        splitList.add("");
        String splitStr = splitList.stream().collect(Collectors.joining(","));
        System.out.println(splitStr);
        String[] s = splitStr.split(",", -1);
        System.out.println(Arrays.toString(s));

        String regex = "^([1-9][0-9]|0)\\.[0-9]{2}%$";
        System.out.println(Pattern.matches(regex, "99.99%"));
        System.out.println("------------------------------");
        double num = 423453451.000000;
        NumberFormat format1 = NumberFormat.getInstance();
        DecimalFormat decimalFormat = new DecimalFormat(",##0.00");
        System.out.println(format1.format(num));
        System.out.println(decimalFormat.format(num));

        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "one");
        testMap.put("2", "two");
        String str = "";
        String reportYear = Arrays.stream(str.split(",")).map(item -> StringUtils.isBlank(item) ? "" : testMap.get(item))
                .collect(Collectors.joining(","));
        System.out.println("reportYear" + reportYear);

        String var1 = "999.99999999";
        Double var2 = Double.parseDouble(var1);
        NumberFormat instance = NumberFormat.getInstance();
        instance.setMaximumFractionDigits(Integer.parseInt("4"));
        System.out.println(instance.format(var2));



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
