package com.dongzeviva.weixin.publicnumber;

import com.dongzeviva.weixin.Initable;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.WeixinPublicNumberManagerInfo;

public interface WeixinPublicNumberManagerInfoProvider extends Initable {

	public abstract WeixinPublicNumberManagerInfo getWeixinPublicNumberManagerInfo(WeixinContext context) throws Exception;
	
	public abstract void updateWeixinPublicNumberManagerInfo(WeixinContext context,WeixinPublicNumberManagerInfo publicNumberManagerInfo) throws Exception;
	
}
