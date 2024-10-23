package com.lixing.lixingdemo.DesignPatterns.responseChain;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/4
 */
public class Test {

    public static void main(String[] args) {
        Handler.Builder<Object> builder = new Handler.Builder<>();
        builder.addHandler(new ValidateHandler()).addHandler(new LoginHandler()).addHandler(new AuthHandler())
                .addHandler(new BusinessHandler());
        Member member = new Member();
        member.setUserName("admin");
        member.setPassword("123456");
        member.setRoleName("管理员");
        builder.build().doHandler(member);

    }
}
