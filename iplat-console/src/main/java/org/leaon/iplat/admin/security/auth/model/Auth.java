/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.auth.model;

import java.util.Date;

import org.leaon.iplat.core.commons.pagination.Page;
import org.leaon.iplat.core.dbpm.orm.Model;

/**
 * 授权管理模块模型类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-15			Leaon				创建Auth.java。
 *
 */
public class Auth extends Model{
	
	//~ Fields =====================================================================================
	
	/**
	 * 账户ID。
	 */
    private String accountId;

    /**
	 * 账户名称。
	 */
    private String accountName;

    /**
	 * 账户密码。
	 */
    private String accountPassword;

    /**
	 * 账户类型。
	 */
    private String accountType;

    /**
	 * 账户最后登录时间。
	 */
    private Date accountLastLogin;

    /**
	 * 账户过期时间。
	 */
    private Date accountExpireTime;

    /**
	 * 账户状态。
	 */
    private String accountStatus;

    /**
     * 账户策略ID。
     */
    private String policyId;
    
    /**
     * 账户策略名字。
     */
    private String policyName;
    
    /**
     * 账户最大有效天数。
     */
    private Short policyMaxDays;

    /**
     * 账户锁定前提示天数。
     */
    private Short policyWarnBefore;

    /**
     * 连续登录指定次数均为成功后，锁定账户。
     */
    private Short policyAttemptsBeforeLock;

    /**
     * 账户锁定天数。
     */
    private Short policyLockingDays;
    
    /**
	 * 组ID。
	 */
    private String groupId;

    /**
	 * 组名称。
	 */
    private String groupName;

    /**
	 * 组类型。
	 */
    private String groupType;

    /**
	 * 组管理员ID。
	 */
    private String groupAdminId;

    /**
	 * 角色ID。
	 */
    private String roleId;

    /**
	 * 角色码。
	 */
    private String roleCode;

    /**
	 * 角色名称。
	 */
    private String roleName;

    /**
	 * 角色类型。
	 */
    private String roleType;

    /**
	 * 角色父ID。
	 */
    private String rolePid;

    /**
	 * 菜单ID。
	 */
    private String menuId;

    /**
	 * 菜单码。
	 */
    private String menuCode;

    /**
	 * 菜单名称。
	 */
    private String menuName;

    /**
	 * 菜单URL。
	 */
    private String menuUrl;

    /**
	 * 菜单级别。
	 */
    private Short menuRank;

    /**
	 * 菜单ID。
	 */
    private String menuPid;
    
    

    
    //~ Constructors ===============================================================================
    
	/**
	 * 构造方法。
	 */
	public Auth() {
		super();
	}

	/**
     * 构造方法。
     * 
	 * @param accountId 账户ID。
	 * @param accountName 账户名称。
	 * @param accountPassword 账户密码。
	 * @param accountType 账户类型。
	 * @param accountLastLogin 最后登录时间。
	 * @param accountExpireTime 账户过期时间。
	 * @param accountStatus 账户状态。
	 * @param groupId 组ID。
	 * @param groupName 组名称。
	 * @param groupType 组类型。
	 * @param groupAdminId 组管理员ID。
	 * @param roleId 角色ID。
	 * @param roleName 角色名称。
	 * @param roleType 角色类型。
	 * @param rolePid 父角色ID。
	 * @param menuId 菜单ID。
	 * @param menuCode 菜单标识码。
	 * @param menuName 菜单名称。
	 * @param menuUrl 菜单URL。
	 * @param menuRank 菜单级别。
	 * @param menuPid 父菜单ID。
	 */
	public Auth(Page page,String accountId, String accountName,
			String accountPassword, String accountType, Date accountLastLogin,
			Date accountExpireTime, String accountStatus, String policyId, String policyName, Short policyMaxDays,
			Short policyWarnBefore, Short policyAttemptsBeforeLock,
			Short policyLockingDays, String groupId,
			String groupName, String groupType, String groupAdminId,
			String roleId, String roleCode, String roleName,
			String roleType, String rolePid, String menuId,
			String menuCode, String menuName, String menuUrl, Short menuRank,
			String menuPid) {
		super(page);
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountPassword = accountPassword;
		this.accountType = accountType;
		this.accountLastLogin = accountLastLogin;
		this.accountExpireTime = accountExpireTime;
		this.accountStatus = accountStatus;
		this.policyId = policyId;
		this.policyName = policyName;
		this.policyMaxDays = policyMaxDays;
		this.policyWarnBefore = policyWarnBefore;
		this.policyAttemptsBeforeLock = policyAttemptsBeforeLock;
		this.policyLockingDays = policyLockingDays;
		this.groupId = groupId;
		this.groupName = groupName;
		this.groupType = groupType;
		this.groupAdminId = groupAdminId;
		this.roleId = roleId;
		this.roleCode = roleCode;
		this.roleName = roleName;
		this.roleType = roleType;
		this.rolePid = rolePid;
		this.menuId = menuId;
		this.menuCode = menuCode;
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.menuRank = menuRank;
		this.menuPid = menuPid;
	}
	

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
        Auth other = (Auth) that;
        return (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getAccountName() == null ? other.getAccountName() == null : this.getAccountName().equals(other.getAccountName()))
            && (this.getAccountPassword() == null ? other.getAccountPassword() == null : this.getAccountPassword().equals(other.getAccountPassword()))
            && (this.getAccountType() == null ? other.getAccountType() == null : this.getAccountType().equals(other.getAccountType()))
            && (this.getAccountLastLogin() == null ? other.getAccountLastLogin() == null : this.getAccountLastLogin().equals(other.getAccountLastLogin()))
            && (this.getAccountExpireTime() == null ? other.getAccountExpireTime() == null : this.getAccountExpireTime().equals(other.getAccountExpireTime()))
            && (this.getAccountStatus() == null ? other.getAccountStatus() == null : this.getAccountStatus().equals(other.getAccountStatus()))
            && (this.getPolicyId() == null ? other.getPolicyId() == null : this.getPolicyId().equals(other.getPolicyId()))
            && (this.getPolicyName() == null ? other.getPolicyName() == null : this.getPolicyName().equals(other.getPolicyName()))
            && (this.getPolicyMaxDays() == null ? other.getPolicyMaxDays() == null : this.getPolicyMaxDays().equals(other.getPolicyMaxDays()))
            && (this.getPolicyWarnBefore() == null ? other.getPolicyWarnBefore() == null : this.getPolicyWarnBefore().equals(other.getPolicyWarnBefore()))
            && (this.getPolicyAttemptsBeforeLock() == null ? other.getPolicyAttemptsBeforeLock() == null : this.getPolicyAttemptsBeforeLock().equals(other.getPolicyAttemptsBeforeLock()))
            && (this.getPolicyLockingDays() == null ? other.getPolicyLockingDays() == null : this.getPolicyLockingDays().equals(other.getPolicyLockingDays()))
            && (this.getGroupId() == null ? other.getGroupId() == null : this.getGroupId().equals(other.getGroupId()))
            && (this.getGroupName() == null ? other.getGroupName() == null : this.getGroupName().equals(other.getGroupName()))
            && (this.getGroupType() == null ? other.getGroupType() == null : this.getGroupType().equals(other.getGroupType()))
            && (this.getGroupAdminId() == null ? other.getGroupAdminId() == null : this.getGroupAdminId().equals(other.getGroupAdminId()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getRoleCode() == null ? other.getRoleCode() == null : this.getRoleCode().equals(other.getRoleCode()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getRoleType() == null ? other.getRoleType() == null : this.getRoleType().equals(other.getRoleType()))
            && (this.getRolePid() == null ? other.getRolePid() == null : this.getRolePid().equals(other.getRolePid()))
            && (this.getMenuId() == null ? other.getMenuId() == null : this.getMenuId().equals(other.getMenuId()))
            && (this.getMenuCode() == null ? other.getMenuCode() == null : this.getMenuCode().equals(other.getMenuCode()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getMenuUrl() == null ? other.getMenuUrl() == null : this.getMenuUrl().equals(other.getMenuUrl()))
            && (this.getMenuRank() == null ? other.getMenuRank() == null : this.getMenuRank().equals(other.getMenuRank()))
            && (this.getMenuPid() == null ? other.getMenuPid() == null : this.getMenuPid().equals(other.getMenuPid()));
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getAccountName() == null) ? 0 : getAccountName().hashCode());
        result = prime * result + ((getAccountPassword() == null) ? 0 : getAccountPassword().hashCode());
        result = prime * result + ((getAccountType() == null) ? 0 : getAccountType().hashCode());
        result = prime * result + ((getAccountLastLogin() == null) ? 0 : getAccountLastLogin().hashCode());
        result = prime * result + ((getAccountExpireTime() == null) ? 0 : getAccountExpireTime().hashCode());
        result = prime * result + ((getAccountStatus() == null) ? 0 : getAccountStatus().hashCode());
        result = prime * result + ((getPolicyId() == null) ? 0 : getPolicyId().hashCode());
        result = prime * result + ((getPolicyName() == null) ? 0 : getPolicyName().hashCode());
        result = prime * result + ((getPolicyMaxDays() == null) ? 0 : getPolicyMaxDays().hashCode());
        result = prime * result + ((getPolicyWarnBefore() == null) ? 0 : getPolicyWarnBefore().hashCode());
        result = prime * result + ((getPolicyAttemptsBeforeLock() == null) ? 0 : getPolicyAttemptsBeforeLock().hashCode());
        result = prime * result + ((getPolicyLockingDays() == null) ? 0 : getPolicyLockingDays().hashCode());
        result = prime * result + ((getGroupId() == null) ? 0 : getGroupId().hashCode());
        result = prime * result + ((getGroupName() == null) ? 0 : getGroupName().hashCode());
        result = prime * result + ((getGroupType() == null) ? 0 : getGroupType().hashCode());
        result = prime * result + ((getGroupAdminId() == null) ? 0 : getGroupAdminId().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getRoleCode() == null) ? 0 : getRoleCode().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRoleType() == null) ? 0 : getRoleType().hashCode());
        result = prime * result + ((getRolePid() == null) ? 0 : getRolePid().hashCode());
        result = prime * result + ((getMenuId() == null) ? 0 : getMenuId().hashCode());
        result = prime * result + ((getMenuCode() == null) ? 0 : getMenuCode().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getMenuUrl() == null) ? 0 : getMenuUrl().hashCode());
        result = prime * result + ((getMenuRank() == null) ? 0 : getMenuRank().hashCode());
        result = prime * result + ((getMenuPid() == null) ? 0 : getMenuPid().hashCode());
        return result;
    }
    
    
    //~ Getters & Setters ============================================================================
    
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getAccountLastLogin() {
		return accountLastLogin;
	}

	public void setAccountLastLogin(Date accountLastLogin) {
		this.accountLastLogin = accountLastLogin;
	}

	public Date getAccountExpireTime() {
		return accountExpireTime;
	}

	public void setAccountExpireTime(Date accountExpireTime) {
		this.accountExpireTime = accountExpireTime;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Short getPolicyMaxDays() {
		return policyMaxDays;
	}

	public void setPolicyMaxDays(Short policyMaxDays) {
		this.policyMaxDays = policyMaxDays;
	}

	public Short getPolicyWarnBefore() {
		return policyWarnBefore;
	}

	public void setPolicyWarnBefore(Short policyWarnBefore) {
		this.policyWarnBefore = policyWarnBefore;
	}

	public Short getPolicyAttemptsBeforeLock() {
		return policyAttemptsBeforeLock;
	}

	public void setPolicyAttemptsBeforeLock(Short policyAttemptsBeforeLock) {
		this.policyAttemptsBeforeLock = policyAttemptsBeforeLock;
	}

	public Short getPolicyLockingDays() {
		return policyLockingDays;
	}

	public void setPolicyLockingDays(Short policyLockingDays) {
		this.policyLockingDays = policyLockingDays;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupType() {
		return groupType;
	}

	public void setGroupType(String groupType) {
		this.groupType = groupType;
	}

	public String getGroupAdminId() {
		return groupAdminId;
	}

	public void setGroupAdminId(String groupAdminId) {
		this.groupAdminId = groupAdminId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRolePid() {
		return rolePid;
	}

	public void setRolePid(String rolePid) {
		this.rolePid = rolePid;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Short getMenuRank() {
		return menuRank;
	}

	public void setMenuRank(Short menuRank) {
		this.menuRank = menuRank;
	}

	public String getMenuPid() {
		return menuPid;
	}

	public void setMenuPid(String menuPid) {
		this.menuPid = menuPid;
	}
	
}