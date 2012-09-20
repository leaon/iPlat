/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.security.auth.entry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 登陆后跳转到登录前的URL。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-12			Leaon				创建LoginUrlEntryPoint.java。
 *
 */
public class LoginUrlEntryPoint implements AuthenticationEntryPoint {
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		String targetUrl = null;
		String url = request.getRequestURI(); 
		// 取得登陆前的url
		String refererUrl = request.getHeader("Referer"); 
		// 增加处理逻辑
		targetUrl = refererUrl;
		response.sendRedirect(targetUrl);
	}
}
