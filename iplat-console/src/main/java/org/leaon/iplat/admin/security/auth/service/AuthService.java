/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.admin.security.auth.service;

import java.util.List;
import java.util.Map;

import org.leaon.iplat.admin.security.auth.model.Auth;

/**
 * 授权管理模块服务接口。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建AuthService.java。
 *
 */
public interface AuthService {
	/**
	 * 根据ID获取一个Auth对象实例。
	 * 
	 * @param id
	 *            数据库主键。
	 * @return
	 */
	public abstract Auth queryAuthDetails(String id);

	/**
	 * 根据查询条件获取Auth对象的<code>List</code>列表。
	 * 
	 * @param parameter
	 *            查询条件。
	 * @return 返回复合条件的对象的<code>List</code>列表。
	 */
	public abstract List queryAuthList(Object parameter);

	/**
	 * 根据查询条件获取Auth对象的<code>Map</code>列表。
	 * 
	 * @param parameter
	 *            查询条件。
	 * @return 返回复合条件的对象的<code>Map</code>列表。
	 */
	public abstract Map queryAuthMap(Object parameter, String mapKey);

	/**
	 * 添加一个授权。
	 * 
	 * @param Auth
	 *            要添加的授权对象。
	 * @return 返回整型数值，如果添加成功则返回操作的行数。
	 */
	public abstract int addAuth(Auth Auth);

	/**
	 * 修改一个授权。
	 * 
	 * @param Auth
	 *            要修改的授权对象。
	 * @return 返回整型数值，如果添加成功则返回操作的行数。
	 */
	public abstract int modifyAuth(Auth Auth);

	/**
	 * 删除一个授权。
	 * 
	 * @param Auth
	 *            要删除的授权对象。
	 * @return 返回整型数值，如果添加成功则返回操作的行数。
	 */
	public abstract int deleteAuth(String id);
}
