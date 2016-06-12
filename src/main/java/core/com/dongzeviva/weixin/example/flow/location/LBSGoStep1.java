package com.dongzeviva.weixin.example.flow.location;

import com.dongzeviva.weixin.bean.WeixinMessageFlowDealType;
import com.dongzeviva.weixin.bean.WeixinMessageWrap4Flow;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.bean.message.WeixinReplyTextMessage;
import com.dongzeviva.weixin.flow.AbstractWeixinMessageFlowHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;

public class LBSGoStep1 extends AbstractWeixinMessageFlowHandle {

	private static final long serialVersionUID = 4494447204920988773L;

	public WeixinMessageWrap4Flow doActionImpl(WeixinRmoteSession cache,
			WeixinRemoteMessage message) throws Exception {
		cache.setAttribute("destination", message.getParam());
		WeixinReplyTextMessage replyTextMessage = new WeixinReplyTextMessage("请上传您的地理位置");
		WeixinMessageWrap4Flow flow = new WeixinMessageWrap4Flow();
		flow.setFlowDealType(WeixinMessageFlowDealType.CONTINUE);
		flow.setWeixinMessage(replyTextMessage);
		return flow;
	}

}
