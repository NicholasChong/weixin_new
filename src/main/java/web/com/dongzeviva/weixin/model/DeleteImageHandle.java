package com.dongzeviva.weixin.model;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.attachment.AttachmentManager;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;

/**
 * 
 * 测试用的
 *
 */
public class DeleteImageHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		WeixinPublicNumber publicNumber = userinfo.getWeixinPublicNumber();
		String fileName = request.getParameter("fileName");
		String filePath = publicNumber.getUname()+"/"+fileName;
		AttachmentManager attachmentManager = context.getAttachmentManager();
		attachmentManager.removeAttachment(context, filePath);
		return SOAResponseMessage.SUCCESS;
	}
	
}
