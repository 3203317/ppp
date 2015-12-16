package com.qingshu.common.plugin.poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class PoiUtils {

	/**
	 * 把单元格内的类型转换至String类型
	 */
	public static Object ConvertCellStr(Cell cell, Object cellStr) {
		if(cell!=null)
		switch (cell.getCellType()) {

		case Cell.CELL_TYPE_STRING:
			// 读取String
			cellStr = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			// 得到Boolean对象的方法
			cellStr = cell.getBooleanCellValue();
			break;

		case Cell.CELL_TYPE_NUMERIC:
			// 先看是否是日期格式
			if (DateUtil.isCellDateFormatted(cell)) {
				// 读取日期格式
				cellStr = cell.getDateCellValue();
			} else {
				// 读取数字
				cellStr =cell.getNumericCellValue();
			}
			break;
		case Cell.CELL_TYPE_FORMULA:
			// 读取公式
			cellStr = cell.getCellFormula();
			break;
		}
		return cellStr;
	}
}
