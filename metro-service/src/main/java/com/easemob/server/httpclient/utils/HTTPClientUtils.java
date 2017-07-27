package com.easemob.server.httpclient.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.easemob.server.comm.Constants;
import com.easemob.server.comm.HTTPMethod;
import com.easemob.server.httpclient.vo.Credential;
import com.easemob.server.httpclient.vo.Token;
import com.quanjing.util.JsonUtil;




public class HTTPClientUtils {


	/**
	 * Send SSL Request
	 * 
	 * @return
	 */
	public static Map sendHTTPRequest(URL url, Credential credential, String dataBody, String method) {

		HttpClient httpClient = getClient(true);

		Map resObjectNode = new HashMap();

		try {

			HttpResponse response = null;

			if (method.equals(HTTPMethod.METHOD_POST)) {
				HttpPost httpPost = new HttpPost(url.toURI());

				if (credential != null) {
					Token.applyAuthentication(httpPost, credential);
				}
				httpPost.setEntity(new StringEntity(dataBody.toString(), "UTF-8"));

				response = httpClient.execute(httpPost);
			} else if (method.equals(HTTPMethod.METHOD_PUT)) {
				HttpPut httpPut = new HttpPut(url.toURI());
				if (credential != null) {
					Token.applyAuthentication(httpPut, credential);
				}
				httpPut.setEntity(new StringEntity(dataBody.toString(), "UTF-8"));

				response = httpClient.execute(httpPut);
			} else if (method.equals(HTTPMethod.METHOD_GET)) {

				HttpGet httpGet = new HttpGet(url.toURI());
				if (credential != null) {
					Token.applyAuthentication(httpGet, credential);
				}

				response = httpClient.execute(httpGet);

			} else if (method.equals(HTTPMethod.METHOD_DELETE)) {
				HttpDelete httpDelete = new HttpDelete(url.toURI());

				if (credential != null) {
					Token.applyAuthentication(httpDelete, credential);
				}

				response = httpClient.execute(httpDelete);
			}

			HttpEntity entity = response.getEntity();
			if (null != entity) {
				String responseContent = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);

				resObjectNode = JsonUtil.getMap4Json(responseContent);
				resObjectNode.put("statusCode", response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

		return resObjectNode;
	}

	
	/**
	 * Create a httpClient instance
	 * 
	 * @param isSSL
	 * @return HttpClient instance
	 */
	public static HttpClient getClient(boolean isSSL) {

		HttpClient httpClient = new DefaultHttpClient();
		if (isSSL) {
			X509TrustManager xtm = new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};

			try {
				SSLContext ctx = SSLContext.getInstance("TLS");

				ctx.init(null, new TrustManager[] { xtm }, null);

				SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);

				httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory));

			} catch (Exception e) {
				throw new RuntimeException();
			}
		}

		return httpClient;
	}

	public static URL getURL(String path) {
		URL url = null;

		try {
			url = new URL(Constants.API_HTTP_SCHEMA, Constants.API_SERVER_HOST, "/" + path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		return url;
	}

	/**
	 * Check illegal String
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);

		return matcher.lookingAt();
	}
}
