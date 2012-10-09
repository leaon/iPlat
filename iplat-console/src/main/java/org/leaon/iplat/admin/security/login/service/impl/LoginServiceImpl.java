/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.login.service.impl;

import org.leaon.iplat.admin.security.login.dao.LoginDao;
import org.leaon.iplat.admin.security.login.service.LoginService;

/**
 * 登录控制模块服务接口实现类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-30			Leaon				创建LoginServiceImpl.java。
 *
 */
public class LoginServiceImpl implements LoginService {
	
	// ~ Fields ====================================================================================
	
	private LoginDao loginDao;

	
	// ~ Constructors ==============================================================================
	
	// ~ Methods ===================================================================================
	
	// ~ Getters & Setters =========================================================================
	
	public LoginDao getLoginDao() {
		return loginDao;
	}

	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
}
