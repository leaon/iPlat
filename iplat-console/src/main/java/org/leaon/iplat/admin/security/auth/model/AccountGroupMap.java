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
 * 2012-9-15			Leaon				创建AccountGroupMap.java。
 *
 */
public class AccountGroupMap extends Model {
	
	// ~ Fields ====================================================================================
	
	/**
	 * 账户ID。
	 */
    private String accountId;

    /**
     * 组ID。
     */
    private String groupId;

    /**
	 * 构造器。
	 */
	public AccountGroupMap() {
	}

	/**
	 * 构造方法。
	 * 
	 * @param page 分页类。
	 * @param accountId 账户ID。
	 * @param groupId 组ID。
	 */
	public AccountGroupMap(Page page, String accountId, String groupId) {
		super(page);
		this.accountId = accountId;
		this.groupId = groupId;
	}

	
	// ~ Methods ===================================================================================
	
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
        AccountGroupMap other = (AccountGroupMap) that;
        return (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        return result;
    }
    
    
    // Getters & Setters =============================================================================

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}