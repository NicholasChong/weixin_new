package com.dongzeviva.weixin.open.handle;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.common.APIURL;
import com.dongzeviva.weixin.service.WeixinApiInvoker;

/**
 * 
 * 创建永久二维码
 * 
 */
public class GetPermanentQRCode extends AbstractWeixinSOAServieHandle{

	@Override
	public SOAResponseMessage handle(WeixinContext context,
									 SOARequestMessage message) throws Exception {
		String uname = message.getUname();
		WeixinPublicNumber publicNumber = context.getWeixinPublicNumber(uname);
		if(publicNumber == null){
			return new SOAResponseMessage(1000, "unknow uname:"+uname);
		}
		WeixinApiInvoker invoker = context.getWeixinApiInvoker();
		String access_token = publicNumber.genAccess_token(context);
		String scene_info = message.getStringParam();
		String result = invoker.getPermanentQRCode(access_token,scene_info);
		JSONObject resultJson = JSONObject.parseObject(result);
		if(resultJson.get("errcode") == null){
			return new SOAResponseMessage(0, APIURL.GET_QRCODE_URL+resultJson.getString("ticket"));
		}else{
			return new SOAResponseMessage(1000, resultJson.getString("errmsg"));
		}
	}

}
