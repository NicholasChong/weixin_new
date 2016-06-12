package com.dongzeviva.weixin.open.handle;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;

/**
 * 
 * 测试用的
 *
 */
public class TestWeixinHandle extends AbstractWeixinSOAServieHandle  {

	public static String KEY_NAME = null;
	
	public SOAResponseMessage handle(WeixinContext appContext, SOARequestMessage requestMessage)
			throws Exception {
		
		return SOAResponseMessage.SUCCESS;
	}
}
