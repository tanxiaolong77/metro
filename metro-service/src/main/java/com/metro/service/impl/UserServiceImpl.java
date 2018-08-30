package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.UserMapper;
import com.metro.model.User;

@Service("userService")
public class UserServiceImpl  extends BaseService<User,String>  {
 
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return userMapper;
	}
}