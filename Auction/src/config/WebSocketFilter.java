package config;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jul 5, 2018
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * Jul 5, 2018        	Vinh          	Create
 */

@WebFilter("/ServerEndPoint")
public class WebSocketFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		final PrincipalWithSession p = new PrincipalWithSession(httpRequest.getSession());
		HttpServletRequestWrapper wrappedRequest = new HttpServletRequestWrapper(httpRequest) {
			@Override
			public Principal getUserPrincipal() {
				return p;
			}
		};
		chain.doFilter(wrappedRequest, response);
	}

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}
}