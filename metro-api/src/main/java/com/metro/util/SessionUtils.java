package com.metro.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.metro.common.constant.Constant;
import com.metro.model.Employee;
import com.metro.model.User;

public class SessionUtils {
	
	/**
	 *  得到当前session
	 */
	public static HttpSession getSession() {
		HttpSession session = getCurRequest().getSession();
		return session;
	}
	
	/**
	 * session中的用户
	 */
	public static Employee getLoginUser(){
		return (Employee) getSession().getAttribute(Constant.SESSION_LOGIN_USER);
	}
	
	/**
	 * session中的管理员
	 */
	public static User getLoginManager(){
		return (User) getSession().getAttribute(Constant.SESSION_LOGIN_MANAGER);
	}
	
	/**
	 * session中的技能类型
	 */
	public static String getSkillType(){
		return  (String) getSession().getAttribute(Constant.SKILL_TYPE);
	}
	
	/**
	 * session中的考试类型
	 */
	public static String getTestType(){
		return  (String) getSession().getAttribute(Constant.TEST_TYPE);
	}
	
	/**
	 * session中的岗位类型
	 */
	public static String getJobType(){
		return  (String) getSession().getAttribute(Constant.JOB_TYPE);
	}
	
	/**
	 * @Title: getCurRequest
	 * @Description:(获得当前的request) 
	 * @param:@return 
	 * @return:HttpServletRequest
	 */
	public static HttpServletRequest getCurRequest(){
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if(requestAttributes != null && requestAttributes instanceof ServletRequestAttributes){
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
			return servletRequestAttributes.getRequest();
		}
		return null;
	}

}
