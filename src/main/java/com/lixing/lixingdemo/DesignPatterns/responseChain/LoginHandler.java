package com.lixing.lixingdemo.DesignPatterns.responseChain;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/4
 */
@Slf4j
public class LoginHandler extends  Handler{
    @Override
    public void doHandler(Member member) {
        if (!"123456".equals(member.getPassword()) || !"admin".equals(member.getUserName())) {
            log.error("用户名密码错误");
            return;
        }
        System.out.println("用户名称密码校验通过");
        if (null != next) {
            next.doHandler(member);
        }
    }
}
