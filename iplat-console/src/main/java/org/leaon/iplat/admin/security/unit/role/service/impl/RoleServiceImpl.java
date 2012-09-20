/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.unit.role.service.impl;

import java.util.List;
import java.util.Map;

import org.leaon.iplat.admin.security.unit.role.dao.RoleDao;
import org.leaon.iplat.admin.security.unit.role.model.RoleModel;
import org.leaon.iplat.admin.security.unit.role.service.RoleService;

/**
 * 角色管理模块服务对象。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建RoleServiceImpl.java。
 *
 */
public class RoleServiceImpl implements RoleService {
	
	private RoleDao roleDao;

	/**
	 * 获取roleDao的值。
	 *
	 * @return 返回roleDao的值。
	 */
	public RoleDao getRoleDao() {
		return roleDao;
	}

	/**
	 * 设置roleDao的值。 
	 * 
	 * @param roleDao 要设置的roleDao的值。
	 */
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.role.service.RoleService#getRole(java.lang.String)
	 */
	public RoleModel queryRoleDetails(String id) {
		List roleList = roleDao.selectList(null, id);
		RoleModel roleModel = null;
		if(roleList!=null&&!roleList.isEmpty()){
			roleModel = (RoleModel)roleList.get(0);
		}
		return roleModel;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.role.service.RoleService#getRoleList(java.lang.String)
	 */
	public List queryRoleList(Object parameter) {
		List roleList = roleDao.selectList(null, parameter);
		return roleList;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.role.service.RoleService#getRoleMap(java.lang.String)
	 */
	public Map queryRoleMap(Object parameter, String mapKey) {
		Map roleMap = roleDao.selectMap(null, parameter, mapKey);
		return roleMap;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.role.service.RoleService#addRole(org.leaon.iplat.admin.security.role.domain.Role)
	 */
	public int addRole(RoleModel roleModel) {
		int result = roleDao.insert(null, roleModel);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.role.service.RoleService#modifyRole(org.leaon.iplat.admin.security.role.domain.Role)
	 */
	public int modifyRole(RoleModel roleModel) {
		int result = roleDao.update(null, roleModel);
		return result;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.admin.security.role.service.RoleService#deleteRole(org.leaon.iplat.admin.security.role.domain.Role)
	 */
	public int deleteRole(String id) {
		int result = roleDao.delete(null,id);
		return 0;
	}
}
