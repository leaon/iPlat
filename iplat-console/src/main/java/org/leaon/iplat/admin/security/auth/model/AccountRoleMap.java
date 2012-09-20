/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.auth.model;

import org.leaon.iplat.core.commons.pagination.Page;
import org.leaon.iplat.core.dbpm.orm.Model;

/**
 * 账户角色管理模块模型类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-15			Leaon				创建AccountRoleMap.java。
 *
 */
public class AccountRoleMap extends Model {
	
	/**
	 * 账户ID。
	 */
    private String accountId;

    /**
     * 角色ID。
     */
    private String roleId;

    /**
	 * 构造方法。
	 */
	public AccountRoleMap() {
	}

	/**
	 * 构造方法。
	 * 
	 * @param page 分页类。
	 * @param accountId 账户ID。
	 * @param roleId 角色ID。
	 */
	public AccountRoleMap(Page page, String accountId, String roleId) {
		super(page);
		this.accountId = accountId;
		this.roleId = roleId;
	}

	
	//~ Methods ====================================================================================
	
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
        AccountRoleMap other = (AccountRoleMap) that;
        return (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        return result;
    }

    
    //~ Getters & Setters ============================================================================
    
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    
}