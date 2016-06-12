package com.dongzeviva.weixin.open;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.WeixinContextFactory;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;

/**
 * 提供Local方式的API
 *
 */
public class WeixinSOALocalDispatch{

	public static SOAResponseMessage doDispatch(SOARequestMessage requestMessage) throws Exception{
		WeixinContext context = WeixinContextFactory.getApplicationContext();
		WeixinSOAService weixinSOAService = context.getWeixinSOAService();
		return weixinSOAService.doService(context, requestMessage);
	}

}