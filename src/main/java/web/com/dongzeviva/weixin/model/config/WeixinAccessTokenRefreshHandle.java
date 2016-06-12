package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 强制刷新AccessToken
 *
 */

public class WeixinAccessTokenRefreshHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		WeixinPublicNumber publicNumber = userinfo.getWeixinPublicNumber();
		String access_token = publicNumber.refreshAccess_token(context);
		return new SOAResponseMessage(0,null,access_token);
	}

}
