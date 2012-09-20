/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.auth.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.leaon.iplat.admin.security.auth.dao.AuthDao;
import org.springframework.security.access.hierarchicalroles.CycleInRoleHierarchyException;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 角色继承关系实现类。通过加载数据库角色，并将角色组装成具有继承关系结构，用于授权控制。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-13			Leaon				创建RoleHierarchyDaoImpl.java。
 *
 */
public class RoleHierarchyDaoImpl implements RoleHierarchy {
	
	// ~ Fields ====================================================================================
	
	/**
	 * 日志器。
	 */
	private static final Logger logger = Logger.getLogger(RoleHierarchyDaoImpl.class);
	
	/**
	 * 角色继承连接串。格式如：“ROLE_ADMIN > ROLE_USER > ROLE_ROOT > ...”，"ROLE_ADMIN拥有最高权限，ROLE_ROOT拥有最低权限"。
	 */
    private List<String> roleHierarchyRepresentation = null;
    
    /**
     * 根角色的CODE码。
     */
    private static  final String ROOT_ROLE_CODE = "ROLE_ROOT";
    
    /**
     * 角色继承的连接符。
     */
    private static final char ROLE_CONNECTOR = '>';
    
    /**
     * 授权模块数据访问对象。
     */
    private AuthDao authDao;

    /**
     * 第一层级角色继承关系映射列表，该列表中key为最高权限角色。
     */
    private Map<GrantedAuthority, Set<GrantedAuthority>> rolesReachableInOneStepMap = null;

    /**
     * 所有层级角色继承关系映射列表，该列表中的key为各级子角色。
     */
    private Map<GrantedAuthority, Set<GrantedAuthority>> rolesReachableInOneOrMoreStepsMap = null;
    
    
    // ~ Constructors ==============================================================================
    
    /**
	 * 构造器。
	 */
	public RoleHierarchyDaoImpl(AuthDao authDao) {
		this.authDao=authDao;
		loadHierarchy();
		System.out.println();
	}

	// ~ Methods ===================================================================================
    
    /**
     * 加载角色继承关系数据。
     *
     * @param statement 加载角色的SQL映射。
     */
		private void loadHierarchy() {
    	logger.debug("加载角色继承关系...");

    	List roleHierarchyRepresentation = authDao.selectList("selectRoleHierarchy", ROOT_ROLE_CODE);
        this.roleHierarchyRepresentation = roleHierarchyRepresentation;
        
        buildRolesReachableInOneStepMap();
        buildRolesReachableInOneOrMoreStepsMap();
    }          

    public Collection<GrantedAuthority> getReachableGrantedAuthorities(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null || authorities.isEmpty()) {
            return AuthorityUtils.NO_AUTHORITIES;
        }
        Set<GrantedAuthority> reachableRoles = new HashSet<GrantedAuthority>();
        for (GrantedAuthority authority : authorities) {
            addReachableRoles(reachableRoles, authority);
            Set<GrantedAuthority> additionalReachableRoles = getRolesReachableInOneOrMoreSteps(authority);
            if (additionalReachableRoles != null) {
                reachableRoles.addAll(additionalReachableRoles);
            }
        }

            logger.debug("角色["+authorities
                    + "]从以下父角色中继承权限：[" + reachableRoles + "]");

        List<GrantedAuthority> reachableRoleList = new ArrayList<GrantedAuthority>(reachableRoles.size());
        reachableRoleList.addAll(reachableRoles);

        return reachableRoleList;
    }

    private void addReachableRoles(Set<GrantedAuthority> reachableRoles,
            GrantedAuthority authority) {

        for (GrantedAuthority testAuthority : reachableRoles) {
            String testKey = testAuthority.getAuthority();
            if ((testKey != null) && (testKey.equals(authority.getAuthority()))) {
                return;
            }
        }
        reachableRoles.add(authority);
    }

    private Set<GrantedAuthority> getRolesReachableInOneOrMoreSteps(
            GrantedAuthority authority) {

        if (authority.getAuthority() == null) {
            return null;
        }

        for (GrantedAuthority testAuthority : rolesReachableInOneOrMoreStepsMap.keySet()) {
            String testKey = testAuthority.getAuthority();
            if ((testKey != null) && (testKey.equals(authority.getAuthority()))) {
                return rolesReachableInOneOrMoreStepsMap.get(testAuthority);
            }
        }

        return null;
    }

    /**
     * 获取父角色的子角色<code>Map</code>列表，此方法将通过单次单层遍历。
     *
     */
    private void buildRolesReachableInOneStepMap() {
        Pattern pattern = Pattern.compile("(\\s*([^\\s>]+)\\s*>\\s*([^\\s>]+))");
        rolesReachableInOneStepMap = new HashMap<GrantedAuthority, Set<GrantedAuthority>>();
        for(int i=0;i<roleHierarchyRepresentation.size();i++){
        	String hierarchyChain = roleHierarchyRepresentation.get(i);
        	hierarchyChain = StringUtils.reverseDelimited(hierarchyChain, ROLE_CONNECTOR);
        	Matcher roleHierarchyMatcher = pattern.matcher(hierarchyChain);
            while (roleHierarchyMatcher.find()) {
                GrantedAuthority higherRole = new SimpleGrantedAuthority(roleHierarchyMatcher.group(2));
                GrantedAuthority lowerRole = new SimpleGrantedAuthority(roleHierarchyMatcher.group(3));
                Set<GrantedAuthority> rolesReachableInOneStepSet;

                if (!rolesReachableInOneStepMap.containsKey(higherRole)) {
                    rolesReachableInOneStepSet = new HashSet<GrantedAuthority>();
                    rolesReachableInOneStepMap.put(higherRole, rolesReachableInOneStepSet);
                } else {
                    rolesReachableInOneStepSet = rolesReachableInOneStepMap.get(higherRole);
                }
                addReachableRoles(rolesReachableInOneStepSet, lowerRole);

                logger.debug("加载角色["
                        + higherRole + "]的父角色[" + lowerRole+"]");
            }
        }
    }

   /**
    * 获取父角色的子角色<code>Map</code>列表，此方法将通过多次多层遍历。
    *
    */
    private void buildRolesReachableInOneOrMoreStepsMap() {
        rolesReachableInOneOrMoreStepsMap = new HashMap<GrantedAuthority, Set<GrantedAuthority>>();
        // iterate over all higher roles from rolesReachableInOneStepMap

        for(GrantedAuthority role : rolesReachableInOneStepMap.keySet()) {
            Set<GrantedAuthority> rolesToVisitSet = new HashSet<GrantedAuthority>();

            if (rolesReachableInOneStepMap.containsKey(role)) {
                rolesToVisitSet.addAll(rolesReachableInOneStepMap.get(role));
            }

            Set<GrantedAuthority> visitedRolesSet = new HashSet<GrantedAuthority>();

            while (!rolesToVisitSet.isEmpty()) {
                // take a role from the rolesToVisit set
                GrantedAuthority aRole = rolesToVisitSet.iterator().next();
                rolesToVisitSet.remove(aRole);
                addReachableRoles(visitedRolesSet, aRole);
                if (rolesReachableInOneStepMap.containsKey(aRole)) {
                    Set<GrantedAuthority> newReachableRoles = rolesReachableInOneStepMap.get(aRole);

                    // definition of a cycle: you can reach the role you are starting from
                    if (rolesToVisitSet.contains(role) || visitedRolesSet.contains(role)) {
                        throw new CycleInRoleHierarchyException();
                    } else {
                         // no cycle
                        rolesToVisitSet.addAll(newReachableRoles);
                    }
                }
            }
            rolesReachableInOneOrMoreStepsMap.put(role, visitedRolesSet);

            logger.debug("加载角色["
                    + role + "]的父角色[" + visitedRolesSet + "]");
        }

    }

    // ~ Getters & Setters =========================================================================
    
	public AuthDao getAuthDao() {
		return authDao;
	}

	public void setAuthDao(AuthDao authDao) {
		this.authDao = authDao;
	}
	
}
