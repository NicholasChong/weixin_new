package com.dongzeviva.weixin.model.config;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.message.BasicHeader;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.attachment.AttachmentManager;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.UserInfo;
import com.dongzeviva.weixin.model.ModelHandle;

/**
 * 
 * 下载图片
 *
 */
public class DownloadImageHandle extends ModelHandle  {

	private static final SOAResponseMessage IMAGE_NOT_FOUND = new SOAResponseMessage(1000,"IMAGE_NOT_FOUND");
	
	public SOAResponseMessage handle(WeixinContext context, HttpServletRequest request,
			UserInfo userinfo) throws Exception {
		AttachmentManager attachmentManager = context.getAttachmentManager();
		String filePath = request.getParameter("filePath");
		filePath = URLDecoder.decode(filePath,"UTF-8");
		byte[] bytes = attachmentManager.downloadAttachment(context,filePath);
		if (bytes == null) {
			bytes = attachmentManager.downloadAttachment(context,filePath);
		}
		if (bytes == null) {
			return IMAGE_NOT_FOUND;
		}
		BasicHeader []headers = new BasicHeader[]{
				new BasicHeader("Content-Length", bytes == null ? "0" : String.valueOf(bytes.length)),
				//new BasicHeader("Content-Disposition", "inline;filename=" +URLEncoder.encode(request.getParameter("fileName"),"UTF-8")),
				new BasicHeader("Content-type", "image/png")};
		return SOAResponseMessage.createStreamMessage(headers, bytes);
	}

}
