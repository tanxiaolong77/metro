package com.metro.util;

import com.metro.vo.UserShow;

public class ThreadLocalManage {

	/** 线程内共享 ThreadLocal通常是全局的，支持泛型 */  
    private static ThreadLocal<UserShow> threadUser = new ThreadLocal<UserShow>();  
    
    private static ThreadLocal<String> threadUrl = new ThreadLocal<String>();  
    
    private static ThreadLocal<Long> accessTime = new ThreadLocal<Long>();  
    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();  
    
//    public final static String SESSION_FACTORY_MYSQL = "mysql";
//    
//    public final static String SESSION_FACTORY_POSTGRESQL = "postgresql";
    
    public static void remove(){
    	threadUser.remove();
    	threadUrl.remove();
    	accessTime.remove();
    }
    
    public static String getUrl() {
		return threadUrl.get();
	}

	public static void setUrl(String threadUrl) {
		ThreadLocalManage.threadUrl.set(threadUrl);
	}

	public static void setUser(UserShow u){
    	threadUser.set(u);
    }
    
    public static UserShow getUser(){
    	return threadUser.get();
    }
    
    public static Long getAccessTime(){
    	return accessTime.get();
    }
    
    public static void setAccessTime(Long time){
    	 accessTime.set(time);
    }
    
    
    public static void setContextType(String contextType) {  
        contextHolder.set(contextType);  
    }  
      
    public static String getContextType() {  
        return contextHolder.get();  
    }  
      
    public static void clearContextType() {  
        contextHolder.remove();  
    }
    
//    public static void changeContextHolder(){
//    	if (contextHolder.get().equals(SESSION_FACTORY_POSTGRESQL)) {
//    		contextHolder.set(SESSION_FACTORY_MYSQL);
//		}else {
//			contextHolder.set(SESSION_FACTORY_MYSQL);
//		}
//    }
}
