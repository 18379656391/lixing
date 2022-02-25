package com.lixing.lixingdemo.asyncDemo.Future;

import lombok.Builder;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-02-09 13:40
 */
@Builder
public class MedalInfo {

    private String userId;
    private String medalName;

    @Override
    public String toString() {
        return "MedalInfo{" +
                "userId='" + userId + '\'' +
                ", medalName='" + medalName + '\'' +
                '}';
    }
}
