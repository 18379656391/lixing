package com.lixing.lixingdemo.asyncDemo.Future;

import lombok.Builder;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-09 13:37
 */
@Builder
public class UserInfo {
    private String userId;
    private String userName;
    private String age;

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
