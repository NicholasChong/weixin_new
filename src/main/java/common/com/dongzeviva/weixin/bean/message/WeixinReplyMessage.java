package com.dongzeviva.weixin.bean.message;

import com.dongzeviva.weixin.flow.WeixinMessageFlowLocalService;
import com.dongzeviva.weixin.service.WeixinMessageHandle;
import com.dongzeviva.weixin.session.WeixinSession;

public abstract class WeixinReplyMessage extends BasicWeixinMessage{

	private static final long serialVersionUID = -8983873190563203103L;

	public WeixinMessage doService(WeixinSession session, WeixinMessageHandle messageHandle,
			WeixinMessageFlowLocalService flowLocalService) throws Exception {
		return this;
	}
	
	public WeixinMessage doService(WeixinSession session, WeixinMessageHandle messageHandle)
			throws Exception {
		return this;
	}
	
	public <T extends WeixinRemoteMessage> T copyWeixinRemoteMessage() {
		throw new RuntimeException("not will walk here");
	}
}
