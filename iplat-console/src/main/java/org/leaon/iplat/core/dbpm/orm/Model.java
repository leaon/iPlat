/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.dbpm.orm;

import java.io.Serializable;

import org.leaon.iplat.core.commons.pagination.Page;

/**
 * 数据持久化实体接口，对于需要被持久化的实体类应该继承此类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建Model.java。
 *
 */
public class Model implements Serializable{
	
	/**
	 * 分页信息。
	 */
	protected Page page;

	/**
	 * 构造方法。
	 * 
	 * @param page
	 */
	public Model() {
		
	}
	
	/**
	 * 构造方法。
	 * 
	 * @param page
	 */
	public Model(Page page) {
		this.page = page;
	}

	/**
	 * 获取page的值。
	 *
	 * @return 返回page的值。
	 */
	protected Page getPage() {
		return page;
	}

	/**
	 * 设置page的值。 
	 * 
	 * @param page 要设置的page的值。
	 */
	protected void setPage(Page page) {
		this.page = page;
	}
	
}
