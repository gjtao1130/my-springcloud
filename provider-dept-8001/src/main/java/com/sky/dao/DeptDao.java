package com.sky.dao;

import java.util.List;

import com.sky.api.entities.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "deptDao")
  //SpringBoot 在整合其资源的时候经常会遇到could not autowired. No beans of ‘xxxx’ type
  //解决方法：增加@Component(value = "deptDao")
public interface DeptDao
{
	public boolean addDept(Dept dept);

	public Dept findById(Long id);

	public List<Dept> findAll();
}
