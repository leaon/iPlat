/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

/**
 * 字符串处理工具类。
 * 
 * @author Leaon
 *
 * Date						Author				Description			
 * ---------------------------------------------------------------------------------------------
 * 2012-08-17				Leaon				创建string-util.js。
 *
 */
function StringUtil() {

	/**
	 * 检查字符串是否为空字符串。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	function isEmptyStr(str) {
		if (trim(str).length == 0 || str == null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查两个字符串是否相等。
	 * 
	 * @param {String}
	 *            str1 第一个字符串。
	 * @param {String}
	 *            str2 第二个字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	function equals(str1, str2) {
		if (str1 == str2) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查两个字符串在忽略大小写的情况下，是否相等。
	 * 
	 * @param {String}
	 *            str1 第一个字符串。
	 * @param {String}
	 *            str2 第二个字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	function equalsIgnoreCase(str1, str2) {
		if (str1.toUpperCase() == str2.toUpperCase()) {
			return true;
		}
		return false;
	}

	/**
	 * 检查字符串是否是有效位数。
	 * 
	 * @param {String}
	 *            str 待检查字符串。
	 * @param {Number}
	 *            cols 待确定的位数。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	function isValidateMinCols(str, cols) {
		if (str.length >= cols) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查字符串是否复合特定的长度。
	 * 
	 * @param {String}
	 *            str 待检查字符串。
	 * @param {Number}
	 *            cols 待确定的位数。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	function isValidateMaxCols(str, cols) {
		if (str.length <= cols) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 检查字符串是否复合特定的长度。
	 * 
	 * @param {String}
	 *            str 待检查字符串。
	 * @param {Number}
	 *            min 允许的最小长度。
	 * @param {Number}
	 *            max 允许的最大长度。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	function isValidateRangeCols(str, min, max) {
		if (str.length >= min && str.length <= max) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 去掉字符串的前后空格。
	 * 
	 * @param {String}
	 *            str 待处理字符串。
	 * @return {String} 返回处理后的字符串。
	 */
	function trim(str) {
		str = trimLeft(trimRight(str));
		return str;
	}

	/**
	 * 去除字符串左侧的空格。
	 * 
	 * @param {String}
	 *            str 待处理字符串。
	 * @return {String} 返回处理后的字符串。
	 */
	function trimLeft(str) {
		var pattern = /^\s/;
		while (pattern.test(str)) {
			str = str.substring(1);
		}
		return str;
	}

	/**
	 * 去除字符串右侧的空格。
	 * 
	 * @param {String}
	 *            str 待处理字符串。
	 * @return {String} 返回处理后的字符串。
	 */
	function trimRight(str) {
		var pattern = /\s$/;
		while (pattern.test(str)) {
			str = str.substring(0, str.length - 1);
		}
		return str;
	}

	/**
	 * 将StringUtil工具类加载到当前DOM。
	 */
	document.write("var stringUtil = new StringUtil();");
}
