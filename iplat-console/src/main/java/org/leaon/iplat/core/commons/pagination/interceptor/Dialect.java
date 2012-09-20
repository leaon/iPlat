/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.pagination.interceptor;

/**
 * 数据库方言，用于根据特定的数据库生成分页SQL语句。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建Dialect.java。
 *
 */
public interface Dialect {
	//支持的数据库类型。
	public static enum Type {
		MYSQL, ORACLE
	}

	/**
	 * 获取分页SQL语句。
	 *
	 * @param sql 原始SQL。
	 * @param skipResults 分页开始位置。
	 * @param maxResults 要获取的数据条目数。
	 * @return 返回封装好的分页SQL语句。
	 */
	public abstract String getLimitString(String sql, int skipResults,
			int maxResults);
}
