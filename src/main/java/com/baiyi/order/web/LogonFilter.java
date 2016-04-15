package com.baiyi.order.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.baiyi.order.model.User;

//jsp过滤
public class LogonFilter implements Filter {

	private final static String passuri = "/jsp/(logon|error)\\.jsp.*";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpServletRequest.getSession();

		String contextPath = httpServletRequest.getContextPath();// 项目根路径
		String servletPath = httpServletRequest.getServletPath();// 访问地址(不带项目名称)
		// String uri = httpServletRequest.getRequestURI();// 访问地址(带项目名称)

		// System.out.printf("%-20s %s%n", servletPath,
		// servletPath.matches(passuri));

		if (servletPath.matches(passuri)) {
			// System.out.println("pass uri");
			chain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}

		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			// System.out.println("not login,redirect");
			httpServletResponse.sendRedirect(contextPath + "/jsp/logon.jsp");
		} else {
			// System.out.println("is login,pass");
			chain.doFilter(httpServletRequest, httpServletResponse);
		}
	}

	@Override
	public void destroy() {

	}

}
