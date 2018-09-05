package com.metro.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metro.common.constant.Constant;
import com.metro.model.Employee;
import com.metro.model.EmployeeExample;
import com.metro.model.User;
import com.metro.model.UserExample;
import com.metro.service.EmployeeService;
import com.metro.service.UserService;
import com.metro.util.BaseUtil;
import com.metro.util.EncryptUtils;
import com.metro.util.SessionUtils;
import com.metro.vo.DataTransObj;


@Controller
public class LoginController  extends BaseController{
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	

//	/**
//	 * 管理主页
//	 * 
//	 */
//	@RequestMapping(value="/")
//	public String toIndex(Model model, HttpServletRequest request) {
//		request.getSession().removeAttribute("code"); // 清除code
//		if( SysUserUtils.getLoginUser() == null){
//			return "redirect:/login";
//		}
//		return "WEB-INF/article.html";
//	}
	/**
	 * 跳转到员工登录页面
	 * 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String toLogin() {
		return "views/login.html";
	}
	
	/**
	 * 跳转到管理员登录页面
	 * 
	 */
	@RequestMapping(value = "m", method = RequestMethod.GET)
	public String toMngLogin() {
		return "views/sys-login.html";
	}
	
	/**
	 * 管理员登录验证
	 * 
	 * @param username
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "sysLogin", method = RequestMethod.POST)
	public @ResponseBody DataTransObj sysLogin(
			@RequestParam(value="username",required=true) String username,
			@RequestParam(value="password",required=true) String password,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		username = StringUtils.trim(username);
		password = StringUtils.trim(password);

		UserExample example = new UserExample();
		UserExample.Criteria c = example.createCriteria();
		c.andUserNameEqualTo(username);
		c.andPassWordEqualTo(EncryptUtils.Md5Encode(password));
		List<User> user = userService.selectByExample(example);
		if (user != null && user.size() > 0) {
			session.setAttribute(Constant.SESSION_LOGIN_MANAGER, user.get(0));
		} else {
			return DataTransObj.onFailure(null, "用户名或密码错误");
		}
		return DataTransObj.onSuccess(null, "登录成功");
	}

	/**
	 * 员工登录
	 * 
	 * @param username
	 * @param usercard
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public @ResponseBody DataTransObj login(String realName,
			String userCard, HttpServletRequest request) {
		HttpSession session = request.getSession();
		realName = StringUtils.trim(realName);
		userCard = StringUtils.trim(userCard);

		EmployeeExample example = new EmployeeExample();
		EmployeeExample.Criteria c = example.createCriteria();
		c.andUserCardEqualTo(userCard);
//		c.andRealNameEqualTo(realName);
		List<Employee> user = employeeService.selectByExample(example);
		
		if (user != null && user.size() > 0) {
			session.setAttribute(Constant.SESSION_LOGIN_USER, user.get(0));
		} else {
			//当前版本因为没有员工信息，所以登录时判断是否有身份证号存在，如果存在的话可以登录，反之新增一条
			Employee e = new Employee();
			e.setId(BaseUtil.getUUID());
			e.setRealName(realName);
			e.setUserCard(userCard);
			e.setCreateTime(new Date());
			employeeService.insert(e);
			session.setAttribute(Constant.SESSION_LOGIN_USER, e);
//			return DataTransObj.onFailure(null, "用户名或密码错误");
		}
		return DataTransObj.onSuccess(null, "登录成功");
	}
	
	
	@RequestMapping("logout")
	public String logoutU(HttpServletRequest request) {
		if(SessionUtils.getLoginUser() != null){
			request.getSession().invalidate();
			return "redirect:/";
		}else if(SessionUtils.getLoginManager() != null){
			request.getSession().invalidate();
			return "redirect:/m";
		}
		return "redirect:/";
	}
	
	/**
	 * 错误页面
	 * 
	 * @return 跳转到登录页面
	 */
	@RequestMapping("error")
	public String error(HttpServletRequest request,ModelMap map) {
		if(SessionUtils.getLoginManager() != null){
			map.put("source","m");
		}else{
			map.put("source","u");
		}
		return "views/error.html";
	}

}
