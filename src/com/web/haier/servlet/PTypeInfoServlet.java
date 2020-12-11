package com.web.haier.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.haier.bean.PTypeInfo;
import com.web.haier.biz.IPTypeInfoBiz;
import com.web.haier.biz.impl.PTypeInfoBizImpl;
import com.web.haier.util.RequestParamUtil;

/**
 * 子分类 控制
 * @author admin
 *
 */
@WebServlet("/ptype")
public class PTypeInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1865483082245657427L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");	// 取出请求的操作标识符
		
		switch (op) {
		case "findAll": findAll(request, response); break;	// 查询所有子分类信息
		case "findByPage": findByPage(request, response); break;	
		case "add": add(request, response); break;	// 添加子分类
		case "update": update(request, response); break;	// 修改子分类
		case "findCondition": findCondition(request, response); break;	// 条件查询
		case "findByPid": findByPid(request, response); break;	// 按子类型编号查询查询
		case "delete": delete(request, response); break;
		default: this.error(request, response); break;
		}
		
		
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByPid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pid = request.getParameter("pid");
		IPTypeInfoBiz ptypeInfoBiz = new PTypeInfoBizImpl();
		this.send(response, ptypeInfoBiz.findByPid(pid));
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PTypeInfo ptypeInfo = RequestParamUtil.getParams(PTypeInfo.class, request);
		
		IPTypeInfoBiz ptypeInfoBiz = new PTypeInfoBizImpl();
		int result = ptypeInfoBiz.delete(ptypeInfo);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}

	private void findCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tid = request.getParameter("tid");
		String pname = request.getParameter("pname");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		IPTypeInfoBiz ptypeInfoBiz = new PTypeInfoBizImpl();
		this.send(response, ptypeInfoBiz.findCondition(tid, pname, page, rows));
	}

	/**
	 * 修改 子分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PTypeInfo ptypeInfo = RequestParamUtil.getParams(PTypeInfo.class, request);
		System.out.println(ptypeInfo);
		
		// 调用 业务模型层,处理
		IPTypeInfoBiz ptypeInfoBiz = new PTypeInfoBizImpl();
		int result = ptypeInfoBiz.update(ptypeInfo);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}

	/**
	 * 添加 子分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PTypeInfo ptypeInfo = RequestParamUtil.getParams(PTypeInfo.class, request);
		
		// 调用 业务模型层,处理
		IPTypeInfoBiz ptypeInfoBiz = new PTypeInfoBizImpl();
		int result = ptypeInfoBiz.add(ptypeInfo);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}

	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		IPTypeInfoBiz ptypeInfoBiz = new PTypeInfoBizImpl();
		this.send(response, ptypeInfoBiz.findByPage(page, rows));
	}

	/**
	 * 查询所有 子分类信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		IPTypeInfoBiz typeInfoBiz = new PTypeInfoBizImpl();
		this.send(response, typeInfoBiz.findAll());;
	}
}
