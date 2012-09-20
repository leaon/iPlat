/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */


package org.leaon.iplat.core.dbpm.dao;

import java.util.List;
import java.util.Map;


/**
 * 平台数据层访问接口。
 *
 * @author 	Leaon
 * @version 1.0
 * @since   1.0
 * 
 * Date				Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-4			Leaon				创建AppDao.java。
 *
 */
public abstract interface IplatDao {
	/**
	 * 生成主键。
	 * 
	 * @return 返回生成的主键。
	 */
	public abstract String generateId();

	/**
	 * 根据条件查询单个实体对象，通常是通过主键。
	 * 
	 * @param statement
	 *            查询语句
	 * @param parameter
	 * 
	 * @return 返回单个实体对象。
	 */
	public abstract Object selectOne(String statement, Object parameter);

	/**
	 * 根据条件查询实体对象，并封装为<code>List</code>列表。
	 * 
	 * @param statement
	 *            查询语句
	 * @param parameter
	 *            查询条件。
	 * 
	 * @return 返回查询到的实体对象的<code>List</code>列表。
	 */
	public abstract List<Object> selectList(String statement, Object parameter);

	/**
	 * 根据条件查询实体对象，并封装为<code>Map</code>列表。
	 * 
	 * @param statement
	 *            查询语句
	 * @param parameter
	 *            查询条件。
	 * @param mapKey
	 *            作为<code>Map</code>列表的key的列名。
	 * 
	 * @return 返回包含数据的<code>Map</code>列表。
	 */
	public abstract Map<Object, Object> selectMap(String statement, Object parameter,
			String mapKey);
	
	/**
	 * 分页查询数据，根据指定的偏移量和抓取量查询数据。
	 * 
	 * @param statement
	 *            查询语句
	 * @param parameter
	 *            查询条件
	 * @param offset
	 *            查询数据的偏移量。
	 * @param limit
	 *            每次抓取数据的条目数。
	 * @param mapKey
	 *            作为<code>Map</code>列表的key的列名。
	 * 
	 * @return 返回包含数据的<code>List</code>列表。
	 * @deprecated 分页在查询期间执行，因此该方法被{@link #selectList(String, Object)}取代。
	 */
	// public abstract List<Model> selectPageList(String statement,Object parameter,int offset, int limit);

	/**
	 * 分页查询数据，根据指定的偏移量和抓取量查询数据。
	 * 
	 * @param statement
	 *            查询语句
	 * @param parameter
	 *            查询条件
	 * @param mapKey
	 *            作为<code>Map</code>列表的key的列名。
	 * @param offset
	 *            查询数据的偏移量。
	 * @param limit
	 *            每次抓取数据的条目数。
	 * 
	 * @return 返回包含数据的<code>Map</code>列表。
	 * @deprecated 分页在查询期间执行，因此该方法被{@link #selectMap(String, Object, String)}取代。
	 */
	// public abstract Map<Object,Model> selectPageMap(String statement,Object parameter,String mapKey,int offset, int limit);

	/**
	 * 插入实体对象。
	 * 
	 * @param statement
	 *            查询语句
	 * @param model
	 *            要插入的实体对象。
	 * 
	 * @return 返回整型数值，用于标示是否插入成功。
	 */
	public abstract int insert(String statement, Object model);

	/**
	 * 通过主键更新数据。
	 * 
	 * @param statement
	 *            查询语句
	 * @param model
	 *            要更新的实体对象。
	 * @param 主键
	 *            。
	 * @return 返回整型数值，用于标示是否修改成功。
	 */
	public abstract int update(String statement, Object model);

	/**
	 * 通过主键删除数据。
	 * 
	 * @param 主键
	 *            。
	 * @return 返回整型数值，用于标示是否删除成功。
	 */
	public abstract int delete(String statement, Object parameter);
}
