package com.dongzeviva.weixin.flow;

import java.io.Serializable;

import com.dongzeviva.weixin.bean.message.WeixinMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.session.WeixinSession;

public interface WeixinMessageFlowLocalService extends Serializable {

	public abstract WeixinRemoteMessage getWeixinRemoteMessage();

	public abstract void setWeixinRemoteMessage(WeixinRemoteMessage weixinRemoteMessage);
	
	public abstract WeixinMessage handleFlow(WeixinSession session, WeixinMessage message)throws Exception;

}