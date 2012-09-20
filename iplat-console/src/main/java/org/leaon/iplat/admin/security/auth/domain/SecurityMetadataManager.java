/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.auth.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.leaon.iplat.admin.security.auth.dao.AuthDao;
import org.leaon.iplat.admin.security.auth.model.Auth;
import org.leaon.iplat.core.security.auth.AntUrlPathMatcher;
import org.leaon.iplat.core.security.auth.IplatInvocationSecurityMetadataSource;
import org.leaon.iplat.core.security.auth.UrlMatcher;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.RequestMatcher;

/**
 * 资源权限元数据管理器。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-6				Leaon				创建AuthMetadataManager.java。
 *
 */
public class SecurityMetadataManager extends IplatInvocationSecurityMetadataSource {
	
	// ~ Fields ====================================================================================
	
	/**
	 * 日志器。
	 */
	private static final Logger logger = Logger.getLogger(SecurityMetadataManager.class);
	
	/**
	 * 是否将请求URL转换成小写进行匹配。
	 */
	private boolean LowerCaseComparisons = true;
	
	/**
	 * URL模式匹配器。
	 */
	private UrlMatcher urlMatcher;
	
	/**
	 * 角色权限对应表。
	 */
	private static Map<String, Collection<ConfigAttribute>> resourceMap;
	
	/**
	 * 数据访问对象，用于加载权限数据。
	 */
	private AuthDao authDao;
	

	//~ Constructors ===============================================================================
	
	/**
	 * @param requestMap
	 */
	public SecurityMetadataManager(
			LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap) {
		super(requestMap);
	}

	/**
	 * 构造方法。
	 * 
	 * @param requestMap
	 * @param lowerCaseComparisons
	 * @param urlMatcher
	 * @param authDao
	 */
	public SecurityMetadataManager(
			boolean lowerCaseComparisons, UrlMatcher urlMatcher, AuthDao authDao) {
		super(null);
		LowerCaseComparisons = lowerCaseComparisons;
		this.urlMatcher = urlMatcher;
		this.authDao = authDao;
		loadResourceDefine();
	}

	
	// ~ Methods ===================================================================================
	
	/**
	 * 加载系统资源的权限配置。
	 * 
	 */
	public void loadResourceDefine() {
		logger.debug(  
                "开始加载系统权限..."); 
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		Collection<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		// 加载角色菜单映射。
		List authList = authDao.selectList("selectRoleMenuAuth", null);
		
		if(authList!=null&&authList.size()>0){
			Auth currentAuth = null;
			for (int i=0;i<authList.size();i++){
				Auth nextAuth = (Auth)authList.get(i);
				if(currentAuth==null){
					currentAuth = nextAuth;
				}
				if(!currentAuth.getMenuId().equals(nextAuth.getMenuId())){
					if(currentAuth.getMenuUrl()!=null){
						resourceMap.put(currentAuth.getMenuUrl(), attributes);
					}
				}
				ConfigAttribute attribute = new SecurityConfig(currentAuth.getRoleCode());
				attributes.add(attribute);
			}
			if(currentAuth.getMenuUrl()!=null){
				resourceMap.put(currentAuth.getMenuUrl(), attributes);
			}
			
			//输出权限加载信息。
	        StringBuffer strConfig = new StringBuffer();
	        Iterator<String> ite = resourceMap.keySet().iterator();   
	        while (ite.hasNext()) {   
	            String resURL = ite.next();   
	            strConfig.append("资源访问路径URL：[" + resURL + "]");   
	            strConfig.append(" , 所需要的角色权限：");   
	            Collection<ConfigAttribute> attss = resourceMap.get(resURL);   
	            for (ConfigAttribute cg : attss) {   
	                strConfig.append("[" + cg.toString() + "]");   
	            }   
	            logger.debug( 
	                    strConfig.toString(), null);   
	            strConfig = new StringBuffer();   
	        } 
	        logger.debug(   
	                "加载系统权限完毕");  
		}
	}

	/**
	 * 根据访问的URL获取授权信息。
	 */
	public Collection<ConfigAttribute> getAttributes(Object filter)
			throws IllegalArgumentException {
		 FilterInvocation filterInvocation = (FilterInvocation) filter;   
	        String requestURI = filterInvocation.getRequestUrl();   
	  
	        Iterator<String> ite = resourceMap.keySet().iterator();   
	        while (ite.hasNext()) {   
	            String resURL = ite.next();   
	  
	            // 比较资源定义中的URL和当前请求的URL   
	            // resURL 资源定义中的URL   
	            // requestURL 当前请求的URL   
	            if (urlMatcher.pathMatchesUrl(resURL, requestURI)) {
	                Collection<ConfigAttribute> atts = resourceMap.get(resURL);   
	                String colRole = "";   
	                for (ConfigAttribute cg : atts) {   
	                    // System.out.println(cg.toString());   
	                    colRole = colRole + "," + cg.toString();   
	                }   
	                if (colRole.length() > 1) {   
	                    colRole = colRole.substring(1);   
	                }   
	  
	                logger.debug("用户请求资源URL："  
	                                + requestURI + " ，所需要的系统角色权限为：[" + colRole   
	                                + "]", null);   
	                return atts;   
	            }   
	        }   
	  
	        return null; 
	}
	
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return new ArrayList<ConfigAttribute>();
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	public void afterPropertiesSet() throws Exception {
        if (LowerCaseComparisons==true) {
        	 ((AntUrlPathMatcher) this.urlMatcher) 
             .setRequiresLowerCaseUrl(true);   
        } else {   
                ((AntUrlPathMatcher) this.urlMatcher)   
                        .setRequiresLowerCaseUrl(false);   
        }
	}

	
	// ~ Getters & Setters ===========================================================================
	
	public boolean isLowerCaseComparisons() {
		return LowerCaseComparisons;
	}

	public void setLowerCaseComparisons(boolean LowerCaseComparisons) {
		this.LowerCaseComparisons = LowerCaseComparisons;
	}

	public UrlMatcher getUrlMatcher() {
		return urlMatcher;
	}

	public void setUrlMatcher(UrlMatcher urlMatcher) {
		this.urlMatcher = urlMatcher;
	}

	public static Map<String, Collection<ConfigAttribute>> getResourceMap() {
		return resourceMap;
	}

	public static void setResourceMap(
			Map<String, Collection<ConfigAttribute>> resourceMap) {
		SecurityMetadataManager.resourceMap = resourceMap;
	}

	public AuthDao getAuthDao() {
		return authDao;
	}

	public void setAuthDao(AuthDao authDao) {
		this.authDao = authDao;
	}
}
