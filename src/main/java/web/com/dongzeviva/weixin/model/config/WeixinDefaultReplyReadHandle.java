package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 读取默认回复
 *
 */

public class WeixinDefaultReplyReadHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		return new SOAResponseMessage(0, null, userinfo.getWeixinPublicNumber().getDefaultReplyMessage());
	}

}
