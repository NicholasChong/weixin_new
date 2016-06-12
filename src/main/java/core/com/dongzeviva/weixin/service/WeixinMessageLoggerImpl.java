package com.dongzeviva.weixin.service;

import com.dongzeviva.weixin.Initable;
import com.dongzeviva.weixin.WeixinContext;
import org.apache.log4j.Logger;

import com.dongzeviva.weixin.bean.message.WeixinChainingMessage;
import com.dongzeviva.weixin.bean.message.WeixinEventMessage;
import com.dongzeviva.weixin.bean.message.WeixinImageMessage;
import com.dongzeviva.weixin.bean.message.WeixinLocationEventMessage;
import com.dongzeviva.weixin.bean.message.WeixinLocationMessage;
import com.dongzeviva.weixin.bean.message.WeixinMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyImageMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyMusicMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyNewsMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyTextMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyVideoMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyVoiceMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyWasteMessage;
import com.dongzeviva.weixin.bean.message.WeixinTextMessage;
import com.dongzeviva.weixin.bean.message.WeixinVideoMessage;
import com.dongzeviva.weixin.bean.message.WeixinVoiceMessage;
import com.dongzeviva.weixin.session.WeixinSession;

public class WeixinMessageLoggerImpl implements WeixinMessageLogger, Initable {

	private static final Logger LOGGER = Logger.getLogger(WeixinMessageLoggerImpl.class);

	public void logConversation(WeixinSession session, WeixinMessage request, WeixinMessage response) throws Exception {
		request.logMessage(session, this);
		response.logMessage(session, this);
	}
	
	public void logRequestDefaultMessage(WeixinSession session, WeixinMessage request) {
		LOGGER.info("request message:" + request.toString());
	}
	
	public void logResponseDefaultMessage(WeixinSession session, WeixinMessage response) {
		LOGGER.info("reply   message:" + response.toString());
	}

	public void initialize(WeixinContext context) throws Exception {

	}

	public void shoutdown(WeixinContext context) throws Exception {

	}

	// ******************************      log request message   ******************************//
	
	public void logRequestTextMessage(WeixinSession session,WeixinTextMessage message) throws Exception {
		this.logRequestDefaultMessage(session, message);
	}

	public void logRequestImageMessage(WeixinSession session,WeixinImageMessage message) throws Exception {
		this.logRequestDefaultMessage(session, message);
	}

	public void logRequestVoiceMessage(WeixinSession session,WeixinVoiceMessage message) throws Exception {
		this.logRequestDefaultMessage(session, message);
	}

	public void logRequestVideoMessage(WeixinSession session,WeixinVideoMessage message) throws Exception {
		this.logRequestDefaultMessage(session, message);
	}

	public void logRequestLocationMessage(WeixinSession session,WeixinLocationMessage message) throws Exception {
		this.logRequestDefaultMessage(session, message);
	}

	public void logRequestLocationEventMessage(WeixinSession session,WeixinLocationEventMessage message) throws Exception {
		this.logRequestDefaultMessage(session, message);
	}
	
	public void logRequestMenuEventMessage(WeixinSession session,WeixinEventMessage message) throws Exception {
		this.logRequestDefaultMessage(session, message);
	}
	
	public void logRequestChainingMessage(WeixinSession session, WeixinChainingMessage message) throws Exception {
		this.logRequestDefaultMessage(session, message);
	}
	
	// ******************************      log reply message   ******************************//
	

	public void logResponseTextMessage(WeixinSession session,WeixinReplyTextMessage message) throws Exception {
		this.logResponseDefaultMessage(session, message);
	}

	public void logResponseImageMessage(WeixinSession session,WeixinReplyImageMessage message) throws Exception {
		this.logResponseDefaultMessage(session, message);
	}

	public void logResponseVoiceMessage(WeixinSession session,WeixinReplyVoiceMessage message) throws Exception {
		this.logResponseDefaultMessage(session, message);
	}

	public void logResponseVideoMessage(WeixinSession session,WeixinReplyVideoMessage message) throws Exception {
		this.logResponseDefaultMessage(session, message);
	}

	public void logResponseMusicMessage(WeixinSession session,WeixinReplyMusicMessage message) throws Exception {
		this.logResponseDefaultMessage(session, message);
	}

	public void logResponseNewsMessage(WeixinSession session,WeixinReplyNewsMessage message) throws Exception {
		this.logResponseDefaultMessage(session, message);
	}
	
	public void logResponseWasteMessage(WeixinSession session,WeixinReplyWasteMessage message) throws Exception {
		this.logResponseDefaultMessage(session, message);
	}

}
