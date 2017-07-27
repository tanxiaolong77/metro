/*
 * Powered By [rapid-framework]
 * Since 2008 - 2016
 */

package com.metro.event;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TempStorege{
	
	public static ConcurrentMap locations = new ConcurrentHashMap();

	public static String getLocations(String openId) {
		return (String)locations.get(openId);
	}

	public static void setLocations(String openId,String location) {
		locations.put(openId, location);
	}
}
