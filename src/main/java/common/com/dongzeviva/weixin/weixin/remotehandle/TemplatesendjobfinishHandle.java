package com.dongzeviva.weixin.weixin.remotehandle;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteTextMessageCellHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;

public class TemplatesendjobfinishHandle implements WeixinRemoteTextMessageCellHandle{

	@Override
	public SOAResponseMessage handleWeixinRemoteTextMessage(
			WeixinRmoteSession cache, WeixinRemoteTextMessage remoteTextMessage)
			throws Exception {
		WeixinMessage eventMessage = remoteTextMessage.getEventMessage();
		//根据返回的内容判断是否需要重新发送，需要实现定时重发机制
		return null;
	}
	@Override
	public String getHandleName() {
		return "Templatesendjobfinish";
	}

}
