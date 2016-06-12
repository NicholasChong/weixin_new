package com.dongzeviva.weixin.service;

import com.dongzeviva.weixin.bean.message.WeixinChainingMessage;
import com.dongzeviva.weixin.bean.message.WeixinEventMessage;
import com.dongzeviva.weixin.bean.message.WeixinImageMessage;
import com.dongzeviva.weixin.bean.message.WeixinLocationEventMessage;
import com.dongzeviva.weixin.bean.message.WeixinLocationMessage;
import com.dongzeviva.weixin.bean.message.WeixinMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteChainingMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteImageMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteLocationEventMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteLocationMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteVideoMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteVoiceMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyNewsMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyTextMessage;
import com.dongzeviva.weixin.bean.message.WeixinTextMessage;
import com.dongzeviva.weixin.bean.message.WeixinVideoMessage;
import com.dongzeviva.weixin.bean.message.WeixinVoiceMessage;
import com.dongzeviva.weixin.session.WeixinSession;

/**
 * 
 * 处理对应类型的消息
 * 
 */
public interface WeixinMessageHandle {

	public abstract WeixinMessage handleClickEventMessage(WeixinSession session,WeixinEventMessage eventMessage) throws Exception;

	public abstract WeixinMessage handleWeixinTextMessage(WeixinSession session,WeixinTextMessage textMessage) throws Exception;

	public abstract WeixinMessage handleWeixinChainingMessage(WeixinSession session,WeixinChainingMessage chainingMessage) throws Exception;

	public abstract WeixinMessage handleWeixinEventMessage(WeixinSession session,WeixinEventMessage eventMessage) throws Exception;

	public abstract WeixinMessage handleWeixinLocationEventMessage(WeixinSession session,WeixinLocationEventMessage locationEventMessage) throws Exception;

	public abstract WeixinMessage handleWeixinImageMessage(WeixinSession session,WeixinImageMessage imageMessage) throws Exception;

	public abstract WeixinMessage handleWeixinLocationMessage(WeixinSession session,WeixinLocationMessage locationMessage) throws Exception;

	public abstract WeixinMessage handleWeixinRemoteChainingMessage(WeixinSession session,WeixinRemoteChainingMessage remoteChainingMessage) throws Exception;

	public abstract WeixinMessage handleWeixinRemoteLocationEventMessage(WeixinSession session,WeixinRemoteLocationEventMessage remoteLocationEventMessage) throws Exception;

	public abstract WeixinMessage handleWeixinRemoteImageMessage(WeixinSession session,WeixinRemoteImageMessage remoteImageMessage) throws Exception;

	public abstract WeixinMessage handleWeixinRemoteLocationMessage(WeixinSession session,WeixinRemoteLocationMessage remoteLocationMessage) throws Exception;

	public abstract WeixinMessage handleWeixinRemoteTextMessage(WeixinSession session,WeixinRemoteTextMessage remoteTextMessage) throws Exception;

	public abstract WeixinMessage handleWeixinRemoteVideoMessage(WeixinSession session,WeixinRemoteVideoMessage remoteVideoMessage) throws Exception;

	public abstract WeixinMessage handleWeixinRemoteVoiceMessage(WeixinSession session,WeixinRemoteVoiceMessage remoteVoiceMessage) throws Exception;

	public abstract WeixinMessage handleWeixinReplyNewsMessage(WeixinSession session,WeixinReplyNewsMessage replyNewsMessage) throws Exception;

	public abstract WeixinMessage handleWeixinReplyTextMessage(WeixinSession session,WeixinReplyTextMessage replyTextMessage) throws Exception;

	public abstract WeixinMessage handleWeixinSubscribeEventMessage(WeixinSession session,WeixinEventMessage eventMessage) throws Exception;

	public abstract WeixinMessage handleWeixinUNSubscribeEventMessage(WeixinSession session,WeixinEventMessage eventMessage) throws Exception;

	public abstract WeixinMessage handleWeixinVideoMessage(WeixinSession session,WeixinVideoMessage videoMessage) throws Exception;

	public abstract WeixinMessage handleWeixinVoiceMessage(WeixinSession session,WeixinVoiceMessage voiceMessage) throws Exception;

}
