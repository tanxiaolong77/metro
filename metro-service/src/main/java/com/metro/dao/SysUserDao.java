/*
 * Powered By [rapid-framework]
 * Web Site: http://www.rapid-framework.org.cn
 * Google Code: http://code.google.com/p/rapid-framework/
 * Since 2008 - 2016
 */

package com.metro.dao;

import com.metro.basic.EntityDao;
import com.metro.model.SysUser;


public interface SysUserDao extends EntityDao<SysUser,String>{
	
	
	public SysUser getByUsername(String username);
	
}
