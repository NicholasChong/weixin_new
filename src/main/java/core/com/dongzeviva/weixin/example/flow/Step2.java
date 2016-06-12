package com.dongzeviva.weixin.example.flow;

import com.dongzeviva.weixin.bean.WeixinMessageFlowDealType;
import com.dongzeviva.weixin.bean.WeixinMessageWrap4Flow;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyTextMessage;
import com.dongzeviva.weixin.flow.AbstractWeixinMessageFlowHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;

public class Step2 extends AbstractWeixinMessageFlowHandle {

	private static final long serialVersionUID = -6211299807252262184L;

	public WeixinMessageWrap4Flow doActionImpl(WeixinRmoteSession cache, WeixinRemoteMessage message)
			throws Exception {
		WeixinRemoteTextMessage textMessage = (WeixinRemoteTextMessage)message;
		String content = textMessage.getContent();
		WeixinMessageWrap4Flow flow = new WeixinMessageWrap4Flow();
		if ("1".equals(content)) {
			WeixinReplyTextMessage replyTextMessage = new WeixinReplyTextMessage("请输入2");
			flow.setFlowDealType(WeixinMessageFlowDealType.CONTINUE);
			flow.setWeixinMessage(replyTextMessage);
		}else{
			WeixinReplyTextMessage replyTextMessage = new WeixinReplyTextMessage("请输入1");
			flow.setFlowDealType(WeixinMessageFlowDealType.HOLD);
			flow.setWeixinMessage(replyTextMessage);
		}
		return flow;
	}
	
}
