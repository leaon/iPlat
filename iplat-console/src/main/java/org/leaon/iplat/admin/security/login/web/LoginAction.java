/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.login.web;

import org.leaon.iplat.admin.security.auth.service.AuthService;
import org.leaon.iplat.admin.security.login.service.LoginService;
import org.leaon.iplat.core.web.IplatActionSupport;

/**
 * 登录控制模块前端处理器。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-30			Leaon				创建LoginAction.java。
 *
 */
public class LoginAction extends IplatActionSupport {
	
	// ~ Fields ====================================================================================
	
	private String error;
	
	private AuthService authService;
	
	private LoginService loginService;
	
	
	// ~ Methods ===================================================================================
	
	/**
	 * 跳转至登录成功页面。
	 *
	 * @return
	 */
	public String goLogin(){
		return "login";
	}
	
	/**
	 * 跳转至登录失败页面。
	 *
	 * @return
	 */
	public String goIndex(){
		return "index";
	}
	
	/**
	 * 跳转至登录成功页面。
	 *
	 * @return
	 */
	public String goHome(){
		return "user-home";
		//		return "admin-home";
	}

	
	// ~ Getters & Setters =========================================================================
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public AuthService getAuthService() {
		return authService;
	}

	public void setAuthService(AuthService authService) {
		this.authService = authService;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
}
