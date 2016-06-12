package com.dongzeviva.weixin.remote.proxy;

import java.util.List;

import com.dongzeviva.weixin.flow.WeixinMessageFlowService;
import org.apache.log4j.Logger;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteLocationEventMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.remote.WeixinRemoteProxy;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteContext;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteMessageHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteMessageServiceFilter;
import com.dongzeviva.weixin.session.WeixinSession;

/**
 * 把微信接收到指令发到Local
 * 
 */
public class WeixinRemoteLocalProxy implements WeixinRemoteProxy {

	private static final Logger LOGGER = Logger.getLogger(WeixinRemoteLocalProxy.class);

	public SOAResponseMessage request(WeixinSession session, WeixinRemoteMessage remoteMessage)
			throws Exception {
		WeixinRemoteContext remoteContext = session.getWeixinContext();
		List<WeixinRemoteMessageServiceFilter> filters = remoteContext.getWeixinRemoteMessageServiceFilters();
		SOAResponseMessage responseMessage = null;
		for (WeixinRemoteMessageServiceFilter filter:filters) {
			responseMessage = filter.doFilter(session, remoteMessage);
			if (responseMessage != null) {
				return responseMessage;
			}
		}
		WeixinMessageFlowService flowLocalService = session.getWeixinMessageFlowService();
		if (flowLocalService != null) {
			return flowLocalService.handleFlow(session, remoteMessage);
		} else {
			WeixinRemoteMessageHandle remoteMessageHandle = remoteContext.getWeixinRemoteMessageHandle();
			String msgType = remoteMessage.getMsgType();
			if (msgType == WeixinRemoteMessage.REMOTE_TEXT) {
				return remoteMessageHandle.handleRemoteTextMessage(session,(WeixinRemoteTextMessage) remoteMessage);
			} else if (msgType == WeixinRemoteMessage.REMOTE_LOCATIONEVENT) {
				return remoteMessageHandle.handleRemoteLocationEventMessage(session,(WeixinRemoteLocationEventMessage) remoteMessage);
			} else {
				LOGGER.error("no nuch remote message type:" + msgType);
				return SOAResponseMessage.NULL;
			}
		}
	}

	public String getProxyTypeName() {
		return "local";
	}

}
