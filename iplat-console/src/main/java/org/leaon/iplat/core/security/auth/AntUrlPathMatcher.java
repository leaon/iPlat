/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.security.auth;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;


/**
 * 基于Ant的URL路径匹配器。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-22			Leaon				创建AntURLPathMatcher.java。
 *
 */
public class AntUrlPathMatcher implements UrlMatcher {
	
	/**
	 * 是否需要小写字符的URL。
	 */
	private boolean requiresLowerCaseUrl;
	
	/**
	 * 路径匹配器。
	 */
	private PathMatcher pathMatcher;
	
	/**
	 * 缺省构造方法。
	 */
	public AntUrlPathMatcher() {
		this(true);
	}
	
	/**
	 * 带参数的构造方法，指定是否需要指定小写类型的URL。
	 * 
	 * @param requiresLowerCaseUrl 是否需要指定小写类型的URL。
	 */
	public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
		this.requiresLowerCaseUrl = true;
		this.pathMatcher = new AntPathMatcher();
		this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	}

	/**
	 * 获取pathMatcher的值。
	 *
	 * @return 返回pathMatcher的值。
	 */
	public PathMatcher getPathMatcher() {
		return pathMatcher;
	}

	/**
	 * 设置pathMatcher的值。 
	 * 
	 * @param pathMatcher 要设置的pathMatcher的值。
	 */
	public void setPathMatcher(PathMatcher pathMatcher) {
		this.pathMatcher = pathMatcher;
	}

	/**
	 * 获取requiresLowerCaseUrl的值。
	 *
	 * @return 返回requiresLowerCaseUrl的值。
	 */
	public boolean isRequiresLowerCaseUrl() {
		return requiresLowerCaseUrl;
	}

	/**
	 * 编译路径为指定的格式。
	 */
	public String compile(String path) {
		if (this.requiresLowerCaseUrl) {
			return path.toLowerCase();
		}

		return path;
	}
	
	/**
	 * 设置requiresLowerCaseUrl的值。
	 *
	 * @param requiresLowerCaseUrl 是否需要指定小写类型的URL。
	 */
	public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
		this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	}

	/**
	 * 匹配URL。
	 */
	public boolean pathMatchesUrl(Object path, String url) {
		if (("/**".equals(path)) || ("**".equals(path))) {
			return true;
		}
		return this.pathMatcher.match((String) path, url);
	}

	/**
	 * 获取全局的匹配模式。
	 */
	public String getUniversalMatchPattern() {
		return "/**";
	}
	
	/**
	 * 获取requiresLowerCaseUrl的值。
	 */
	public boolean requiresLowerCaseUrl() {
		return this.requiresLowerCaseUrl;
	}
	
	/**
	 * 重写toString方法。
	 */
	public String toString() {
		return super.getClass().getName() + "[requiresLowerCase='"
				+ this.requiresLowerCaseUrl + "']";
	}

}
