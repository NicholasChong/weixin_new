package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.bean.message.WeixinMessage;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 更新关注回复
 * 
 */
public class WeixinCareReplyUpdateHandle extends ModelHandle {
	
	private static final Logger logger = Logger.getLogger(WeixinCareReplyUpdateHandle.class);

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {

		WeixinPublicNumber publicNumber = userinfo.getWeixinPublicNumber();
		WeixinMessage message = context.buildWeixinMessage(request.getParameter("jsonobj"));
		publicNumber.setSubscribeReplyMessage(message);
		context.updateWeixinPublicNumber(publicNumber);
		logger.info("UPDATE MESSAGE:"+message.toString());
		return SOAResponseMessage.SUCCESS;
	}

}
