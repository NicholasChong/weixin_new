package com.dongzeviva.weixin.open.handle;

import java.net.URLDecoder;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinApiResult;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.service.WeixinApiInvoker;

/**
 * 
 * 发送客服消息
 *
 */
public class SendWeixinMessageHandle extends AbstractWeixinSOAServieHandle  {
	
	public SOAResponseMessage handle(WeixinContext context, SOARequestMessage requestMessage)
			throws Exception {
		String uname = requestMessage.getUname();
		WeixinPublicNumber weixinPublicNumber = context.getWeixinPublicNumber(uname);
		if (weixinPublicNumber == null) {
			return new SOAResponseMessage(1000,"unknow uname:"+uname);
		}
		WeixinApiInvoker apiInvoker = context.getWeixinApiInvoker();
		WeixinApiResult result = apiInvoker.sendMessage(weixinPublicNumber.genAccess_token(context)
				, URLDecoder.decode(requestMessage.getStringParam(), "UTF-8"));
		if (result.getErrcode() == 0) {
			return SOAResponseMessage.SUCCESS;
		}else{
			return new SOAResponseMessage(1000, result.getErrmsg());
		}
	}

}
