package com.dongzeviva.weixin;

import javax.servlet.ServletConfig;

import com.dongzeviva.weixin.example.weather.WeixinRemoteTextMessageWeatherCellHandle;
import com.dongzeviva.weixin.remote.extend.DefaultWeixinRemoteContext;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteContextFactory;


public class DefaultWeixinRemoteMessageHandleServlet extends WeixinRemoteMessageHandleServlet {

	private static final long serialVersionUID = 1L;

	public void initWeixinConfig(ServletConfig config) {
		DefaultWeixinRemoteContext remoteContext = (DefaultWeixinRemoteContext) WeixinRemoteContextFactory.getWeixinRemoteContext();
		remoteContext.putWeixinRemoteTextMessageCellHandle("天气", new WeixinRemoteTextMessageWeatherCellHandle());
		remoteContext.init();
	}
}