package com.dongzeviva.weixin.model.config;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 读取微信公众号
 *
 */
//TODO 仅读取相关信息
public class WeixinPublicnumbersReadHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		Map<String, WeixinPublicNumber> weixinPublicNumbers = context.getWeixinPublicNumbers();
		JSONArray jsonArray = new JSONArray();
		Set<String> s = weixinPublicNumbers.keySet();
		Iterator<String> i = s.iterator();
		while(i.hasNext()){
			String uname = i.next();
			WeixinPublicNumber publicNumber = weixinPublicNumbers.get(uname);
			JSONObject jsonObject = (JSONObject) JSONObject.toJSON(publicNumber);
			jsonArray.add(jsonObject);
		}
		return new SOAResponseMessage(0, null,jsonArray);
	}

}
