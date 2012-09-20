/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.dbpm.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.leaon.iplat.core.commons.UUIDGenerator;
import org.leaon.iplat.core.dbpm.dao.IplatDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

/**
 * 基于MyBatis的数据访问对象。
 *
 * @author 	Leaon
 * @version 1.0
 * @since   1.0
 * 
 * Date				Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-4			Leaon				创建AppSqlSessionDao.java。
 *
 */
public class IplatSqlSessionDao extends SqlSessionDaoSupport implements IplatDao{
	/**
	 * 日志器。
	 */
	private static final Logger logger = Logger.getLogger(IplatSqlSessionDao.class);
	
	/**
	 * 主键生成策略，默认为UUID生成方式。
	 */
	public String generateId() {
		return UUIDGenerator.generateIdCode().toString();
	}
	
	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.IplatDao#selectOne(java.lang.String, java.lang.Object)
	 */
	public Object selectOne(String statement, Object parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#selectByCondition(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List<Object> selectList(String statement, Object parameter) {
		return getSqlSession().selectList(statement, parameter);
	}
	
	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#selectByCondition(java.lang.Object, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Map<Object, Object> selectMap(String statement, Object parameter, String mapKey) {
		return getSqlSession().selectMap(statement, parameter, mapKey);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#insert(org.leaon.iplat.core.dbpm.orm.Model)
	 */
	public int insert(String statement, Object model) {
		return getSqlSession().insert(statement, model);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#updateByPrimaryKey(java.lang.String)
	 */
	public int update(String statement, Object model) {
		return getSqlSession().update(statement, model);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#deleteByPrimaryKey(java.lang.String)
	 */
	public int delete(String statement,Object parameter) {
		return getSqlSession().delete(statement, parameter);
	}

}
