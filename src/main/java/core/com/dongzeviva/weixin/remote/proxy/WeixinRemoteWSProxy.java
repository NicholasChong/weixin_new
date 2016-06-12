package com.dongzeviva.weixin.remote.proxy;

import javax.naming.OperationNotSupportedException;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.remote.WeixinRemoteProxy;
import com.dongzeviva.weixin.session.WeixinSession;

/**
 * 把微信接收到指令发到WebService
 *
 */
public class WeixinRemoteWSProxy implements WeixinRemoteProxy {

	public SOAResponseMessage request(WeixinSession session, WeixinRemoteMessage remoteMessage) throws Exception {
		throw new OperationNotSupportedException("not support WeixinRemoteWSProxy");
	}

	public String getProxyTypeName() {
		return "ws";
	}

	
}
