/*
 * Powered By [rapid-framework]
 * Since 2008 - 2016
 */

package com.metro.event;

import java.util.Map;

public interface Event{
	
	String execute(Map<String, String> param)throws Exception;
}
