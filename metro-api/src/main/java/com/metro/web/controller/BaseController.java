package com.metro.web.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import com.metro.service.SystemService;
import com.metro.vo.UserShow;
import com.quanjing.util.ActResult;
import com.quanjing.util.BeanFactory;
import com.quanjing.util.constant.SessionCode;

public class BaseController {
	

	@Autowired
	private SystemService systemService;
	
	
	public UserShow getLoginUser(HttpServletRequest request) {
		UserShow loginUser = null;
	   
		ActResult ret =null;
		Object ticket = getSession(request).getAttribute(SessionCode.loginUser);
		if (ticket != null) {
			ActResult<UserShow> act = systemService.hasLogin(ticket.toString());
			if (act.isSuccess()) {
				loginUser = act.getData();
			}
		}
		return loginUser;
	}
	
	protected String getErrorMsg(BindingResult result) {
		StringBuffer ret=new StringBuffer();
		if (result.hasErrors()) {  
			List<ObjectError> errors=result.getAllErrors();
			for(ObjectError e:errors){
				String msg=e.getDefaultMessage();
				if(msg.startsWith("{")){//自定义message
					msg=BeanFactory.getMessage(msg.substring(1, msg.length()-1));
				}else if(e.getCodes()[0].codePointCount(0, msg.length()-1)>=2){//根据code查找
					msg=BeanFactory.getMessage(e.getCodes()[0]);
				}
				if(msg==null){
					if(e instanceof FieldError){
						FieldError error=(FieldError)e;
						msg=error.getField()+e.getDefaultMessage();
					}else{
						msg=e.getDefaultMessage();
					}
				}
				ret.append(msg+",");
			}
			ret.deleteCharAt(ret.length()-1);
		}
		return ret.toString();
	}
	
	protected String getIpAddr(HttpServletRequest request) {
	    String ip = request.getHeader("x-forwarded-for");  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("Proxy-Client-IP");  
	    }  
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getHeader("WL-Proxy-Client-IP");  
	    }
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
	        ip = request.getRemoteAddr();  
	    }
	    return ip;  
	}
	
	/**
	 * xml转换为map
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> xmlToMap(HttpServletRequest request)
			throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();

		InputStream ins = null;
		try {
			ins = request.getInputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = reader.read(ins);
			Element root = doc.getRootElement();

			List<Element> list = root.elements();

			for (Element e : list) {
				map.put(e.getName(), e.getText());
			}

			return map;
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} finally {
			ins.close();
		}

		return null;
	}
	
	
	public HttpSession getSession(HttpServletRequest request){
		return request.getSession();
	}
	
	public SystemService getSystemService(){
		return systemService;
	}

}
