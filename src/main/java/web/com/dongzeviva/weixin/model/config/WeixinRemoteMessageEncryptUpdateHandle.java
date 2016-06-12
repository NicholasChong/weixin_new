package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.model.ModelHandle;

public class WeixinRemoteMessageEncryptUpdateHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		String encrypt = request.getParameter("encrypt");
		boolean isEncrypt = Boolean.valueOf(request.getParameter("isEncrypt"));
		WeixinPublicNumber publicNumber = userinfo.getWeixinPublicNumber(); 
		publicNumber.setEncrypt_remote_message(isEncrypt);
		publicNumber.setRemote_message_encrypt(encrypt);
		context.updateWeixinPublicNumber( publicNumber);
		return SOAResponseMessage.SUCCESS;
	}

}
