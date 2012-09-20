/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.unit.role.web;

import java.util.List;
import java.util.Map;

import org.leaon.iplat.admin.security.unit.role.model.RoleModel;
import org.leaon.iplat.admin.security.unit.role.service.RoleService;
import org.leaon.iplat.core.commons.exception.BizException;
import org.leaon.iplat.core.dbpm.orm.Model;
import org.leaon.iplat.core.web.IplatActionSupport;

/**
 * 角色管理模块请求处理器对象。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建RoleAction.java。
 *
 */
public class RoleAction extends IplatActionSupport {

	/**
	 * 角色操作服务类。提供对系统角色相关操作的支持。
	 */
	private RoleService roleService;

	/**
	 * 角色模型实体。用于收集和传递数据。
	 */
	private RoleModel roleModel;

	/**
	 * 角色<code>List</code>列表，用于查询和传递Role类型的数据集合。
	 */
	private List roleList;

	/**
	 * 角色<code>Mapt</code>列表，用于查询和传递Role类型的数据集合。
	 */
	private Map roleMap;

	/**
	 * 获取role的值。
	 * 
	 * @return 返回role的值。
	 */
	public RoleModel getRole() {
		return roleModel;
	}

	/**
	 * 设置role的值。
	 * 
	 * @param roleModel
	 *            要设置的role的值。
	 */
	public void setRole(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	/**
	 * 获取roleService的值。
	 * 
	 * @return 返回roleService的值。
	 */
	public RoleService getRoleService() {
		return roleService;
	}

	/**
	 * 设置roleService的值。
	 * 
	 * @param roleService
	 *            要设置的roleService的值。
	 */
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * 获取roleList的值。
	 * 
	 * @return 返回roleList的值。
	 */
	public List getRoleList() {
		return roleList;
	}

	/**
	 * 设置roleList的值。
	 * 
	 * @param roleList
	 *            要设置的roleList的值。
	 */
	public void setRoleList(List roleList) {
		this.roleList = roleList;
	}

	/**
	 * 获取roleMap的值。
	 * 
	 * @return 返回roleMap的值。
	 */
	public Map getRoleMap() {
		return roleMap;
	}

	/**
	 * 设置roleMap的值。
	 * 
	 * @param roleMap
	 *            要设置的roleMap的值。
	 */
	public void setRoleMap(Map roleMap) {
		this.roleMap = roleMap;
	}

	/**
	 * 查询单个角色对象数据。
	 * 
	 * @return 返回角色对象。
	 * @throws BizException
	 *             当查询的数据不存在时，抛出业务操作异常。
	 */
	public String queryRoleDetails() throws BizException {
		roleModel = roleService.queryRoleDetails(roleModel.getId());
		return SUCCESS;
	}

	/**
	 * 查询多个角色对象数据，并返回<code>List</code>列表。
	 * 
	 * @return 返回角色对象列表。
	 */
	public String queryRoleList() {
		roleList = roleService.queryRoleList(roleModel);
		return SUCCESS;
	}

	/**
	 * 查询多个角色对象数据，并返回<code>Map</code>列表。
	 * 
	 * @return 返回角色对象列表。
	 */
	public String queryRoleMap() {
		roleMap = roleService.queryRoleMap(roleModel, "id");
		return SUCCESS;
	}

	/**
	 * 添加角色。
	 * 
	 * @return 返回操作标识。
	 */
	public String addRole() {
		int result = roleService.addRole(roleModel);
		return SUCCESS;
	}

	/**
	 * 修改角色预操作，用于初始化数据。
	 * 
	 * @return 返回操作标识。
	 */
	public String preModifyRole() {
		roleModel = roleService.queryRoleDetails(roleModel.getId());
		return SUCCESS;
	}

	/**
	 * 修改角色。
	 * 
	 * @return
	 */
	public String modifyRole() {
		int result = roleService.modifyRole(roleModel);
		return SUCCESS;
	}

	/**
	 * 删除角色预操作，用于初始化数据。
	 * 
	 * @return 返回操作标识。
	 */
	public String preDeleteRole() {
		return SUCCESS;
	}

	/**
	 * 删除角色。
	 * 
	 * @return 返回操作标识。
	 */
	public String deleteRole() {
		int result = roleService.deleteRole(roleModel.getId());
		return SUCCESS;
	}

}
