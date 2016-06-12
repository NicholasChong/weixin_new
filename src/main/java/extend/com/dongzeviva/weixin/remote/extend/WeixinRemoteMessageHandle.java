package com.dongzeviva.weixin.remote.extend;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteLocationEventMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;

public interface WeixinRemoteMessageHandle {

	public abstract SOAResponseMessage handleRemoteTextMessage(WeixinRmoteSession cache, WeixinRemoteTextMessage remoteTextMessage)throws Exception;
	
	public abstract SOAResponseMessage handleRemoteLocationEventMessage(WeixinRmoteSession cache,WeixinRemoteLocationEventMessage remoteLocationEventMessage)throws Exception;
	
	
}
