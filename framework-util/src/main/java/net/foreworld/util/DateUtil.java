package net.foreworld.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * RestUtil
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public class DateUtil {

	public static String DATE_PATTERN = "yyyy-MM-dd";
	public static String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static String TIME_PATTERN = "HH:mm:ss";

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			DATE_PATTERN);

	/**
	 * 检查字符串是否是日期格式
	 *
	 * @param format
	 *            默认 "yyyy-MM-dd"
	 * @param str
	 *            "2007-02-28"
	 * @return
	 */
	public static Date checkDateFormat(String format, String str) {
		str = StringUtil.isEmpty(str);
		if (null == str)
			return null;

		// TODO
		DateFormat df = (null == format) ? dateFormat : new SimpleDateFormat(
				format);

		try {
			Date date = (Date) df.parse(str);
			return (str.equals(df.format(date))) ? date : null;
		} catch (Exception ignore) {
			return null;
		}
	}

	/**
	 * 日期类型转换字符串
	 *
	 * @param format
	 *            默认 "yyyy-MM-dd"
	 * @param date
	 * @return
	 */
	public static String date2Str(String format, Date date) {
		DateFormat df = (null == format) ? dateFormat : new SimpleDateFormat(
				format);
		return df.format(date);
	}

	/**
	 * 获取相对日期
	 *
	 * @param date
	 * @param field
	 *            Calendar.DATE
	 * @param amount
	 *            -5
	 * @return
	 */
	public static Date getRelativeDate(Date date, int field, int amount) {
		if (null == date)
			return null;

		Calendar newDate;
		(newDate = Calendar.getInstance()).setTime(date);

		// TODO
		newDate.add(field, amount);
		return newDate.getTime();
	}
}
