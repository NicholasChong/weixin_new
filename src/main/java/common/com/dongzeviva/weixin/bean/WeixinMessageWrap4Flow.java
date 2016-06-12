package com.dongzeviva.weixin.bean;

import com.dongzeviva.weixin.bean.message.WeixinMessage;

/**
 * 
 * 封装Flow消息的外包装
 *
 */
public class WeixinMessageWrap4Flow {

	private WeixinMessageFlowDealType flowDealType = WeixinMessageFlowDealType.CONTINUE;

	private WeixinMessage weixinMessage;

	public WeixinMessageFlowDealType getFlowDealType() {
		return flowDealType;
	}

	public WeixinMessage getWeixinMessage() {
		return weixinMessage;
	}

	public void setFlowDealType(WeixinMessageFlowDealType flowDealType) {
		this.flowDealType = flowDealType;
	}

	public void setWeixinMessage(WeixinMessage weixinMessage) {
		this.weixinMessage = weixinMessage;
	}


}
