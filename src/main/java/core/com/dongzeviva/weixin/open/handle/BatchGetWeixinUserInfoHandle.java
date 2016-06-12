package com.dongzeviva.weixin.open.handle;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.service.WeixinApiInvoker;

/**
 * 
 * 获取微信用户信息
 *
 */
public class BatchGetWeixinUserInfoHandle extends AbstractWeixinSOAServieHandle  {
	
	public SOAResponseMessage handle(WeixinContext context, SOARequestMessage requestMessage)
			throws Exception {
		String uname = requestMessage.getUname();
		WeixinPublicNumber weixinPublicNumber = context.getWeixinPublicNumber(uname);
		if (weixinPublicNumber == null) {
			return new SOAResponseMessage(1000,"unknow uname:"+uname);
		}
		String access_token = weixinPublicNumber.genAccess_token(context);
		String openidListStr = requestMessage.getStringParam();
		String[] openidList = openidListStr.replace("[", "").replace("]", "").replaceAll(" ", "").split(",");
		WeixinApiInvoker apiInvoker = context.getWeixinApiInvoker();
		String result = apiInvoker.batchGetUserInfo(access_token, openidList);
		JSONObject jsonObject = JSONObject.parseObject(result);
		if(jsonObject.get("errcode") == null){
			return new SOAResponseMessage(0, null, jsonObject);
		}else{
			return new SOAResponseMessage(1000, jsonObject.getString("errmsg"));
		}
	}
}
