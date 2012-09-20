/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.unit.role.service;

import java.util.List;
import java.util.Map;

import org.leaon.iplat.admin.security.unit.role.model.RoleModel;

/**
 * 角色管理模块服务接口。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建RoleService.java。
 *
 */
public interface RoleService {
	/**
	 * 根据ID获取一个Role对象实例。
	 * 
	 * @param id
	 *            数据库主键。
	 * @return
	 */
	public abstract RoleModel queryRoleDetails(String id);

	/**
	 * 根据查询条件获取Role对象的<code>List</code>列表。
	 * 
	 * @param parameter
	 *            查询条件。
	 * @return 返回复合条件的对象的<code>List</code>列表。
	 */
	public abstract List queryRoleList(Object parameter);

	/**
	 * 根据查询条件获取Role对象的<code>Map</code>列表。
	 * 
	 * @param parameter
	 *            查询条件。
	 * @return 返回复合条件的对象的<code>Map</code>列表。
	 */
	public abstract Map queryRoleMap(Object parameter, String mapKey);

	/**
	 * 添加一个角色。
	 * 
	 * @param roleModel
	 *            要添加的角色对象。
	 * @return 返回整型数值，如果添加成功则返回操作的行数。
	 */
	public abstract int addRole(RoleModel roleModel);

	/**
	 * 修改一个角色。
	 * 
	 * @param roleModel
	 *            要修改的角色对象。
	 * @return 返回整型数值，如果添加成功则返回操作的行数。
	 */
	public abstract int modifyRole(RoleModel roleModel);

	/**
	 * 删除一个角色。
	 * 
	 * @param role
	 *            要删除的角色对象。
	 * @return 返回整型数值，如果添加成功则返回操作的行数。
	 */
	public abstract int deleteRole(String id);
}
