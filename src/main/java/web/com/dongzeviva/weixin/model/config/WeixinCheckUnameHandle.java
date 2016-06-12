package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 验证用户名是否已存在
 *
 */
public class WeixinCheckUnameHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		
		WeixinPublicNumber publicNumber = context.getWeixinPublicNumber4Edit(request.getParameter("username"));
		if(publicNumber!=null){
			return new SOAResponseMessage(199, "该用户名已存在");
		}
		return SOAResponseMessage.SUCCESS;
	}
	

}
