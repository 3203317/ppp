package com.qingshu.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
/**
 * 数值处理工具类
 * @author ZhengBin
 *
 */
public class NumberUtil {
	public final static int scale = 2;
	/**
	 * 保留两位小数
	 * @param value
	 * @return 
	 */
	public static double round(double value) {
		return new BigDecimal(Double.toString(value)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	/**
	 * 格式化浮点数为0.00（保留两位小数）
	 * @param value
	 * @return
	 */
	public static String roundStr(double value) {
		return new DecimalFormat("0.00").format(value);
	}
	/**
	 * 格式化带%的字符串（保留两位小数）
	 * @param value
	 * @return
	 */
	public static String StringFmt(String value) {
		String strFmt=null;
		for(int i=0;i<value.length();i++){
			strFmt = NumberUtil.roundStr(Double.parseDouble(value.substring(0, value.indexOf("%"))))+"%";
		}
		return strFmt;
	}
	
	
	public static void main(String[] args) {
		System.out.println(roundStr(2.654));
	}
}
