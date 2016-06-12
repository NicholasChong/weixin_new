package com.dongzeviva.weixin.weixin.remotehandle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.jetty.util.UrlEncoded;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.message.WeixinRemoteTextMessage;
import com.dongzeviva.weixin.common.SimpleHttpClient;
import com.dongzeviva.weixin.remote.extend.WeixinRemoteTextMessageCellHandle;
import com.dongzeviva.weixin.remote.extend.WeixinRmoteSession;
import com.dongzeviva.weixin.utils.PropUtil;
/**
 * 个人取消订单列表查询
 * 
 * @author Administrator
 *
 */
public class CanceledOrderListQueryHandle implements WeixinRemoteTextMessageCellHandle{
	
	private static final Logger log = Logger.getLogger(CanceledOrderListQueryHandle.class);

	private static final String url = PropUtil.getProperty("server.soa.orderlist.canceled.query");
	private static final String MORE = PropUtil.getProperty("server.soa.content.dingdan");
	private static final String YUDING = PropUtil.getProperty("server.soa.content.yuding");
	
	@Override
	public SOAResponseMessage handleWeixinRemoteTextMessage(WeixinRmoteSession cache,
			WeixinRemoteTextMessage remoteTextMessage) throws Exception {
		String wxid = remoteTextMessage.getFromUserName();
		log.info(wxid);
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("__sessionid", wxid);
		String res = SimpleHttpClient.invokeGet4String(url, headers, 7200);
		log.info(res);
		StringBuffer content = new StringBuffer();
		JSONArray array = JSONArray.parseArray(res);
		if(array.size()>0){
			for(int i=0;i<array.size();i++){
				JSONObject json =array.getJSONObject(i);
				String orderType = json.getString("orderType");
				if(orderType.equals("SINGLE")){
					content.append("订单编号："+json.getString("OrderId")+",共计"+json.get("orderCount")+"份,取消"+json.get("canceledCount")+"份,取消时间："+json.get("updateOrderDate")+"\n");
				}else{
					content.append("订单编号："+json.getString("OrderId")+",共计"+json.get("orderCount")+"份,取消"+json.get("canceledCount")+"份,您取消了其中配送日期为："+json.get("OrderDate")+"的订单\n");
				}
			}
			content.append("点击<a href=\""+MORE+"\">此处</a>查看更多订单信息\n");
			content.append("回复r+取消订单号,可以快速查询订单详情哦\n\n");
			content.append("如有疑问，请联系客服电话4008900301。非工作时段也可以发送\"help\"或\"帮助\"进行相关咨询。");
		}else{
			content.append("您未取消过订单，点击<a href=\""+YUDING+"\">此处</a>购买");
		}
		return new SOAResponseMessage(0,content.toString());
	}

	@Override
	public String getHandleName() {
		// TODO Auto-generated method stub
		return "OrderListQuery";
	}
	
	public static void main(String[] args) throws IOException {
		String wxid = "onbRUsyIXOMPNETJ1IkjJGSVAu4A";
		Map<String,String> headers = new HashMap<String, String>();
		headers.put("__sessionid", wxid);
		String res = SimpleHttpClient.invokeGet4String("http://localhost:8080/zaofans-dongze/orderform/orderForms/search/canceled", headers, 7200);
		log.info(res);
		StringBuffer content = new StringBuffer();
		if(!StringUtils.isBlank(res)){
			log.info(res);
			JSONArray array = JSONArray.parseArray(res);
			if(array.size()>0){
				content.append("您近期取消的订单如下：\n\n");
				for(int i=0;i<array.size();i++){
					JSONObject json =array.getJSONObject(i);
					String orderType = json.getString("orderType");
					if(orderType.equals("SINGLE")){
						content.append("订单编号："+json.getString("orderId")+",共计"+json.get("orderCount")+"份,取消"+json.get("canceledCount")+"份,取消时间："+json.get("updateOrderDate")+"\n");
					}else{
						content.append("订单编号："+json.getString("orderId")+",共计"+json.get("orderCount")+"份,取消"+json.get("canceledCount")+"份,您取消了其中配送日期为："+json.get("canceledOrderDate")+"的订单\n");
					}
				}
				content.append("点击<a href=\""+MORE+"\">此处</a>查看更多订单信息\n");
				content.append("回复r+取消订单号,可以快速查询订单详情哦\n\n");
				content.append("如有疑问，请联系客服电话4008900301。非工作时段也可以发送\"help\"或\"帮助\"进行相关咨询。");
			}else{
				content.append("您未取消过订单，点击<a href=\""+YUDING+"\">此处</a>购买");
			}
		}
		JSONObject message = new JSONObject();
		message.put("touser", wxid);
		message.put("msgtype", "text");
		JSONObject text = new JSONObject();
		text.put("content",content);
		message.put("text", text);
		System.out.println(message);
		Map<String,String> map = new HashMap<String,String>();
		map.put("param", UrlEncoded.encodeString(message.toString(),"utf-8"));
		map.put("uname", "zaofans");
		map.put("type", "sendWeixinMessage");
		String result = SimpleHttpClient.invokePost4String("http://www.zaofans.com/weixin-test/open-api", map, 30000);
		System.out.println(result);
	}
	
}
