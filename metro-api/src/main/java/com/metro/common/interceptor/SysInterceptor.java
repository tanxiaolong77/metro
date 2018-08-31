package com.metro.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.metro.model.User;
import com.metro.util.SessionUtils;

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
			response.sendRedirect(ctx + "/syslogin");
			return false;
		} else {
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null){
			response.sendRedirect("/error");
		}
	}

}
