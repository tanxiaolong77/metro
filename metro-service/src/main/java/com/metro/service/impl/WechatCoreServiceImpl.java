/*
 * Powered By [rapid-framework]
 * Since 2008 - 2016
 */

package com.metro.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.metro.event.AutoReplyEvent;
import com.metro.event.PushEvent;
import com.metro.service.WechatCoreService;
import com.metro.util.MessageUtil;


@Service("wechatCoreService")
public class WechatCoreServiceImpl  implements WechatCoreService {
	
	  private static Logger logger = LoggerFactory.getLogger(WechatCoreService.class);
	  

	/**
	   * 处理微信发来的请求
	   * 
	   * @param request
	   * @return
	   */
	public String execute(Map<String, String> param) throws Exception {
		String respMessage = null;
		
		if(null != param){

			// 文本消息
			if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(param.get("MsgType"))) {
				
				respMessage = AutoReplyEvent.instance().execute(param);
			} /*
			 * else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {// 事件推送
			 * String eventType = requestMap.get("Event");// 事件类型
			 * 
			 * if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {// 订阅
			 * respContent = "欢迎关注xxx公众号！"; return
			 * MessageResponse.getTextMessage(fromUserName , toUserName ,
			 * respContent); } else if
			 * (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {// 自定义菜单点击事件 String
			 * eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
			 * logger.info("eventKey is:" +eventKey); return xxx; } } //开启微信声音识别测试
			 * 2015-3-30 else if(msgType.equals("voice")) { String recvMessage =
			 * requestMap.get("Recognition"); //respContent =
			 * "收到的语音解析结果："+recvMessage; if(recvMessage!=null){ respContent =
			 * TulingApiProcess.getTulingResult(recvMessage); }else{ respContent =
			 * "您说的太模糊了，能不能重新说下呢？"; } return
			 * MessageResponse.getTextMessage(fromUserName , toUserName ,
			 * respContent); } //拍照功能 else if(msgType.equals("pic_sysphoto")) {
			 * 
			 * } else { return MessageResponse.getTextMessage(fromUserName ,
			 * toUserName , "返回为空"); }
			 */
			// 事件推送
			else if (MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(param.get("MsgType"))) {
				respMessage = PushEvent.instance().execute(param);
			}
		}
		return respMessage;
	}
}
