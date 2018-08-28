package com.metro.service;

import com.metro.basic.EntityService;
import com.metro.model.SysUser;


public interface SysUserService extends EntityService<SysUser,String> {
	
	
	public SysUser checkUser(String username, String password);
}
