package com.sky.controller;

import com.sky.api.entities.Dept;
import com.sky.api.service.DeptClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //一定不能忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService(){
            @Override
            public boolean add(Dept dept) {
                return false;
            }

            @Override
            public Dept get(Long id) {
                return new Dept().setDeptno(id).setDname("无信息").setDb_source("null");
            }

            @Override
            public List<Dept> list() {
                return null;
            }
        };
    }
}
