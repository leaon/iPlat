/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.tree;

import java.io.Serializable;

/**
 * 树节点实体Bean，用于存储实体数据。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5				Leaon				创建NodeBean.java。
 *
 */
public abstract class NodeBean implements Serializable {
	/**
	 * 可序列化ID。
	 */
	private static final long serialVersionUID = -2253373053352564202L;

	/**
	 * 节点ID。
	 */
	private String id;
	
	/**
	 * 父节点ID。
	 */
	private String parentId;
	
	/**
	 * 节点名称。
	 */
	private String name;

	/**
	 * @return 返回id的值。
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id 设置id的值。
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return 返回parentId的值。
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * @param parentId 设置parentId的值。
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return 返回name的值。
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 设置name的值。
	 */
	public void setName(String name) {
		this.name = name;
	}

}
