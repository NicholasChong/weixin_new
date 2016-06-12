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
 * 更新关键词自动回复
 *
 */
public class WeixinKeywordUpdateHandle extends ModelHandle  {
	
	private static final Logger logger = Logger.getLogger(WeixinKeywordUpdateHandle.class);

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		
		WeixinPublicNumber publicNumber = userinfo.getWeixinPublicNumber();
		String keyword = request.getParameter("keyword");
		String action = request.getParameter("action");
		if(action==null){
			return SOAResponseMessage.SYSTEM_ERROR;
		}else if(action.equals("delete")){
			publicNumber.removeEditKeywordsReplyMessage(keyword);
			context.updateWeixinPublicNumber( publicNumber);
			return SOAResponseMessage.SUCCESS;
		}else if(action.equals("update")){
			String oldkeyword = request.getParameter("oldkeyword");
			publicNumber.removeEditKeywordsReplyMessage(oldkeyword);
			
		}
		WeixinMessage message = context.buildWeixinMessage(request.getParameter("jsonobj"));
		publicNumber.putEditKeywordsReplyMessage(keyword, message);
		context.updateWeixinPublicNumber( publicNumber);
		logger.info("UPDATE MESSAGE:"+message.toString());
		return SOAResponseMessage.SUCCESS;
	}
	

}
