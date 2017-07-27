package com.metro.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.metro.test.BasicWebTest;

public class TestUserController extends BasicWebTest{
//	@Test  
    public void testSendRegistCode() throws Exception {
    	mvc.perform(MockMvcRequestBuilders.get("/user/sendRegistCode?phone=18310542321").cookie(ticket))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(print()).andReturn();
    } 
//	@Test  
	public void testLogin() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/user/login")
				.param("username", "18310542321")
				.param("password", "123456"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andDo(print());
	} 
//	@Test  
	public void testResetTicket() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/resetTicket").cookie(ticket))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(print()).andReturn();
	} 
//	@Test  
	public void testRegist() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/regist")
				.param("phone", "18310542321")
				.param("password", "123456")
				.param("code", "2345")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(print()).andReturn();
	} 
	@Test  
	public void testUpdate() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/update.user")
//				.param("signature", "每个月总有那么30多天不想上班儿")
//				.param("nickName", "Orian")
				.param("schoolName", "北京工业大学")
				.param("company", "阿里巴巴")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(print()).andReturn();
	} 
//	@Test  
	public void testInfo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/info.user")
				.cookie(ticket)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(print()).andReturn();
	} 
//	@Test  
	public void testOthersInfo() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/info?id=36")
				.cookie(ticket)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(print()).andReturn();
	} 
//	@Test  
	public void testModifyPassword() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/modifyPassword.user")
				.param("oldPassword", "123456")
				.param("newPassword", "123456")
				.cookie(ticket)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(print()).andReturn();
	} 
//	@Test  
	public void testGetNearbyUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/user/getNearbyUser.user")
				.param("lng", "116.365051")
				.param("lat", "39.967304")
				.cookie(ticket)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(print()).andReturn();
	} 
	

}
