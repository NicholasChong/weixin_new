package com.dongzeviva.weixin.publicnumber;

import java.util.Properties;

import com.dongzeviva.weixin.Initable;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.WeixinPublicNumberManagerInfo;
import com.dongzeviva.weixin.common.FileUtil;

public class LocalWeixinPublicNumberManagerInfoProvider implements WeixinPublicNumberManagerInfoProvider,Initable {

	public void initialize(WeixinContext context) throws Exception {
		Properties properties = FileUtil.readProperties("admin.properties");
		WeixinPublicNumberManagerInfo weixinPublicNumberManagerInfo = context.getWeixinPublicNumberManagerInfoFromLocal();
		weixinPublicNumberManagerInfo.setManagerName(properties.getProperty("username"));
		weixinPublicNumberManagerInfo.setManagerPassword(properties.getProperty("password"));
	}

	public void shoutdown(WeixinContext context) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public WeixinPublicNumberManagerInfo getWeixinPublicNumberManagerInfo(WeixinContext context)throws Exception {
		return context.getWeixinPublicNumberManagerInfoFromLocal();
	}

	public void updateWeixinPublicNumberManagerInfo(WeixinContext context,WeixinPublicNumberManagerInfo publicNumberManagerInfo)
			throws Exception {
		Properties properties = FileUtil.readProperties("admin.properties");
		String new_password = publicNumberManagerInfo.getManagerPassword();
		properties.setProperty("password", new_password);
		FileUtil.writeProperties(properties, "admin.properties");
	}
	
}
