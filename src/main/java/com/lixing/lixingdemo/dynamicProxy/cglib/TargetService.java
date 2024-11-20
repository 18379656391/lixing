package com.lixing.lixingdemo.dynamicProxy.cglib;

/**
 * @author lixing41189
 * @version 1.0
 * @date 2024/11/6
 */
public class TargetService {

    public void add() {
        System.out.println("cglib新增一条数据");
    }

    public void delete() {
        System.out.println("cglib删除一条数据");
    }

    public void update() {
        System.out.println("cglib更新一条数据");
    }

    public void query() {
        System.out.println("cglib查询一条数据");
    }
}
