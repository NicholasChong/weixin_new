package com.dongzeviva.weixin.open.handle;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.service.WeixinApiInvoker;

/**
 * 
 * 获取用户增减数据
 *
 */
public class GetUserSummaryHandle extends AbstractWeixinSOAServieHandle  {
	
	public SOAResponseMessage handle(WeixinContext context, SOARequestMessage requestMessage)
			throws Exception {
		String uname = requestMessage.getUname();
		WeixinPublicNumber weixinPublicNumber = context.getWeixinPublicNumber(uname);
		if (weixinPublicNumber == null) {
			return new SOAResponseMessage(1000,"unknow uname:"+uname);
		}
		String access_token = weixinPublicNumber.genAccess_token(context);
		String dateInfo = requestMessage.getStringParam();
		WeixinApiInvoker apiInvoker = context.getWeixinApiInvoker();
		String result = apiInvoker.getStatisticsData(access_token, "getUserSummary",dateInfo);
		JSONObject jsonObject = JSONObject.parseObject(result);
		if(jsonObject.get("errcode") == null){
			return new SOAResponseMessage(0, null, jsonObject);
		}else{
			return new SOAResponseMessage(1000, jsonObject.getString("errmsg"));
		}
	}
}
