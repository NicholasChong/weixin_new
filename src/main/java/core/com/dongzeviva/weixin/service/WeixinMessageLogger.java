package com.dongzeviva.weixin.service;

import com.dongzeviva.weixin.Initable;
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

public interface WeixinMessageLogger extends Initable {

	public abstract void logConversation(WeixinSession session,WeixinMessage request, WeixinMessage response) throws Exception;
	
	public abstract void logRequestDefaultMessage(WeixinSession session, WeixinMessage request) throws Exception;

	public abstract void logResponseDefaultMessage(WeixinSession session, WeixinMessage response) throws Exception;

	public abstract void logRequestTextMessage(WeixinSession session, WeixinTextMessage message) throws Exception;

	public abstract void logRequestImageMessage(WeixinSession session, WeixinImageMessage message) throws Exception;

	public abstract void logRequestVoiceMessage(WeixinSession session, WeixinVoiceMessage message) throws Exception;

	public abstract void logRequestVideoMessage(WeixinSession session, WeixinVideoMessage message) throws Exception;

	public abstract void logRequestLocationMessage(WeixinSession session, WeixinLocationMessage message) throws Exception;

	public abstract void logRequestLocationEventMessage(WeixinSession session, WeixinLocationEventMessage message) throws Exception;

	public abstract void logRequestMenuEventMessage(WeixinSession session, WeixinEventMessage message) throws Exception;
	
	public abstract void logRequestChainingMessage(WeixinSession session, WeixinChainingMessage message) throws Exception;

	public abstract void logResponseTextMessage(WeixinSession session, WeixinReplyTextMessage message) throws Exception;

	public abstract void logResponseImageMessage(WeixinSession session, WeixinReplyImageMessage message) throws Exception;

	public abstract void logResponseVoiceMessage(WeixinSession session, WeixinReplyVoiceMessage message) throws Exception;

	public abstract void logResponseVideoMessage(WeixinSession session, WeixinReplyVideoMessage message) throws Exception;

	public abstract void logResponseMusicMessage(WeixinSession session, WeixinReplyMusicMessage message) throws Exception;

	public abstract void logResponseNewsMessage(WeixinSession session, WeixinReplyNewsMessage message) throws Exception;

	public abstract void logResponseWasteMessage(WeixinSession session, WeixinReplyWasteMessage message) throws Exception;
	

	
}