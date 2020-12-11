/**
 * 
 */
package com.web.haier.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.haier.bean.AddressInfo;
import com.web.haier.bean.MemberInfo;
import com.web.haier.biz.IAddressInfoBiz;
import com.web.haier.biz.impl.AddressInfoBizImpl;
import com.web.haier.util.RequestParamUtil;
import com.web.haier.util.SessionKeyConstant;

/**
 * 收货地址管理控制层
 * @author lh
 * @data 2020年12月6日
 * Email 2944862497@qq.com
 */
@WebServlet("/address")
public class AddressInfoServlet extends BaseServlet{
	private static final long serialVersionUID = 1674272053609816503L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch(op) {
		case "add": add(request, response); break; 
		case "findAll": findAll(request, response); break;
		case "update": update(request, response); break;
		case "delete": delete(request, response); break;
		case "setDefault": setDefault(request, response); break;
		default: this.error(request, response); break;
		}
	}
	
	/**
	 * 设置 默认地址
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void setDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String onaddrId = request.getParameter("onaddrId");
		String addrId = request.getParameter("addrId");
		IAddressInfoBiz addressInfoBiz = new AddressInfoBizImpl();
		int result = addressInfoBiz.setDefault(onaddrId, addrId);
		if (result > 0) {
			this.send(response, 200);
			return;
		}
		this.send(response, 500);
	}

	/**
	 * 删除地址信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String addrId = request.getParameter("addrId");
		IAddressInfoBiz addressInfoBiz = new AddressInfoBizImpl();
		int result = addressInfoBiz.delete(addrId);
		if (result > 0) {
			this.send(response, 200);
			return;
		}
		this.send(response, 500);
	}

	/**
	 * 修改收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestParamUtil requestparamUtil = new RequestParamUtil();
		AddressInfo addressInfo = requestparamUtil.getParams(AddressInfo.class, request);
		IAddressInfoBiz addressInfoBiz = new AddressInfoBizImpl();
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		addressInfo.setMid(memberInfo.getMid());
		int result = addressInfoBiz.update(addressInfo);
		if (result > 0) {
			this.send(response, 200);
			return;
		}
		this.send(response, 500);
		
	}

	/**
	 * 查询用户所有收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IAddressInfoBiz addressInfoBiz = new AddressInfoBizImpl();
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		this.send(response, addressInfoBiz.findAll(String.valueOf(memberInfo.getMid())));
	}

	/**
	 * 添加收货地址
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestParamUtil requestparamUtil = new RequestParamUtil();
		AddressInfo addressInfo = requestparamUtil.getParams(AddressInfo.class, request);
		IAddressInfoBiz addressInfoBiz = new AddressInfoBizImpl();
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		addressInfo.setMid(memberInfo.getMid());
		int result = addressInfoBiz.add(addressInfo);
		if (result > 0) {
			this.send(response, 200);
			return;
		}
		this.send(response, 500);
	}
}
