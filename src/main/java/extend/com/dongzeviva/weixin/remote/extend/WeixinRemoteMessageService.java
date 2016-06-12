package com.dongzeviva.weixin.remote.extend;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;

/**
 * 提供给第三方处理远程的微信消息
 */
public interface WeixinRemoteMessageService {

	
	/**
	 * 处理消息
	 * @param cache
	 * @param remoteMessage
	 * @return
	 * @throws Exception
	 */
	public SOAResponseMessage doService(WeixinRmoteSession cache,WeixinRemoteMessage remoteMessage) throws Exception;

	
}
