/*
 * Powered By [rapid-framework]
 * Since 2008 - 2016
 */

package com.metro.event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.metro.model.TextMessage;
import com.metro.util.MessageUtil;
import com.quanjing.util.HttpClientUtil;
import com.quanjing.util.JsonUtil;


public class PushEvent  implements Event{
	
	private PushEvent(){}
	
	private static PushEvent event;
	
	public static PushEvent instance(){
		if(null == event){
			event = new PushEvent();
		}
		return event;
	}

	public String execute(Map<String, String> param) throws Exception {

		// 发送方帐号（open_id）
		String fromUserName = param.get("FromUserName");
		// 公众帐号
		String toUserName = param.get("ToUserName");
		
		String eventType = param.get("Event");// 事件类型
		
		String result = "";
		
		// 订阅
		if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {

			TextMessage text = new TextMessage();
			text.setContent("胖子你终于关注啦 等你半天了 哈哈哈");
			text.setToUserName(fromUserName);
			text.setFromUserName(toUserName);
			text.setCreateTime(new Date().getTime() + "");
			text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			result = MessageUtil.textMessageToXml(text);
		}
		// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
		else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {// 取消订阅

		} else if (eventType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {// 地理位置
			
			Map paramMap = new HashMap();
			paramMap.put("callback","renderReverse");
			paramMap.put("location",""+param.get("Latitude")+","+param.get("Longitude")+"");
			paramMap.put("output","json");
			paramMap.put("pois","0");
			paramMap.put("ak","iMump67QQfotDkRTYnR1eGZqSn8pIM58");
			String res = HttpClientUtil.sendHttpRequest("get","http://api.map.baidu.com/geocoder/v2/", paramMap);
			res = res.substring(29, res.length()-1);
			JSONObject json = JsonUtil.getObject(res);
			json = JsonUtil.getObject(json.getString("result"));
			TempStorege.setLocations(fromUserName,json.getString("formatted_address"));
		}
		// 自定义菜单点击事件
		else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
			String eventKey = param.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
			if (eventKey.equals("WITH_US")) {
				
				Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("2016-11-10");
				Date currentDate = new Date();
				long disTime = currentDate.getTime() - startDate.getTime();
				
				TextMessage text = new TextMessage();
				text.setContent("好好好 可以可以可以"+disTime / 1000 / 60 / 60 / 24+"天之痒~");
				text.setToUserName(fromUserName);
				text.setFromUserName(toUserName);
				text.setCreateTime(new Date().getTime() + "");
				text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				result = MessageUtil.textMessageToXml(text);
			}else if(eventKey.equals("WHERE")){
				
				TextMessage text = new TextMessage();
				text.setContent("（偷窥中）你老公当前位置在："+TempStorege.getLocations("on_dZwpCex8NmQNal562Q7GZpFgI"));
				text.setToUserName(fromUserName);
				text.setFromUserName(toUserName);
				text.setCreateTime(new Date().getTime() + "");
				text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				result = MessageUtil.textMessageToXml(text);
			}else if(eventKey.equals("WHERE_WLFE")){
				
				TextMessage text = new TextMessage();
				text.setContent("（明目张胆观察中）老婆当前位置在："+TempStorege.getLocations("on_dZwuKVMLsdUAqRJbebiUA7wZQ"));
				text.setToUserName(fromUserName);
				text.setFromUserName(toUserName);
				text.setCreateTime(new Date().getTime() + "");
				text.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				result = MessageUtil.textMessageToXml(text);
			}
		}
		return result;
	}
}
