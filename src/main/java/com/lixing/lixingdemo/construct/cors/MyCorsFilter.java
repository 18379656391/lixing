package com.lixing.lixingdemo.construct.cors;


import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/26
 * 跨域配置，除了可以实现WebMvcConfigurer，还可以使用配置类
 */
@Configuration
@ConditionalOnMissingClass("com.lixing.lixingdemo.construct.MyWebConfig")
public class MyCorsFilter {

    @Bean
    public CorsFilter corsFilter(){
        // 创建CORS配置对象
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedHeader("*");
        config.addExposedHeader("*");
        // 添加地址映射
        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config);
        // 返回配置对象
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
