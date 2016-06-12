package com.dongzeviva.weixin.example.flow;

import com.dongzeviva.weixin.bean.WeixinMessageFlowDealType;
import com.dongzeviva.weixin.bean.WeixinMessageWrap4Flow;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyTextMessage;
import com.dongzeviva.weixin.flow.AbstractWeixinMessageFlowHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;

public class Step1 extends AbstractWeixinMessageFlowHandle {

	private static final long serialVersionUID = 2575971879060869575L;

	public WeixinMessageWrap4Flow doActionImpl(WeixinRmoteSession cache, WeixinRemoteMessage message)
			throws Exception {
		
		WeixinReplyTextMessage replyTextMessage = new WeixinReplyTextMessage("请输入1");
		WeixinMessageWrap4Flow flow = new WeixinMessageWrap4Flow();
		flow.setFlowDealType(WeixinMessageFlowDealType.CONTINUE);
		flow.setWeixinMessage(replyTextMessage);
		return flow;
	}

	
	
}
