/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */
package org.leaon.iplat.core.web.databind;

import org.slf4j.Logger;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.LoggerFactory;

/**
 * 基于Jackson的类实例对象与JSON之间的数据绑定和操作类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1			Leaon				创建JsonBinder.java。
 *
 */
public class JsonBinder {

	/** 日志器。*/
	private static Logger logger = LoggerFactory.getLogger(JsonBinder.class);

	/** 对象映射器实例。*/
	private ObjectMapper mapper;

	/**
	 * 构造器。
	 *
	 * @param inclusion 需要进行数据绑定的类实例对象的属性集。
	 */
	public JsonBinder(Inclusion inclusion) {
		mapper = new ObjectMapper();
		// 设置输出包含的属性
		mapper.getSerializationConfig().withSerializationInclusion(inclusion);
		// 设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
		mapper.getDeserializationConfig()
				.with(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES
						);
	}

	/**
	 * 创建输出全部属性到Json字符串的Binder。
	 *
	 * @return JsonBinder 返回<code>JsonBinder</code>的实例对象。
	 */
	public static JsonBinder buildNormalBinder() {
		return new JsonBinder(Inclusion.ALWAYS);
	}

	/**
	 * 创建只输出非空属性到Json字符串的Binder。
	 *
	 * @return JsonBinder 返回<code>JsonBinder</code>的实例对象。
	 */
	public static JsonBinder buildNonNullBinder() {
		return new JsonBinder(Inclusion.NON_NULL);
	}

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Binder。
	 *
	 * @return JsonBinder 返回<code>JsonBinder</code>的实例对象。
	 */
	public static JsonBinder buildNonDefaultBinder() {
		return new JsonBinder(Inclusion.NON_DEFAULT);
	}

	/**
	 * 将JSON字符串转换为给定的类的实例对象。 如果JSON字符串为<code>null</code>或"null"字符串，返回
	 * <code>null</code>； 如果JSON字符串为"[]"，返回空集合。
	 *
	 * @param <T> 返回的实例对象的类型。
	 * @param jsonString 待转换JSON字符串。
	 * @param clazz 类的Class对象实例。
	 * @return 返回指定的类的实例对象。
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
// TODO: A method should have only one exit point, and that should be the last statement in the method
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.error("由JSON转换为实例对象时失败:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 将JSON字符串转换为给定的类的实例对象的集合。 如果JSON字符串为<code>null</code>或"null"字符串，返回
	 * <code>null</code>； 如果JSON字符串为"[]"，返回空集合。
	 *
	 * @param <T> 返回的实例对象的类型(这里一般为集合类型，如：List<T>)。
	 * @param jsonString 待转换JSON字符串。
	 * @param ref 类型引用器的对象实例。
	 * @return 返回指定的类的实例对象集合。
	 */
	public <T> T fromJson2(String jsonString, TypeReference<T> ref) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return getMapper().readValue(jsonString, ref);
		} catch (IOException e) {
			logger.error("由JSON转换为实例对象时失败:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 将对象实例转换为JSON数据。 如果对象为<code>null</code>，返回字符串"null"； 如果集合为空集合,返回"[]"。
	 *
	 * @param object 待转换实例对象。
	 * @return 返回转换后的JSON字符串。
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("由实例对象转换为JSON时失败:" + object, e);
			return null;
		}
	}

	/**
	 * 设置转换日期类型的format pattern,如果不设置默认打印Timestamp毫秒数。
	 *
	 * @param pattern 新的日期格式化字符串值。
	 */
	public void setDateFormat(String pattern) {
		if (StringUtils.isNotBlank(pattern)) {
			DateFormat df = new SimpleDateFormat(pattern);
			mapper.getSerializationConfig().withDateFormat(df);
			mapper.getDeserializationConfig().withDateFormat(df);
		}
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API。
	 *
	 * @return mapper。
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
}
