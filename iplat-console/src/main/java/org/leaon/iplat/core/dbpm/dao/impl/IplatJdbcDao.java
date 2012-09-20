/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.dbpm.dao.impl;

import java.util.List;
import java.util.Map;

import org.leaon.iplat.core.commons.UUIDGenerator;
import org.leaon.iplat.core.dbpm.dao.IplatDao;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * 基于Spring JDBC的数据访问对象。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date				Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-4			Leaon				创建AppJdbcDao.java。
 *
 */
public class IplatJdbcDao extends JdbcDaoSupport implements IplatDao {

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.IplatDao#generateId()
	 */
	public String generateId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.IplatDao#selectOne(java.lang.String, java.lang.Object)
	 */
	public Object selectOne(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.IplatDao#selectList(java.lang.String, java.lang.Object)
	 */
	public List<Object> selectList(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.IplatDao#selectMap(java.lang.String, java.lang.Object, java.lang.String)
	 */
	public Map<Object, Object> selectMap(String statement, Object parameter,
			String mapKey) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.IplatDao#insert(java.lang.String, org.leaon.iplat.core.dbpm.orm.Model)
	 */
	public int insert(String statement, Object model) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.IplatDao#update(java.lang.String, org.leaon.iplat.core.dbpm.orm.Model)
	 */
	public int update(String statement, Object model) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.IplatDao#delete(java.lang.String, java.lang.Object)
	 */
	public int delete(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
