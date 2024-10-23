package com.lixing.lixingdemo.DesignPatterns.responseChain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/4
 */
@Slf4j
public class AuthHandler extends Handler{
    @Override
    public void doHandler(Member member) {
        if (!"管理员".equals(member.getRoleName())) {
            log.error("当前用户没有操作权限");
            return;
        }
        System.out.println("用户操作权限校验通过");
        if (null != next) {
            next.doHandler(member);
        }
    }
}
