package com.dongzeviva.weixin.publicnumber;

import com.dongzeviva.weixin.Initable;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;

/**
 * 微信公众号获取提供者
 *
 */
public interface WeixinPublicNumberProvider extends Initable {
	
	public abstract WeixinPublicNumber getWeixinPublicNumber(WeixinContext context, String uname) throws Exception;
	
	public abstract void updateWeixinPublicNumber(WeixinContext context,WeixinPublicNumber publicNumber)throws Exception;
	
	public abstract void removeWeixinPublicNumber(WeixinContext context,String uname)throws Exception;
	
	public abstract void addWeixinPublicNumber(WeixinContext context,WeixinPublicNumber publicNumber)throws Exception;
}
