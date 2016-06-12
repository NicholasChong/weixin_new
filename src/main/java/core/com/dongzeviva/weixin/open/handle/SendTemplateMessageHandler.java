package com.dongzeviva.weixin.open.handle;

import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.service.WeixinApiInvoker;
import com.dongzeviva.weixin.service.WeixinTemplateMessageBuilder;
import com.dongzeviva.weixin.service.WeixinTemplateMessageBuilder4Prop;
import com.dongzeviva.weixin.service.WeixinTemplateMessageBuilder4joyseedtest;
import com.dongzeviva.weixin.service.WeixinTemplateMessageBuilder4tea;
import com.dongzeviva.weixin.service.WeixinTemplateMessageBuilder4zaofans;
import com.dongzeviva.weixin.service.WeixinTemplateMessageBuilder4zumuquqi;
import com.dongzeviva.weixin.utils.PropUtil;

public class SendTemplateMessageHandler extends AbstractWeixinSOAServieHandle {
	
	private static final Logger LOGGER = Logger.getLogger(SendTemplateMessageHandler.class);
	
	private static final String BRAND = PropUtil.getProperty("brand");
	
	@Override
	public SOAResponseMessage handle(WeixinContext context,
									 SOARequestMessage message) throws Exception {
		String uname = message.getUname();
		WeixinPublicNumber publicNumber = context.getWeixinPublicNumber(uname);
		if(publicNumber == null){
			return new SOAResponseMessage(1000, "unknow uname:"+uname);
		}
		WeixinApiInvoker invoker = context.getWeixinApiInvoker();
		String access_token = publicNumber.genAccess_token(context);
		String param = message.getStringParam();
		param = URLDecoder.decode(param,"UTF-8");
		String templateMessage ="";
		if(StringUtils.isBlank(BRAND)){
			LOGGER.info("错误！core文件中brand未配置");
		}
		if("joyseed".equalsIgnoreCase(BRAND)){
			templateMessage = WeixinTemplateMessageBuilder.getTemplateStr(param);
		}else if("zaofans".equalsIgnoreCase(BRAND)){
			 templateMessage = WeixinTemplateMessageBuilder4zaofans.getTemplateStr(param);
		}else if("zumuquqi".equalsIgnoreCase(BRAND)){
			templateMessage = WeixinTemplateMessageBuilder4zumuquqi.getTemplateStr(param);
		}else if("joyseed_test".equalsIgnoreCase(BRAND)){
			templateMessage = WeixinTemplateMessageBuilder4joyseedtest.getTemplateStr(param);
		}else if("tea".equalsIgnoreCase(BRAND)){
			templateMessage = WeixinTemplateMessageBuilder4tea.getTemplateStr(param);
		}else{
			templateMessage = WeixinTemplateMessageBuilder4Prop.getTemplateStr(param);
		}
		LOGGER.info(templateMessage);
		String result = invoker.sendTemplateMessage(access_token, templateMessage);
		JSONObject resultJson = JSONObject.parseObject(result);
		if (resultJson.getInteger("errcode") == 0) {
			return SOAResponseMessage.SUCCESS;
		} else {
			return new SOAResponseMessage(1000, resultJson.getString("errmsg"));
		}
	}

}
