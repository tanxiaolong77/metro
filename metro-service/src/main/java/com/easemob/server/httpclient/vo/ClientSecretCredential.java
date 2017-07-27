package com.easemob.server.httpclient.vo;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.comm.Roles;
import com.quanjing.util.JsonUtil;



public class ClientSecretCredential extends Credential {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientSecretCredential.class);

	private static URL CLIENTSECRETCREDENTAIL_TOKEN_URL = null;

	@Override
	protected URL getUrl() {
		return CLIENTSECRETCREDENTAIL_TOKEN_URL;
	}

	public ClientSecretCredential(String clientID, String clientSecret, String role) {
		super(clientID, clientSecret);

		if (role.equals(Roles.USER_ROLE_APPADMIN)) {
			CLIENTSECRETCREDENTAIL_TOKEN_URL = EndPoints.TOKEN_APP_URL;
		}
	}

	@Override
	protected GrantType getGrantType() {
		return GrantType.CLIENT_CREDENTIALS;
	}

	@Override
	public Token getToken() {

		if (null == token || token.isExpired()) {
			try {
				Map map = new HashMap();
				map.put("grant_type", "client_credentials");
				map.put("client_id", tokenKey1);
				map.put("client_secret", tokenKey2);
				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));

				HttpPost httpPost = new HttpPost();
				httpPost.setURI(CLIENTSECRETCREDENTAIL_TOKEN_URL.toURI());

                for (NameValuePair nameValuePair : headers) {
                    httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
                }
				httpPost.setEntity(new StringEntity(JsonUtil.toJson(map), "UTF-8"));
				int tryTime = 1;
				HttpResponse tokenResponse = null;
				while (tryTime <= 3) {
					tokenResponse = client.execute(httpPost);
					LOGGER.info("---getToken 第{}次", tryTime);
					if (tokenResponse.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED) {
						tryTime++;
						httpPost.releaseConnection();
					} else {
						break;
					}
				}

				HttpEntity entity = tokenResponse.getEntity();

				String results = EntityUtils.toString(entity, "UTF-8");

				LOGGER.info("---getToken返回结果-------statuscode:" + tokenResponse.getStatusLine().toString());
				LOGGER.info(results);
				if (tokenResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					Map json=JsonUtil.getMap4Json(results);

					String accessToken = (String) json.get("access_token");
					Long expiredAt = System.currentTimeMillis() + (Integer)json.get("expires_in") * 1000;

					token = new Token(accessToken, expiredAt);
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Some errors occurred while fetching a token by username and password .");
			}
		}

		return token;
	}

}
