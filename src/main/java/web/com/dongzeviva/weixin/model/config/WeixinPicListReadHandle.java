package com.dongzeviva.weixin.model.config;

import javax.servlet.http.HttpServletRequest;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.attachment.AttachmentManager;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 读取公众号图片文件列表
 *
 */

public class WeixinPicListReadHandle extends ModelHandle  {

	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		AttachmentManager attachmentManager = context.getAttachmentManager();
		String[] filenames = attachmentManager.listAttachment(context, userinfo.getUname());
		return new SOAResponseMessage(0, null,filenames);
	}

}
