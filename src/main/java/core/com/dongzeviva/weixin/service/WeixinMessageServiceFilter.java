package com.dongzeviva.weixin.service;

import com.dongzeviva.weixin.bean.message.WeixinMessage;
import com.dongzeviva.weixin.session.WeixinSession;

/**
 * 消息内容过滤 
 *
 */
public interface WeixinMessageServiceFilter {

	/**
	 * 对消息内容进行过滤处理
	 * @param session
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public abstract WeixinMessage doFilter(WeixinSession session, WeixinMessage message)
			throws Exception;

}
