package com.dongzeviva.weixin.open;

import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;

/**
 * 单个的服务处理，包括外部发来的数据和本地请求获得的数据
 *
 */
public interface WeixinSOAServieHandle {

	/**
	 * 处理具体的服务
	 * @param context
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public abstract SOAResponseMessage handle(WeixinContext context, SOARequestMessage message) throws Exception;
	
	/**
	 * 该服务是否需要认证
	 * @return
	 */
	public abstract boolean needValidate();
	
	/**
	 * 设置该服务是否需要认证
	 * @param needValidate
	 */
	public abstract void setNeedValidate(boolean needValidate);
}
