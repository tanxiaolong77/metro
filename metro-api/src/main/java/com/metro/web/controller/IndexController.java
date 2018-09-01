package com.metro.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.metro.util.BeanUtils;
import com.metro.util.SessionUtils;
import com.metro.vo.EmployeeVO;
import com.metro.vo.UserVO;


@Controller
public class IndexController  extends BaseController{
	
	
	
	/**
	 * 跳转到管理员登录页面
	 * 
	 */
	@RequestMapping(value = "index.m", method = RequestMethod.GET)
	public String toMngLogin(ModelMap modelMap) {
		UserVO userVO = BeanUtils.transferB(SessionUtils.getLoginManager(),UserVO.class);
		modelMap.put("user",userVO);
		return "views/sys-index.html";
	}
	
	/**
	 * 跳转到管理员登录页面
	 * 
	 */
	@RequestMapping(value = "index.u", method = RequestMethod.GET)
	public String toLogin(ModelMap modelMap) {
		EmployeeVO employeeVO = BeanUtils.transferB(SessionUtils.getLoginUser(),EmployeeVO.class);
		modelMap.put("user",employeeVO);
		return "views/index.html";
	}
	
	
}
