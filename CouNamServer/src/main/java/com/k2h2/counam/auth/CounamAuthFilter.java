package com.k2h2.counam.auth;

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

import org.springframework.stereotype.Component;

@Component("counamAuthFilter")
public class CounamAuthFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		if(userId == null) {
			//request.getRequestDispatcher("/m/login.html").forward(request, response);
			System.out.println("sdf");
			res.sendRedirect("/counam/m/login.html");
		} else {
			chain.doFilter(request, response);	
		}
	}
}