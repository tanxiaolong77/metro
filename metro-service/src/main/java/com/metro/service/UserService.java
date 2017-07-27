package com.metro.service;

import java.util.List;

import com.metro.model.User;
import com.metro.query.UserQuery;
import com.metro.service.base.EntityService;
import com.metro.vo.UserShow;
import com.quanjing.util.ActResult;
import com.quanjing.util.constant.RedisKey;
import com.quanjing.util.stereotype.QueryCached;

public interface UserService extends EntityService<User,Long>{
	
	
	public ActResult login(String userName, String password) ;
	
	
	public String generateTicket(Long userId) ;
	
	
	public ActResult<String> resetTicket(String ticket) ;
	
	@QueryCached(keyPreFix=RedisKey.user_show)
	UserShow getUserShow(Long id);
	
	
	public void logout(Long uid);
	
	/**
	 * 根据主键查询包含密码的数据
	 * @param id
	 * @return
	 */
	User getByIdWithPwd(Long id);
	
	/**
	 * 修改密码
	 * @param id
	 * @param password
	 */
	ActResult<User> updatePwd(Long id,String password);
	
	public User findByUserName(String userName);
	
	
	public int countByUserName(String userName);
	
	
	public ActResult<User> modifyPhone(Long userId, String phone);
	
	public ActResult<User> loginWithUser(String userName, String password);

	public String getPhoneById(Long id);

	public List<User> findUser(UserQuery query);

	public long findMaxId();

	public User findByPhone(String phone);
}
