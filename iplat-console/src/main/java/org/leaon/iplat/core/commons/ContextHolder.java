/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * 以静态变量保存Spring ApplicationContext，可在任何代码任何地方任何时候中取出ApplicaitonContext。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date				Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5			Leaon				创建ContextHolder.java。
 *
 */
public class ContextHolder implements ApplicationContextAware, DisposableBean {

	/** <code>ApplicationContext</code>属性。 */
	private static ApplicationContext applicationContext = null;

	/** 日志器。*/
	private static Logger logger = Logger.getLogger(ContextHolder.class);

	/**
	 * 实现ApplicationContextAware接口，注入Context到静态变量中。
	 *
	 * @param applicationContext 要设置的<code>ApplicationContext</code>的值。
	 */
	public void setApplicationContext(ApplicationContext applicationContext) {
		logger.debug("注入ApplicationContext到SpringContextHolder:" + applicationContext);

		if (ContextHolder.applicationContext != null) {
			logger.warn("SpringContextHolder中的ApplicationContext被覆盖, 原有ApplicationContext为:"
					+ ContextHolder.applicationContext);
		}

		ContextHolder.applicationContext = applicationContext;
	}

	/**
	 * 实现DisposableBean接口，在Context关闭时清理静态变量。
	 */
	public void destroy() {
		ContextHolder.clear();
	}

	/**
	 * 取得存储在静态变量中的ApplicationContext.
	 *
	 * @return 返回ApplicationContext对象。
	 */
	public static ApplicationContext getApplicationContext() {
		assertContextInjected();
		return applicationContext;
	}

	/**
	 * 从静态变量applicationContext中取得Bean，自动转型为所赋值对象的类型。
	 *
	 * @param <T> Bean的类型。
	 * @param name 要获取的Bean的类型。
	 * @return 返回要获取的Bean实例。
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		assertContextInjected();
		return (T) applicationContext.getBean(name);
	}

	/**
	 * 从静态变量applicationContext中取得Bean， 自动转型为所赋值对象的类型。
	 *
	 * @param <T> Bean的类型。
	 * @param requiredType 要获取的Bean的类型。
	 * @return 返回要获取的Bean实例。
	 */
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return applicationContext.getBean(requiredType);
	}

	/**
	 * 清除SpringContextHolder中的ApplicationContext为Null。
	 */
	public static void clear() {
		logger.debug("清除SpringContextHolder中的ApplicationContext：" + applicationContext);
		applicationContext = null;
	}

	/**
	 * 检查ApplicationContext不为空。
	 */
	private static void assertContextInjected() {
		if (applicationContext == null) {
			throw new IllegalStateException("applicaitonContext未注入，请在applicationContext.xml中定义SpringContextHolder。");
		}
	}
}
