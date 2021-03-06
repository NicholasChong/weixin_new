package com.dongzeviva.weixin.remote.proxy;

import java.util.HashMap;
import java.util.Map;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.common.Encryptor;
import com.dongzeviva.weixin.common.SimpleHttpClient;
import com.dongzeviva.weixin.remote.WeixinRemoteProxy;
import com.dongzeviva.weixin.session.WeixinSession;

/**
 * 把微信接收到指令发到HTTP
 *
 */
public class WeixinRemoteHttpProxy implements WeixinRemoteProxy {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public SOAResponseMessage request(WeixinSession session, WeixinRemoteMessage remoteMessage) throws Exception {
		WeixinPublicNumber publicNumber = session.getWeixinPublicNumber();
		String remoteMessageString = null;
		if (publicNumber.isEncrypt_remote_message()) {
			remoteMessageString = Encryptor.encryptData(remoteMessage.toString(), publicNumber.getRemote_message_encrypt());
		}else{
			remoteMessageString =  remoteMessage.toString();
		}
		Map param = new HashMap();
		param.put("weixin_remote_message", remoteMessageString);
		String message = SimpleHttpClient.invokePost4String(remoteMessage.getUrl(), param,3000);
		if (message == null) {
			return SOAResponseMessage.SYSTEM_ERROR;
		}
		return SOAResponseMessage.parse(message);
	}
	
	public String getProxyTypeName() {
		return "http";
	}

	
	
}
