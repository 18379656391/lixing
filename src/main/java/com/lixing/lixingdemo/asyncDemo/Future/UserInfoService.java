package com.lixing.lixingdemo.asyncDemo.Future;

import lombok.SneakyThrows;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-09 13:36
 * 模拟用户基本信息查询
 */
public class UserInfoService {

    @SneakyThrows
    public UserInfo getUserInfo(Long userId) {
        Thread.sleep(300); // 模拟调用耗时
        return UserInfo.builder().userId("666").userName("张三").age("24").build();
    }

    @SneakyThrows
    public MedalInfo getMedalInfo(Long userId){
        Thread.sleep(500);
        return MedalInfo.builder().userId("666").medalName("守护勋章").build();
    }
}
