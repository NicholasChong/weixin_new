package com.dongzeviva.weixin.model.config;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.message.WeixinMessage;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 读取关键词回复
 *
 */

public class WeixinKeywordKeysReadHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		Map<String,WeixinMessage> keywords = userinfo.getWeixinPublicNumber().getKeywordsReplyMessage();
		return new SOAResponseMessage(0,null,keywords.keySet().toArray(new String[0]));
	}

}
