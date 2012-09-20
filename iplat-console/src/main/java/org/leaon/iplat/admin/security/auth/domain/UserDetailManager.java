/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.auth.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.leaon.iplat.admin.security.auth.dao.AuthDao;
import org.leaon.iplat.admin.security.auth.model.Auth;
import org.leaon.iplat.admin.security.unit.account.model.Account;
import org.leaon.iplat.core.security.auth.IplatUserDetailService;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 授权模块账户管理器。用于账户登录时加载账户权限及访问控制。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-6				Leaon				创建UserDetailManager.java。
 *
 */
public class UserDetailManager extends IplatUserDetailService{
	
	// ~ Fields ====================================================================================
	
	/**
	 * 数据访问对象，用于加载权限数据。
	 */
	private AuthDao authDao;

	
	// ~ Methods ===================================================================================
	
	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.security.auth.IplatUserDetailService#loadUserByUsername(java.lang.String)
	 */
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Auth accountAuth = (Auth) authDao.selectOne("selectAccountPolicyAuth",
				username);
		List accountRoleList = new ArrayList();
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		Auth accountRoleAuth = null;
		if (accountAuth != null) {
			accountRoleList = authDao.selectList("selectAccountRoleAuth",
					accountAuth.getAccountId());
		}
		for (int i = 0; i < accountRoleList.size(); i++) {
			accountRoleAuth = (Auth) accountRoleList.get(i);
			GrantedAuthority auth = new SimpleGrantedAuthority(
					accountRoleAuth.getRoleCode());
			auths.add(auth);
		}
		//初始化登录账户信息。
		Account account = new Account(accountAuth.getAccountId(),
				accountAuth.getAccountName(), accountAuth.getAccountPassword(),
				accountAuth.getAccountType(),
				accountAuth.getAccountLastLogin(), null, null,
				accountAuth.getAccountExpireTime(),
				accountAuth.getAccountStatus(), accountAuth.getPolicyId(),
				auths, true, true, true, true);
		
		//进行账户合法性检测。根据账户的策略文件对账户进行可用性检测。
		//validateAccount(accountAuth);
		return account;
	}
	
	/**
	 * 根据账户的安全策略，检测账户当前状态是否为合法账户。
	 *
	 * @param account 待检测账户。
	 * @param policy 账户的安全策略。
	 */
	private boolean validateAccount(Auth accountAuth){
		return true;
	}
	
	// ~ Getters & Setters ===========================================================================

	public AuthDao getAuthDao() {
		return authDao;
	}

	public void setAuthDao(AuthDao authDao) {
		this.authDao = authDao;
	}

}
