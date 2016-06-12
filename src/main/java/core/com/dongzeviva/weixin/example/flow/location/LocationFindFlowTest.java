package com.dongzeviva.weixin.example.flow.location;

import com.dongzeviva.weixin.bean.WeixinMessageFlow;
import com.dongzeviva.weixin.flow.WeixinMessageFlowHandle;

public class LocationFindFlowTest implements WeixinMessageFlow {

	private static final long serialVersionUID = -3213092745849670717L;
	private WeixinMessageFlowHandle[] flowHandles = new WeixinMessageFlowHandle[]{new LBSFindStep1(),new LBSFindStep2()};
	
	public WeixinMessageFlowHandle[] getFlowHandles() {
		return flowHandles;
	}

	public String getName() {
		return "附近";
	}
	
}
