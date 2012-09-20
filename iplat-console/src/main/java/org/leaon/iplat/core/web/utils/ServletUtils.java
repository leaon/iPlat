/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */
package org.leaon.iplat.core.web.utils;

import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.leaon.iplat.core.commons.utils.EncodeUtils;
import org.springframework.util.Assert;


/**
 * Servlet操作相关工具类。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date					Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-9-1				Leaon				创建ServletUtils.java。
 *
 */
public class ServletUtils {

	/** 日志器。 */
	private static final Logger logger = Logger.getLogger(ServletUtils.class);

	// -- Content Type 定义 --//
	/** 静态常量，TEXT的Content Type类型。 */
	public static final String TEXT_TYPE = "text/plain";

	/** 静态常量，JSON的Content Type类型。 */
	public static final String JSON_TYPE = "application/json";

	/** 静态常量，XML的Content Type类型。 */
	public static final String XML_TYPE = "text/xml";

	/** 静态常量，HTML的Content Type类型。 */
	public static final String HTML_TYPE = "text/html";

	/** 静态常量，JavaScript的Content Type类型。 */
	public static final String JS_TYPE = "text/javascript";

	/** 静态常量，Microsoft Excel的Content Type类型。 */
	public static final String EXCEL_TYPE = "application/vnd.ms-excel";

	/** 静态常量，Authorization头。. */
	public static final String AUTHENTICATION_HEADER = "Authorization";

	/** 静态常量，一年中有多少秒。 */
	public static final long ONE_YEAR_SECONDS = 60 * 60 * 24 * 365;

	/**
	 * 设置客户端缓存过期时间。
	 * 
	 * @param response
	 *            Servlet Response。
	 * @param expiresSeconds
	 *            过期时长（秒）。
	 */
	public static void setExpiresHeader(HttpServletResponse response,
			long expiresSeconds) {
		// Http 1.0 header
		response.setDateHeader("Expires", System.currentTimeMillis()
				+ expiresSeconds * 1000);
		// Http 1.1 header
		response.setHeader("Cache-Control", "private, max-age="
				+ expiresSeconds);
	}

	/**
	 * 设置禁止客户端缓存。
	 * 
	 * @param response
	 *            Servlet Response。
	 */
	public static void setDisableCacheHeader(HttpServletResponse response) {
		// Http 1.0 header
		response.setDateHeader("Expires", 1L);
		response.addHeader("Pragma", "no-cache");
		// Http 1.1 header
		response.setHeader("Cache-Control", "no-cache, no-store, max-age=0");
	}

	/**
	 * 设置Last-Modified头信息。
	 * 
	 * @param response
	 *            - response。
	 * @param lastModifiedDate
	 *            - last modified date。
	 */
	public static void setLastModifiedHeader(HttpServletResponse response,
			long lastModifiedDate) {
		response.setDateHeader("Last-Modified", lastModifiedDate);
	}

	/**
	 * 设置Etag头信息。
	 * 
	 * @param response
	 *            Servlet Response。
	 * @param etag
	 *            要设置的头信息。
	 */
	public static void setEtag(HttpServletResponse response, String etag) {
		response.setHeader("ETag", etag);
	}

	/**
	 * 根据浏览器If-Modified-Since Header， 计算文件是否已被修改。
	 * 
	 * 如果无修改，checkIfModify返回false，设置304 not modify status。
	 * 
	 * @param request
	 *            Servlet Request。
	 * @param response
	 *            - Servlet Response。
	 * @param lastModified
	 *            内容的最后修改时间。
	 * @return 如果成功，则返回 true；否则返回 false。
	 */
	public static boolean checkIfModifiedSince(HttpServletRequest request,
			HttpServletResponse response, long lastModified) {
		long ifModifiedSince = request.getDateHeader("If-Modified-Since");
		if ((ifModifiedSince != -1) && (lastModified < ifModifiedSince + 1000)) {
			response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			return false;
		}
		return true;
	}

	/**
	 * 根据浏览器 If-None-Match Header, 计算Etag是否已无效。
	 * 
	 * 如果Etag有效, checkIfNoneMatch返回false, 设置304 not modify status.
	 * 
	 * @param request
	 *            - Servlet Request。
	 * @param response
	 *            - Servlet Response。
	 * @param etag
	 *            内容的ETag.
	 * @return 如果成功，则返回 true；否则返回 false。
	 */
	public static boolean checkIfNoneMatchEtag(HttpServletRequest request,
			HttpServletResponse response, String etag) {
		String headerValue = request.getHeader("If-None-Match");
		if (headerValue != null) {
			boolean conditionSatisfied = false;
			if (!"*".equals(headerValue)) {
				StringTokenizer commaTokenizer = new StringTokenizer(
						headerValue, ",");

				while (!conditionSatisfied && commaTokenizer.hasMoreTokens()) {
					String currentToken = commaTokenizer.nextToken();
					if (currentToken.trim().equals(etag)) {
						conditionSatisfied = true;
					}
				}
			} else {
				conditionSatisfied = true;
			}

			if (conditionSatisfied) {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				response.setHeader("ETag", etag);
				return false;
			}
		}
		return true;
	}

	/**
	 * 设置让浏览器弹出下载对话框的Header。
	 * 
	 * @param response
	 *            - Servlet Response。
	 * @param fileName
	 *            下载后的文件名。
	 */
	public static void setFileDownloadHeader(HttpServletResponse response,
			String fileName) {
		try {
			// 中文文件名支持
			String encodedfileName = new String(fileName.getBytes(),
					"ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ encodedfileName + "\"");
		} catch (UnsupportedEncodingException e) {
		}
	}

	/**
	 * 取得带相同前缀的Request Parameters。返回的结果的Parameter名已去除前缀。
	 * 
	 * @param request
	 *            - Servlet Request。
	 * @param prefix
	 *            - 字符串前缀。
	 * @return 返回包含相关参数的<code>Map</code>。
	 */
	public static Map<String, Object> getParametersStartingWith(
			ServletRequest request, String prefix) {
		Assert.notNull(request, "Request 对象不能为空。");
		Enumeration<?> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {

				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * 对Http Basic验证的 Header进行编码。
	 * 
	 * @param userName
	 *            - 用户名。
	 * @param password
	 *            - 密码。
	 * @return 返回编码后的用户名密码串。
	 */
	public static String encodeHttpBasic(String userName, String password) {
		String encode = userName + ":" + password;
		return "Basic " + EncodeUtils.base64Encode(encode.getBytes());
	}
}
