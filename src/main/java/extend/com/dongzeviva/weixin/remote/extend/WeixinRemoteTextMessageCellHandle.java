package com.dongzeviva.weixin.remote.extend;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;

public interface WeixinRemoteTextMessageCellHandle {

	
	public abstract SOAResponseMessage handleWeixinRemoteTextMessage(WeixinRmoteSession cache, WeixinRemoteTextMessage remoteTextMessage) throws Exception;
	
	@Deprecated
	public abstract String getHandleName();
	
}
