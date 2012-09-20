/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */


package org.leaon.iplat.core.commons.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * 属性转换工具类，用户对象属性与字符串之间的互相转换。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5				Leaon				创建ConvertUtils.java。
 *
 */
public class ConvertUtils {
	/**
	 * 日志器。
	 */
	private static final Logger logger = Logger.getLogger(ConvertUtils.class);
	
	/**
	 * 静态初始化日期转换器。
	 */
	static {
		registerDateConverter();
	}

	/**
	 * 提取集合中的对象的属性(通过getter方法)， 组合成<code>List</code>。
	 * 
	 * @param collection
	 *            来源集合。
	 * @param propertyName
	 *            要提取的属性名。
	 * @return 返回存储属性值的<code>List</code>列表。
	 */
	public static List<Object> convertElementPropertyToList(
			final Collection<?> collection, final String propertyName) {
		List<Object> list = new ArrayList<Object>();

		try {
			for (Object obj : collection) {
				list.add(PropertyUtils.getProperty(obj, propertyName));
			}
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性(通过getter函数)， 组合成由分割符分隔的字符串。
	 * 
	 * @param collection
	 *            来源集合。
	 * @param propertyName
	 *            要提取的属性名。
	 * @param separator
	 *            分隔符。
	 * @return 返回具有指定类型分隔符的属性字符串。
	 */
	public static String convertElementPropertyToString(
			final Collection<?> collection, final String propertyName,
			final String separator) {
		List<?> list = convertElementPropertyToList(collection, propertyName);
		return StringUtils.join(list, separator);
	}

	/**
	 * 转换字符串到相应类型。
	 * 
	 * @param value
	 *            待转换的字符串。
	 * @param toType
	 *            转换目标类型。
	 * @return 返回转换后的实例对象。
	 */
	public static Object convertStringToObject(String value, Class<?> toType) {
		try {
			return org.apache.commons.beanutils.ConvertUtils.convert(value,
					toType);
		} catch (Exception e) {
			throw ReflectionUtils.convertReflectionExceptionToUnchecked(e);
		}
	}

	/**
	 * 定义日期转换器的格式： yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss。
	 */
	private static void registerDateConverter() {
		DateConverter dc = new DateConverter();
		dc.setUseLocaleFormat(true);
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss" });
		org.apache.commons.beanutils.ConvertUtils.register(dc, Date.class);
	}
}
