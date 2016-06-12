package com.dongzeviva.weixin.model.config;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 微信公众号管理
 *
 */
public class WeixinPublicnumberManagerHandle extends ModelHandle  {

	private SOAResponseMessage not_login = new SOAResponseMessage(1000, "not login");
	
	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		if (request.getSession().getAttribute("weixinPublicNumbers") == null) {
			return not_login;
		}
		String uname = request.getParameter("uname");
		int status = Integer.parseInt(request.getParameter("staus"));
		WeixinPublicNumber publicNumber = context.getWeixinPublicNumber4Edit( uname);
		publicNumber.setStatus(status);
		if(status != -1){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = df.parse(request.getParameter("service_time"));
			long service_time = date.getTime();
			publicNumber.setService_time(service_time);
		}
		context.updateWeixinPublicNumber( publicNumber);
		return SOAResponseMessage.SUCCESS;
	}
	

}
