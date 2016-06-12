package com.dongzeviva.weixin.bean.message;

import com.dongzeviva.weixin.service.WeixinMessageHandle;
import com.dongzeviva.weixin.service.WeixinMessageLogger;
import com.dongzeviva.weixin.session.WeixinSession;

public class WeixinReplyTextMessage extends WeixinReplyMessage implements WeixinMessage {

	private static final long serialVersionUID = 4897623804809376724L;
	
	public WeixinReplyTextMessage(){
		this.setMsgType(TEXT);
		this.setReply(true);
	}

	public WeixinReplyTextMessage(String content) {
		this.content = content;
		this.setMsgType(TEXT);
		this.setReply(true);
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WeixinMessage doService(WeixinSession session, WeixinMessageHandle messageHandle)
			throws Exception {
		return messageHandle.handleWeixinReplyTextMessage(session, this);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends WeixinMessage> T copyMessage() {
		return (T)new WeixinReplyTextMessage(this.content);
	}

	public void logMessage(WeixinSession session, WeixinMessageLogger logger) throws Exception {
		logger.logResponseTextMessage(session, this);
	}
	
	
	
	
}
