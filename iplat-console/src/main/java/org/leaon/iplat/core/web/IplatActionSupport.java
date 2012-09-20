/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 平台前端控制器支持，基于Struts2实现。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-30			Leaon				创建IplatActionSupport.java。
 *
 */
public class IplatActionSupport extends ActionSupport implements ServletRequestAware,SessionAware,ApplicationAware {
	
	// ~ Fields =======================================================================================
	
	/**
	 * 成功返回标识。
	 */
	protected static final String SUCCESS = "success";
	
	/**
	 * 失败返回标识。
	 */
	protected static final String ERROR = "error";
	
	/**
	 * HttpServletRequest 对象。
	 */
	protected HttpServletRequest request;
	
	/**
	 * <code>Map</code>形式绑定的<code>HttpSession</code>。
	 */
	protected Map session;
	
	/**
	 * <code>Map</code>形式绑定的<code>ApplicationContext</code>。
	 */
	protected Map context;

	/**
	 * 模块名称。
	 */
	protected String moduleName;
	
	
	// ~ Methods ===================================================================================
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ServletRequestAware#setServletRequest(javax.servlet.http.HttpServletRequest)
	 */
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.SessionAware#setSession(java.util.Map)
	 */
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	/* (non-Javadoc)
	 * @see org.apache.struts2.interceptor.ApplicationAware#setApplication(java.util.Map)
	 */
	@Override
	public void setApplication(Map<String, Object> context) {
		this.context = context;
	}
	
	
	// ~ Getters & Setters =========================================================================
	
	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

}
