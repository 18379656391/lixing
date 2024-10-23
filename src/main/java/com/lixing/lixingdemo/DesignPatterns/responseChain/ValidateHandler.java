package com.lixing.lixingdemo.DesignPatterns.responseChain;

import com.alibaba.excel.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/2/4
 */
@Slf4j
public class ValidateHandler extends Handler{
    @Override
    public void doHandler(Member member) {
        if (StringUtils.isBlank(member.getUserName()) || StringUtils.isBlank(member.getPassword())) {
            log.error("用户名称和密码不能为空");
            return;
        }
        System.out.println("用户名称密码空校验通过");
        if (null != next) {
            next.doHandler(member);
        }
    }
}
