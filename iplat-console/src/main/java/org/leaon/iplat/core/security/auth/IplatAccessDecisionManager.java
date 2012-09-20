/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.security.auth;

import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyAuthoritiesMapper;
import org.springframework.security.access.hierarchicalroles.UserDetailsServiceWrapper;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * 平台访问决策管理器，实现Spring Security的AcdessDecisionManager借口，用于管理用户对系统的访问控制权限。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * @deprecated 已经被基于表决器的角色继承访问控制器{@link org.leaon.iplat.admin.security.auth.domain.RoleHierarchyDaoImpl}替代。
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-22			Leaon				创建IplatAccessDecisionManager.java。
 *
 */
public class IplatAccessDecisionManager implements AccessDecisionManager {
	/**
	 * 日志器。
	 */
	private static final Logger logger = Logger.getLogger(IplatAccessDecisionManager.class);
	
	/**
	 * 访问控制，用于决策用户是否拥有特定权限，并决定是否允许方形。
	 * 该方法将对用户授权和权限集合进行比较：
	 * 1.将通过访问的URL查找用户是否拥有对此URL的访问权限。
	 * 2.校验用户是否对特定的资源拥有权限。
	 * 3.如果用户没有授权，则抛出<code>AccessDeniedException</code>异常。
	 * 
	 * @param authentication 用户所拥有的授权。
	 * @param object 要访问的资源对象。
	 * @param configAttributes 系统权限集合。
	 */
    public void decide(Authentication authentication, Object object,
            Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
    	UserDetails userDetail = (UserDetails)authentication.getPrincipal();
        if(configAttributes == null){
        	logger.debug("没有找到对资源<"+object.toString()+">的授权，放行。");
            return;
        }
        logger.debug("用户<"+userDetail.getUsername()+">正在访问资源<"+object.toString()+">。");
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        //进行权限匹配。
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            String needRole=((SecurityConfig)ca).getAttribute(); //遍历角色权限表取出角色。
            for(GrantedAuthority ga:authentication.getAuthorities()){
            	UserDetailsServiceWrapper o;
            	RoleHierarchyAuthoritiesMapper p;
                if(needRole.equals(ga.getAuthority())){
                	logger.debug("资源<"+object.toString()+">允许用户<"+userDetail.getUsername()+">访问。");
                    return;
                }
            }
        }
        throw new AccessDeniedException("用户<"+userDetail.getUsername()+">没有权限访问资源<"+object.toString()+">。");
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }
}
