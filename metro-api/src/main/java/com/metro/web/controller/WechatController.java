package com.metro.web.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metro.service.WechatCoreService;
import com.metro.util.SignUtil;


/***
 * 微信接入
* @Title: WechatController.java
* @Company Beijing Panorama Media Inc.
* @author xiaolong.Tan 
* @date 2017年4月6日 上午11:38:42
 */
@Controller
@RequestMapping("/wechat")
public class WechatController extends BaseController{
	
	
  private String DNBX_TOKEN = "tanxiaolong";
   
  private static Logger logger = LoggerFactory.getLogger(WechatController.class);
   
  @Autowired
  private WechatCoreService wechatService;
   
  /**
   * 微信接入
   * @param wc
   * @return
   * @throws IOException 
   */
  @ResponseBody
  @RequestMapping(value = "connect")
  public void connectWeixin(HttpServletRequest request, HttpServletResponse response) throws IOException{

		PrintWriter out = response.getWriter();
      
		try {
			if ("GET".equals(request.getMethod())) {
				String signature = request.getParameter("signature");// 微信加密签名
				String timestamp = request.getParameter("timestamp");// 时间戳
				String nonce = request.getParameter("nonce");// 随机数
				String echostr = request.getParameter("echostr");// 随机字符串

				// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
				if (SignUtil.checkSignature(DNBX_TOKEN, signature, timestamp,
						nonce)) {
					logger.info("Connect the weixin server is successful.");
					response.getWriter().write(echostr);
				} else {
					logger.error("Failed to verify the signature!");
				}
			} else {
				String respMessage = "异常消息！";

				try {
					respMessage = wechatService.execute(xmlToMap(request));
					out.write(respMessage);
					logger.info("The request completed successfully");
					logger.info("to weixin server " + respMessage);
				} catch (Exception e) {
					logger.error("Failed to convert the message from weixin!");
				}

			}
		} catch (Exception e) {
			logger.error("Connect the weixin server is error.");
		} finally {
			out.close();
		}
	}
  
}
