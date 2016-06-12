package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumberManagerInfo;
import com.dongzeviva.weixin.model.ModelHandle;

public class WeixinAdministratorManagement extends ModelHandle{

	private SOAResponseMessage VERIFY_CODE_NOT_THROUGH = new SOAResponseMessage(805,"VERIFY_CODE_NOT_THROUGH");
	
	private SOAResponseMessage USERINFO_NOT_THROUGH = new SOAResponseMessage(806,"USERINFO_NOT_THROUGH");
	
	private boolean validate_verify_code( HttpServletRequest request){
		String verify_code = request.getParameter("verify_code");
		HttpSession session = request.getSession();
	    return session.getAttribute("verify_code").toString().toLowerCase().equals(verify_code.toLowerCase());
	}
	
	
	public SOAResponseMessage handle(WeixinContext context,HttpServletRequest request, UserInfo userinfo) throws Exception {
		if (!validate_verify_code(request)) {
			return VERIFY_CODE_NOT_THROUGH;
		}
		String username = request.getParameter("username");
		String password = request.getParameter("old_password");
		WeixinPublicNumberManagerInfo publicNumberManagerInfo = context.getWeixinPublicNumberManagerInfo();
		if(publicNumberManagerInfo.getManagerName().equals(username) && publicNumberManagerInfo.getManagerPassword().equals(password)){
			String new_password = request.getParameter("new_password");
			publicNumberManagerInfo.setManagerPassword(new_password);
			context.updateWeixinPublicNumberManagerInfo(publicNumberManagerInfo);
			return new SOAResponseMessage(0,"修改成功");
		}else{
			return USERINFO_NOT_THROUGH;
		}
	}

}
