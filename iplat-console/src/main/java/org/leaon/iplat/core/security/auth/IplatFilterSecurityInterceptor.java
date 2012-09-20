/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.security.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 平台的系统安全性拦截过滤器，用于拦截过滤并验证用户的请求。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-22			Leaon				创建IplatFilterSecurityInterceptor.java。
 *
 */
public class IplatFilterSecurityInterceptor extends FilterSecurityInterceptor {
	
	/**
	 * 资源的元数据。
	 */
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

    /**
     * 执行该过滤器，该过滤器将被加载到Servlet过滤器链中，并通过反射机制执行doFilter方法。
     * 详见 {@link #invoke(FilterInvocation)} 方法。
     * 
     * @param request
     *            Servlet Request。
     * @param response
     *            Servlet Response。
     * @param chain
     *            过滤器链。
     * 
     * @throws IOException
     *            如果失败，则抛出<code>IOException</code>。
     * @throws ServletException
     *             如果失败，则抛出<code>ServletException</code>。
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        FilterInvocation fi = new FilterInvocation(request, response, chain);
        invoke(fi);
    }

    public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    public Class<? extends Object> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    /**
     * 执行过滤器链。
     *
     * @param fi 过滤器调用执行器。
     * @throws IOException 如果执行失败，则抛出<code>IOException</code>。
     * @throws ServletException 如果执行失败，则抛出<code>ServletException</code>。
     */
    public void invoke(FilterInvocation fi) throws IOException,
            ServletException {
        InterceptorStatusToken token = super.beforeInvocation(fi);
        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }
    
    /**
     * 获取资源元数据实例。
     */
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.securityMetadataSource;
    }

    /**
     * 设置资源元数据实例。
     *
     * @param newSource 新的资源元数据。
     */
    public void setSecurityMetadataSource(
            FilterInvocationSecurityMetadataSource newSource) {
        this.securityMetadataSource = newSource;
    }
    
    /**
     * 销毁过滤器。
     */
    public void destroy() {
    	
    }

    /**
     * 初始化过滤器。
     */
    public void init(FilterConfig arg0) throws ServletException {
    	
    }

}
