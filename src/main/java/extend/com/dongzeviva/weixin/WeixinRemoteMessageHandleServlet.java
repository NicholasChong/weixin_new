package com.dongzeviva.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinMessageFlow;
import com.dongzeviva.weixin.common.FileUtil;
import com.dongzeviva.weixin.remote.extend.DefaultWeixinRemoteContext;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteMessageService;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteMessageServiceImpl;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteSessionImpl;
import com.dongzeviva.weixin.service.WeixinReplyMessageJsonBuilderImpl;
import org.apache.log4j.Logger;

import com.dongzeviva.weixin.bean.message.WeixinRemoteMessage;
import com.dongzeviva.weixin.remote.extend.WeixinMessageCacheStorage;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteContextFactory;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteTextMessageCellHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;

public class WeixinRemoteMessageHandleServlet extends HttpServlet {
	
	private Logger logger = Logger.getLogger(WeixinRemoteMessageHandleServlet.class);
	
	private static final long serialVersionUID = 1L;
	
	private WeixinReplyMessageJsonBuilderImpl builderImpl = new WeixinReplyMessageJsonBuilderImpl();
	
	private WeixinRemoteMessageService weixinRemoteMessageService = new WeixinRemoteMessageServiceImpl();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		SOAResponseMessage message = SOAResponseMessage.NULL;
		WeixinRemoteMessage remoteMessage = null;
		try {
			remoteMessage = (WeixinRemoteMessage) builderImpl.buildMessage(request.getParameter("weixin_remote_message"));
			WeixinRmoteSession cache = WeixinMessageCacheStorage.getWeixinMessageCache(remoteMessage.getFromUserName());
			if (cache == null) {
				cache = new WeixinRemoteSessionImpl(remoteMessage.getFromUserName(),remoteMessage.getToUserName());
				WeixinMessageCacheStorage.putWeixinMessageCache(remoteMessage.getFromUserName(), cache);
			}
			message = weixinRemoteMessageService.doService(cache, remoteMessage);
		} catch (Throwable e) {
			e.printStackTrace();
			message = SOAResponseMessage.NULL;
		}
		PrintWriter writer = response.getWriter();
		writer.write(message.toString());
		writer.flush();
	}
	
	public void init(ServletConfig config) throws ServletException {
		try {
			this.initWeixinConfig(config);
		} catch (Exception e) {
			throw new ServletException(e);
		}
		super.init(config);
	}
	
	public void initWeixinConfig(ServletConfig config) throws Exception{
		DefaultWeixinRemoteContext remoteContext = (DefaultWeixinRemoteContext) WeixinRemoteContextFactory.getWeixinRemoteContext();
		Map<Object,Object> properties = FileUtil.readProperties("remote.properties");
		Set<Entry<Object,Object>> entries = properties.entrySet();
		for(Entry<Object,Object> entry : entries){
			String key = String.valueOf(entry.getKey());
			String className = String.valueOf(entry.getValue()).trim();
			Object clazz = Class.forName(className).newInstance();
			if (clazz instanceof WeixinMessageFlow) {
				remoteContext.putWeixinMessageFlow(key, (WeixinMessageFlow)clazz);
			}else if (clazz instanceof WeixinRemoteTextMessageCellHandle) {
				remoteContext.putWeixinRemoteTextMessageCellHandle(key, (WeixinRemoteTextMessageCellHandle)clazz);
			}else{
				throw new IOException("class ["+className+"] load failed!");
			}
			logger.info("load class from remote.properties:"+className);
		}
		remoteContext.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}