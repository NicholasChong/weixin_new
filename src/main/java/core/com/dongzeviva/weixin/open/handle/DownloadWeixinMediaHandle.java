package com.dongzeviva.weixin.open.handle;

import java.io.InputStream;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.service.WeixinApiInvoker;

/**
 * 下载多媒体服务，
 * 请勿使用，有问题
 *
 */
//FIXME complete me
public class DownloadWeixinMediaHandle extends AbstractWeixinSOAServieHandle  {
	
	public static String KEY_NAME = null;
	
	public SOAResponseMessage handle(WeixinContext context, SOARequestMessage requestMessage)
			throws Exception {
		String uname = requestMessage.getUname();
		WeixinPublicNumber weixinPublicNumber = context.getWeixinPublicNumber(uname);
		if (weixinPublicNumber == null) {
			return new SOAResponseMessage(1000,"unknow uname:"+uname);
		}
		WeixinApiInvoker apiInvoker = context.getWeixinApiInvoker();
		String access_token = weixinPublicNumber.genAccess_token(context);
		String media_id = requestMessage.getStringParam();
		InputStream is = apiInvoker.downloadMedia(access_token, media_id);
		return new SOAResponseMessage(0,null,is);
	}

}
