package com.dongzeviva.weixin.session;

import com.dongzeviva.weixin.Initable;

public interface WeixinSessionFactory extends Initable {

	public abstract WeixinSession getWeixinSession(String openid,String uname) throws Exception;
	
	public abstract WeixinSession addWeixinSession(String openid,String uname) throws Exception ;
	
	public abstract void updateWeixinSession(WeixinSession session) throws Exception ;
	
}
