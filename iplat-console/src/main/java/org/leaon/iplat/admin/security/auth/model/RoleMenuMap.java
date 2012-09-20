/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.auth.model;

import org.leaon.iplat.core.commons.pagination.Page;
import org.leaon.iplat.core.dbpm.orm.Model;

/**
 * 角色菜单管理模块模型类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-15			Leaon				创建RoleMenu.java。
 *
 */
public class RoleMenuMap extends Model {
	
	// ~ Fields ====================================================================================
	
	/**
	 * 角色ID。
	 */
    private String roleId;

    /**
     * 菜单ID。
     */
    private String menuId;

    
    // ~ Constructors ==============================================================================
    
    /**
	 * 构造方法。
	 */
	public RoleMenuMap() {
		
	}

	/**
	 * 构造方法。
	 * 
	 * @param page 分页类。
	 * @param roleId 角色ID。
	 * @param menuId 菜单ID。
	 */
	public RoleMenuMap(Page page, String roleId, String menuId) {
		super(page);
		this.roleId = roleId;
		this.menuId = menuId;
	}

	
	// ~ Methods ===================================================================================

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
        RoleMenuMap other = (RoleMenuMap) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        return result;
    }
    

	// ~ Getters & Setters ===========================================================================
    
	public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

}