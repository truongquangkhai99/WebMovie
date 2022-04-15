package com.asm.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

import com.asm.model.Users;
@WebFilter(urlPatterns = {"/admin/*"})
public class AuthFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		Users user = (Users) session.getAttribute("user");
		if(user == null) {
			((HttpServletResponse) response).sendRedirect("/asm/login");
		} else if (!user.getAdmin()) {
			((HttpServletResponse) response).sendRedirect("/asm/home");
		} else {
			chain.doFilter(request, response);
		}
		
	}
	
}