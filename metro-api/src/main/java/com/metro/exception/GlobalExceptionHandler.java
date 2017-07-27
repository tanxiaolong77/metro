package com.metro.exception;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.metro.util.Constant;
import com.metro.util.CookieUtil;
import com.quanjing.util.EmailUtil;
import com.quanjing.util.IPUtil;

public class GlobalExceptionHandler implements HandlerExceptionResolver {

	private Logger logg = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private String[] receive;

	public void setReceive(String[] receive) {
		this.receive = receive;
	}

	private String getExceptionAllinformation(Exception ex) {
		String sOut = ex.getMessage();
		StackTraceElement[] trace = ex.getStackTrace();
		for (StackTraceElement s : trace) {
			sOut += "\t " + s + "\r\n";
		}
		return sOut;
	}

	

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		String ticket = CookieUtil.getValue(request, Constant.Ticket);

		String info = getExceptionAllinformation(ex);

		logg.error("ticket=" + ticket + "\r\n" + info);
		ex.printStackTrace();
		if(ex.getStackTrace()[0].getClassName().startsWith("org.apache.catalina")||ex.getStackTrace()[0].getClassName().startsWith("org.apache.tomcat")){
			return null;
		}
		if (receive != null) {
			StringBuilder sb = new StringBuilder("");
			for (String key : request.getParameterMap().keySet()) {
				sb.append(key + "=" + request.getParameter(key) + "</br>");
			}
			Enumeration<String> headers= request.getHeaderNames();
			while (headers.hasMoreElements()) {
				String headerKey=headers.nextElement();
				sb.append(headerKey + "=" + request.getHeader(headerKey) + "</br>");
			}
			EmailUtil.getInstance().send(IPUtil.getLocalIp4()+"-地铁系统错误"+request.getServletPath(),
					"url=" +request.getServletPath()+"<br/>参数:<br/>ticket=" + ticket + "<br/>"+ sb.toString() + "<br/><hr/>" + info, receive);
		}

		return null;
	}

}
