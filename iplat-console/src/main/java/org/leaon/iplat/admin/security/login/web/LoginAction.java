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
	
	/**
	 * 登录过程中的错误标识。
	 */
	private String error;
	
	/**
	 * 授权模块服务类。
	 */
	private AuthService authService;
	
	/**
	 * 登录模块服务类。
	 */
	private LoginService loginService;
	
	
	// ~ Methods ===================================================================================
	
	/**
	 * 跳转至登录成功页面。
	 *
	 * @return
	 */
	public String goLogin(){
		//处理错误信息，将错误码转换为实际消息内容。
		if(error !=null){
			System.out.println(getText(error));
			addActionError(getText(error));
		}
//		if("ROLE_ADMIN"){
//			return "admin-home";
//		}else if("ROLE_USER"){
//			return "user-home";
//		}else {
//			return "login";
//		}
		return "login";
	}
	
	/**
	 * 跳转至登录失败页面。
	 *
	 * @return
	 */
	public String goIndex(){
		//判断当前用户权限，并跳转到相应的主页。
//		if("ROLE_ADMIN"){
//			return "admin-home";
//		}else if("ROLE_USER"){
//			return "user-home";
//		}else {
//			return "index";
//		}
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
