/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.pagination.interceptor;

import java.sql.Connection;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.DefaultParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;

/**
 * 自定义SQL拦截器，用于执行分页操作。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1			Leaon				创建PaginationInterceptor.java。
 *
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class }) })
public class PaginationInterceptor implements Interceptor {

	/**
	 * 日志器。
	 */
	protected static Logger logger = Logger
			.getLogger(PaginationInterceptor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin
	 * .Invocation)
	 */
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation
				.getTarget();
		MetaObject metaStatementHandler = MetaObject
				.forObject(statementHandler);

		RowBounds rowBounds = (RowBounds) metaStatementHandler
				.getValue("delegate.rowBounds");
		if (rowBounds == null || rowBounds == RowBounds.DEFAULT) {
			return invocation.proceed();
		}

		DefaultParameterHandler defaultParameterHandler = (DefaultParameterHandler) metaStatementHandler
				.getValue("delegate.parameterHandler");
		Map parameterMap = (Map) defaultParameterHandler.getParameterObject();
		Object sidx = parameterMap.get("_sidx");
		Object sord = parameterMap.get("_sord");

		String originalSql = (String) metaStatementHandler
				.getValue("delegate.boundSql.sql");

		if (sidx != null && sord != null) {
			sidx = sidx.toString().toUpperCase();
			sord = sord.toString().toUpperCase();
			originalSql = originalSql + " ORDER BY " + sidx + " " + sord;
		}

		Configuration configuration = (Configuration) metaStatementHandler
				.getValue("delegate.configuration");

		Dialect.Type databaseType = null;
		try {
			databaseType = Dialect.Type.valueOf(configuration.getVariables()
					.getProperty("dialect").toUpperCase());
		} catch (Exception e) {
			// ignore
		}
		if (databaseType == null) {
			throw new RuntimeException(
					"the value of the dialect property in configuration.xml is not defined : "
							+ configuration.getVariables().getProperty(
									"dialect"));
		}
		
		Dialect dialect = null;
		switch (databaseType) {
		case ORACLE:
			//Oracle分页逻辑。
			dialect = new OracleDialect();
			break;
		case MYSQL:
			// MySQL分页逻辑。
			dialect = new OracleDialect();
			break;

		}

		metaStatementHandler.setValue("delegate.boundSql.sql", dialect
				.getLimitString(originalSql, rowBounds.getOffset(),
						rowBounds.getLimit()));
		metaStatementHandler.setValue("delegate.rowBounds.offset",
				RowBounds.NO_ROW_OFFSET);
		metaStatementHandler.setValue("delegate.rowBounds.limit",
				RowBounds.NO_ROW_LIMIT);
		if (logger.isDebugEnabled()) {
			BoundSql boundSql = statementHandler.getBoundSql();
			logger.debug("生成分页SQL：" + boundSql.getSql());
		}
		return invocation.proceed();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
	 */
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
	 */
	public void setProperties(Properties arg0) {
		// TODO Auto-generated method stub

	}
}
