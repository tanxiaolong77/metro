package com.metro.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.metro.model.Employee;
import com.metro.model.Jobs;
import com.metro.model.JobsExample;
import com.metro.model.Match;
import com.metro.model.MatchExample;
import com.metro.model.Score;
import com.metro.model.ScoreExample;
import com.metro.service.JobsService;
import com.metro.service.MatchService;
import com.metro.service.ScoreService;
import com.metro.util.BeanUtils;
import com.metro.util.SessionUtils;
import com.metro.vo.EmployeeVO;
import com.metro.vo.UserVO;


@Controller
public class IndexController  extends BaseController{
	
	@Autowired
	private JobsService jobsService;
	
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
