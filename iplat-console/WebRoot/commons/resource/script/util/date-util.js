/**
 * Copyright © 2010 - 2012 Leaon. All Rights Reserved.
 */

/**
 * 日期处理工具类。
 * 
 * @author Leaon
 * @param {String} year 年。
 * @param {String} month 月。
 * @param {String} day 日。
 * @param {String} hour 时。
 * @param {String} minute 分。
 * @param {String} second 秒。
 *
 * Date						Author				Description			
 * ---------------------------------------------------------------------------------------------
 * 2012-08-17				Leaon				创建date-util.js。
 *
 */
function DateUtil(year, month, day, hour, minute, second) {
	/** 
	 * 初始化当前客户端日期时间。
	 */
	this.curDateTime = new Date();

	/**  
	 * 返回Date类型对象。
	 * 
	 * @return {Date} 通过各项参数返回日期，如果参数为空，则返回当前日期。
	 */
	this.getDateTime = function() {
		var date = null;
		if ((year == null && month == null && day == null && hour == null
				&& minute == null && second == null)) {
			date = this.curDateTime;
		} else if (year != null && month != null && day != null && hour == null
				&& minute == null && second == null) {
			date = new Date(year, month - 1, day);
		} else if (year != null && month != null && day != null && hour != null
				&& minute != null && second != null) {
			date = new Date(year, month - 1, day, hour, minute, second);
		}
		return date;
	};

	/**  
	 * 取得年值。
	 * 
	 * @return {Number} 返回年。
	 */
	this.getYear = function() {
		var year = null;
		var dateTime = this.getDateTime();

		if (dateTime != null) {
			year = dateTime.getFullYear();
		} else {
			year = this.curDateTime.getFullYear();
		}
		return year;
	};

	/**  
	 * 取得月值。 
	 * 
	 * @return {Number} 返回月。
	 */
	this.getMonth = function() {
		var month = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			month = dateTime.getMonth() + 1;
		} else {
			month = this.curDateTime.getMonth() + 1;
		}
		return month;
	};

	/**  
	 * 取得日值。
	 * 
	 * @return {Number} 返回日。
	 */
	this.getDay = function() {
		var day = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			day = dateTime.getDate();
		} else {
			day = this.curDateTime.getDate();
		}
		return day;
	};

	/**  
	 * 取得24进制小时。
	 * 
	 * @return {Number} 返回24进制小时。
	 */
	this.getHours = function() {
		var hour = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			hour = dateTime.getHours();
		} else {
			hour = this.curDateTime.getHours();
		}
		return hour;
	};

	/**  
	 * 取得分值。
	 * 
	 * @return {Number} 返回分钟。
	 */
	this.getMinutes = function() {
		var minute = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			minute = dateTime.getMinutes();
		} else {
			minute = this.curDateTime.getMinutes();
		}
		return minute;
	};

	/**  
	 * 取得秒值。
	 * 
	 * @return {Number} 返回秒。
	 */
	this.getSeconds = function() {
		var second = null;
		var dateTime = this.getDateTime();
		if (dateTime != null) {
			second = dateTime.getSeconds();
		} else {
			second = this.curDateTime.getSeconds();
		}
		return second;
	};

	/**  
	 * 取得一天之内的时刻范围。  
	 *   
	 * @return {String} ["凌晨"|"上午"|"中午"|"下午"|"晚上"]。
	 */
	this.getDateRange = function() {
		var hour = window.parseInt(this.getHour());
		var range = "凌晨";
		if (hour >= 6 && hour < 11) {
			range = "早晨";
		} else if (hour >= 11 && hour < 14) {
			range = "中午";
		} else if (hour >= 14 && hour <= 18) {
			range = "下午";
		} else if (hour > 18 && hour < 24) {
			range = "晚上";
		}
		return range;
	};
	/**  
	 * 取得12进制小时值。
	 * 
	 * @return {Number} 返回12进制小时。
	 */
	this.get12PatternHour = function() {
		return hour > 12 ? (hour + 12 - 24) : hour;
	};

	/**  
	 * 判断是否为闰年。
	 * 闰年算法说明：能被4整除并且不能被100整除或者能被400整除的年份是闰年。 
	 * 
	 *  @return {Boolean} 如果是闰年，返回true；否则返回false。
	 */
	this.isLeapYear = function() {
		var flag = false;
		if ((this.getYear() % 4 == 0 && this.getYear() % 100 != 0)
				|| (this.getYear() % 400 == 0)) {
			flag = true;
		}
		return flag;
	};

	/**  
	 * 根据月份获取该月的最大天数。
	 * 
	 * @return {Number} 返回当月最大天数。
	 */
	this.getMaxDaysOfMonth = function() {
		var days = 31;
		var month = this.getMonth();
		switch (month) {
		case 2:
			if (this.isLeapYear()) {
				days = 29;
			} else {
				days = 28;
			}
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			days = 30;
			break;
		default:
			break;
		}
		return days;
	};

	/**  
	 * 根据指定的格式对日期进行格式化。
	 * 
	 * @param formatStr 
	 * @return {String} 返回格式化后的日期字符串。
	 */
	this.formatDate = function(formatStr) {
		var str = formatStr;
		//var week = ['日', '一', '二', '三', '四', '五', '六'];

		str = str.replace(/yyyy|YYYY/, this.getFullYear());
		str = str.replace(/yy|YY/,
				(this.getYear() % 100) > 9 ? (this.getYear() % 100).toString()
						: '0' + (this.getYear() % 100));

		str = str.replace(/MM/, this.getMonth() > 9 ? this.getMonth()
				.toString() : '0' + this.getMonth());
		str = str.replace(/M/g, this.getMonth());

		//str = str.replace(/w|W/g, week[this.getDay()]);

		str = str.replace(/dd|DD/, this.getDay() > 9 ? this.getDay().toString()
				: '0' + this.getDay());
		str = str.replace(/d|D/g, this.getDate());

		str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours()
				.toString() : '0' + this.getHours());
		str = str.replace(/h|H/g, this.getHours());
		str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes()
				.toString() : '0' + this.getMinutes());
		str = str.replace(/m/g, this.getMinutes());

		str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds()
				.toString() : '0' + this.getSeconds());
		str = str.replace(/s|S/g, this.getSeconds());

		return str;
	};

	/**  
	 * 求两个时间的天数差 日期格式为“YYYY-MM-dd”。
	 * 
	 * @param dateOne 第一个日期。
	 * @param dateTwo 第二个日期。
	 * @return {Number} 返回两个日期之间的天数差。
	 */
	this.daysBetween = function(dateOne, dateTwo) {
		var OneMonth = dateOne.substring(5, dateOne.lastIndexOf('-'));
		var OneDay = dateOne.substring(dateOne.length,
				dateOne.lastIndexOf('-') + 1);
		var OneYear = dateOne.substring(0, dateOne.indexOf('-'));

		var TwoMonth = dateTwo.substring(5, dateTwo.lastIndexOf('-'));
		var TwoDay = dateTwo.substring(dateTwo.length,
				dateTwo.lastIndexOf('-') + 1);
		var TwoYear = dateTwo.substring(0, dateTwo.indexOf('-'));

		var cha = ((Date.parse(OneMonth + '/' + OneDay + '/' + OneYear) - Date
				.parse(TwoMonth + '/' + TwoDay + '/' + TwoYear)) / 86400000);
		return Math.abs(cha);
	};

	/**  
	 * 日期相加计算,计算对应的时间的毫秒数。
	 * 
	 * @param strInterval {String} 时间操作基数，可以是s:秒；mi:分钟；h:小时；d:天；w:周；q：季度；m:月；y:年。
	 * @param number {Number} 要增加的数量。
	 * @return {Date} 返回新的日期。
	 */
	this.DateAdd = function(strInterval, number) {
		var dtTmp = this.getDateTime();
		switch (strInterval) {
		case 's':
			return new Date(Date.parse(dtTmp) + (1000 * number));
		case 'mi':
			return new Date(Date.parse(dtTmp) + (60000 * number));
		case 'h':
			return new Date(Date.parse(dtTmp) + (3600000 * number));
		case 'd':
			return new Date(Date.parse(dtTmp) + (86400000 * number));
		case 'w':
			return new Date(Date.parse(dtTmp) + ((86400000 * 7) * number));
		case 'q':
			return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + number
					* 3, dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(),
					dtTmp.getSeconds());
		case 'm':
			return new Date(dtTmp.getFullYear(), (dtTmp.getMonth()) + number,
					dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(),
					dtTmp.getSeconds());
		case 'y':
			return new Date((dtTmp.getFullYear() + number), dtTmp.getMonth(),
					dtTmp.getDate(), dtTmp.getHours(), dtTmp.getMinutes(),
					dtTmp.getSeconds());
		}
	};

	/**  
	 * 日期相减计算,计算两个日期相差的数量级数。
	 * 
	 * @param strInterval {String} 时间操作基数，可以是s:秒；mi:分钟；h:小时；d:天；w:周；q：季度；m:月；y:年。
	 * @param dtEnd {Date} 要相减的日期。
	 * @return {Number} 返回新的日期。
	 */
	this.DateDiff = function(strInterval, dtEnd) {
		var dtStart = this.getDateTime();
		if (typeof dtEnd == 'string')//如果是字符串转换为日期型   
		{
			dtEnd = toDate(dtEnd);
		}
		switch (strInterval) {
		case 's':
			return parseInt((dtEnd - dtStart) / 1000);
		case 'mi':
			return parseInt((dtEnd - dtStart) / 60000);
		case 'h':
			return parseInt((dtEnd - dtStart) / 3600000);
		case 'd':
			return parseInt((dtEnd - dtStart) / 86400000);
		case 'w':
			return parseInt((dtEnd - dtStart) / (86400000 * 7));
		case 'm':
			return (dtEnd.getMonth() + 1)
					+ ((dtEnd.getFullYear() - dtStart.getFullYear()) * 12)
					- (dtStart.getMonth() + 1);
		case 'y':
			return dtEnd.getFullYear() - dtStart.getFullYear();
		}
	};

	/**
	 * 日期输出字符串，重载了系统的toString方法。
	 * 
	 * @param showWeek 是否显示星期。
	 * @return {String} 返回日期的字符串格式。
	 */
	this.toString = function(showWeek) {
		var myDate = this.getDateTime();
		var str = myDate.toLocaleDateString();
		if (showWeek) {
			var week = [ '日', '一', '二', '三', '四', '五', '六' ];
			str += ' 星期' + week[myDate.getDay()];
		}
		return str;
	};

	/**
	 * 日期合法性验证。
	 * 格式为：YYYY-MM-DD或YYYY/MM/DD
	 * 
	 * @param dateStr {String} 要验证的日期。
	 * @return {Booleaon} 如果正确返回true；否则返回false。
	 */
	this.isValidDate = function(dateStr) {
		var sDate = dateStr.replace(/(^\s+|\s+$)/g, ''); //去两边空格;    
		if (sDate == '') {
			return true;
		}
		//如果格式满足YYYY-(/)MM-(/)DD或YYYY-(/)M-(/)DD或YYYY-(/)M-(/)D或YYYY-(/)MM-(/)D就替换为''    
		//数据库中，合法日期可以是:YYYY-MM/DD(2003-3/21),数据库会自动转换为YYYY-MM-DD格式    
		var s = sDate.replace(
				'/[\d]{ 4,4 }[\-/]{ 1 }[\d]{ 1,2 }[\-/]{ 1 }[\d]{ 1,2 }/g', '');
		if (s == '') //说明格式满足YYYY-MM-DD或YYYY-M-DD或YYYY-M-D或YYYY-MM-D    
		{
			var t = new Date(sDate.replace(/\-/g, '/'));
			var ar = sDate.split('/[-/:]/');
			if (ar[0] != t.getYear() || ar[1] != t.getMonth() + 1
					|| ar[2] != t.getDate()) {
				//alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');    
				return false;
			}
		} else {
			//alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。');    
			return false;
		}
		return true;
	};

	/**
	 * 日期合法性验证。
	 * 格式为：格式为：YYYY-MM-DD HH:MM:SS
	 * 
	 * @param {String} dateStr 要验证的日期。
	 * @return {Booleaon} 如果正确返回true；否则返回false。
	 */
	this.isValidDateTime = function(dateStr) {
		var reg = /^(\d+)-(\d{ 1,2 })-(\d{ 1,2 }) (\d{ 1,2 }):(\d{ 1,2 }):(\d{ 1,2 })$/;
		var r = dateStr.match(reg);
		if (r == null)
			return false;
		r[2] = r[2] - 1;
		var d = new Date(r[1], r[2], r[3], r[4], r[5], r[6]);
		if (d.getFullYear() != r[1])
			return false;
		if (d.getMonth() != r[2])
			return false;
		if (d.getDate() != r[3])
			return false;
		if (d.getHours() != r[4])
			return false;
		if (d.getMinutes() != r[5])
			return false;
		if (d.getSeconds() != r[6])
			return false;
		return true;
	};

	/**
	 * 把日期分割成数组。
	 * 
	 * @return {Array} 返回包含日期个字段值得数组。
	 */
	this.toArray = function() {
		var myDate = this.getDateTime();
		var myArray = Array();
		myArray[0] = myDate.getFullYear();
		myArray[1] = myDate.getMonth();
		myArray[2] = myDate.getDate();
		myArray[3] = myDate.getHours();
		myArray[4] = myDate.getMinutes();
		myArray[5] = myDate.getSeconds();
		return myArray;
	};

	/**
	 * 取得日期数据信息，参数 interval 表示数据类型。   
	 * 
	 * @param {String} interval 字段类型，y：年；m：月；d：日；ww：周；h：时；mi：分；s：秒。
	 * @return {Number} 返回日期字段的字符串值。
	 */
	this.datePart = function(interval) {
		var myDate = this.getDateTime();
		var partStr = '';
		//var Week = ['日', '一', '二', '三', '四', '五', '六'];
		switch (interval) {
		case 'y':
			partStr = myDate.getFullYear();
			break;
		case 'm':
			partStr = myDate.getMonth() + 1;
			break;
		case 'd':
			partStr = myDate.getDate();
			break;
		/*	
		case 'w' :
			partStr = Week[myDate.getDay()];
			break;
		 */
		case 'ww':
			partStr = myDate.weekNumOfYear();
			break;
		case 'h':
			partStr = myDate.getHours();
			break;
		case 'mi':
			partStr = myDate.getMinutes();
			break;
		case 's':
			partStr = myDate.getSeconds();
			break;
		}
		return partStr;
	};

	/**
	 * 取得当前日期所在周是一年中的第几周。   
	 * 
	 * @return {Number} 返回当前日期在一年中的周数。
	 */
	this.weekNumOfYear = function() {
		var myDate = this;
		var ary = myDate.toArray();
		var year = ary[0];
		var month = ary[1] + 1;
		var day = ary[2];
		myDate = new Date(year, month, day);
		var result = this.datePart("ww", myDate);
		return result;
	};

	/**
	 * 将字符串转换成日期类型。   
	 * 
	 * @param {String} dateStr 要转换的字符串。
	 * @return {Date} 返回转换后的日期类型。
	 */
	this.toDate = function(dateStr) {
		if (isValidDate(dateStr)) {
			var converted = Date.parse(dateStr);
			var myDate = new Date(converted);
			if (isNaN(myDate)) {
				var arys = dateStr.split('-');
				myDate = new Date(arys[0], --arys[1], arys[2]);
			}
			return myDate;
		} else {
			return null;
		}
	};

	/**
	 * 将DateUtil工具类加载到当前DOM。
	 */
	document.write("var dateUtil = new DateUtil();");
}