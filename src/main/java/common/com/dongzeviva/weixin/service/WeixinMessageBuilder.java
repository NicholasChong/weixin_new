package com.dongzeviva.weixin.service;

import com.dongzeviva.weixin.bean.message.WeixinMessage;

/**
 * 
 * 文本消息转成消息对象
 *
 */
public interface WeixinMessageBuilder {

	/**
	 * 文本消息转成消息对象
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public abstract WeixinMessage buildMessage(String content) throws Exception;

}
