package com.metro.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.metro.dao.UserDao;
import com.metro.model.User;
import com.metro.query.UserQuery;
import com.metro.service.UserService;
import com.metro.service.base.BaseService;
import com.metro.service.base.EntityDao;
import com.metro.vo.UserShow;
import com.quanjing.util.ActResult;
import com.quanjing.util.EncryptUtils;
import com.quanjing.util.KeyHelp;
import com.quanjing.util.RedisUtil;
import com.quanjing.util.StringUtils;
import com.quanjing.util.TimeUtil;
import com.quanjing.util.constant.ErrorCode;
import com.quanjing.util.constant.RedisKey;

@Service("userService")
public class UserServiceImpl extends BaseService<User, Long>  implements UserService{

	@Autowired
	private UserDao userDao;
	@Autowired
	private RedisUtil redis;
	
	protected EntityDao getEntityDao() {
		return userDao;
	}


	private static BlockingQueue<String> sendQueue=new LinkedBlockingQueue<String>(60000);
	
	private final int threadCount=2;
	
	private ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
	

	private static Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	
	
	public UserServiceImpl() {

		logger.info("init UpdateLogin thread:"+threadCount);
		for(int i=0;i<threadCount;i++){
			UpdateLogin t1 = new UpdateLogin();
			threadPool.execute(t1);
		}

		
	}


	private class UpdateLogin implements Runnable{
		
		public void run() {
			
			while(true){
				String login=null;
				try {
					login = sendQueue.take();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(login!=null){
					logger.debug("readay to update: "+login);
					String[] arr=login.split(",");
					
					try {
						User user=new User();
						user.setId(Long.valueOf(arr[0]));
						user.setLastLoginTime(TimeUtil.strToDate(arr[1], "yyyy-MM-dd HH:mm:ss"));
						userDao.update(user);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}else{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
		
	}
	
	
	public User save(User entity) throws DataAccessException {
		Map map=new HashMap();
		map.put("username", entity.getUsername());
		entity.setStatus(0);
		long c=userDao.findPage_count(map);
		if(c<1){
			//密码加密
			String encodePwd = EncryptUtils.baseMd5Encode(entity.getPassword());
			entity.setPassword(encodePwd);
			userDao.insert(entity);
		}
		
		return entity;
	}
	
	
	
	public User getById(Long id) throws DataAccessException {
		User user= userDao.getById(id);
		
		return user;
	}
	
	
	
	public void update(User entity) throws DataAccessException {
		if(entity.getId()!=null){
			userDao.update(entity);
			redis.del(RedisKey.user_show+"_["+entity.getId()+"]");
		}
	}
	
	
	public ActResult login(String userName, String password) {
		ActResult<User> ret=ActResult.fail("");
		try {
			ret = loginWithUser(userName,password);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if(ret.isSuccess()){
			
			String ticket;
			try {
				ticket =generateTicket(ret.getData().getId());
				Map<String,Object> map=new HashMap();
				map.put("ticket", ticket);
				map.put("user", ret.getData());
				return ActResult.success(map);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ActResult.fail("");
		}else{
			return ActResult.fail("");
		}
		
		
	}
	
	public ActResult<User> loginWithUser(String userName, String password) {
		if(StringUtils.isNullOrEmpty(userName))
			return ActResult.fail("账号不能为空");
		
		if(StringUtils.isNullOrEmpty(password)){
			return ActResult.fail("密码不能为空");
		}
		String pwdEncode = EncryptUtils.baseMd5Encode(password);
		
		Map map = new HashMap();
		map.put("username", userName);
		map.put("password", pwdEncode);
		User user=userDao.selectOne(map);
		
		
		if(StringUtils.isNullOrEmpty(user)){
			logger.error(userName + "," + password + " eror");
			return ActResult.fail("账号或密码错误",ErrorCode.loginErrorNoMatch);
		}else{
			if (user.getStatus() == null || user.getStatus().intValue() > -1) {
				sendQueue.add(user.getId() + "," + TimeUtil.getCurrentTime());
				user.setLastLoginTime(new Date());
				user.setPassword(null);
				return ActResult.success(user);
			} else if (user.getStatus() != null && user.getStatus().intValue() == -1) {
				logger.error(userName + "," + password + " is locked");
				return ActResult.fail("您的账号被锁定");
			} else {
				logger.error(userName + "," + password + " is incorrect");
				return ActResult.fail("账号信息有误");
			}
		}
	}
	
	

	public User getByIdWithPwd(Long id) {
		return userDao.getByIdWithPwd(id);
	}
	
	public ActResult updatePwd(Long id, String password) {
		//密码加密
		Map<String,Object> map=new HashMap();
		map.put("id", id+"");
		map.put("password", EncryptUtils.baseMd5Encode(password));
		userDao.updatePwd(map);
		redis.del(RedisKey.user_login+id);
		return ActResult.success();
	}
	

	public User findByUserName(String userName) {
		User user=null;
		Map<String,String> map=new HashMap();
		map.put("userName", userName);
		List<User> li=userDao.getByUserName(userName);
		if(li!=null&&li.size()>0){
			user=li.get(0);
		}
		
		return user;
	}
	
	
	
	
	
	public void logout(Long uid) {
		
		
	}

	
	public int countByUserName(String userName) {
		Map<String,String> map=new HashMap();
		map.put("username", userName);
		return (int)userDao.findPage_count(map);
	}

	

	public ActResult<User> modifyPhone(Long userId, String phone) {
		
		return null;
	}

	public UserShow getUserShow(Long id) {
		return userDao.getUserShow(id);
	}
	

	
	public String generateTicket(Long userId) {
		String ticket=null;
		try {
			ticket = KeyHelp.getInstance(redis).encode(userId+","+TimeUtil.getCurrentTime());
			redis.setMapData(RedisKey.user_login+userId, "ticket", ticket);
			redis.setMapData(RedisKey.user_login+userId, "lastLogin", TimeUtil.getCurrentTime());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}


	public ActResult resetTicket(String ticket) {
			if(StringUtils.isEmpty(ticket)){
				return ActResult.fail("未登陆",ErrorCode.noLogin);
			}
			
			String info=null;
			try {
				info = KeyHelp.getInstance(redis).decode(ticket);
			} catch (Exception e) {
				e.printStackTrace();
				return ActResult.fail("ticket有误",ErrorCode.ticketError);
			}
			String[] data=info.split(",");
			Long userId=Long.valueOf(data[0]);
			Map<String,String> map=redis.getMap(RedisKey.user_login+userId);
			if(map==null){
				return ActResult.fail("未登陆",ErrorCode.noLogin);
			}
			String ticket1=map.get("ticket");
			
			if(ticket1!=null&&ticket1.equals(ticket)){
				String newticket=generateTicket(userId);
				return ActResult.success(newticket);
			}
			
			return ActResult.fail("ticket有误",ErrorCode.ticketError);
		
		
	}
	
	public User findByPhone(String phone) {

		List<User> li=userDao.getByPhone(phone);
		User user = null;
		if(li!=null&&li.size()>0){
			user=li.get(0);
		}
		return user;
	}
	

	public String getPhoneById(Long id) {
		return userDao.getPhoneById(id);
	}
	
	public List<User> findUser(UserQuery query){
		return userDao.findUser(query);
	}

	public long findMaxId(){
		return userDao.findMaxId();
	}
}
