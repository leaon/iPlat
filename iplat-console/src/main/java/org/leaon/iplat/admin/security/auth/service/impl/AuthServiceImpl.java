/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.auth.service.impl;

import java.util.List;
import java.util.Map;

import org.leaon.iplat.admin.security.auth.dao.AuthDao;
import org.leaon.iplat.admin.security.auth.model.Auth;
import org.leaon.iplat.admin.security.auth.service.AuthService;

/**
 * TODO
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-6			Leaon				创建AuthServiceImpl.java。
 *
 */
public class AuthServiceImpl implements AuthService{
	
	// ~ Fields ====================================================================================
	
	/**
	 * 授权管理模块数据访问对象。
	 */
	private AuthDao authDao;
	
	
	// ~ Getters & Setters ===========================================================================
	
	/**
	 * 获取authDao的值。
	 *
	 * @return 返回authDao的值。
	 */
	public AuthDao getAuthDao() {
		return authDao;
	}

	/**
	 * 设置authDao的值。 
	 * 
	 * @param authDao 要设置的authDao的值。
	 */
	public void setAuthDao(AuthDao authDao) {
		this.authDao = authDao;
	}

	// ~ Methods ===================================================================================
	
	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.auth.service.AuthService#getAuth(java.lang.String)
	 */
	public Auth queryAuthDetails(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.auth.service.AuthService#getAuthList(java.lang.Object)
	 */
	public List queryAuthList(Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.auth.service.AuthService#getAuthMap(java.lang.Object, java.lang.String)
	 */
	public Map queryAuthMap(Object parameter, String mapKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.auth.service.AuthService#addAuth(org.leaon.iplat.admin.security.auth.model.Auth)
	 */
	public int addAuth(Auth Auth) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.auth.service.AuthService#modifyAuth(org.leaon.iplat.admin.security.auth.model.Auth)
	 */
	public int modifyAuth(Auth Auth) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.auth.service.AuthService#deleteAuth(java.lang.String)
	 */
	public int deleteAuth(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
