/**
 * 
 */
package com.web.haier.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.config.AlipayConfigInfo;
import com.web.haier.biz.IOrderInfoBiz;
import com.web.haier.biz.impl.OrderInfoBizImpl;

/**
 * @author lh
 * @data 2020年12月15日
 * Email 2944862497@qq.com
 */
@WebServlet("/notify")
public class Notify extends BaseServlet{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String,String> params = new HashMap<String,String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]
		                : valueStr + values[i] + ",";
		    }
		    //乱码解决，这段代码在出现乱码时使用
		    //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
		    params.put(name, valueStr);	
		}
	    
		boolean signVerified;
		try {
			signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfigInfo.alipay_public_key, AlipayConfigInfo.charset, AlipayConfigInfo.sign_type);
			 //调用SDK验证签名 
		    if (signVerified){
			    // TODO 验签成功后，按照支付结果异步通知中的描述，对支付结果中的业务内容进行二次校验，校验成功后在response中返回success并继续商户自身业务处理，校验失败返回failure 
		    	IOrderInfoBiz orderInfoBiz = new OrderInfoBizImpl();
		    	int result = orderInfoBiz.updateOrderStatus(params.get("out_trade_no"), params.get("gmt_create"));
		    } else {
				// TODO 验签失败则记录异常日志，并在response中返回failure. 
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	}

