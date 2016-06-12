package com.dongzeviva.weixin.example.flow.location;

import com.dongzeviva.weixin.bean.WeixinMessageFlow;
import com.dongzeviva.weixin.flow.WeixinMessageFlowHandle;

public class LocationGoFlowTest implements WeixinMessageFlow {

	private static final long serialVersionUID = 2314065280861659478L;
	private WeixinMessageFlowHandle[] flowHandles = new WeixinMessageFlowHandle[]{new LBSGoStep1(),new LBSGoStep2()};
	
	public WeixinMessageFlowHandle[] getFlowHandles() {
		return flowHandles;
	}

	public String getName() {
		return "我要去";
	}
	
}
