package com.dongzeviva.weixin.service;

import org.apache.log4j.Logger;

import com.dongzeviva.weixin.bean.message.WeixinMessage;
import com.dongzeviva.weixin.session.WeixinSession;

public class Log4jWeixinMessageLogger extends WeixinMessageLoggerImpl{

	private static final Logger LOGGER = Logger.getLogger(Log4jWeixinMessageLogger.class);
	
	public void logRequestDefaultMessage(WeixinSession session, WeixinMessage request) {
		LOGGER.info("request message:" + request.toString());
	}

	public void logResponseDefaultMessage(WeixinSession session, WeixinMessage response) {
		LOGGER.info("reply   message:" + response.toString());
	}
	
	
}
