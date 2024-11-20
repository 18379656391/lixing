package com.lixing.lixingdemo.dynamicProxy.jdkProxy;

import org.springframework.stereotype.Service;

@Service
public class DaoServiceImpl implements DaoService {
    @Override
    public void add() {
        System.out.println("新增一条数据");
    }

    @Override
    public void delete() {
        System.out.println("删除一条数据");
    }

    @Override
    public void update() {
        System.out.println("更新一条数据");
    }

    @Override
    public void query() {
        System.out.println("查询一条数据");
    }
}
