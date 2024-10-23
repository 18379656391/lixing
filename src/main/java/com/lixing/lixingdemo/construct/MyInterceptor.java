package com.lixing.lixingdemo.construct;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/7/25
 * 请求拦截器
 * 执行顺序 preHandle -> controller -> postHandle -> afterCompletion
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 请求前置处理，可以执行一些预处理逻辑，如认证、授权、记录日志等
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 返回true继续处理请求，返回false则停止后续处理
        System.out.println("preHandle接收到请求信息，path:" + request.getRequestURL());
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("controller业务执行完成，postHandle开始执行--返回状态:" + response.getStatus());
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 请求处理完成后执行，可用于资源清理，记录请求完成的日志等等
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion执行，整个请求处理结束----");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
