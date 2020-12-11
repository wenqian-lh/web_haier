/**
 * 
 */
package com.web.haier.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.haier.bean.AdminInfo;
import com.web.haier.biz.IAdminInfoBiz;
import com.web.haier.biz.impl.AdminInfoBizImpl;
import com.web.haier.util.RequestParamUtil;
import com.web.haier.util.SessionKeyConstant;

/**
 * 管理员控制层
 * @author lh
 * @data 2020年11月21日
 * Email 2944862497@qq.com
 */

@WebServlet("/admin")
public class AdminInfoServlet extends BaseServlet{
	private static final long serialVersionUID = -4108775601619170773L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch(op) {
		case "login" : login(request, response); break;
		case "checkAdmin" : checkAdmin(request, response); break;
		case "findByPage" : findByPage(request, response); break;
		case "add" : add(request, response); break;
		case "update" : update(request, response); break;
		case "delete" : delete(request, response); break;
		case "findByCondition" : findByCondition(request, response); break;
		case "adminOut": adminOut(request, response); break;
		default: this.error(request, response);
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	private void adminOut(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(SessionKeyConstant.ADMININFOLOGIN, null);	
	}

	/**
	 * 指定字段模糊查询管理员信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status = request.getParameter("admin_status");
		String val = request.getParameter("admin_by");
		String text = request.getParameter("admin_text");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		this.send(response, adminInfoBiz.findByCondition(status, val, text, page, rows));		
	}

	/**
	 * 添加
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminInfo adminInfo = RequestParamUtil.getParams(AdminInfo.class, request);
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		int result = adminInfoBiz.add(adminInfo);
		if(result > 0) {
			this.send(response, 200, "success");
			return;
		}
		this.send(response, 500, "error");
	}

	/**
	 * 修改
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AdminInfo adminInfo = RequestParamUtil.getParams(AdminInfo.class, request);
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		int result = adminInfoBiz.update(adminInfo);
		if(result > 0) {
			this.send(response, 200, "success");
			return;
		}
		this.send(response, 500, "error");
	}
		

	/**
	 * 删除
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer adminId = Integer.parseInt(request.getParameter("adminId"));
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		int result = adminInfoBiz.delete(adminId);
		if(result > 0) {
			this.send(response, 200, "success");
			return;
		}
		this.send(response, 500, "error");
	}

	/**
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		this.send(response, adminInfoBiz.findByPage(page, rows));	
	}

	/**
	 * 检查管理员登录，和权限验证
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void checkAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object obj = request.getSession().getAttribute(SessionKeyConstant.ADMININFOLOGIN);
		if(obj == null) {
			this.send(response, 500, "失败"); // 未登录
			return;
		}
		Object obj1 = request.getSession().getAttribute(SessionKeyConstant.ISSUPERADMIN);
		if (obj1.equals("1")) {
			this.send(response, 201, obj); // 超级管理员
			return;
		}
		this.send(response, 200, obj); // 普通管理员
	}

	/**
	 * 管理员登录
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 判断验证码输入是否正确
		String code = request.getParameter("code");
	    if( !code.equals(String.valueOf(request.getSession().getAttribute(SessionKeyConstant.VERIFICATIONCODE)))) {
	    	this.send(response, 501);
	    	return;
	    }
	    // 获取账号和密码
	    String adminId = request.getParameter("adminId");
		String pwd = request.getParameter("pwd");
		AdminInfo adminInfo = new AdminInfo();
		IAdminInfoBiz adminInfoBiz = new AdminInfoBizImpl();
		adminInfo = adminInfoBiz.login(adminId, pwd);
		if (adminInfo != null) {
			request.getSession().setAttribute(SessionKeyConstant.ADMININFOLOGIN, adminInfo);
			// 判断该管理员是否是超级管理员
			if (adminInfo.getStatus() == 1) {
				request.getSession().setAttribute(SessionKeyConstant.ISSUPERADMIN, "1"); // 权限控制
				this.send(response, 200, adminInfo);
				return;
			}	
			request.getSession().setAttribute(SessionKeyConstant.ISSUPERADMIN, "0"); // 权限控制
			this.send(response, 200, adminInfo); // 超级管理员登录
			return;
		}
		this.send(response, 500, "失败"); // 登录失败
		return;
	}
}
