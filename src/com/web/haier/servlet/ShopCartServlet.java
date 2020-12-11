package com.web.haier.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.haier.bean.MemberInfo;
import com.web.haier.bean.ShopCart;
import com.web.haier.biz.IShopCartBiz;
import com.web.haier.biz.impl.ShopCartBizImpl;
import com.web.haier.util.RequestParamUtil;
import com.web.haier.util.SessionKeyConstant;

@WebServlet("/shopcart")
public class ShopCartServlet extends BaseServlet {
	private static final long serialVersionUID = -4784251048088660284L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		
		switch (op) {
		case "add": add(request, response); break;
		case "delete": delete(request, response); break;
		case "findAllByMid": findAllByMid(request, response); break;
		case "update": update(request, response); break;
		case "findAllByCartId": findAllByCartId(request, response); break;
		default : this.error(request, response); break;
		}
	}
	
	/**
	 * 根据 cartId 查询 所选购物车商品信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAllByCartId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartIds = request.getParameter("cartIds");
		String cartId[] = cartIds.split(",");
		MemberInfo memberInfo = (MemberInfo) request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		String mid = memberInfo.getMid().toString();
		IShopCartBiz shopCartBiz = new ShopCartBizImpl();
		this.send(response, shopCartBiz.findAllByCartId(cartId, mid));		
	}
	
	/**
	 * 修改购物车商品数量
	 * @param request
	 * @param response
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
		String num = request.getParameter("num");
		String cartId = request.getParameter("cartId");
		IShopCartBiz shopCartBiz = new ShopCartBizImpl();
		shopCartBiz.update(cartId, num);
		
	}

	/**
	 * 根据mid 查询 所有购物车商品信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findAllByMid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		IShopCartBiz shopCartBiz = new ShopCartBizImpl();
		this.send(response, shopCartBiz.findAllByMid(mid));
	}
	
	/**
	 * 删除 购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartIds = request.getParameter("cartIds");
		String cartId[] = cartIds.split(",");
		IShopCartBiz shopCartBiz = new ShopCartBizImpl(); 
		int result = shopCartBiz.delete(cartId); 
		if (result > 0) { // 说明 添加成功
			this.send(response, 200, "成功");
			return; 
		} 
		this.send(response, 500, "失败");		
		return;
	}

	/**
	 * 添加 购物车
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ShopCart shopCart = RequestParamUtil.getParams(ShopCart.class, request);
		
		IShopCartBiz shopCartBiz = new ShopCartBizImpl();
		int result = shopCartBiz.add(shopCart);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}
}
