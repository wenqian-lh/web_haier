package com.web.haier.filter;
/**
 * 	字符编码过滤器
 * @author 黄程大哥的C
 *
 */

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.haier.util.StringUtil;



@WebFilter(filterName = "CharacterEncodingFilter", value="/*",
			initParams = @WebInitParam(name = "encoding", value = "utf-8"))
public class CharacterEncodingFilter  implements Filter{
	private String encoding = "utf-8";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//转换
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		//设置编码及
		req.setCharacterEncoding(encoding);
		resp.setCharacterEncoding(encoding);
		chain.doFilter(request, response);//链式调用  调用下一个过滤器
		
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 初始化参数
		String temp = filterConfig.getInitParameter("encoding");
		if(!StringUtil.checkNull(temp)) {
			
		}
	}
	
	
	
}
