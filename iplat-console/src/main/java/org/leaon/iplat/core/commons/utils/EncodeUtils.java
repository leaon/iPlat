/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

package org.leaon.iplat.core.commons.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringEscapeUtils;

/**
 * 
 * 各种格式的编码解码工具类，集成Commons-Codec，Commons-Lang及JDK提供的编解码方法。
 *
 * @author Leaon
 * @version 1.0
 * @since 1.0
 * 
 * Date				Author				Description			
 * -------------------------------------------------------------------------------------------------
 * 2012-8-5				Leaon				创建EncodeUtils.java。
 *
 */
public class EncodeUtils {

	/**
	 * 静态常量，缺省URL编解码时使用的字符集编码。
	 */
// TODO: Avoid excessively long variable names like DEFAULT_URL_ENCODING
	private static final String DEFAULT_URL_ENCODING = "UTF-8";

	/**
	 * Hex编码。
	 * 
	 * @param input
	 *            待编码比特流。
	 * @return 返回编码字符串。
	 */
// TODO: Parameter 'input' is not assigned and could be declared final
	public static String hexEncode(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码。
	 * 
	 * @param input
	 *            待解码字符串。
	 * @return 返回解码比特流。
	 */
// TODO: Parameter 'input' is not assigned and could be declared final
	public static byte[] hexDecode(String input) {
		try {
			return Hex.decodeHex(input.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException("哈希解码错误", e);
		}
	}

	/**
	 * Base64编码。
	 * 
	 * @param input
	 *            待编码比特流。
	 * @return 返回编码字符串。
	 */
// TODO: Parameter 'input' is not assigned and could be declared final
	public static String base64Encode(byte[] input) {
		return new String(Base64.encodeBase64(input));
	}

	/**
	 * Base64编码， URL安全(将Base64中的URL非法字符如"+"，"/'，"="转为其他字符， 见RFC3548)。
	 * 
	 * @param input
	 *            待编码比特流。
	 * @return 返回编码字符串。
	 */
// TODO: Parameter 'input' is not assigned and could be declared final
	public static String base64URLSafeEncode(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码。
	 * 
	 * @param input
	 *            待解码字符串。
	 * @return 返回解码的比特流。
	 */
// TODO: Parameter 'input' is not assigned and could be declared final
	public static byte[] base64Decode(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * URL编码， Encode默认为UTF-8。
	 * 
	 * @param input
	 *            待编码比特流。
	 * @return 返回编码字符串。
	 */
// TODO: Parameter 'input' is not assigned and could be declared final
	public static String URLEncode(String input) {
		try {
			return URLEncoder.encode(input, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("不支持的编码类型", e);
		}
	}

	/**
	 * URL解码, Encode默认为UTF-8。
	 * 
	 * @param input
	 *            待解码字符串。
	 * @return 返回解码比特流。
	 */
// TODO: Parameter 'input' is not assigned and could be declared final
	public static String URLDecode(String input) {
		try {
			return URLDecoder.decode(input, DEFAULT_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("不支持的编码类型", e);
		}
	}

	/**
	 * HTML特殊字符转换。转换含有特殊字符的HTML字符串。
	 * 
	 * @param html
	 *            待转换的HTML字符串。
	 * @return 返回转换后的HTML字符串。
	 */
// TODO: Parameter 'html' is not assigned and could be declared final
	public static String HTMLEscape(String html) {
		return StringEscapeUtils.escapeHtml(html);
	}

	/**
	 * HTML特殊字符过滤。将转换后的特殊HTML字符过滤掉。
	 * 
	 * @param htmlEscaped
	 *            待过滤HTML字符串。
	 * @return 返回过滤后的HTML字符串。
	 */
// TODO: Parameter 'htmlEscaped' is not assigned and could be declared final
	public static String HTMLUnescape(String htmlEscaped) {
		return StringEscapeUtils.unescapeHtml(htmlEscaped);
	}

	/**
	 * HTML特殊字符转换。转换含有特殊字符的HTML字符串。
	 * 
	 * @param xml
	 *            待转换XML字符串。
	 * @return 返回转换后的XML字符串。
	 */
// TODO: Parameter 'xml' is not assigned and could be declared final
	public static String XMLEscape(String xml) {
		return StringEscapeUtils.escapeXml(xml);
	}

	/**
	 * HTML特殊字符过滤。过滤含有特殊字符的HTML字符串。
	 * 
	 * @param xmlEscaped
	 *            待过滤的XML字符串。
	 * @return 返回过滤后的XML字符串。
	 */
// TODO: Parameter 'xmlEscaped' is not assigned and could be declared final
	public static String XMLUnescape(String xmlEscaped) {
		return StringEscapeUtils.unescapeXml(xmlEscaped);
	}
}
