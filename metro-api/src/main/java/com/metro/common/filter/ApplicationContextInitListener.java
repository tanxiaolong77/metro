package com.metro.common.filter;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;


@Component
public class ApplicationContextInitListener 
	implements ApplicationListener<ContextRefreshedEvent>, ServletContextAware {

	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private ServletContext servletContext;
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		ApplicationContext parentContext = ((ContextRefreshedEvent) event)
				.getApplicationContext().getParent();
		
		// 子容器初始化时(spring-mvc)
		if (null != parentContext) {
			
			String ctxPath = servletContext.getContextPath();
			
			logger.info("根路径:"+ctxPath);
			
		}
	}
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}