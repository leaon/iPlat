/**
 * Copyright © 2010 - ${year} Leaon. All Rights Reserved.
 */
package org.leaon.iplat.core.commons;

import java.util.UUID;

import org.apache.log4j.Logger;



/**
 * 
 * UUID生成器。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date				Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5				Leaon				创建UUIDGenerator.java。
 *
 */
public class UUIDGenerator {

	/**
	 * 日志器。
	 */
	@SuppressWarnings("unused")
// TODO: Avoid unused private fields such as 'logger'
	private static final Logger logger = Logger.getLogger(UUIDGenerator.class);

	/**
	 * 生成ID值。
	 * 
	 * @return UUID 返回生成的逐渐ID。
	 */
	public static UUID generateIdCode() {
		return UUID.randomUUID();
	}
}
