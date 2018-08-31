package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.EmployeeMapper;
import com.metro.model.Employee;
import com.metro.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl  extends BaseService<Employee,String> implements EmployeeService{
	
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return employeeMapper;
	}
 
}