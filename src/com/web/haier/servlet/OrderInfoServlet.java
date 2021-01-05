/**
 * 
 */
package com.web.haier.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfigInfo;
import com.web.haier.bean.MemberInfo;
import com.web.haier.bean.OrderInfo;
import com.web.haier.biz.IOrderInfoBiz;
import com.web.haier.biz.impl.OrderInfoBizImpl;
import com.web.haier.util.RequestParamUtil;
import com.web.haier.util.SessionKeyConstant;

/**
 * @author lh
 * @data 2020年12月13日
 * Email 2944862497@qq.com
 */

@WebServlet("/order")

public class OrderInfoServlet extends BaseServlet{
	
	private OrderInfo orderInfo;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6913677258135214003L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter("op");
		switch (op) {
		case "pay": pay(request, response); break;  // 沙箱支付
		case "add": add(request, response); break; // 添加订单信息
		case "addorderlist": addorderlist(request, response); break; // 添加订单详细信息
		case "finds": finds(request, response); break; // 分页查询订单信息
		default : this.error(request, response); break;
	}

}


	/**
	 * 查询所有订单信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void finds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IOrderInfoBiz orderInfoBiz = new OrderInfoBizImpl();
		this.send(response, orderInfoBiz.finds());
	}
	
	/**
	 * 沙箱支付
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AlipayClient alipayClient =  new  DefaultAlipayClient(AlipayConfigInfo.gatewayUrl , AlipayConfigInfo.app_id, AlipayConfigInfo.merchant_private_key, "JSON", AlipayConfigInfo.charset, AlipayConfigInfo.alipay_public_key, AlipayConfigInfo.sign_type);
		    AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request 
		    alipayRequest.setReturnUrl(AlipayConfigInfo.return_url);
		    alipayRequest.setNotifyUrl(AlipayConfigInfo.notify_url); //在公共参数中设置回跳和通知地址 
		    alipayRequest.setBizContent( "{"  +
			         "    \"out_trade_no\":\""+orderInfo.getOid()+"\","  +
			         "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\","  +
			         "    \"total_amount\":"+orderInfo.getTotal()+","  +
			         "    \"subject\":\"海尔电器网上支付\","  + 
			         "    \"body\":\"海尔电器\","  +
			         "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\","  +
			         "    \"extend_params\":{"  +
			         "    \"sys_service_provider_id\":\"2088511833207846\""  +
			         "    }" +
			         "  }" ); //填充业务参数 
		    String form= "" ;
		     try  {
		        form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单 
		    }  catch  (AlipayApiException e) {
		        e.printStackTrace();
		    }
		 this.send(response, 200, form);
		/*
		 * response.setContentType( "text/html;charset=" + "utf-8");
		 * response.getWriter().write(form); //直接将完整的表单html输出到页面
		 * response.getWriter().flush(); response.getWriter().close();
		 */	
	}

	/**
	 * 添加订单详细信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addorderlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderlist = request.getParameter("orderlist");
		String oid = request.getParameter("oid");
		IOrderInfoBiz orderInfoBiz = new OrderInfoBizImpl();
		int result = orderInfoBiz.addOrderList(orderlist, oid);
		if(result > 0) {
			this.send(response, 200);
			return;
		}
		this.send(response, 500);
	}

	/**
	 * 添加一条订单信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestParamUtil requestParamUtil = new RequestParamUtil();
	    orderInfo = requestParamUtil.getParams(OrderInfo.class, request);
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		Random random = new Random();
		Date dateTime = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddhhmmss");
		// 存储用户编号至订单对象
		orderInfo.setMid(memberInfo.getMid());
		// 生成订单编号
		orderInfo.setOid(System.currentTimeMillis() + String.valueOf(random.nextInt(900000) + 100000)+ft.format(dateTime)+String.valueOf(random.nextInt(90000) + 10000));			
		//订单状态
		orderInfo.setStatus(0);
		
		IOrderInfoBiz orderInfoBiz = new OrderInfoBizImpl();
		int result = orderInfoBiz.add(orderInfo);
		if(result > 0) {
			this.send(response, 200, orderInfo.getOid());	
			return;
		}
		this.send(response, 500);
	}
}