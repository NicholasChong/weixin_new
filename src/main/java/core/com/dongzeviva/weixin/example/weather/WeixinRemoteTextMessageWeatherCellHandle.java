package com.dongzeviva.weixin.example.weather;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteTextMessageCellHandle;

public class WeixinRemoteTextMessageWeatherCellHandle implements WeixinRemoteTextMessageCellHandle{

	public SOAResponseMessage handleWeixinRemoteTextMessage(WeixinRmoteSession cache, WeixinRemoteTextMessage remoteTextMessage)
			throws Exception {
		String []param = remoteTextMessage.getParam();
		String city = null;
		if (param == null || param.length == 0) {
			city = "上海";
		}else{
			city = param[0];
		}
		return GetWeather.getWeather(city);
	}

	public String getHandleName() {
		return "天气";
	}

	
	
	
}
