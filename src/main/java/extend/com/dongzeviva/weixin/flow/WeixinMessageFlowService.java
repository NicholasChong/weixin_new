package com.dongzeviva.weixin.flow;

import java.io.Serializable;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinMessageFlow;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;

public interface WeixinMessageFlowService extends Serializable{

	public abstract WeixinRemoteMessage getWeixinRemoteMessage();
	
	public abstract WeixinMessageFlow getMessageFlow();

	public abstract SOAResponseMessage goback(WeixinRmoteSession cache, WeixinRemoteMessage message)throws Exception ;

	public abstract SOAResponseMessage gobreak(WeixinRmoteSession cache, WeixinRemoteMessage message)throws Exception ;

	public abstract SOAResponseMessage handleFlow(WeixinRmoteSession cache, WeixinRemoteMessage message)throws Exception;

	public abstract void setCurrentAction(int currentIndex);
	
	public abstract void setMessageFlow(WeixinMessageFlow messageFlow) ;

	public abstract void setWeixinRemoteMessage(WeixinRemoteMessage remoteMessage);

}
