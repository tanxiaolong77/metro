package com.easemob.server.httpclient.vo;

import java.net.URL;

import com.easemob.server.comm.Constants;
import com.easemob.server.httpclient.utils.HTTPClientUtils;


public interface EndPoints {

	static final URL TOKEN_APP_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/token");

	static final URL USERS_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users");

	static final URL MESSAGES_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/messages");

	static final URL CHATGROUPS_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatgroups");

	static final URL CHATFILES_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatfiles");
	// 微图
	static final URL USERS_URL2 = HTTPClientUtils.getURL(Constants.APPKEY2.replace("#", "/") + "/users");

	static final URL TOKEN_APP_URL2 = HTTPClientUtils.getURL(Constants.APPKEY2.replace("#", "/") + "/token");
}
