package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.bean.WeixinPublicNumberType;
import com.dongzeviva.weixin.model.ModelHandle;

public class WeixinAppidUpdateHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		String appid = request.getParameter("appid");
		String appSecret = request.getParameter("appSecret");
		WeixinPublicNumber publicNumber = userinfo.getWeixinPublicNumber(); 
		publicNumber.setApp_id(appid);
		publicNumber.setApp_secret(appSecret);
		publicNumber.setPublic_Type(WeixinPublicNumberType.SERVIC);
		context.updateWeixinPublicNumber( publicNumber);
		return SOAResponseMessage.SUCCESS;
	}

}
