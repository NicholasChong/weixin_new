package com.dongzeviva.weixin.bean;

import java.io.Serializable;

import com.dongzeviva.weixin.flow.WeixinMessageFlowHandle;

public interface WeixinMessageFlow extends Serializable{

	public abstract WeixinMessageFlowHandle[] getFlowHandles();

	public abstract String getName() ;

}
