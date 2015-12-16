package com.qingshu.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.serial.SerialClob;

import org.apache.log4j.Logger;

public class ConvertUtils {
	private final static Logger log = Logger.getLogger(ConvertUtils.class);

	/**
	 * 字符串转数字
	 * 
	 * @param str
	 * @return
	 */
	public static Integer getInteger(Object object) {
		Integer i = null;
		if (ObjectUtils.isEmpty(object)) {
			return i;
		}
		try {
			i = (Integer.parseInt(getString(object)));
		} catch (NumberFormatException e) {
			log.debug("字符串转数字异常");
			return i;
		}
		return i;
	}

	/**
	 * 字符串转数字
	 * 
	 * @param str
	 * @return
	 */
	public static Short getShort(String str) {
		Short i = null;
		if (ObjectUtils.isEmpty(str)) {
			return i;
		}
		try {
			i = (Short.parseShort(str));
		} catch (NumberFormatException e) {
			log.debug("字符串转数字异常");
			return i;
		}
		return i;
	}

	/**
	 * 字符串转Long
	 * 
	 * @param str
	 * @return
	 */
	public static Long getLong(String str) {
		Long l = null;
		if (ObjectUtils.isEmpty(str)) {
			return l;
		}
		try {
			l = (Long.parseLong(str));
		} catch (NumberFormatException e) {
			log.debug("字符串转数字异常");
			return l;
		}
		return l;
	}

	/**
	 * 字符串转Double
	 * 
	 * @param s
	 * @return
	 */
	public static Double getDouble(String s) {
		Double d = null;
		if (ObjectUtils.isEmpty(s)) {
			return d;
		}
		try {
			d = (Double.parseDouble(s));
		} catch (NumberFormatException e) {
			return d;
		}
		return d;
	}

	public static String getString(Object object) {
		if (ObjectUtils.isEmpty(object)) {
			return "";
		}
		return (object.toString().trim());
	}

	/**
	 * 字符串转Double
	 * 
	 * @param s
	 * @return
	 */
	public static Double getDouble(Object object) {
		Double d = null;
		if (ObjectUtils.isEmpty(object)) {
			return d;
		}
		try {
			d = (Double.parseDouble(getString(object)));
		} catch (NumberFormatException e) {
			return d;
		}
		return d;
	}

	/**
	 * CLOB类型转换成String类型
	 */

	public String ClobToString(Clob clob) throws SQLException, IOException {

		String reString = "";
		Reader is = clob.getCharacterStream();// 得到流
		BufferedReader br = new BufferedReader(is);
		String s = br.readLine();
		StringBuffer sb = new StringBuffer();
		while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
			sb.append(s);
			s = br.readLine();
		}
		reString = sb.toString();
		return reString;
	}

	/**
	 * 将String转成Clob
	 * 
	 * @param str字段
	 */
	public static Clob stringToClob(String str) {
		if (null == str)
			return null;
		else {
			try {
				Clob c = new SerialClob(str.toCharArray());
				return c;
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * 解析页面经过encodeURI编码的字符串
	 * 
	 * @param str
	 */
	public static String getURLDecoder(String str) {
		String s = "";
		try {
			s = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * 字符串转Long
	 * 
	 * @param str
	 * @return
	 */
	public static Integer getInteger(Long l) {
		Integer i = null;
		if (ObjectUtils.isEmpty(l)) {
			return i;
		}
		try {
			i = l.intValue();
		} catch (NumberFormatException e) {
			log.debug("字符串转数字异常");
			return i;
		}
		return i;
	}

	/**
	 * List转换为数组
	 */
	public static String[] listToArray(List<String> list) {
		String[] strs = list.toArray(new String[list.size()]);
		return strs;
	}
	/**
	 * List转换为数组
	 * @param <T>
	 * @param <T>
	 *//*
	public static <T> Object[] listToArray(List<T> list) {
		Object[] strs = list.toArray(new Object[list.size()]);
		return strs;
	}*/

	/**
	 * 数组转换为字符串
	 */
	public static String ArrayToString(String[] strs) {
		StringBuffer sb = new StringBuffer();
		if (!ObjectUtils.isEmpty(strs)) {
			int i = 0;
			for (String string : strs) {
				sb.append(string);
				i++;
				if (i < strs.length) {
					sb.append(",");
				}
			}
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	public static <T> List<T> MapToList(Map<String, Object> maps) {

		List<T> list = new ArrayList<T>();
		for (Map.Entry<String, Object> entry : maps.entrySet()) {
			list.add((T) entry.getValue());
		}
		return list;
	}
}
