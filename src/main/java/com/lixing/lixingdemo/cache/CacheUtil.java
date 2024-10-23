package com.lixing.lixingdemo.cache;

import com.lixing.lixingdemo.pojo.Student;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/8/13
 */
@Component
public class CacheUtil {

    /**
     * 这里要注意的是Cache和@Transactional一样也使用了代理，类内调用将失效,原因：https://blog.csdn.net/weixin_42227763/article/details/117965268
     * value：缓存key的前缀。
     * key：缓存key的后缀。
     * sync：设置如果缓存过期是不是只放一个请求去请求数据库，其他请求阻塞，默认是false（根据个人需求）。
     * unless：不缓存空值,这里不使用，会报错
     * 查询用户信息类
     * 如果需要加自定义字符串，需要用单引号
     * 如果查询为null，也会被缓存
     */
    @Cacheable(value = "Student",key = "#id") //此时缓存中的key为id,value为查询出来的数据(#id就表示取出参数id的值作为key)
    public Student getKey(String id) {
        System.out.println("-----执行了cache方法体，开始从一级缓存redis中获取值--");
        // redis.get(key)
        // db.get(key)
        Student student = new Student();
        student.setName("lixing");
        student.setAge(18);
        return student;
    }
}
