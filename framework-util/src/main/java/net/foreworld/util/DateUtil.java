package net.foreworld.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RestUtil
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public class DateUtil {

	/**
	 * 检查字符串是否是日期格式
	 *
	 * @param format
	 *            "yyyy-MM-dd"
	 * @param str
	 *            "2007-02-28"
	 * @return
	 */
	public static boolean checkDateFormat(String format, String str) {
		final DateFormat df = new SimpleDateFormat(format);
		try {
			Date date = (Date) df.parse(str);
			return str.equals(df.format(date));
		} catch (Exception ignore) {
			return false;
		}
	}
}
