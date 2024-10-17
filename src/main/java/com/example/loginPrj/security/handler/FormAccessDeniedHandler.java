package com.example.loginPrj.security.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;


public class FormAccessDeniedHandler implements AccessDeniedHandler {
	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private final String errorPage;

	public FormAccessDeniedHandler(String errorPage) {
		this.errorPage = errorPage;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

		System.out.println(accessDeniedException.getMessage());
		System.out.println(accessDeniedException);
		redirectStrategy.sendRedirect(request, response, errorPage);

	}
}
