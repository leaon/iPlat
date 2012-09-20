/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.pagination.interceptor;

/**
 * 基于MySQL数据库的分页方言类，用于MySQL数据库下的分页。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建MySQLDialect.java。
 *
 */
public class MySQLDialect implements Dialect {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mybatis.extend.interceptor.IDialect#getLimitString(java.lang.String,
	 * int, int)
	 */
	public String getLimitString(String sql, int offset, int limit) {

		sql = sql.trim();
		StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);

		pagingSelect.append(sql);
		pagingSelect.append(" LIMIT ");
		pagingSelect.append(offset + " " + limit);

		return pagingSelect.toString();
	}
}
