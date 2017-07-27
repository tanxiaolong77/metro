package com.metro.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.metro.service.SystemService;
import com.metro.service.UserService;
import com.metro.util.ThreadLocalManage;
import com.metro.vo.UserShow;
import com.quanjing.util.ActResult;
import com.quanjing.util.JsonUtil;
import com.quanjing.util.constant.ErrorCode;
import com.quanjing.util.constant.SessionCode;

/***
 * 登录控制
* @Title: UserInterceptor.java
* @Company Beijing Panorama Media Inc.
* @author xiaolong.Tan 
* @date 2017年4月19日 上午9:21:20
 */
public class UserInterceptor implements HandlerInterceptor {

	private static Logger logger= LoggerFactory.getLogger(UserInterceptor.class);
	
	@Autowired
	private SystemService systemService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		ActResult ret =null;
		Object ticket = session.getAttribute(SessionCode.loginUser);
		if (ticket != null) {
			ActResult<UserShow> act = systemService.hasLogin(String.valueOf(ticket));
			if(null == act.getData()){
				ret = ActResult.fail("用户缓存数据有误",ErrorCode.ticketError);
			}else if (act.isSuccess()) {
				ThreadLocalManage.setUser(act.getData());
				ThreadLocalManage.setUrl(request.getRequestURI());
				return true;
			}else{
				ret=act;
			}
		}else{
			ret=ActResult.fail("未登陆",ErrorCode.noLogin);
		}
		
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json; charset=utf-8");
		response.getWriter().write(JsonUtil.toJsonString(ret));
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		ThreadLocalManage.remove();
	}

}
