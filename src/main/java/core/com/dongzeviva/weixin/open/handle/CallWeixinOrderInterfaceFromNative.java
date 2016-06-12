package com.dongzeviva.weixin.open.handle;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.dongzeviva.weixin.WeixinContext;
import com.dongzeviva.weixin.bean.SOARequestMessage;
import com.dongzeviva.weixin.bean.SOAResponseMessage;
import com.dongzeviva.weixin.bean.WeixinPublicNumber;
import com.dongzeviva.weixin.service.WeixinApiInvoker;
import com.dongzeviva.weixin.utils.PropUtil;
import com.dongzeviva.weixin.weixin.common.PayUtils;
import com.dongzeviva.weixin.weixin.common.XmlUtils;

public class CallWeixinOrderInterfaceFromNative extends AbstractWeixinSOAServieHandle {
	private static final Logger LOGGER = Logger.getLogger(CallWeixinOrderInterfaceFromNative.class);
	private static final String appid = PropUtil.getProperty("server.weixin.pay.appid");
	private static final String mch_id = PropUtil.getProperty("server.weixin.pay.mchid");
	private static final String key = PropUtil.getProperty("server.weixin.pay.key");

	@Override
	public SOAResponseMessage handle(WeixinContext context,
									 SOARequestMessage message) throws Exception {
		//  调用统一下单接口
		String uname = message.getUname();
		WeixinPublicNumber publicNumber = context.getWeixinPublicNumber(uname);
		if (publicNumber == null) {
			return new SOAResponseMessage(1000, "unknow uname:" + uname);
		}
//		String appid = publicNumber.getApp_id();
//		String mch_id = publicNumber.getMch_id();
//		String key = publicNumber.getKey();
		String param = message.getStringParam();
		param =  URLDecoder.decode(param,"UTF-8");
		JSONObject orderInfo = JSONObject.parseObject(param);
		Map<String, String> map = parseOrderInfo(orderInfo);
		if(map == null){
			return new SOAResponseMessage(1000, "core文件中server.weixin.pay.notifyurl2必须配置，请检查");
		}
		map.put("appid", appid); 
		map.put("mch_id", mch_id); 
		map.put("nonce_str", RandomStringUtils.randomAlphanumeric(16));// 随机生成16位验证字符
		String signValue = PayUtils.paySign(map, key);
		if (StringUtils.isBlank(signValue)) {
			return new SOAResponseMessage(1000, "sign wrong");
		}
		map.put("sign", signValue);
		String xml = XmlUtils.maptoXml(map);
		WeixinApiInvoker invoker = context.getWeixinApiInvoker();
		String result = invoker.callWeixinOrderInterface(xml);
		Map<String, String> resultMap = XmlUtils.parseContent(result);
		if ("FAIL".equalsIgnoreCase(resultMap.get("return_code"))) {
			return new SOAResponseMessage(1000, "return_msg:" + resultMap.get("return_msg"));
		} else if ("FAIL".equalsIgnoreCase(resultMap.get("result_code"))) {
			return new SOAResponseMessage(1000, "err_code:"	+ resultMap.get("err_code") + ";err_code_des:"
					+ resultMap.get("err_code_des"));
		} else {
			Map<String,String> params = new HashMap<String,String>();
			params.put("return_code",resultMap.get("return_code"));
			params.put("result_code",resultMap.get("result_code"));
			params.put("appid", appid);
			params.put("mch_id", mch_id);
			params.put("prepay_id", resultMap.get("prepay_id"));
			params.put("nonce_str", RandomStringUtils.randomAlphanumeric(16));
			String paySign = PayUtils.paySign(params, key);
			params.put("sign", paySign);
			JSONObject json = new JSONObject();
			Iterator<String> it = params.keySet().iterator();  
		       while (it.hasNext()) {  
		           String keyStr = it.next().toString();
		           String valueStr = params.get(keyStr);
		           json.put(keyStr, valueStr);
		       }
			return new SOAResponseMessage(0, json.toString());
		}
	}

	private Map<String, String> parseOrderInfo(JSONObject orderInfo) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("trade_type", orderInfo.getString("trade_type"));
		if (orderInfo.getString("product_id") != null) {
			map.put("product_id", orderInfo.getString("product_id"));
		}
		map.put("body", orderInfo.getString("body"));
		if (orderInfo.getString("detail") != null) {
			map.put("detail", orderInfo.getString("detail"));
		}
		if (orderInfo.getString("attach") != null) {
			map.put("attach", orderInfo.getString("attach"));
		}
		map.put("out_trade_no", orderInfo.getString("out_trade_no"));
		if (orderInfo.getString("fee_type") != null) {
			map.put("fee_type", orderInfo.getString("fee_type"));
		}
		if(orderInfo.getString("total_fee").indexOf(".")!=-1){
			throw new RuntimeException("total_fee is wrong");
		}
		Integer total_fee =orderInfo.getInteger("total_fee");
		if(total_fee <= 1){
			total_fee = 1;
		}
		map.put("total_fee", total_fee+"");
		map.put("spbill_create_ip", orderInfo.getString("spbill_create_ip"));
		if(map.get("spbill_create_ip")==null){
			//如果没有获取到ip，写一个默认的ip
			map.put("spbill_create_ip", "127.0.0.1");
		}
		if (orderInfo.getString("time_start") != null) {
			map.put("time_start", orderInfo.getString("time_start"));
		}
		if (orderInfo.getString("time_expire") != null) {
			map.put("time_expire", orderInfo.getString("time_expire"));
		}
		if (orderInfo.getString("goods_tag") != null) {
			map.put("goods_tag", orderInfo.getString("goods_tag"));
		}
//		map.put("notify_url", orderInfo.getString("notify_url"));
		String notify_url="";
		if(orderInfo.getString("notify_url")!=null){
			notify_url=orderInfo.getString("notify_url");
		}else{
			notify_url = PropUtil.getProperty("server.weixin.pay.notifyurl2");
			if(StringUtils.isBlank(notify_url)){
				LOGGER.error("core文件中server.weixin.pay.notifyurl2必须配置，请检查");
				return null;
			}
		}
		map.put("notify_url", notify_url);
		if (orderInfo.getString("limit_pay") != null) {
			map.put("limit_pay", orderInfo.getString("limit_pay"));
		}
		if (orderInfo.getString("openid")!=null) {
			map.put("openid", orderInfo.getString("openid"));
		}
		return map;
	}

}
