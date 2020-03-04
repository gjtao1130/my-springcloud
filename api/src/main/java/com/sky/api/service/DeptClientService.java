package com.sky.api.service;

import com.sky.api.entities.Dept;
import com.sky.controller.DeptClientServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 *
 * @Description: 修改microservicecloud-api工程，根据已经有的DeptClientService接口
  新建一个实现了FallbackFactory接口的类 DeptClientServiceFallbackFactory
  实现Hystrix熔断降级
 */

//@FeignClient(value = "MICROSERVICECLOUD-DEPT") //Feign客户端注解
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory= DeptClientServiceFallbackFactory.class)
//Feign客户端注解，使用Hystrix断路器服务降级时要添加fallbackFactory=DeptClientServiceFallbackFactory.class
public interface DeptClientService {
    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept);

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable("id") Long id);

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list();
}
