/**
 * Copyright © 2010 - ${year} Leaon. All Rights Reserved.
 */
package org.leaon.iplat.core.web.databind;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;

import org.apache.commons.lang.StringUtils;

/**
 * 基于Jaxb2.0的类实例对象与XML数据之间的绑定和操作类(特别支持Root对象是List的情形)。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建JaxbBinder.java。
 *
 */
public class JaxbBinder {

	/** JAXBContext 成员实例。 */
	private JAXBContext jaxbContext; // 线程安全的Context。

	/**
	 * 构造器。通过传入Class对象构造JAXB上下文。
	 *
	 * @param types 所有需要序列化的Root对象的类型。
	 */
	public JaxbBinder(Class<?>... types) {
		try {
			jaxbContext = JAXBContext.newInstance(types);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将类实例对象转换为XML数据。
	 *
	 * @param root 顶层类实例对象。
	 * @param encoding 字符集编码。
	 * @return 转换后的XML数据。
	 */
	public String toXML(Object root, String encoding) {
		try {
			StringWriter writer = new StringWriter();
			createMarshaller(encoding).marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将实例对象转换为XML数据, 特别支持对根元素是集合的情形。
	 *
	 * @param root 泛型的类实例对象集合。
	 * @param rootName 转换后的XML根名称。
	 * @param encoding 字符集编码。
	 * @return 返回转换后的XML数据。
	 */
	public String toXml(@SuppressWarnings("rawtypes") Collection root,
			String rootName, String encoding) {
		try {
			CollectionWrapper wrapper = new CollectionWrapper();
			wrapper.collection = root;
			JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(
					new QName(rootName), CollectionWrapper.class, wrapper);

			StringWriter writer = new StringWriter();
			createMarshaller(encoding).marshal(wrapperElement, writer);

			return writer.toString();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将XML数据转换为类实例对象。
	 *
	 * @param <T> 返回的类实例对象的类型。
	 * @param xml 待转换的XML数据。
	 * @return 返回转换后的类实例对象。
	 */
	@SuppressWarnings("unchecked")
	public <T> T fromXML(String xml) {
		try {
			StringReader reader = new StringReader(xml);
			return (T) createUnmarshaller().unmarshal(reader);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建用于将对象树转换为XML数据的<code>Marshaller</code>，设定encoding(可为Null)。
	 *
	 * @param encoding 字符集编码。
	 * @return 返回<code>Marshaller</code>的实例对象。
	 */
	public Marshaller createMarshaller(String encoding) {
		try {
			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);

			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}
			return marshaller;
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 创建用于将XML数据转换为对象树的<code>UnMarshaller</code>。
	 *
	 * @return 返回<code>Unmarshaller</code>的实例对象。
	 */
	public Unmarshaller createUnmarshaller() {
		try {
			return jaxbContext.createUnmarshaller();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 封装Root Element 是 Collection的情况。
	 */
	private class CollectionWrapper {

		/** 绑定的集合实例。*/
		@SuppressWarnings({ "rawtypes" })
		@XmlAnyElement
		protected Collection collection;

		/**
		 * 获取绑定的集合实例。
		 *
		 * @return collection 返回集合实例。
		 */
		@SuppressWarnings({ "rawtypes", "unused" })
		public Collection getCollection() {
			return collection;
		}

		/**
		 * 设置绑定的集合实例。
		 *
		 * @param collection 要设置的集合实例。
		 */
		@SuppressWarnings("unused")
		public void setCollection(
				@SuppressWarnings("rawtypes") Collection collection) {
			this.collection = collection;
		}
	}
}
