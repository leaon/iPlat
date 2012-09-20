/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;
import org.springframework.web.context.ContextLoaderListener;

/**
 * 
 * Properties 文件操作类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5				Leaon				创建PropertiesUtils.java。
 *
 */
public class PropertiesUtils {

	/**
	 * 静态常量，默认字符集编码。
	 */
	private static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 日志器
	 */
	private static Logger logger = LoggerFactory
			.getLogger(PropertiesUtils.class);

	/**
	 * 参数文件加载器。
	 */
	private static PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

	/**
	 * 资源加载器。
	 */
	private static ResourceLoader resourceLoader = new DefaultResourceLoader();

	/**
	 * 载入多个properties文件, 相同的属性在最后载入的文件中的值将会覆盖之前的载入， 文件路径使用Spring Resource格式,
	 * 文件编码使用UTF-8。
	 * 
	 * @param resourcesPaths
	 *            参数文件路径，可以同时加载多个路径，用逗号分隔。
	 * @return 返回初始化后的<code>Properties</code>实例。
	 * @throws IOException
	 *             - 当文件不存在时，抛出此异常。
	 * @see org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
	 */
	public static Properties loadProperties(String... resourcesPaths)
			throws IOException {
		Properties props = new Properties();
		for (String location : resourcesPaths) {

			logger.debug("从以下位置加载属性文件：" + location);

			InputStream is = null;
			try {
				Resource resource = resourceLoader.getResource(location);
				is = resource.getInputStream();
				propertiesPersister.load(props, new InputStreamReader(is,
						DEFAULT_ENCODING));
			} catch (IOException ex) {
				logger.info("无法从以下位置加载属性文件：" + location + "： "
						+ ex.getMessage());
			} finally {
				if (is != null) {
					is.close();
				}
			}
		}
		return props;
	}
}
