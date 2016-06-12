package com.dongzeviva.weixin.weixin.remotehandle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.common.SimpleHttpClient;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteTextMessageCellHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;
import com.dongzeviva.weixin.utils.PropUtil;

public class UnsubscribeCellHandle implements WeixinRemoteTextMessageCellHandle {

	private static final Logger log = Logger.getLogger(UnsubscribeCellHandle.class);

	@Override
	public SOAResponseMessage handleWeixinRemoteTextMessage(
			WeixinRmoteSession cache, WeixinRemoteTextMessage remoteTextMessage)			 {
			String openid = remoteTextMessage.getFromUserName();
			JSONObject json = new JSONObject();
			json.put("__wxopenid", openid);
			Map<String, String> params = new HashMap<String, String>();
			params.put("datas", json.toString());
			String unsubscribe_url = PropUtil.getProperty("server.soa.usercenter.unsubscribe");
			if(StringUtils.isBlank(unsubscribe_url)){
				log.error("core文件中server.soa.usercenter.unsubscribe必须配置，请检查");
				return null;
			}
			try {
				SimpleHttpClient.invokePost4String(unsubscribe_url,params, 7200);
			} catch (IOException ex) {
				log.error(ex);
			}
			return SOAResponseMessage.NULL;
		}

	@Override
	public String getHandleName() {
		// TODO Auto-generated method stub
		return "Unsubscribe";
	}

	private static boolean isNotEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * 
	 * @param source
	 * @return
	 */
	public static String filterEmoji(String source) {
		int len = source.length();
		StringBuilder buf = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isNotEmojiCharacter(codePoint)) {
				buf.append(codePoint);
			}
		}
		return buf.toString();
	}
}
