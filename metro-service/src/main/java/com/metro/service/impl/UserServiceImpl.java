package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.UserMapper;
import com.metro.model.User;
import com.metro.service.UserService;

@Service("userService")
public class UserServiceImpl  extends BaseService<User,String> implements UserService  {
 
	
	@Autowired
	private UserMapper userMapper;
	
	@Override
	protected EntityDao getEntityDao() {
		return userMapper;
	}
}