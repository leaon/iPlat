/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 类加载工具类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5				Leaon				创建ClassUtils.java。
 *
 */
public class ClassUtils {

	/** 日志器。 */
	private static final Logger logger = Logger.getLogger(ClassUtils.class);

	/**
	 * 返回指定接口的所有实现类。
	 * 
	 * @param itf
	 *            接口类型。
	 * @return 该接口的所有实现类的<code>List</code>列表。
	 */
	@SuppressWarnings("rawtypes")
	public static List<Class> getAllClassByInterface(Class<?> itf) {
		List<Class> returnClassList = new ArrayList<Class>(); // 返回结果。

		// 如果不是一个接口，则不做处理。
		if (itf.isInterface()) {
			String packageName = itf.getPackage().getName(); // 获得当前的包名。
			try {
				List<Class> allClass = getClasses(packageName); // 获得当前包下以及子包下的所有类。

				// 判断是否是同一个接口。
				for (int i = 0; i < allClass.size(); i++) {
					if (itf.isAssignableFrom(allClass.get(i))) { // 判断是不是一个接口。
						if (!itf.equals(allClass.get(i))) { // 本身不加进去。
							returnClassList.add(allClass.get(i));
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return returnClassList;
	}

	/**
	 * 从一个包中查找出所有的类，在jar包中不能查找。
	 * 
	 * @param packageName
	 *            包名。
	 * @return 返回指定包中的所有类的<code>List</code>列表。
	 * @throws ClassNotFoundException
	 *             当指定的包中不包含类时，抛出此异常。
	 * @throws IOException
	 *             当指定的文件路径不存在时，抛出此异常。
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}

	/**
	 * 在指定的路径下，查找指定包下的所有类。
	 * 
	 * @param directory
	 *            要查找的目录。
	 * @param packageName
	 *            要查找的包名。
	 * @return 返回符合条件的包中的所有类的<code>List</code>列表。
	 * @throws ClassNotFoundException
	 *             - 当指定的包中不存在类时，抛出此异常。
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName)
			throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				classes.add(Class.forName(packageName
						+ '.'
						+ file.getName().substring(0,
								file.getName().length() - 6)));
			}
		}
		return classes;
	}
}
