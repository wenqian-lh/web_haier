/**
 * 
 */
package com.web.haier.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.web.haier.bean.SkuInfo;
import com.web.haier.biz.ISkuInfoBiz;
import com.web.haier.biz.impl.SkuInfoBizImpl;
import com.web.haier.util.FileUploadUtil;

/**
 * @author lh
 * @data 2020年11月29日
 * Email 2944862497@qq.com
 */
@WebServlet("/skuinfo")
public class SkuInfoServlet extends BaseServlet{
	private static final long serialVersionUID = 2957050657505847547L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch(op) {
		 case "findByPage": findByPage(request, response); break;		
		 case "add": add(request, response); break;
		 case "update": update(request,response); break; 
		 case "findBySid": findBySid(request, response); break; 
		 case "findCondition": findCondition(request, response); break;
		 case "getAttributes": getAttributes(request, response); break; 
		// case "findByFirst": findByFirst(request,response); break;
		// case "upload": upload(request, response); break;		
		 default: this.error(request, response);
		}
	}

	/**
	 * 根据商品编号获取商品属性
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getAttributes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
		ISkuInfoBiz skuInfoBiz = new SkuInfoBizImpl();
		this.send(response, skuInfoBiz.getAttributes(gid));		
	}

	/**
	 * 按条件查询商品信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tid = request.getParameter("tid");
		String pid = request.getParameter("pid");
		String sname = request.getParameter("sname");
		String status = request.getParameter("status");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		ISkuInfoBiz skuInfoBiz = new SkuInfoBizImpl();
		this.send(response, skuInfoBiz.findCondition(tid, pid, sname, status, page, rows));
		
	}

	/**
	 * 修改商品信息
	 * @param request
	 * @param response
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
			SkuInfo skuInfo = fileUploadUtil.uploads(SkuInfo.class, pageContext);
			ISkuInfoBiz skuInfoBiz = new SkuInfoBizImpl();
			int result = skuInfoBiz.update(skuInfo);
			if(result > 0) {
				this.send(response, 200, "成功");
				return;
			}
			this.send(response, 500, "失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findBySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");
		ISkuInfoBiz skuInfoBiz = new SkuInfoBizImpl();
		this.send(response, skuInfoBiz.findBySid(sid));	
	}

	/**
	 * 添加商品信息
	 * @param request
	 * @param response
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
			SkuInfo skuInfo = fileUploadUtil.uploads(SkuInfo.class, pageContext);
			ISkuInfoBiz skuInfoBiz = new SkuInfoBizImpl();
			int result = skuInfoBiz.add(skuInfo);
			if(result > 0) {
				this.send(response, 200, "成功");
				return;
			}
			this.send(response, 500, "失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * 库存信息分页查询
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
	
		ISkuInfoBiz skuInfoBiz = new SkuInfoBizImpl();
		this.send(response, skuInfoBiz.findByPage(page, rows));
		
	}

}
