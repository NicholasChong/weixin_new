package com.dongzeviva.weixin.open.handle;

import java.net.URLDecoder;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.service.WeixinApiInvoker;

public class Long2ShortURLHandle extends AbstractWeixinSOAServieHandle {

	@Override
	public SOAResponseMessage handle(WeixinContext context,
									 SOARequestMessage message) throws Exception {
		String uname = message.getUname();
		WeixinPublicNumber publicNumber = context.getWeixinPublicNumber(uname);
		if(publicNumber == null){
			return new SOAResponseMessage(1000, "unknow uname:"+uname);
		}
		String access_token = publicNumber.genAccess_token(context);
		String url = message.getStringParam();
		//可能需要转码
		url = URLDecoder.decode(url, "utf-8");
		WeixinApiInvoker invoker = context.getWeixinApiInvoker();
		String result = invoker.long2ShortURL(access_token, url);
		JSONObject resultJson = JSONObject.parseObject(result);
		if("0".equals(resultJson.getString("errcode"))){
			return new SOAResponseMessage(0, resultJson.getString("short_url"));
		}else{
			return new SOAResponseMessage(1000, resultJson.getString("errmsg"));
		}
	}

}
