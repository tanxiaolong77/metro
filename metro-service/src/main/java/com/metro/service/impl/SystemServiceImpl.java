package com.metro.service.impl;

import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.service.SystemService;
import com.metro.service.UserService;
import com.metro.vo.UserShow;
import com.quanjing.util.ActResult;
import com.quanjing.util.KeyHelp;
import com.quanjing.util.RedisUtil;
import com.quanjing.util.StringUtils;
import com.quanjing.util.TimeUtil;
import com.quanjing.util.constant.ErrorCode;
import com.quanjing.util.constant.RedisKey;
import com.quanjing.util.constant.UserConstant;

@Service("systemService")
public class SystemServiceImpl implements SystemService{

	@Autowired
	private RedisUtil redis;
	
	@Autowired
	private UserService userService;
	
	private static Logger logger= LoggerFactory.getLogger(SystemServiceImpl.class);
	
	public ActResult<UserShow> hasLogin(String ticket) {
		if(StringUtils.isEmpty(ticket)){
			return ActResult.fail("未登陆",ErrorCode.noLogin);
		}
		try {
			String info = KeyHelp.getInstance(redis).decode(ticket);
			String[] data=info.split(",");
			Long userId=Long.valueOf(data[0]);
			Map<String,String> map=redis.getMap(RedisKey.user_login+userId);
			if(map==null){
				return ActResult.fail("未登陆",ErrorCode.noLogin);
			}
			String ticket1=map.get("ticket");
			
			if(ticket1!=null&&ticket1.equals(ticket)){
				Date now=new Date();
				String lastlogin=map.get("lastLogin");
				
				if(TimeUtil.getSecondsTimeSpan(TimeUtil.strToDate(lastlogin, "yyyy-MM-dd HH:mm:ss"), now)>UserConstant.USER_TICKET_TIME){
					return ActResult.fail("登录已过期，请重新登录",ErrorCode.ticketOverdue);
				}
				return ActResult.success(userService.getUserShow(userId));
			}else{
				logger.debug(userId+" ticket error["+ticket+"], right="+ticket1);
				return ActResult.fail("登录已过期，请重新登录",ErrorCode.ticketOverdue);
			}
			
		} catch (Exception e) {
			logger.info("ticket error:"+ticket);
			return ActResult.fail("登录有误",ErrorCode.ticketError);
		}
	}

}
