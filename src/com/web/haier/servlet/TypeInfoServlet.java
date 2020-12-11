package com.web.haier.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.haier.bean.TypeInfo;
import com.web.haier.biz.ITypeInfoBiz;
import com.web.haier.biz.impl.TypeInfoBizImpl;
import com.web.haier.util.RequestParamUtil;

/**
 * 父分类 控制
 * @author admin
 *
 */
@WebServlet("/type")
public class TypeInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1865483082245657427L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");	// 取出请求的操作标识符
		
		switch (op) {
		case "findAll": findAll(request, response); break;	// 查询所有父分类信息
		case "findByPage": findByPage(request, response); break;	// 查询所有父分类信息
		case "add": add(request, response); break;	// 添加父分类
		case "update": update(request, response); break;	// 修改父分类
		case "delete": delete(request, response); break;
		default: this.error(request, response); break;
		}
		
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TypeInfo typeInfo = RequestParamUtil.getParams(TypeInfo.class, request);
		
		// 调用 业务模型层,处理
		ITypeInfoBiz typeInfoBiz = new TypeInfoBizImpl();
		int result = typeInfoBiz.delete(typeInfo);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}

	/**
	 * 修改 父分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		TypeInfo typeInfo = RequestParamUtil.getParams(TypeInfo.class, request);
		System.out.println(typeInfo);
		
		// 调用 业务模型层,处理
		ITypeInfoBiz typeInfoBiz = new TypeInfoBizImpl();
		int result = typeInfoBiz.update(typeInfo);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}

	/**
	 * 添加 父分类
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		TypeInfo typeInfo = RequestParamUtil.getParams(TypeInfo.class, request);
		
		// 调用 业务模型层,处理
		ITypeInfoBiz typeInfoBiz = new TypeInfoBizImpl();
		int result = typeInfoBiz.add(typeInfo);
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
		
		ITypeInfoBiz typeInfoBiz = new TypeInfoBizImpl();
		this.send(response, typeInfoBiz.findByPage(page, rows));
	}

	/**
	 * 查询所有 父分类信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ITypeInfoBiz typeInfoBiz = new TypeInfoBizImpl();
		this.send(response, typeInfoBiz.findAll());;
	}
}
