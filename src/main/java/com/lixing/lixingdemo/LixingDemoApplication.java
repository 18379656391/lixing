package com.lixing.lixingdemo;

import com.lixing.lixingdemo.annotation.EnableDynamicConfiguration;
import net.sf.cglib.core.DebuggingClassWriter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"com.lixing.lixingdemo"})
// (this) JDK代理时，指向接口和代理类proxy（通过反射），cglib（通过修改底层字节码）代理时 指向接口和子类(不使用proxy)
// JDK动态代理是通过实现接口的方式，只能代理接口中的方法，
// -实现原理：根据类加载器和接口创建代理类，此代理类时接口的实现类，使用java.lang.reflect包下的Proxy类和InvocationHandler接口来创建代理对象
// -实现方式：通过实现InvocationHandler接口创建自己的调用处理器，然后使用Proxy类的newProxyInstance方法生成代理对象。这个代理对象会实现目标对象所实现的接口，并在调用目标方法时，将调用转发给InvocationHandler接口的invoke方法。
// cglib是通过继承的方式生成代理类，可以代理没有实现接口的类，可以对对象中的方法进行代理，无法代理final的类和方法或者private的方法（因为继承）
// -实现原理：cglib是一个代码生成的库，它可以在运行时动态生成目标类的子类，并通过子类来拦截和增强方法的调用
// -实现方式：cglib通过Enhancer类来创建代理对象，Enhancer会为目标类生成一个子类，并在子类中重写目标方法。重写方法中，会插入横切逻辑（前置通知、后置通知）
//开启Aop,默认为false(JDK代理模式) true(Cglib模式)
@EnableAspectJAutoProxy(proxyTargetClass = true)
// 开启事务  参数：1.proxyTargetClass=true 开启cglib代理，默认是false使用JDK动态代理（如果为设置为true,就相当于EnableAspectJAutoProxy的proxyTargetClass的值也设置为true,开启cglib）
// 2.mode 开启哪种代理模式 是SpringAop还是aspectj
// 3.order 通知执行的优先级，默认最低
@EnableTransactionManagement
@EnableAsync
// 自定义动态加载配置注解
@EnableDynamicConfiguration(enable = false)
// 启用定时任务，可以通过使用@Scheduled注解来声明方法为定时任务
@EnableScheduling
// 注入指定xml中的bean
@ImportResource("classpath:cache-config.xml")
public class LixingDemoApplication {

    public static void main(String[] args) {
        // 查看cglib动态代理生成的类
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"/Users/lixing/Documents/Project/lixing/lixing/src/main/java/com/lixing/lixingdemo/dynamicProxy/cglib");
        // 查看jdk动态代理生成的类
        // System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        SpringApplication.run(LixingDemoApplication.class, args);
    }

}
