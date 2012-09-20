/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */
 
/**
 * 正则表达式处理工具类。
 * 
 * @author Leaon
 *
 * Date						Author				Description			
 * ---------------------------------------------------------------------------------------------
 * 2012-08-17				Leaon				创建RegexUtil.js。
 *
 */
function RegexUtil() {

	/**
	 * 判断是否是中文字符。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isChinese = function(str) {
		var str = str.replace(/(^\s*)|(\s*$)/g, '');
		if (!(/^[\u4E00-\uFA29]*$/.test(str) && (!/^[\uE7C7-\uE7F3]*$/
				.test(str)))) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否是电子邮件。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isEmail = function(str) {
		if (/^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/.test(str)) {
			return true
		}
		return false;
	}

	/**
	 * 判断是否是图片格式。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isImg = function(str) {
		var objReg = new RegExp("[.]+(jpg|jpeg|swf|gif)$", "gi");
		if (objReg.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是整型数字。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isInteger = function(str) {
		if (/^-?\d+$/.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否浮点型数字。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isFloat = function(str) {
		if (/^(-?\d+)(\.\d+)?$/.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是邮政编码。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isPost = function(str) {
		if (/^\d{1,6}$/.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是手机号码。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isMobile = function(str) {
		if (/^1[35]\d{9}/.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是电话号码。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isPhone = function(str) {
		if (/^(0[1-9]\d{1,2}-)\d{7,8}(-\d{1,8})?/.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是QQ号码。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isQQ = function(str) {
		if (/^\d{5,9}$/.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是IP地址。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isIP = function(str) {
		var reg = /^(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])$/;
		if (reg.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是日期类型。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isDate = function(str) {
		var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
		if (reg.test(str)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否是身份证号码。
	 * 
	 * @param {String} str 待检查字符串。
	 * @return {Boolean} 如果复合则返回true；否则返回false。
	 */
	this.isIdCardNo = function(idNumber) {
		var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5,
				8, 4, 2, 1);
		var varArray = new Array();
		var lngProduct = 0;
		var intCheckDigit;

		if ((idNumber.length != 15) && (idNumber.length != 18)) {
			return false;
		}
		for (i = 0; i < idNumber.length; i++) {
			varArray[i] = idNumber.charAt(i);
			if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
				return false;
			} else if (i < 17) {
				varArray[i] = varArray[i] * factorArr[i];
			}
		}
		if (idNumber.length == 18) {
			var date8 = idNumber.substring(6, 14);
			if (checkDate(date8) == false) {
				return false;
			}
			for (i = 0; i < 17; i++) {
				lngProduct = lngProduct + varArray[i];
			}
			intCheckDigit = 12 - lngProduct % 11;
			switch (intCheckDigit) {
				case 10 :
					intCheckDigit = 'X';
					break;
				case 11 :
					intCheckDigit = 0;
					break;
				case 12 :
					intCheckDigit = 1;
					break;
			}
			if (varArray[17].toUpperCase() != intCheckDigit) {
				return false;
			}
		} else {
			var date6 = idNumber.substring(6, 12);
			if (checkDate(date6) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 将RegexUtil工具类加载到当前DOM。
	 */
	document.write("var regexUtil = new RegexUtil();");
}
