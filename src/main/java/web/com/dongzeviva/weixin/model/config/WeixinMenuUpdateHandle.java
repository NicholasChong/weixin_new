package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 更新微信自定义菜单
 *
 */
public class WeixinMenuUpdateHandle extends ModelHandle  {
	
	private static final Logger logger = Logger.getLogger(WeixinMenuUpdateHandle.class);

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		String menu = request.getParameter("menu");
		WeixinPublicNumber publicNumber = userinfo.getWeixinPublicNumber(); 
		publicNumber.setWeixinMenu(menu);
		context.updateWeixinPublicNumber( publicNumber);
		logger.info("UPDATE MENU"+menu);
		return SOAResponseMessage.SUCCESS;
	}

}
