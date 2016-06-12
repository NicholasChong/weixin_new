package com.dongzeviva.weixin.example.flow;

import com.dongzeviva.weixin.bean.WeixinMessageFlowDealType;
import com.dongzeviva.weixin.bean.WeixinMessageWrap4Flow;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyTextMessage;
import com.dongzeviva.weixin.flow.AbstractWeixinMessageFlowHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;

public class Step3 extends AbstractWeixinMessageFlowHandle{

	private static final long serialVersionUID = 1442073882707215582L;

	public WeixinMessageWrap4Flow doActionImpl(WeixinRmoteSession cache, WeixinRemoteMessage message)
			throws Exception {
		WeixinRemoteTextMessage textMessage = (WeixinRemoteTextMessage)message;
		String content = textMessage.getContent();
		WeixinMessageWrap4Flow flow = new WeixinMessageWrap4Flow();
		if ("2".equals(content)) {
			WeixinReplyTextMessage replyTextMessage = new WeixinReplyTextMessage("流程测试结束");
			flow.setFlowDealType(WeixinMessageFlowDealType.END);
			flow.setWeixinMessage(replyTextMessage);
		}else{
			WeixinReplyTextMessage replyTextMessage = new WeixinReplyTextMessage("请输入2");
			flow.setFlowDealType(WeixinMessageFlowDealType.HOLD);
			flow.setWeixinMessage(replyTextMessage);
		}
		return flow;
	}
	
}
