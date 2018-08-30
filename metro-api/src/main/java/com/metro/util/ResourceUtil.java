package com.metro.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * @author 鸵鸟
 * 
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("application");
	static{
		if(bundle==null){
			System.out.println("未找到配置文件");
		}
	}
	private ResourceUtil() {
		
	}
	public static final String getPropertyValue(String queryStr){
		return bundle.getString(queryStr);
	}


	
}
