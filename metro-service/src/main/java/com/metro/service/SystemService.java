package com.metro.service;

import com.metro.vo.UserShow;
import com.quanjing.util.ActResult;

public interface SystemService{
	/**
	 * 根据令牌获取用户信息
	 * @param ticket
	 * @return
	 */
	ActResult<UserShow> hasLogin(String ticket);
}
