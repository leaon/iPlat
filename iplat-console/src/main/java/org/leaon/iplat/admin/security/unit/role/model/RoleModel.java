/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.unit.role.model;

import java.util.Date;

import org.leaon.iplat.core.dbpm.orm.Model;

/**
 * 角色管理模块的数据传递模型。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-4			Leaon				创建RoleModel.java。
 *
 */
public class RoleModel extends Model {

	/**
	 * 角色ID。
	 */
	private String id;

	/**
	 * 角色名称。
	 */
	private String name;

	/**
	 * 角色类型。
	 */
	private String type;

	/**
	 * 创建时间。
	 */
	private Date createTime;

	/**
	 * 修改时间。
	 */
	private Date modifyTime;

	/**
	 * 角色继承性。
	 */
	private Boolean inherit;

	/**
	 * 父角色ID。
	 */
	private String pid;

	/**
	 * 查询条件：创建时间开始。
	 */
	private String ltCreateTime;

	/**
	 * 查询条件：创建时间结束。
	 */
	private String gtCreateTime;

	/**
	 * 查询条件：修改时间开始。
	 */
	private String ltModifyTime;

	/**
	 * 查询条件：修改时间结束。
	 */
	private String gtModifyTime;

	
	/**
	 * 获取id的值。
	 * 
	 * @return 返回id的值。
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置id的值。
	 * 
	 * @param id
	 *            要设置的id的值。
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取name的值。
	 * 
	 * @return 返回name的值。
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置name的值。
	 * 
	 * @param name
	 *            要设置的name的值。
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取type的值。
	 * 
	 * @return 返回type的值。
	 */
	public String getType() {
		return type;
	}

	/**
	 * 设置type的值。
	 * 
	 * @param type
	 *            要设置的type的值。
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 获取createTime的值。
	 * 
	 * @return 返回createTime的值。
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 设置createTime的值。
	 * 
	 * @param createTime
	 *            要设置的createTime的值。
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取modifyTime的值。
	 * 
	 * @return 返回modifyTime的值。
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

	/**
	 * 设置modifyTime的值。
	 * 
	 * @param modifyTime
	 *            要设置的modifyTime的值。
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	/**
	 * 获取inherit的值。
	 * 
	 * @return 返回inherit的值。
	 */
	public Boolean getInherit() {
		return inherit;
	}

	/**
	 * 设置inherit的值。
	 * 
	 * @param inherit
	 *            要设置的inherit的值。
	 */
	public void setInherit(Boolean inherit) {
		this.inherit = inherit;
	}

	/**
	 * 获取pid的值。
	 * 
	 * @return 返回pid的值。
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * 设置pid的值。
	 * 
	 * @param pid
	 *            要设置的pid的值。
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}

	/**
	 * 获取ltCreateTime的值。
	 *
	 * @return 返回ltCreateTime的值。
	 */
	public String getLtCreateTime() {
		return ltCreateTime;
	}

	/**
	 * 设置ltCreateTime的值。 
	 * 
	 * @param ltCreateTime 要设置的ltCreateTime的值。
	 */
	public void setLtCreateTime(String ltCreateTime) {
		this.ltCreateTime = ltCreateTime;
	}

	/**
	 * 获取gtCreateTime的值。
	 *
	 * @return 返回gtCreateTime的值。
	 */
	public String getGtCreateTime() {
		return gtCreateTime;
	}

	/**
	 * 设置gtCreateTime的值。 
	 * 
	 * @param gtCreateTime 要设置的gtCreateTime的值。
	 */
	public void setGtCreateTime(String gtCreateTime) {
		this.gtCreateTime = gtCreateTime;
	}

	/**
	 * 获取ltModifyTime的值。
	 *
	 * @return 返回ltModifyTime的值。
	 */
	public String getLtModifyTime() {
		return ltModifyTime;
	}

	/**
	 * 设置ltModifyTime的值。 
	 * 
	 * @param ltModifyTime 要设置的ltModifyTime的值。
	 */
	public void setLtModifyTime(String ltModifyTime) {
		this.ltModifyTime = ltModifyTime;
	}

	/**
	 * 获取gtModifyTime的值。
	 *
	 * @return 返回gtModifyTime的值。
	 */
	public String getGtModifyTime() {
		return gtModifyTime;
	}

	/**
	 * 设置gtModifyTime的值。 
	 * 
	 * @param gtModifyTime 要设置的gtModifyTime的值。
	 */
	public void setGtModifyTime(String gtModifyTime) {
		this.gtModifyTime = gtModifyTime;
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		RoleModel other = (RoleModel) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getName() == null ? other.getName() == null : this
						.getName().equals(other.getName()))
				&& (this.getPid() == null ? other.getPid() == null : this
						.getPid().equals(other.getPid()))
				&& (this.getType() == null ? other.getType() == null : this
						.getType().equals(other.getType()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getModifyTime() == null ? other.getModifyTime() == null
						: this.getModifyTime().equals(other.getModifyTime()))
				&& (this.getInherit() == null ? other.getInherit() == null
						: this.getInherit().equals(other.getInherit()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result
				+ ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
		result = prime * result
				+ ((getInherit() == null) ? 0 : getInherit().hashCode());
		return result;
	}
	
}