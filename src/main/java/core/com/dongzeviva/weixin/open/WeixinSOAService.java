package com.dongzeviva.weixin.open;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;

/**
 *  控制服务处理接口
 *
 */
public interface WeixinSOAService {

	/**
	 * 处理服务
	 * @param context
	 * @param requestMessage
	 * @return
	 * @throws Exception
	 */
	public SOAResponseMessage doService(WeixinContext context, SOARequestMessage requestMessage)throws Exception;
	
	
}
