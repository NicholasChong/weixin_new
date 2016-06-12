package com.dongzeviva.weixin.remote;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.session.WeixinSession;

/**
 * 用于远程调用第三方组件处理用户发来的指令
 *
 */
public interface WeixinRemoteService {

	/**
	 * 处理服务
	 * @param session
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public SOAResponseMessage doService(WeixinSession session, WeixinRemoteMessage message)throws Exception;
	
}
