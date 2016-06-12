package com.dongzeviva.weixin.open.handle;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.service.WeixinApiInvoker;

/**
 * 上传微信多媒体文件
 *
 */
@Deprecated
// FIXME
public class UploadWeixinMediaHandle extends AbstractWeixinSOAServieHandle {

	public static String KEY_NAME = null;

	public SOAResponseMessage handle(WeixinContext context, SOARequestMessage requestMessage) throws Exception {
		String uname = requestMessage.getUname();
		WeixinPublicNumber weixinPublicNumber = context.getWeixinPublicNumber(uname);
		if (weixinPublicNumber == null) {
			return new SOAResponseMessage(1000, "unknow uname:" + uname);
		}
		String access_token = weixinPublicNumber.genAccess_token(context);
		JSONObject jsonObject = JSONObject.parseObject(requestMessage.getStringParam());
		String type = jsonObject.getString("type");
		byte[] array = (byte[]) jsonObject.get("data");
		String filename = jsonObject.getString("filename");
		String fileLength = jsonObject.getString("fileLength");
		String contentType = jsonObject.getString("contentType");
		WeixinApiInvoker apiInvoker = context.getWeixinApiInvoker();
		String result = apiInvoker.uploadMedia(access_token, type, array, filename, fileLength, contentType);
		JSONObject resultJsonObject = JSONObject.parseObject(result);
		if (resultJsonObject.containsKey("errcode") && StringUtils.isBlank(resultJsonObject.getString("errcode"))) {
			return new SOAResponseMessage(0, result);
		} else {
			return new SOAResponseMessage(1000, resultJsonObject.getString("errmsg"));
		}
	}

}
