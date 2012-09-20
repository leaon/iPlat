/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.security.auth;

/**
 * URL匹配器，用于匹配前台请求的URL地址。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-22			Leaon				创建URLMatcher.java。
 *
 */
public interface UrlMatcher {
	Object compile(String paramString);

	boolean pathMatchesUrl(Object paramObject,
			String paramString);

	String getUniversalMatchPattern();

	boolean requiresLowerCaseUrl();

}
