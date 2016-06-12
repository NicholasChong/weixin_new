package com.dongzeviva.weixin.weixin;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.dongzeviva.weixin.common.TConstant;

public class ContextListener implements ServletContextListener {
	private static final Log log = LogFactory.getLog(ServletContextListener.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		log.debug("contextDestroyed");
	}
	@Override
	public void contextInitialized(ServletContextEvent event) {
		String webInfPath = event.getServletContext().getRealPath("WEB-INF");
		init(webInfPath);
		log.debug("contextInitialized");
	}
	public void init(String webInfPath) {
		initHome(webInfPath);
		initLogger(webInfPath);
		initCache(webInfPath);
		initRootConfig(webInfPath);
		initTables(webInfPath);
		initVelocity(webInfPath);
		initJMS(webInfPath);
		// getCountDownLatch().countDown();
	}
	protected void initHome(String webInfPath) {
//		ZFContextUtil.initHome(webInfPath);
		TConstant.WEBINF_PATH = webInfPath;
		TConstant.APP_HOME = new File(webInfPath).getParentFile().getAbsolutePath();
	}
	protected void initLogger(String webInfPath) {
//		ZFContextUtil.initLogger(webInfPath);
	}
	protected void initCache(String webInfPath) {
//		ZFContextUtil.initCache(webInfPath);
	}
	protected void initRootConfig(String webInfPath) {
//		ZFContextUtil.initRootConfig(webInfPath);
	}
	protected void initTables(String webInfPath) {
//		ZFContextUtil.initTables(webInfPath);
	}
	protected void initVelocity(String webInfPath) {
//		ZFContextUtil.initVelocity(webInfPath);
	}
	protected void initJMS(String webInfPath) {
		// Nothing
	}
}
