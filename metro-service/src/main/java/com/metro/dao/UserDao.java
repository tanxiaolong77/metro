/*
 * Powered By [rapid-framework]
 * Since 2008 - 2016
 */

package com.metro.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.metro.model.User;
import com.metro.query.UserQuery;
import com.metro.service.base.EntityDao;
import com.metro.vo.UserShow;

public interface UserDao extends  EntityDao<User,java.lang.Long>{
	
	
	long findPage_count(Map map);
	
	long findMaxId();

	User getByIdWithPwd(Long id);
	
	UserShow getUserShow(Long id);
	
	
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 */
	void updatePwd(Map map);
	
	List<User> getByPhone(@Param("phone")String phone);
	
	List<User> getByUserName(@Param("username")String userName);
	
	String getPhoneById(Long id);
	
	List<User> findUser(UserQuery query);
}