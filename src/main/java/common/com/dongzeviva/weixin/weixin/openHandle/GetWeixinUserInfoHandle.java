package com.dongzeviva.weixin.weixin.openHandle;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.open.WeixinSOALocalDispatch;

public class GetWeixinUserInfoHandle {
	
	private static final Logger LOGGER = Logger.getLogger(GetWeixinUserInfoHandle.class);

	public static JSONObject getUserInfo(String uname,String openid){
		SOARequestMessage request = new SOARequestMessage();
		request.setType("getWeixinUserInfo");
		request.setUname(uname);
		request.setObjectParam(openid);
		try {
			SOAResponseMessage response = WeixinSOALocalDispatch.doDispatch(request);
			return JSONObject.parseObject(response.getData().toString());
		} catch (Exception ex) {
			LOGGER.error("getWeixinUserInfo failed");
			LOGGER.error(ex);
		}
		return null;
	}
	
}
