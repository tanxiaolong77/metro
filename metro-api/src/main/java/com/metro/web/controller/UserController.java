package com.metro.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metro.model.User;
import com.metro.service.SmsService;
import com.metro.service.UserService;
import com.metro.util.Constant;
import com.metro.util.CookieUtil;
import com.metro.vo.UserShow;
import com.quanjing.util.ActResult;
import com.quanjing.util.EncryptUtils;
import com.quanjing.util.RedisUtil;
import com.quanjing.util.StringUtils;
import com.quanjing.util.constant.SessionCode;
import com.quanjing.util.constant.UserConstant;


/**
 * 用户（登录/相关信息）
 * @author 
 *
 */
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController extends BaseController{
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsService smsService;
	
	@Autowired
	private RedisUtil redisUtil;
	
	/**
	 * 功能：用户注册发送短信
	 * @param phone
	 * @return
	 */
	@RequestMapping("sendRegistCode")
	public ActResult<Object> sendRegistCode(HttpServletRequest request,String phone,Boolean bool){
		if (bool==null) {
			bool=true;
		}
		if(StringUtils.isPhoneNumber(phone)){
			User user = userService.findByPhone(phone);
			if (user!=null&&bool) {
				return ActResult.fail("用户已经注册");
			}
				//生成6位验证码发送并放入redis中
				 String code = StringUtils.getRandomNum();
				// 发送验证码短信
				ActResult<Object> result = smsService.sendCode(phone,code);
				if (result.isSuccess()) {
					// 保存验证码 
					redisUtil.setData(phone+ UserConstant.SENDMSGREGPHONE_SUFFIX, code,UserConstant.SENDMSGREGPHONE_TIME);
					return ActResult.success("已发送，有效时间5分钟");
				} else {
					return ActResult.fail(result.getMsg());
				}
		}
		return ActResult.fail("请输入合法的手机号码");
	}
	
	
	
	/**
	 * 功能：用户登录
	 * @param request
	 * @param userName
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	public ActResult login(HttpServletRequest request,HttpServletResponse response,@RequestParam()String username,@RequestParam()String password) {
		  ActResult ret= userService.login(username, password);
			Map data = (HashMap)ret.getData();
			if(null != data && null != data.get("ticket") && StringUtils.isNotNullEmpty(data.get("ticket").toString())){
				getSession(request).setAttribute(SessionCode.loginUser,data.get("ticket").toString());
				data.put("ticket",null);
			} 
		  return ret;
	}
	
	
	@RequestMapping("resetTicket")
	public ActResult resetTicket(HttpServletRequest request,HttpServletResponse response,String ticket){
		ActResult<String> ret =userService.resetTicket(ticket);
		 
		 return ret;
	}
	
	
	/**
	 * 功能：注册
	 * @param request
	 * @param code
	 * 
	 */
	@RequestMapping("regist")
	public ActResult<User> regist(HttpServletRequest request,HttpServletResponse response,@RequestParam()String phone,@RequestParam()String password, @RequestParam()String code){
		
		String redisCode  = redisUtil.getData(phone + UserConstant.SENDMSGREGPHONE_SUFFIX);
		if(StringUtils.isNullOrEmpty(redisCode)){
			return ActResult.fail("验证码已失效");
		}
		if(StringUtils.isEmpty(code)||!code.trim().equals(redisCode)){
			return ActResult.fail("验证码错误");
		}
		
		
		int find1 = userService.countByUserName(phone);
		
		if(find1==0){
			User user=new User();
			user.setPhone(phone);
			user.setPassword(password);
			user.setUsername(user.getPhone());
			user.setGender(0);
			user.setCreateTime(new Date());
			user.setStatus(0);
			//在userService中会对密码进行加密会注册环信用户
			userService.save(user);
			redisUtil.del(user.getPhone() + UserConstant.SENDMSGREGPHONE_SUFFIX);
			//自动登录
			return login(request, response, phone, password);
		}
		return ActResult.fail("该手机用户已存在");
	}
	
	
	/**
	 * 功能：修改用户信息
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("update.user")
	public ActResult<User> updateUser(HttpServletRequest request, User user) {
		if(StringUtils.isNullOrEmpty(user)){
			return ActResult.success("信息未修改");
		}
		if(user.getPhone()!=null&&!StringUtils.isPhoneNumber(user.getPhone())){
			return ActResult.fail("手机号码不合法");
		}
		if(user.getNickName()!=null&&user.getNickName().length()>=20){
			return ActResult.fail("昵称不能超过20个字符");
		}
		if(!StringUtils.isEmpty(user.getNickName())&&user.getNickName().contains("友搭")){
			return  ActResult.fail("昵称不能包含'友搭'字样");
		}
		if(StringUtils.isEmpty(user.getBirthday())){
			return  ActResult.fail("生日不能为空");
		}
		if(user.getUsername() == null){
			return  ActResult.fail("用户名不能为空");
		}
		UserShow loginUser=getLoginUser( request);
		user.setId(loginUser.getId());
		
		User oldUser = userService.getById(user.getId());
		if (user.getGender() == null) {
			user.setGender(0);//修改性别时如果性别为空则默认为无性别用户
		}
		
		user.setPassword(null);
		user.setLastLoginTime(null);
		user.setPhone(null);
		
		userService.update(user);
		return ActResult.success(userService.getById(user.getId()));
	}


	
	/**
	 *
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value="info.user")
	public ActResult<UserShow> info(HttpServletRequest request, Long id){
		if (id==null) {
			return ActResult.fail("id不能为空");
		}
		
		UserShow loginUser = getLoginUser(request);
		
		return ActResult.success(loginUser);
	}
	
	
	/**
	 * 功能：登陆后修改密码
	 * @param request
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 */
	/** 移除手机号依赖
	@RequestMapping("modifyPassword.user")
	*/
	@Deprecated
	public ActResult<User> modifyPassword(HttpServletRequest request,String oldPassword,String newPassword){
		UserShow loginUser=getLoginUser( request);
		
		if(StringUtils.isNullOrEmpty(loginUser)){
			return ActResult.fail("用户未登录");
		}else{
			User user = userService.getByIdWithPwd(loginUser.getId());
			if(!EncryptUtils.baseMd5Encode(oldPassword).equals(user.getPassword())){
				return ActResult.fail("旧密码不匹配");
			}
			if(!StringUtils.isPassWord(newPassword)){
				return ActResult.fail("新密码不合法");
			}
			userService.updatePwd(loginUser.getId(), newPassword);
			return ActResult.success(user);
		}
	}
	
//	/**
//	 * 功能：修改密码发送验证码
//	 * @param request
//	 * @param phoneNumber
//	 * @return
//	 */
//	/**
//	移除手机号依赖
//	@RequestMapping("modifyPasswordSendSms")
//	*/
//	@Deprecated
//	public ActResult<User> modifyPasswordSendSms(HttpServletRequest request,String phoneNumber){
//		if(StringUtils.isPhoneNumber(phoneNumber)){
//			User user = userService.findByPhone(phoneNumber);
//			if(StringUtils.isEmpty(user)){
//				return ActResult.fail("该手机号码未注册");
//			}
//			String code = null;
//			//首先查询redis中是否存在该手机号码已发送的验证码
//			code = redisUtil.getData(phoneNumber + UserConstant.FINDPWD_BYPHONE);
//			//不存在生成6位验证码发送并放入redis中
//			if(StringUtils.isNullOrEmpty(code)){
//				code = StringUtils.getRandomNum();
//				//发送验证码短信：
//				ActResult<Object> result = smsService.sendContent(phoneNumber, "您的id:"+user.getId()+",用户名:"+user.getUsername()+",短信验证码："+code);
//				if(result.isSuccess()){
//					//保存验证码信息时效2分钟
//					redisUtil.setData(phoneNumber + UserConstant.FINDPWD_BYPHONE,code,UserConstant.FINDPWDBYPHONEFUNCTION_TIME);
//					return ActResult.success("验证码已发送，请注意查收");
//				}else{
//					return ActResult.fail(result.getMsg());
//				}
//			}
//			return ActResult.fail("验证码已发送，请稍后再试");
//		}
//		return ActResult.fail("请输入合法的手机号码");
//	}
	
	
	
	/**
	 * 功能：注销
	 * @param request
	 * @param ticket
	 */
	@RequestMapping("logout")
	public ActResult logOut(HttpServletRequest request){
		String ticket = request.getHeader(Constant.Ticket);
		if(ticket==null){
			ticket=CookieUtil.getValue(request, Constant.Ticket);
		}
		if (ticket != null) {
			ActResult<UserShow> login=getSystemService().hasLogin(ticket);
			if(login.isSuccess()){
				redisUtil.removeSet("loginuser", login.getData().getId()+"");
			}

		}
		return ActResult.success();
	}
}
