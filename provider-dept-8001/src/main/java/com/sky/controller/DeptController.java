package com.sky.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sky.api.entities.Dept;
import com.sky.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept)
    {
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_back") //Hystrix熔断，出错后调用方法processHystrix_back
    //服务调用异常后，会调用@HystrixCommand标注好的方法：processHystrix_back
    public Dept get(@PathVariable("id") Long id)
    {
        Dept dept = deptService.get(id);
        if (dept == null)
        {
            throw new RuntimeException("改ID"+id+"没有记录！");
        }
        return dept;
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list()
    {
        return deptService.list();
    }

    public Dept processHystrix_back(@PathVariable("id") Long id)
    {
        return new Dept().setDeptno(id).setDname("无信息").setDb_source("null");
    }
}
