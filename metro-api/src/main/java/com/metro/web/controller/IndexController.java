package com.metro.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.metro.model.Employee;
import com.metro.service.MatchService;
import com.metro.service.ScoreService;
import com.metro.util.BeanUtils;
import com.metro.util.SessionUtils;
import com.metro.vo.UserVO;


@Controller
public class IndexController  extends BaseController{
	
	@Autowired
	MatchService matchService;
	
	@Autowired
	ScoreService scoreService;
	
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
	 * 跳转到技能类型页面
	 * 
	 */
	@RequestMapping(value = "index.u", method = RequestMethod.GET)
	public String toLogin(ModelMap modelMap) {
		Employee user = BeanUtils.transferB(SessionUtils.getLoginUser(),Employee.class);
		modelMap.put("user",user);
		return "views/index.html";
	}
	
}
