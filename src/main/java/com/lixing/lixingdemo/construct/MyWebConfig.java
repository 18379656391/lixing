package com.lixing.lixingdemo.construct;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.impl.FailingSerializer;
import com.lixing.lixingdemo.construct.HttpMessageConverter.MyMessageConverter;
import com.lixing.lixingdemo.construct.formatter.CommaFormatAnnotationFormatterFactory;
import com.lixing.lixingdemo.construct.formatter.CommaJsonAnnotationIntrospector;
import com.lixing.lixingdemo.construct.formatter.StringXssDeserializer;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.resource.VersionResourceResolver;
import org.springframework.web.util.UrlPathHelper;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/24
 * 可以通过此方式自定义拦截器或者消息转换器
 * https://blog.csdn.net/yuliantao/article/details/136517144
 * https://www.jianshu.com/p/3e1de3d02dd8
 */
@Configuration
public class MyWebConfig implements WebMvcConfigurer {


    @Autowired
    private MyInterceptor myInterceptor;


    /**
     * 帮助配置HandlerMapping，配置路径匹配规则
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 是否使用尾斜杠匹配，默认为true; true表示/test和/test/都能匹配
        configurer.setUseTrailingSlashMatch(true);
        WebMvcConfigurer.super.configurePathMatch(configurer);
        // 为所有的接口添加统一前缀。如果的URL为: "/hello", 则转换为: "/lx/hello"
        // 可以用配置文件配置server.servlet.context-path全局路径替代
        // configurer.addPathPrefix("lx", c -> c.isAnnotationPresent(Controller.class));
        // UrlPathHelper是一个处理URL地址的帮助类, 自带了一些优化URL的方法；
        // 如：getSanitizedPath，就是将"//"自动转换为"/", 所以当输入为"//"也是没有问题的，
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        configurer.setUrlPathHelper(urlPathHelper);
    }

    /**
     * 配置内容协商的一些选项，说白了就是告诉系统什么类型用什么来标识
     * 内容协商：在HTTP协议中，内容协商是一种机制，是指服务器根据客户端请求的 Accept 头来决定返回哪种类型的资源表示形式的过程。
     * Spring MVC 提供了两种主要的内容协商策略：扩展名协商（Extension-based Content Negotiation）和媒体类型协商（Media Type-based Content Negotiation）
     * 通过为同一 URI 指向的资源提供不同的展现形式，可以使用户代理选择与用户需求相适应的最佳匹配
     *
     * @param configurer
     */

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // 设置默认的媒体类型为 application/json
        // 是否通过请求URL上的扩展名来确定要使用的媒体类型。
        configurer.favorPathExtension(true)
                // 设置是否使用 URL 参数来确定要使用的媒体类型。
                .favorParameter(false)
                // 检查Accept请求头
                .ignoreAcceptHeader(false)
                // 设置是否使用 JAF (Java Activation Framework) 来解析 Accept 头。
                .useJaf(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON);
    }

    /**
     * 一步请求调用支持
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        WebMvcConfigurer.super.configureAsyncSupport(configurer);
    }

    /**
     * 默认静态资源处理，配置默认servlet
     *
     * @param configurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
    }

    /**
     * 配置内容转换器(作用在请求内容映射到请求参数中间)，可以把时间转化成需要的时区或者格式，还可以将对象A转换为B对象
     * 以下配置代码用于使用LocalDate，LocalTime ,LocalDateTime属性接收前端传来的标准时间字符串，
     * 并返回指定的时间格式，LocalDate对应 yyyy-MM-dd，LocalDateTime对应yyyy-MM-dd HH:mm:dd，LocalTime对应 HH:mm:dd，
     * 不论参数的形式传递如body、pathVariable、requestParam里，只要时间字符串满足格式要求都可以转为对应的时间日期属性。
     *
     * @param registry
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 注意：前台使用contentType: "application/json"，后台使用@RequestBody来接收数据的话，此注解不生效，因为指定是json的话就会默认使用jackson的解析器
        // 只适用于表单提交数据或者url传参
        registry.addFormatterForFieldAnnotation(new CommaFormatAnnotationFormatterFactory());
    }


    /**
     * 拦截器配置
     *
     * @param registry addInterceptor:需要一个实现HandlerInterceptor接口的拦截器实例
     *                 addPathPatterns:添加需要拦截的路径规则； -addPathPatterns("/**")表示对所有请求都拦截
     *                 excludePathPatterns:添加不需要拦截的路径规则
     *                 拦截器的主要用途：进行用户登录状态的拦截，日志的拦截等
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截器
        registry.addInterceptor(myInterceptor)
                // 执行拦截器的执行顺序，值越小，越先执行
                .order(1)
                // 设置需要拦截的路径(拦截的路径需要经过拦截器)
                .addPathPatterns("/**")
                // 设置放行的路径，登录放行（放行的路径不会执行拦截器里的方法）
                .excludePathPatterns("/login")
                // 放行Swagger访问
                .excludePathPatterns("swagger-ui.html/**", "swagger-ui.html");

    }

    /**
     * 静态资源处理
     * 将指定的资源路径映射到一个或多个URL路径上，并指定资源的缓存策略、版本号以及是否允许目录列表等选项。
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 通过springboot访问本地文件，http://192.168.31.11:8999/lx/static/test.html
        registry.addResourceHandler("/static/**").addResourceLocations("file:/Users/lixing/Downloads/下载文件/")
                // 设置缓存控制头（cache-control header）30分钟过期（30分钟内请求相同资源则不在发送请求）
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES).cachePublic())
                .resourceChain(false)
                // 添加 VersionResourceResolver ，且指定版本号
                .addResolver(new VersionResourceResolver().addFixedVersionStrategy("1.0", "/**"));

        // 添加对swagger-ui的处理
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("claspath:/META-INF/resources/webjars/");
    }

    /**
     * 跨域配置
     *
     * @param registry 出于浏览器的同源（同一个域）策略限制
     *                 跨域配置类方式：MyCorsFilter
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射路径，凡是在addMapping里的路径都可以跨域访问，
        registry.addMapping("/**")
                // 允许跨域的域名，可以用*表示允许任何域名使用
                .allowedOriginPatterns("*")
                // 允许任何方法（post、get等）
                .allowedMethods("GET", "POST")
                // 允许任何请求头
                .allowedHeaders("*")
                // 允许跨域携带cookie
                .allowCredentials(true)
                // 跨域允许时间
                .maxAge(3600)
                // 暴露那些响应头部信息
                .exposedHeaders("Authorization");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

    /**
     * 视图跳转控制器 前后端分离项目一般不配置，只有在单体项目中，前后端一体接入视图技术，比如Thymeleaf，才需要配置
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
    }

    /**
     * 配置视图解析器
     *
     * @param registry
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
    }

    /**
     * 自定义参数解析器，处理请求参数
     *
     * @param resolvers
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
    }

    /**
     * 自定义控制器方法返回值处理器
     *
     * @param handlers
     */
    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        WebMvcConfigurer.super.addReturnValueHandlers(handlers);
    }

    /**
     * 配置消息转换器：配置在接收request和返回response的数据转换器
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
        // converters.add(fastJsonHttpMessageConverters());
        // 自定义消息转换器
        converters.add(myMessageConverter());
    }

    /**
     * 配置扩展SpringMvc框架的信息转换器
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        // converters.add(mappingJackson2HttpMessageConverter());
        converters.add(stringHttpMessageConverter());
    }

    @Bean
    public MyMessageConverter myMessageConverter() {
        return new MyMessageConverter();
    }

    /**
     * 引入Fastjson解析json，不使用默认的jackson,必须在pom.xml引入fastjson的jar包，并且版必须大于1.2.10
     *
     * @return
     */
    //@Bean
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverters() {
        //1、定义一个convert转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加fastjson的配置信息
        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        SerializerFeature[] serializerFeatures = new SerializerFeature[]{
                //    输出key是包含双引号
                //SerializerFeature.QuoteFieldNames,
                //    是否输出为null的字段,若为null 则显示该字段
                SerializerFeature.WriteMapNullValue,
                //    数值字段如果为null，则输出为0
                SerializerFeature.WriteNullNumberAsZero,
                //     List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //    字符类型字段如果为null,输出为"",而非null
                SerializerFeature.WriteNullStringAsEmpty,
                //    Boolean字段如果为null,输出为false,而非null
                SerializerFeature.WriteNullBooleanAsFalse,
                //    Date的日期转换器
                SerializerFeature.WriteDateUseDateFormat,
                //    循环引用（如果不配置有可能会进入死循环）
                SerializerFeature.DisableCircularReferenceDetect,
                //    格式化JSON
                SerializerFeature.PrettyFormat
        };


        fastJsonConfig.setSerializerFeatures(serializerFeatures);
        fastJsonConfig.setCharset(StandardCharsets.UTF_8);


        //3.解决乱码问题。定义响应的MIME类型，设置响应的content-type为application/json;charset=UTF-8
        List<MediaType> fastMediaType = new ArrayList<>();
        fastMediaType.add(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE));

        //4.converter消息转换器添加配置信息
        fastConverter.setSupportedMediaTypes(fastMediaType);

        //5、在convert中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);


        return fastConverter;
    }

    /**
     * 自定义转换器
     *
     * @return
     */
    // @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        // 将返回结果的控制不进行序列化
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter() {
            // 空值处理，将返回结果的空值不进行序列化
            @Override
            public ObjectMapper getObjectMapper() {
                super.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
                super.getObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

                // 防止xss攻击，对@RequestBody json进行xss过滤
                // 该方案无法对单个字段做特殊处理，比如@RequestBody String参数就不行,因为这里是通过反序列化实现的
                // 想要支持富文本的话, 避免Xss过滤的话， 在文本属性加上@JsonDeserialize(using = StringDeserializer.class) 注解
                SimpleModule simpleModule = new SimpleModule();
                simpleModule.addDeserializer(String.class, new StringXssDeserializer());
                super.getObjectMapper().registerModule(simpleModule);
                return super.getObjectMapper();
            }
        };

//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        //objectMapper.setAnnotationIntrospector(new CommaJsonAnnotationIntrospector());
//        //日期格式化
//        converter.setObjectMapper(objectMapper);


        // 设置中文编码格式
        List<MediaType> mediaTypes = Collections.singletonList(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypes);
        return converter;
    }

    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(new MediaType("text", "plain", StandardCharsets.UTF_8)));
        return converter;
    }


    /**
     * 配置异常处理器
     *
     * @param resolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        WebMvcConfigurer.super.configureHandlerExceptionResolvers(resolvers);
    }

    /**
     * 扩展异常处理器
     *
     * @param resolvers
     */
    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        WebMvcConfigurer.super.extendHandlerExceptionResolvers(resolvers);
    }

    /**
     * JSR303的自定义验证器
     *
     * @return
     */
    @Override
    public Validator getValidator() {
        return WebMvcConfigurer.super.getValidator();
    }

    /**
     * 消息处理对象
     *
     * @return
     */
    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return WebMvcConfigurer.super.getMessageCodesResolver();
    }
}
