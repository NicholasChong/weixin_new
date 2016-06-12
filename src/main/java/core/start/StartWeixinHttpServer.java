package start;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

import com.dongzeviva.weixin.utils.PropUtil;

public class StartWeixinHttpServer {
	
	private static final Logger LOGGER  = Logger.getLogger(StartWeixinHttpServer.class);

	public static void main(String[] args) throws Exception {
		ClassLoader cp = ClassLoader.getSystemClassLoader();
		Class<?> cls = cp.loadClass(StartWeixinHttpServer.class.getName());
		cls.getMethod("start").invoke(null);
	}
	
	public static void start() throws Exception {
		String path = new File(".").getAbsolutePath();
		path = path.substring(0, path.length() - 1);
		String deployPath = path+"WEB-INF";
		if (!new File(deployPath).exists()) {
			path = path + "src\\main\\webapp";
		}
		Server server = new Server();  
		Connector connector = new SelectChannelConnector();
		String app_server = PropUtil.getProperty("APP_SERVER");
		String port = null;
		if (StringUtils.isBlank(app_server)) {
			port = "8300";
		}else{
			port = app_server.substring(app_server.indexOf(":")+1, app_server.length());
		}
		connector.setPort(Integer.parseInt(port));
		server.setConnectors(new Connector[] { connector });
		WebAppContext webapp = new WebAppContext();
		String app_service = PropUtil.getProperty("APP_SERVICE");
		if (StringUtils.isBlank(app_service)) {
			app_service = "weixin";
		}
		webapp.setContextPath("/"+app_service);
		webapp.setWar(path);
		server.setStopAtShutdown(true);
		server.setHandler(webapp);
		System.out.println("  SERVER PATH     "+path);
		System.out.println("  SERVER PORT     "+port);
		System.out.println("  SERVER SERVICE  /"+app_service+"\n");
		server.start();
		LOGGER.info(">> server started");
	}
	
}
