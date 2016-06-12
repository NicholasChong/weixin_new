package com.dongzeviva.weixin.flow;

import java.io.Serializable;

import com.dongzeviva.weixin.bean.WeixinMessageWrap4Flow;
import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;

public interface WeixinMessageFlowHandle extends Serializable{

	public abstract WeixinMessageWrap4Flow doAction(WeixinRmoteSession cache, WeixinRemoteMessage message) throws Exception;
	
	public abstract void setGoback(boolean isGoback) ;
	
	public abstract boolean isGoback();
	
	public abstract WeixinRemoteMessage getStoreMessage();

}
