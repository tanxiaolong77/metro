package com.metro.test;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.metro.service.UserService;
import com.quanjing.util.ActResult;
import com.quanjing.util.constant.UserConstant;
@WebAppConfiguration
@ContextConfiguration({ "classpath*:/spring-*.xml", "classpath*:/spring/*.xml" })
public class BasicWebTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	protected WebApplicationContext wac;
	protected MockMvc mvc;
	
	@Autowired
	protected UserService userService;
	
	protected static Cookie ticket=null;
	
	@Before
	public void setUp() {
		this.mvc=MockMvcBuilders.webAppContextSetup(wac).build();
		try {
			ticket=login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	private Cookie login() throws Exception {
		 ActResult ret= userService.login("18600962172", "123456");
		 if(ret.isSuccess()){
			  Map<String,Object> data=(Map)ret.getData();
			  Cookie cookie = new Cookie("ticket",(String)data.get("ticket")); 
			  cookie.setMaxAge(UserConstant.COOKIE_TIME); 
			  cookie.setPath("/"); 
			  cookie.setDomain("junittest.tiankong.com");
			  ticket=cookie;
			  return cookie;
		  }
		  return null;
		  
    } 
	
	
}
