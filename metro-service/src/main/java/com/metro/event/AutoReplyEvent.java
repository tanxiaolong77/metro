/*
 * Powered By [rapid-framework]
 * Since 2008 - 2016
 */

package com.metro.event;

import java.util.Date;
import java.util.Map;

import com.metro.model.TextMessage;
import com.metro.util.MessageUtil;

public class AutoReplyEvent  implements Event{
	
	private AutoReplyEvent(){}
	
	private static AutoReplyEvent event;
	
	public static AutoReplyEvent instance(){
		if(null == event){
			event = new AutoReplyEvent();
		}
		return event;
	}

	public String execute(Map<String, String> param) throws Exception {
		// 发送方帐号（open_id）
		String fromUserName = param.get("FromUserName");
		// 公众帐号
		String toUserName = param.get("ToUserName");
		// 消息类型
		String msgType = param.get("MsgType");
		// 消息内容
		String content = param.get("Content");
		// 这里根据关键字执行相应的逻辑，只有你想不到的，没有做不到的 自动回复
		if (content.equals("xxx")) {

		}
		
		TextMessage text = new TextMessage();
		text.setContent("哎哟胖妞你又来啦");
		text.setToUserName(fromUserName);
		text.setFromUserName(toUserName);
		text.setCreateTime(new Date().getTime() + "");
		text.setMsgType(msgType);

		return MessageUtil.textMessageToXml(text);
	
	}
}
