package com.dongzeviva.weixin.model;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;

/**
 * 
 * 登出操作
 *
 */
public class LogoutHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		request.getSession().setAttribute("userinfo", null);
		return SOAResponseMessage.SUCCESS;
	}
	

}
