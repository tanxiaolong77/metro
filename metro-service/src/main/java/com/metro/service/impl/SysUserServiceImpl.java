package com.metro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.basic.BaseService;
import com.metro.basic.EntityDao;
import com.metro.dao.SysUserDao;
import com.metro.model.SysUser;
import com.metro.service.SysUserService;
import com.metro.util.EncryptUtils;

@Service("sysUserService")
public class SysUserServiceImpl extends BaseService<SysUser, String> implements SysUserService{

	@Autowired
	private SysUserDao sysUserDao;
	
	@Override
	protected EntityDao getEntityDao() {
		return sysUserDao;
	}

	@Override
	public SysUser checkUser(String username, String password) {
		SysUser sysUser = sysUserDao.getByUsername(username);
		if(sysUser != null){
			String md5Encode = EncryptUtils.Md5Encode(password);
			if(md5Encode.equals(sysUser.getPassword())){
				return sysUser;
			}
		}
		return null;
	}

}
