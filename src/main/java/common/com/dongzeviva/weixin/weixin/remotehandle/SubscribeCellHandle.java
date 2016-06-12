package com.dongzeviva.weixin.weixin.remotehandle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.dongzeviva.weixin.weixin.common.BFConstant;
import com.dongzeviva.weixin.weixin.openHandle.GetWeixinUserInfoHandle;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinEventMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.common.SimpleHttpClient;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteTextMessageCellHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;
import com.dongzeviva.weixin.utils.PropUtil;

public class SubscribeCellHandle implements WeixinRemoteTextMessageCellHandle {

	private static final Logger log = Logger.getLogger(SubscribeCellHandle.class);
	@Override
	public SOAResponseMessage handleWeixinRemoteTextMessage(WeixinRmoteSession cache,
			WeixinRemoteTextMessage remoteTextMessage) {
		String openid = remoteTextMessage.getFromUserName();
		WeixinEventMessage eventMessage = remoteTextMessage.getEventMessage();
		String scene_id = "";
		if (eventMessage != null) {
			scene_id = eventMessage.getEventKey();
			if (!StringUtils.isBlank(scene_id)) {
				if (scene_id.indexOf("last_trade_no") != -1) {
					scene_id = "";
				} else {
					scene_id = scene_id.replace("qrscene_", "");
				}
			}
		}
		long starttime = System.currentTimeMillis();
		JSONObject userInfo = GetWeixinUserInfoHandle.getUserInfo(BFConstant.UNAME, openid);
		long endtime = System.currentTimeMillis();
		log.info("invoke getUserInfo after subscribe:" + (endtime - starttime));
		if (userInfo != null) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("__wxopenid", openid);
			String name = userInfo.getString("nickname");
			name = filterEmoji(name);
			params.put("NAME", userInfo.getString("nickname"));
			params.put("PORTRAIT", userInfo.getString("headimgurl"));
			params.put("SEX", userInfo.getString("sex"));
			params.put("SCENEID", scene_id);
			String register_url = PropUtil.getProperty("server.soa.usercenter.register");
			if (StringUtils.isBlank(register_url)) {
				log.error("core文件中server.soa.usercenter.register必须配置，请检查");
				return null;
			}
			String result = "";
			try {
				result = SimpleHttpClient.invokePost4String(register_url, params, 7200);
				log.info("send userinfo to " + register_url + " ----" + scene_id);
				log.info("send userinfo result:" + result);
			} catch (IOException ex) {
				log.error("send userinfo to " + register_url + " fail");
				log.error(ex);
			}
			log.info(userInfo + "---" + result);
			JSONObject resJson = JSONObject.parseObject(result);
			String roleLevel = resJson.getString("ROLELEVEL");
			return new SOAResponseMessage(0, roleLevel);
		}
		return new SOAResponseMessage(1000, "register failed");
	}
	@Override
	@Deprecated
	public String getHandleName() {
		return "Subscribe";
	}
	private static boolean isNotEmojiCharacter(char codePoint)  
	{  
	    return (codePoint == 0x0) ||  
	        (codePoint == 0x9) ||  
	        (codePoint == 0xA) ||  
	        (codePoint == 0xD) ||  
	        ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||  
	        ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) ||  
	        ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));  
	}
	/** 
	 * 过滤emoji 或者 其他非文字类型的字符 
	 * @param source 
	 * @return 
	 */  
	public static String filterEmoji(String source)  
	{  
	    int len = source.length();  
	    StringBuilder buf = new StringBuilder(len);  
	    for (int i = 0; i < len; i++)  
	    {  
	        char codePoint = source.charAt(i);  
	        if (isNotEmojiCharacter(codePoint))  
	        {  
	            buf.append(codePoint);  
	        }  
	    }  
	    return buf.toString();  
	}  
}