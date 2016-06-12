package com.dongzeviva.weixin.example.jar;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteTextMessageCellHandle;

public class WeixinDaiban implements WeixinRemoteTextMessageCellHandle{

	public SOAResponseMessage handleWeixinRemoteTextMessage(WeixinRmoteSession cache,
			WeixinRemoteTextMessage remoteTextMessage) throws Exception {
		return new SOAResponseMessage(0, "审批完成");
	}

	public String getHandleName() {
		return "审批";
	}
	
	
}
