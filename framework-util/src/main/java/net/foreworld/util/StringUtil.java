package net.foreworld.util;

/**
 * StringUtil
 *
 * @author huangxin (3203317@qq.com)
 * @license LGPL (http://www.gnu.org/licenses/lgpl.html)
 * @copyright FOREWORLD.NET
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 *
	 * @param str
	 * @return 为null或“”则返回null，否则返回trim()后的字符串
	 */
	public static String isEmpty(String str) {
		if (null == str)
			return null;
		str = str.trim();
		return 0 == str.length() ? null : str;
	}

	public static String isEmpty(String str, String defaultStr) {
		String result = isEmpty(str);
		if (null == result)
			return defaultStr;
		return result;
	}
}
