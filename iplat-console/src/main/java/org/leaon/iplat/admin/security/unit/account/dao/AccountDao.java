/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.unit.account.dao;

import java.util.List;
import java.util.Map;

import org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao;

/**
 * 
 * 账户管理模块数据访问对象。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-8				Leaon				创建AccountDao.java。
 *
 */
public class AccountDao extends IplatSqlSessionDao{

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#generateId()
	 */
	@Override
	public String generateId() {
		// TODO Auto-generated method stub
		return super.generateId();
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#selectOne(java.lang.String, java.lang.Object)
	 */
	@Override
	public Object selectOne(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return super.selectOne(statement, parameter);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#selectList(java.lang.String, java.lang.Object)
	 */
	@Override
	public List<Object> selectList(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return super.selectList(statement, parameter);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#selectMap(java.lang.String, java.lang.Object, java.lang.String)
	 */
	@Override
	public Map<Object, Object> selectMap(String statement, Object parameter,
			String mapKey) {
		// TODO Auto-generated method stub
		return super.selectMap(statement, parameter, mapKey);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#insert(java.lang.String, org.leaon.iplat.core.dbpm.orm.Model)
	 */
	@Override
	public int insert(String statement, Object model) {
		// TODO Auto-generated method stub
		return super.insert(statement, model);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#update(java.lang.String, org.leaon.iplat.core.dbpm.orm.Model)
	 */
	@Override
	public int update(String statement, Object model) {
		// TODO Auto-generated method stub
		return super.update(statement, model);
	}

	/* (non-Javadoc)
	 * @see org.leaon.iplat.core.dbpm.dao.impl.IplatSqlSessionDao#delete(java.lang.String, java.lang.Object)
	 */
	@Override
	public int delete(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return super.delete(statement, parameter);
	}

}