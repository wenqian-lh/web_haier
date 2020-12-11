package com.web.haier.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.web.haier.bean.MemberInfo;
import com.web.haier.biz.IMemberInfoBiz;
import com.web.haier.biz.impl.MemberInfoBizImpl;
import com.web.haier.util.FileUploadUtil;
import com.web.haier.util.MyUtil;
import com.web.haier.util.RequestParamUtil;
import com.web.haier.util.SessionKeyConstant;
import com.web.haier.util.StringUtil;

/**
 * 会员 控制
 * @author admin
 *
 */
@WebServlet("/member")
public class MemberInfoServlet extends BaseServlet {
	private static final long serialVersionUID = 1865483082245657427L;
	private String email = "";
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");	
		
		switch (op) {
		case "findByMid": findByMid(request, response); break;	
		case "findAll": findAll(request, response); break;	
		case "findByPage": findByPage(request, response); break;	
		case "add": add(request, response); break;	
		case "update": update(request, response); break;
		case "delete": delete(request, response); break;
		case "findCondition": findCondition(request, response); break;	// 多条件组合分页查询
		case "sendVerifyCode": sendVerifyCode(request, response); break;
		case "reg": reg(request, response); break;
		case "login": login(request, response); break;	// 登录
		case "check": check(request, response); break;	// 检查是否登录
		case "out": out(request, response); break;		// 退出
		case "save": save(request, response); break;
		default: this.error(request, response); break;
		}
	}

	private void save(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
			MemberInfo mf = fileUploadUtil.uploads(MemberInfo.class, pageContext);
			IMemberInfoBiz MemberInfoBiz = new MemberInfoBizImpl();
			if (StringUtil.checkNull(mf.getPhoto())) {
			     mf.setPhoto(MemberInfoBiz.getPhoto(mf).getPhoto()); 
			}
			
			int result = MemberInfoBiz.save(mf);
			if (result > 0) {
				request.getSession().setAttribute(SessionKeyConstant.MEMBERINFOLOGIN, mf);
				this.send(response, 200, "成功");
				return;
			}
			this.send(response, 500, "失败");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void out(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().setAttribute(SessionKeyConstant.MEMBERINFOLOGIN, null);	
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 判断验证码输入是否正确
		String code = request.getParameter("code");
	    if( !code.equals(String.valueOf(request.getSession().getAttribute(SessionKeyConstant.VERIFICATIONCODE)))) {
	    	this.send(response, 501);
	    	return;
	    }
	 	String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		IMemberInfoBiz memberInfoBiz = new MemberInfoBizImpl();
		MemberInfo mf = memberInfoBiz.login(email, pwd);
		if (mf != null) {
			request.getSession().setAttribute(SessionKeyConstant.MEMBERINFOLOGIN, mf);
			this.send(response, 200, "成功");
		}
		this.send(response, 500, "失败");
	}

	private void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object obj =  request.getSession().getAttribute(SessionKeyConstant.MEMBERINFOLOGIN);
		if (obj == null) {
			this.send(response, 500, "失败");
			return;
		}
		this.send(response, 200, obj);
	}

	private void reg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberInfo memberInfo = RequestParamUtil.getParams(MemberInfo.class, request);
		if(!this.email.equals(memberInfo.getEmail())) {
			this.send(response, 500, "邮箱不一致");
			return;
		}		
		// 调用 业务模型层,处理
		IMemberInfoBiz memberInfoBiz = new MemberInfoBizImpl();
		int result = memberInfoBiz.reg(memberInfo);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}

	private void sendVerifyCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		this.email = email;
		MyUtil myUtil = new MyUtil();
		String a = myUtil.senEmail(email);
		//String a = "123456";
		this.send(response, 200, a);
	}

	private void findByMid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		IMemberInfoBiz MemberInfoBiz = new MemberInfoBizImpl();
		MemberInfo MemberInfo = MemberInfoBiz.findByMid(mid);
		if (MemberInfo == null) {
			this.send(response, 400, null);
			return;
		}
		this.send(response, 200, MemberInfo);
	}

	private void findCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String way = request.getParameter("way");
		String zway = request.getParameter("zway");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		IMemberInfoBiz MemberInfoBiz = new MemberInfoBizImpl();
		this.send(response, MemberInfoBiz.findByCondition(way, zway, page, rows));
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		MemberInfo MemberInfo = RequestParamUtil.getParams(MemberInfo.class, request);
		
		// 调用 业务模型层,处理
		IMemberInfoBiz MemberInfoBiz = new MemberInfoBizImpl();
		int result = MemberInfoBiz.delete(MemberInfo);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}

	/**
	 * 修改 会员信息
	 * @param request
	 * @param response
	 * @throws  ServletException
	 * @throws  IOException
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberInfo MemberInfo = RequestParamUtil.getParams(MemberInfo.class, request);
		
		// 调用 业务模型层,处理
		IMemberInfoBiz MemberInfoBiz = new MemberInfoBizImpl();
		int result = MemberInfoBiz.update(MemberInfo);
		if (result > 0) {	// 说明 添加成功
			this.send(response, 200, "成功");
			return;
		}
		this.send(response, 500, "失败");
		return;
	}

	/**
	 * 添加 会员
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		MemberInfo MemberInfo = RequestParamUtil.getParams(MemberInfo.class, request);
		
		// 调用 业务模型层,处理
		IMemberInfoBiz MemberInfoBiz = new MemberInfoBizImpl();
		int result = MemberInfoBiz.add(MemberInfo);
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
		
		IMemberInfoBiz MemberInfoBiz = new MemberInfoBizImpl();
		this.send(response, MemberInfoBiz.findByPage(page, rows));
	}

	/**
	 * 查询所有 会员信息
	 * @param req
	 * @param resp
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findAll(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		IMemberInfoBiz MemberInfoBiz = new MemberInfoBizImpl();
		this.send(response, MemberInfoBiz.findAll());;
	}
}
