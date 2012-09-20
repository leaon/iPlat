/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.security.auth.filter;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * TODO
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-12			Leaon				创建IplatLogoutAuthenticationFilter.java。
 *
 */
public class IplatLogoutAuthenticationFilter extends LogoutFilter {

	/**
	 * @param logoutSuccessHandler
	 * @param handlers
	 */
	public IplatLogoutAuthenticationFilter(
			LogoutSuccessHandler logoutSuccessHandler,
			LogoutHandler... handlers) {
		super(logoutSuccessHandler, handlers);
	}

	/**
	 * @param logoutSuccessUrl
	 * @param handlers
	 */
	public IplatLogoutAuthenticationFilter(String logoutSuccessUrl,
			LogoutHandler... handlers) {
		super(logoutSuccessUrl, handlers);
	}

}
