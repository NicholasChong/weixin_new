package com.dongzeviva.weixin.model;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;

public abstract class ModelHandle {
	
	boolean needLogin = true;

	public abstract SOAResponseMessage handle(WeixinContext context, HttpServletRequest request
			,UserInfo userinfo) throws Exception;
	
}
