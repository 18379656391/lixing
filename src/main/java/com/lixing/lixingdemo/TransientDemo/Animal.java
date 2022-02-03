package com.lixing.lixingdemo.TransientDemo;

import lombok.Builder;

import java.io.Serializable;

/**
 * @version: 1.0
 * @author: lixing41189
 * @date: 2022-01-18 16:33
 */
@Builder
public class Animal implements Serializable {

    private static final long serialVersionUID = -4929339306986716521L;

    private String animalType;

    private transient String animalName;

    private String animalColor;

    @Override
    public String toString() {
        return "Animal{" +
                "animalType='" + animalType + '\'' +
                ", animalName='" + animalName + '\'' +
                ", animalColor='" + animalColor + '\'' +
                '}';
    }
}
