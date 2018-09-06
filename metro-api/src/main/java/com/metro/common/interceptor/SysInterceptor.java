package com.metro.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.metro.model.User;
import com.metro.util.ResourceUtil;
import com.metro.util.SessionUtils;
import com.metro.web.controller.BaseController;

public class SysInterceptor implements HandlerInterceptor {

	/*
	 * private Set<String> ignorePath = new HashSet<String>
	 * (Arrays.asList("/login", "/captcha", "/notlogin", "/ErrorHandler"));
	 */

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String uri = request.getRequestURI(); // 请求路径
		String ctx = request.getContextPath();
		String path = uri.replace(ctx, "");

		User sessionUser = SessionUtils.getLoginManager();

		if (sessionUser == null) { // 转到登陆页面
			response.sendRedirect(ctx + "/m");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if(modelAndView != null && modelAndView.getModel() != null){
			String server = ResourceUtil.getPropertyValue("server.name");
			if(StringUtils.isBlank(server)){
				server = BaseController.getServerIp();
			}
			int port = request.getServerPort();
			String portStr = "";
			if(port != 80){
				portStr = ":"+port;
				server += portStr;
			}
				
			modelAndView.getModel().put("server",server);
		}
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
