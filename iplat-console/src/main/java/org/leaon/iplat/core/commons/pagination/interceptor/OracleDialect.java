/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.pagination.interceptor;

/**
 * 基于Oracle数据库的分页方言类，用于Oracle数据库下的分页。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建OracleDialect.java。
 *
 */
public class OracleDialect implements Dialect {

	 /* (non-Javadoc)   
     * @see org.mybatis.extend.interceptor.IDialect#getLimitString(java.lang.String, int, int)   
     */     
    public String getLimitString(String sql, int offset, int limit) {     
     
        sql = sql.trim();     
        StringBuilder pagingSelect = new StringBuilder(sql.length() + 100);     
             
        pagingSelect.append("SELECT * FROM ( SELECT ROW_.*, ROWNUM ROWNUM_ FROM ( ");     
             
        pagingSelect.append(sql);     
             
        pagingSelect.append(" ) ROW_ ) WHERE ROWNUM_ > ").append(offset).append(" AND ROWNUM_ <= ").append(offset + limit);     
             
        return pagingSelect.toString();     
    }
}
