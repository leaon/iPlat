package org.leaon.iplat.admin.security.unit.account.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.leaon.iplat.core.dbpm.orm.Model;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

/**
 * 账户管理模块模型对象。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-8				Leaon				创建AccountModel.java。
 *
 */
public class Account extends Model implements UserDetails, CredentialsContainer {

	// ~ Fields ====================================================================================
	
	/**
	 * 账户ID。
	 */
	private String id;

	/**
	 * 账户名称。
	 */
	private String name;

	/**
	 * 账户密码
	 */
	private String password;

	/**
	 * 账户类型。
	 */
	private String type;

	/**
	 * 最后登录时间。
	 */
	private Date lastLogin;

	/**
	 * 创建时间。
	 */
	private Date createTime;

	/**
	 * 最后修改时间。
	 */
	private Date modifyTime;

	/**
	 * 密码过期时间。
	 */
	private Date expireTime;

	/**
	 * 账户状态。
	 */
	private String status;

	/**
	 * 账户安全策略ID。
	 */
	private String policyId;
	
	/**
	 * 账户所拥有的权限。
	 */
	private  Set<GrantedAuthority> authorities;
	
	/**
	 * 账户不过期。
	 */
    private  boolean accountNonExpired;
    
    /**
     * 账户不锁定。
     */
    private  boolean accountNonLocked;
    
    /**
     * 凭证不过期。
     */
    private  boolean credentialsNonExpired;
    
    /**
     * 账户是否可用。
     */
    private  boolean enabled;

    
    // ~ Constructors ==============================================================================
    
	/**
	 * 构造方法。
	 */
	public Account() {
	}


	/**
	 * 构造方法。
	 * 
	 * @param name
	 *            账户名称。
	 * @param password
	 *            账户密码。
	 * @param id
	 *            账户ID。
	 * @param type
	 *            账户类型。
	 * @param lastLogin
	 *            最后登录时间。
	 * @param createTime
	 *            创建时间。
	 * @param modifyTime
	 *            最后修改时间。
	 * @param expireTime
	 *            账户密码过期时间。
	 * @param status
	 *            账户状态。
	 * @param policyId
	 *            账户安全策略ID。
	 * @param authorities
	 *            授予账户的权限。
	 * @param accountNonExpired
	 *            账户密码是否不过期。
	 * @param accountNonLocked
	 *            账户是否不锁定。
	 * @param credentialsNonExpired
	 *            账户凭证是否不过期。
	 * @param enabled
	 *            账户是否可用。
	 */
	public Account(String id, String name, String password, String type,
			Date lastLogin, Date createTime, Date modifyTime, Date expireTime,
			String status, String policyId, Collection<? extends GrantedAuthority> authorities,
			boolean accountNonExpired, boolean accountNonLocked,
			boolean credentialsNonExpired, boolean enabled) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.type = type;
		this.lastLogin = lastLogin;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.expireTime = expireTime;
		this.status = status;
		this.policyId = policyId;
		this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
	}

	
	// ~ Methods ===================================================================================
	
    /* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.CredentialsContainer#eraseCredentials()
	 */
	@Override
	public void eraseCredentials() {
		password = null;
	}
	
	private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        // Ensure array iteration order is predictable (as per UserDetails.getAuthorities() contract and SEC-717)
        SortedSet<GrantedAuthority> sortedAuthorities =
            new TreeSet<GrantedAuthority>(new AuthorityComparator());

        for (GrantedAuthority grantedAuthority : authorities) {
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }
	
	private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {
        private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            // Neither should ever be null as each entry is checked before adding it to the set.
            // If the authority is null, it is a custom authority and should precede others.
            if (g2.getAuthority() == null) {
                return -1;
            }

            if (g1.getAuthority() == null) {
                return 1;
            }

            return g1.getAuthority().compareTo(g2.getAuthority());
        }
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
		Account other = (Account) that;
		return (this.getId() == null ? other.getId() == null : this.getId()
				.equals(other.getId()))
				&& (this.getName() == null ? other.getName() == null : this
						.getName().equals(other.getName()))
				&& (this.getPassword() == null ? other.getPassword() == null
						: this.getPassword().equals(other.getPassword()))
				&& (this.getType() == null ? other.getType() == null : this
						.getType().equals(other.getType()))
				&& (this.getLastLogin() == null ? other.getLastLogin() == null
						: this.getLastLogin().equals(other.getLastLogin()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getModifyTime() == null ? other.getModifyTime() == null
						: this.getModifyTime().equals(other.getModifyTime()))
				&& (this.getExpireTime() == null ? other.getExpireTime() == null
						: this.getExpireTime().equals(other.getExpireTime()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getPolicyId() == null ? other.getPolicyId() == null : this
						.getPolicyId().equals(other.getPolicyId()));
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result
				+ ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result
				+ ((getPassword() == null) ? 0 : getPassword().hashCode());
		result = prime * result
				+ ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result
				+ ((getLastLogin() == null) ? 0 : getLastLogin().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result
				+ ((getModifyTime() == null) ? 0 : getModifyTime().hashCode());
		result = prime * result
				+ ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getPolicyId() == null) ? 0 : getPolicyId().hashCode());
		
		return result;
	}
	
	
	// ~ Getters & Setters =========================================================================

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Date getLastLogin() {
		return lastLogin;
	}


	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public Date getModifyTime() {
		return modifyTime;
	}


	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}


	public Date getExpireTime() {
		return expireTime;
	}


	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getPolicyId() {
		return policyId;
	}


	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}


	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}


	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}


	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}


	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}


	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}


	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}


	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}


	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}