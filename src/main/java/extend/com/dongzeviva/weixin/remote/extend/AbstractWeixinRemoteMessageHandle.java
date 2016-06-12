package com.dongzeviva.weixin.remote.extend;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinMessageFlow;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.flow.WeixinMessageFlowService;
import com.dongzeviva.weixin.flow.WeixinMessageFlowServiceImpl;
import org.apache.log4j.Logger;

public abstract class AbstractWeixinRemoteMessageHandle implements WeixinRemoteMessageHandle{

	private static final Logger LOGGER = Logger.getLogger(AbstractWeixinRemoteMessageHandle.class); 
	
	public SOAResponseMessage handleRemoteTextMessage(WeixinRmoteSession session, WeixinRemoteTextMessage remoteTextMessage) throws Exception {
		WeixinRemoteContext remoteContext = session.getWeixinContext();
		WeixinMessageFlow messageFlow = remoteContext.getWeixinMessageFlow(remoteTextMessage.getServiceKey());
		if (messageFlow != null) {
			WeixinMessageFlowService flowService = new WeixinMessageFlowServiceImpl();
			flowService.setMessageFlow(messageFlow);
			session.setWeixinMessageFlowService(flowService);
			SOAResponseMessage responseMessage = flowService.handleFlow(session, remoteTextMessage);
			//FIXME  不确定
			//responseMessage.setCode(300);
			return responseMessage;
		}
		WeixinRemoteTextMessageCellHandle handle = remoteContext.getWeixinRemoteTextMessageCellHandle(remoteTextMessage.getServiceKey());
		if (handle == null) {
			LOGGER.error("please config your remote message reply,"+remoteTextMessage.getServiceKey());
			return SOAResponseMessage.SYSTEM_ERROR_302;
//			return SOAResponseMessage.SYSTEM_ERROR;
		}
		return handle.handleWeixinRemoteTextMessage(session, remoteTextMessage);
	}
	
}
