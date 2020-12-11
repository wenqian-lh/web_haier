/**
 * 
 */
package com.web.haier.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

import com.web.haier.bean.GoodsInfo;
import com.web.haier.biz.IGoodsInfoBiz;
import com.web.haier.biz.impl.GoodsInfoBizImpl;
import com.web.haier.util.FileUploadUtil;

/**
 * 商品信息管理控制层
 * @author lh
 * @data 2020年11月27日
 * Email 2944862497@qq.com
 */
@WebServlet("/goods")
public class GoodsInfoServlet extends BaseServlet{
	private static final long serialVersionUID = 4892125069876410612L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch(op) {
		 case "findByPage": findByPage(request, response); break;		
		 case "add": add(request, response); break;
		 case "update": update(request,response); break; 
		 case "findByGid": findByGid(request, response); break; 
		 case "findCondition": findCondition(request, response); break;	
		 case "findConditioners": findConditioners(request, response); break;	
		 case "getGoodsName": getGoodsName(request, response); break;
		 case "upload": upload(request, response); break;
		 case "findByFirst": findByFirst(request, response); break;
		 case "finds": finds(request, response); break;
		 case "findByName": findByName(request, response); break;
		 default: this.error(request, response);
		}
	}

	/**
	 * 条件搜索
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findConditioners(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tid = request.getParameter("tid");
		String pid = request.getParameter("pid");
		String price = request.getParameter("price");
		String desc = request.getParameter("desc");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		//System.out.println(tid + "--" + pid + "--" + price + "--" + page + "--" + rows);
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.putIfAbsent("total", goodsInfoBiz.total(tid, pid, price));
		map.putIfAbsent("rows", goodsInfoBiz.findByConditioners(tid, pid, price, desc, page, rows));
		this.send(response, map);
	}

	/**
	 * 按名称查询商品信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		String name = request.getParameter("name");
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.putIfAbsent("total", goodsInfoBiz.total("", "", name, "1"));
		map.putIfAbsent("rows", goodsInfoBiz.findByName(name, page, rows));
		this.send(response, map);
	}

	private void finds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IGoodsInfoBiz GoodsInfoBiz = new GoodsInfoBizImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		this.send(response, GoodsInfoBiz.finds(page, rows));
	}

	private void findByFirst(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		
		// 要返回第一页的数据 以及 总记录数
		Map<String, Object> map = new HashMap<String, Object>();
		map.putIfAbsent("total", goodsInfoBiz.total());
		map.putIfAbsent("rows", goodsInfoBiz.finds(page, rows));
		this.send(response, map);
	}

	/**
	 * 按条件查询商品信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void getGoodsName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tid = request.getParameter("tid");
		String pid = request.getParameter("pid");
		String status = request.getParameter("status");
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.getGoodsName(tid, pid, status));
		
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
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBiz.findByCondition(tid, pid, sname, status, page, rows));
	}

	/**
	 * 修改商品信息
	 * @param request
	 * @param response
	 */
	private void update(HttpServletRequest request, HttpServletResponse response) {
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		GoodsInfo goodsInfo;
		try {
			goodsInfo = fileUploadUtil.uploads(GoodsInfo.class, pageContext);
			IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
			int result = goodsInfoBiz.update(goodsInfo);
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
	 * 按商品编号查询信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByGid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
		IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
		GoodsInfo  goodsInfo = goodsInfoBiz.findByGid(gid);
		if(goodsInfo == null) {
			this.send(response, 500);		
			return;
		}
		this.send(response, 200, goodsInfo);	
	}

	/**
	 * 富文本编辑器的图片上传
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUploadUtil fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		Map<String, Object> result = new HashMap<String, Object>();	
		Map<String, String> map;
		try {
			map = fileUploadUtil.uploads(pageContext);
			result.put("filename", "图片");
			result.put("url", "../../" + map.get("upload"));
			result.put("uploaded", "1");
		} catch (Exception e) {
			e.printStackTrace();
		}	
		this.send(response, result);	
	}

	/**
	 * 添加商品信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FileUploadUtil  fileUploadUtil = new FileUploadUtil();
		PageContext pageContext = JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
		try {
			GoodsInfo goodsInfo = fileUploadUtil.uploads(GoodsInfo.class, pageContext);
			System.out.println(goodsInfo);
			IGoodsInfoBiz goodsInfoBiz = new GoodsInfoBizImpl();
			int result = goodsInfoBiz.add(goodsInfo);
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
	 * 分页查询商品信息
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		int rows = Integer.parseInt(request.getParameter("rows"));
		IGoodsInfoBiz goodsInfoBIz = new GoodsInfoBizImpl();
		this.send(response, goodsInfoBIz.findByPage(page, rows));
	}

}
