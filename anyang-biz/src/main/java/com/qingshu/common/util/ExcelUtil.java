package com.qingshu.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;

import com.qingshu.base.vo.Report;
import com.qingshu.base.vo.ReportIndex;

@SuppressWarnings("deprecation")
public final class ExcelUtil {

	private ExcelUtil() {
	}

	/** Excel 文件要存放的位置 */
	public static String outputFile = "excel_demo.xls";

	/**
	 * 标题列表
	 */
	private static String[] HEAD_LIST = { "序号", "名字", "年龄", "备注" };

	private static String[] VALUE_LIST = { "01", "感觉", "20", "1987-05-07",
			"........." };

	/**
	 * 字段列表
	 */
	private static String[] FIELD_LIST = { "index", "name", "age", "content" };

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// ------------------------------------------------------------
		 List<String[]> list = new ArrayList<String[]>();
		 list.add(VALUE_LIST);
		 list.add(VALUE_LIST);
		 list.add(VALUE_LIST);
		 //createExcel(outputFile, HEAD_LIST, list);

		// ------------------------------------------------------------
		 List<Map<String, Object>> dataList = new ArrayList<Map<String,
		Object>>();
		 Map<String, Object> map = new HashMap<String, Object>();
		 map.put("index", "001");
		 map.put("name", "张三");
		 map.put("age", "22");
		 map.put("content", "大家好");
		 dataList.add(map);
		 dataList.add(map);
		 dataList.add(map);
		
		 createExcel(getHomePath()+"/aa.xls", HEAD_LIST, FIELD_LIST, dataList,"测试");

		// -------------------------------------------------------------
		// readExcel(null);
		// --------------------------------------------------------------
		//new ExcelUtil().testReadExcel();
	}
	
	/**
	 * 创建通用EXCEL头部
	 * 
	 * @param headString 头部显示的字符
	 * @param colSum 该报表的列数
	 */
	public static void createNormalHead(String headString, int colSum,HSSFWorkbook wb,HSSFSheet sheet) {

		HSSFRow row = sheet.createRow(0);

		// 设置第一行
		HSSFCell cell = row.createCell(0);
		row.setHeight((short) 500);

		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(headString));

		// 指定合并区域
		sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));
		HSSFCellStyle cellStyle = wb.createCellStyle();

		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行

		// 设置单元格字体
		HSSFFont font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 380);
		cellStyle.setFont(font);

		cell.setCellStyle(cellStyle);
	}
	/**
	 * 创建标题行，指定了第几行和文字对齐方式
	 * @param headString
	 * @param colSum
	 * @param rowNum
	 * @param align
	 * @param textSize
	 * @param wb
	 * @param sheet
	 */
	public static void createNormalHead(String headString, int colSum,int rowNum,short align,short textSize,Workbook wb,Sheet sheet) {

		Row row = sheet.createRow(rowNum);
		
		// 设置第一行
		Cell cell = row.createCell(0);
		row.setHeight((short) 400);
		
		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(headString));
		
		// 指定合并区域
		sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, 0, colSum));
		CellStyle cellStyle = wb.createCellStyle();
		
		cellStyle.setAlignment(align); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行
		
		// 设置单元格字体
		Font font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) textSize);
		cellStyle.setFont(font);
		
		cell.setCellStyle(cellStyle);
	}
	/**
	 * 创建通用EXCEL头部
	 * 
	 * @param headString 头部显示的字符
	 * @param colSum 该报表的列数
	 */
	public static void createNormalHead(String headString, int colSum,Workbook wb,Sheet sheet) {
		
		Row row = sheet.createRow(0);
		
		// 设置第一行
		Cell cell = row.createCell(0);
		row.setHeight((short) 400);
		
		// 定义单元格为字符串类型
		cell.setCellType(HSSFCell.ENCODING_UTF_16);
		cell.setCellValue(new HSSFRichTextString(headString));
		
		// 指定合并区域
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colSum));
		CellStyle cellStyle = wb.createCellStyle();
		
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行
		
		// 设置单元格字体
		Font font = wb.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
		font.setFontHeight((short) 300);
		cellStyle.setFont(font);
		
		cell.setCellStyle(cellStyle);
	}
	/**
	 * 输出表
	 * @param wb
	 * @param response
	 */
	public static void excelOut(Workbook wb,String fileName,HttpServletResponse response){
		try {
			  response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8")); 
	          response.setContentType("application/msexcel;charset=UTF-8"); 
			// 新建一输出文件流
			OutputStream out;
				out = response.getOutputStream();
				wb.write(out);
				out.flush();
				// 操作结束，关闭文件
				out.close();
				response.flushBuffer();  
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	}
	public static String getHomePath()
	{
		FileSystemView fsv = FileSystemView.getFileSystemView(); 
		return fsv.getHomeDirectory().toString();
	}

	/**
	 * 使用举例
	 * 
	 */
	public void testCreateExcel() {

		List<Map<String, Object>> dataList = getDataList();
		List<String> headList = getHeadList();
		List<String> fieldList = getFieldList();

		try {
			createExcel("TEST01.xls", headList, fieldList, dataList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 使用举例
	 * 
	 * @throws Exception
	 * 
	 */
	@SuppressWarnings("static-access")
	public void testReadExcel() throws Exception {
		String excelUrl = "C:/javadeveloper/workspace/Mybatis_one/src/测试考勤2003.xls";
		List<String[]> list = this.readExcel(excelUrl);
		for (String[] str : list) {
			for (String s : str) {
				//System.out.print(s + " | ");
			}
			//System.out.println("");
		}
	}

	/**
	 * 测试举例
	 * 
	 * @return
	 */
	private List<Map<String, Object>> getDataList() {
		/**
		 * 数据集合
		 */
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		// 单行数据
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("index", "001");
		map.put("name", "张三");
		map.put("age", "22");
		map.put("content", "大家好");
		map.put("date", new Date());
		dataList.add(map);
		dataList.add(map);
		dataList.add(map);

		return dataList;
	}

	/**
	 * 测试举例
	 * 
	 * @return
	 */
	private List<String> getHeadList() {
		List<String> headList = new ArrayList<String>();
		headList.add("序号");
		headList.add("名字");
		headList.add("年龄");
		headList.add("出生");
		headList.add("备注");

		return headList;
	}

	/**
	 * 测试举例
	 * 
	 * @return
	 */
	private List<String> getFieldList() {
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("index");
		fieldList.add("name");
		fieldList.add("age");
		fieldList.add("date");
		fieldList.add("content");

		return fieldList;
	}

	/**
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题集合
	 * @param field_list
	 *            Excel文件Field标题集合
	 * @param dataList
	 *            Excel文件数据内容部分
	 * @throws Exception
	 */
	public static void createExcel(String excel_name, String[] headList,
			String[] fieldList, List<Map<String, Object>> dataList,String title)
			throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		HSSFSheet sheet = workbook.createSheet();
		// 在索引0的位置创建行（最顶端的行）
		createNormalHead(title,18,workbook,sheet);
		HSSFRow row = sheet.createRow(1);
		// ===============================================================
		for (int i = 0; i < headList.length; i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList[i]);
		}
		// ===============================================================

		for (int n = 0; n < dataList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 2);
			Map<String, Object> dataMap = dataList.get(n);
			// ===============================================================
			for (int i = 0; i < fieldList.length; i++) {

				// 在索引0的位置创建单元格（左上端）
				HSSFCell cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(objToString(dataMap.get(fieldList[i])));
			}
			// ===============================================================
		}

		// 新建一输出文件流
		FileOutputStream fOut = new FileOutputStream(excel_name);
		// 把相应的Excel 工作簿存盘
		workbook.write(fOut);
		fOut.flush();
		// 操作结束，关闭文件
		fOut.close();
		//System.out.println("[" + excel_name + "]" + "文件生成...");
	}
	/**
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题集合
	 * @param field_list
	 *            Excel文件Field标题集合
	 * @param dataList
	 *            Excel文件数据内容部分
	 * @throws Exception
	 */
	public static void createExcel(String excel_name, String[] headList,
			String[] fieldList, List<Map<String, Object>> dataList,String title,String date,HttpServletResponse response)
			throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		HSSFFont font = workbook.createFont();
		font.setFontName("仿宋_GB2312");
		cellStyle.setFont(font);
		
		HSSFCellStyle cellStyle2 = workbook.createCellStyle();
		cellStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle2.setWrapText(true);// 指定单元格自动换行
		
		HSSFSheet sheet = workbook.createSheet();
		sheet.setColumnWidth(0, 1800); 
		sheet.setColumnWidth(1, 3000); 
		sheet.setColumnWidth(2, 3000); 
		sheet.setColumnWidth(3, 3000); 
		sheet.setColumnWidth(4, 3000); 
		sheet.setColumnWidth(5, 3000); 
		sheet.setColumnWidth(6, 3000); 
		sheet.setColumnWidth(7, 3000); 
		sheet.setColumnWidth(8, 3000); 
		createNormalHead(title,8,workbook,sheet);
		HSSFRow row2 = sheet.createRow(1);
		row2.setHeight((short)350);
		HSSFCell cell0 = row2.createCell(0);
		cell0.setCellValue("日期");
		cell0.setCellStyle(cellStyle2);
		HSSFCell cell1 = row2.createCell(1);
		cell1.setCellValue(date);
		cell1.setCellStyle(cellStyle2);
		sheet.addMergedRegion(new Region(1, (short) 6, 1, (short) 8));
		HSSFCell cell6 = row2.createCell(6);
		cell6.setCellValue("单位：元/500克");
		cell6.setCellStyle(cellStyle2);
		HSSFRow row = sheet.createRow(2);
		row.setHeight((short)300);
		// ===============================================================
		for (int i = 0; i < headList.length; i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(cellStyle);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList[i]);
		}
		// ===============================================================
		if(dataList!=null&&dataList.size()>0){
			for (int n = 0; n < dataList.size(); n++) {
				// 在索引1的位置创建行（最顶端的行）
				HSSFRow row_value = sheet.createRow(n + 3);
				row_value.setHeight((short)300);
				Map<String, Object> dataMap = dataList.get(n);
				// ===============================================================
				for (int i = 0; i < fieldList.length; i++) {
					
					// 在索引0的位置创建单元格（左上端）
					HSSFCell cell = row_value.createCell(i);
					cell.setCellStyle(cellStyle);
					// 定义单元格为字符串类型
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					// 在单元格中输入一些内容
					cell.setCellValue(objToString(dataMap.get(fieldList[i])));
				}
				// ===============================================================
			}
		}
		  response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(excel_name, "UTF-8")); 
          response.setContentType("application/msexcel;charset=UTF-8"); 
		// 新建一输出文件流
		OutputStream out = response.getOutputStream(); 
		// 把相应的Excel 工作簿存盘
		workbook.write(out);
		out.flush();
		// 操作结束，关闭文件
		out.close();
		response.flushBuffer();  
		
		//System.out.println("[" + excel_name + "]" + "文件生成...");
	}

	/**
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题集合
	 * @param field_list
	 *            Excel文件Field标题集合
	 * @param dataList
	 *            Excel文件数据内容部分
	 * @throws Exception
	 */
	public static void createExcel(String excel_name, List<String> headList,
			List<String> fieldList, List<Map<String, Object>> dataList)
			throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		HSSFSheet sheet = workbook.createSheet();
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow(0);
		// ===============================================================
		for (int i = 0; i < headList.size(); i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList.get(i));
		}
		// ===============================================================

		for (int n = 0; n < dataList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 1);
			Map<String, Object> dataMap = dataList.get(n);
			// ===============================================================
			for (int i = 0; i < fieldList.size(); i++) {

				// 在索引0的位置创建单元格（左上端）
				HSSFCell cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(objToString(dataMap.get(fieldList.get(i))));
			}
			// ===============================================================
		}

		// 新建一输出文件流
		FileOutputStream fOut = new FileOutputStream(excel_name);
		// 把相应的Excel 工作簿存盘
		workbook.write(fOut);
		fOut.flush();
		// 操作结束，关闭文件
		fOut.close();
		//System.out.println("[" + excel_name + "]" + "文件生成...");
	}

	/**
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题集合
	 * @param field_list
	 *            Excel文件Field标题集合
	 * @param dataList
	 *            Excel文件数据内容部分
	 * @throws HSSFWorkbook
	 */
	public static HSSFWorkbook createExcel(List<String> headList,
			List<String> fieldList, List<Map<String, Object>> dataList)
			throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		HSSFSheet sheet = workbook.createSheet();
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow(0);
		// ===============================================================
		for (int i = 0; i < headList.size(); i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList.get(i));
		}
		// ===============================================================

		for (int n = 0; n < dataList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 1);
			Map<String, Object> dataMap = dataList.get(n);
			// ===============================================================
			for (int i = 0; i < fieldList.size(); i++) {

				// 在索引0的位置创建单元格（左上端）
				HSSFCell cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(objToString(dataMap.get(fieldList.get(i))));
			}
			// ===============================================================
		}
		return workbook;
	}

	private static String objToString(Object obj) {
		if (obj == null) {
			return "";
		} else {
			if (obj instanceof String) {
				return (String) obj;
			} else if (obj instanceof Date) {
				return null;// DateUtil.dateToString((Date)
							// obj,DateUtil.DATESTYLE_SHORT_EX);
			} else {
				return obj.toString();
			}
		}
	}

	/**
	 * 
	 * @param excel_name
	 *            生成的Excel文件路径+名称
	 * @param headList
	 *            Excel文件Head标题部分
	 * @param valueList
	 *            Excel文件数据内容部分
	 * @throws Exception
	 */
	public static void bulidExcel(String excel_name, String[] headList,
			List<String[]> valueList) throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		HSSFSheet sheet = workbook.createSheet();
		// 在索引0的位置创建行（最顶端的行）
		HSSFRow row = sheet.createRow(0);
		// ===============================================================
		for (int i = 0; i < headList.length; i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList[i]);
		}
		// ===============================================================

		for (int n = 0; n < valueList.size(); n++) {
			// 在索引1的位置创建行（最顶端的行）
			HSSFRow row_value = sheet.createRow(n + 1);
			String[] valueArray = valueList.get(n);
			// ===============================================================
			for (int i = 0; i < valueArray.length; i++) {

				// 在索引0的位置创建单元格（左上端）
				HSSFCell cell = row_value.createCell(i);
				// 定义单元格为字符串类型
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				// 在单元格中输入一些内容
				cell.setCellValue(valueArray[i]);
			}
			// ===============================================================
		}

		// 新建一输出文件流
		FileOutputStream fOut = new FileOutputStream(excel_name);
		// 把相应的Excel 工作簿存盘
		workbook.write(fOut);
		fOut.flush();
		// 操作结束，关闭文件
		fOut.close();
		//System.out.println("[" + excel_name + "]" + "文件生成...");
	}

	/**
	 * 读取 Excel文件内容
	 * 
	 * @param excel_name
	 * @return
	 * @throws Exception
	 */
	public static List<String[]> readExcel(String excel_name) throws Exception {
		// 结果集
		List<String[]> list = new ArrayList<String[]>();

		HSSFWorkbook hssfworkbook = new HSSFWorkbook(new FileInputStream(
				excel_name));

		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);

		// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
		for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
			HSSFRow hssfrow = hssfsheet.getRow(j);
			if(hssfrow!=null){
			int col = hssfrow.getPhysicalNumberOfCells();
			// 单行数据
			String[] arrayString = new String[col];
			for (int i = 0; i < col; i++) {
				HSSFCell cell = hssfrow.getCell(i);
				if (cell == null) {
					arrayString[i] = "";
				} else if (cell.getCellType() == 0) {
					// arrayString[i] = new Double(cell.getNumericCellValue()).toString();
					if (HSSFCell.CELL_TYPE_NUMERIC == cell.getCellType()) { 
						  if (HSSFDateUtil.isCellDateFormatted(cell)) {    
						    Date d = cell.getDateCellValue();    
//						    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");    
						     DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						    arrayString[i] = formater.format(d);   
						   } else {    
						       arrayString[i] = new BigDecimal(cell.getNumericCellValue()).longValue()+"";    
						}
					}
				} else {// 如果EXCEL表格中的数据类型为字符串型
					arrayString[i] = cell.getStringCellValue().trim();
				}
			}
			list.add(arrayString);
		}
		}
		return list;
	}

	/**
	 * 读取 Excel文件内容
	 * 
	 * @param excel_name
	 * @return
	 * @throws Exception
	 */
	public static List<List<Object>> readExcelByList(String excel_name)
			throws Exception {
		// 结果集
		List<List<Object>> list = new ArrayList<List<Object>>();

		HSSFWorkbook hssfworkbook = new HSSFWorkbook(new FileInputStream(
				excel_name));

		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
		HSSFSheet hssfsheet = hssfworkbook.getSheetAt(0);

		// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数
		for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
			HSSFRow hssfrow = hssfsheet.getRow(j);
			if (hssfrow != null) {
				int col = hssfrow.getPhysicalNumberOfCells();
				// 单行数据
				List<Object> arrayString = new ArrayList<Object>();
				for (int i = 0; i < col; i++) {
					HSSFCell cell = hssfrow.getCell(i);
					if (cell == null) {
						arrayString.add("");
					} else if (cell.getCellType() == 0) {
						arrayString.add(new Double(cell.getNumericCellValue())
								.toString());
					} else {// 如果EXCEL表格中的数据类型为字符串型
						arrayString.add(cell.getStringCellValue().trim());
					}
				}
				list.add(arrayString);
			}
		}
		return list;
	}

	/**
	 * 读取 Excel文件内容
	 * 
	 * @param excel_name
	 * @return
	 * @throws Exception
	 */
	public static List<List<Object>> readExcelByInputStream(
			InputStream inputstream) throws Exception {
		// 结果集
		List<List<Object>> list = new ArrayList<List<Object>>();

		HSSFWorkbook hssfworkbook = new HSSFWorkbook(inputstream);

		// 遍历该表格中所有的工作表，i表示工作表的数量 getNumberOfSheets表示工作表的总数
		Sheet hssfsheet = hssfworkbook.getSheetAt(0);

		// 遍历该行所有的行,j表示行数 getPhysicalNumberOfRows行的总数

		// //System.out.println("excel行数： "+hssfsheet.getPhysicalNumberOfRows());
		for (int j = 0; j < hssfsheet.getPhysicalNumberOfRows(); j++) {
			Row hssfrow = hssfsheet.getRow(j);
			if (hssfrow != null) {
				int col = hssfrow.getPhysicalNumberOfCells();
				// 单行数据
				List<Object> arrayString = new ArrayList<Object>();
				for (int i = 0; i < col; i++) {
					Cell cell = hssfrow.getCell(i);
					if (cell == null) {
						arrayString.add("");
					} else if (cell.getCellType() == 0) {
						arrayString.add(new Double(cell.getNumericCellValue())
								.toString());
					} else {// 如果EXCEL表格中的数据类型为字符串型
						arrayString.add(cell.getStringCellValue().trim());
					}
				}
				list.add(arrayString);
			}
		}
		return list;
	}

	/**
	 * 导入 excel
	 * 
	 * @param file : Excel文件
	 * @param pojoClass : 对应的导入对象 (每行记录)
	 * @return
	 */
	public static Collection importExcel(File file, Class pojoClass) {
		try {
			// 将传入的File构造为FileInputStream;
			FileInputStream in = new FileInputStream(file);
			return importExcelByIs(in, pojoClass);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 导入 excel
	 * 
	 * @param inputstream : 文件输入流
	 * @param pojoClass : 对应的导入对象 (每行记录)
	 * @return
	 */
	public static Collection importExcelByIs(InputStream inputstream, Class pojoClass) {
		Collection dist = new ArrayList<Object>();
		try {
			// 得到目标目标类的所有的字段列表
			Field filed[] = pojoClass.getDeclaredFields();
			// 将所有标有Annotation的字段，也就是允许导入数据的字段,放入到一个map中
			Map<String, Method> fieldSetMap = new HashMap<String, Method>();
			Map<String, Method> fieldSetConvertMap = new HashMap<String, Method>();
			// 循环读取所有字段
			for (int i = 0; i < filed.length; i++) {
				Field f = filed[i];
				// 得到单个字段上的Annotation
				Excel excel = f.getAnnotation(Excel.class);
				// 如果标识了Annotationd的话
				if (excel != null) {
					// 构造设置了Annotation的字段的Setter方法
					String fieldname = f.getName();
					String setMethodName = "set" + fieldname.substring(0, 1).toUpperCase() + fieldname.substring(1);
					// 构造调用的method，
					Method setMethod = pojoClass.getMethod(setMethodName, new Class[] { f.getType() });
					// 将这个method以Annotaion的名字为key来存入。
					// 对于重名将导致 覆盖 失败，对于此处的限制需要
					fieldSetMap.put(excel.exportName(), setMethod);
					if (excel.importConvertSign() == 1) {
						//----------------------------------------------------------------
						//update-begin--Author:Quainty  Date:20130524 for：[8]excel导出时间问题
						// 用get/setXxxxConvert方法名的话， 由于直接使用了数据库绑定的Entity对象，注入会有冲突
						StringBuffer setConvertMethodName = new StringBuffer("convertSet");
						setConvertMethodName.append(fieldname.substring(0, 1).toUpperCase());
						setConvertMethodName.append(fieldname.substring(1));
						//update-end--Author:Quainty  Date:20130524 for：[8]excel导出时间问题
						//----------------------------------------------------------------
						Method getConvertMethod = pojoClass.getMethod(setConvertMethodName.toString(), new Class[] { String.class });
						fieldSetConvertMap.put(excel.exportName(), getConvertMethod);
					}
				}
			}
			// 将传入的File构造为FileInputStream;
			// // 得到工作表
			Workbook book = WorkbookFactory.create(inputstream);
			// // 得到第一页
			Sheet sheet = book.getSheetAt(0);
			// // 得到第一面的所有行
			Iterator<Row> row = sheet.rowIterator();
			// 得到第一行，也就是标题行
			Row title = row.next();
			// 得到第一行的所有列
			Iterator<Cell> cellTitle = title.cellIterator();
			// 将标题的文字内容放入到一个map中。
			Map titlemap = new HashMap();
			// 从标题第一列开始
			int i = 0;
			// 循环标题所有的列
			while (cellTitle.hasNext()) {
				Cell cell = cellTitle.next();
				String value = cell.getStringCellValue();
				titlemap.put(i, value);
				i = i + 1;
			}
			// 用来格式化日期的DateFormat
			// SimpleDateFormat sf;
			while (row.hasNext()) {
				// 标题下的第一行
				Row rown = row.next();
				// 行的所有列
				Iterator<Cell> cellbody = rown.cellIterator();
				// 得到传入类的实例
				Object tObject = pojoClass.newInstance();
				int k = 0;
				// 遍历一行的列
				while (cellbody.hasNext()) {
					Cell cell = cellbody.next();
					// 这里得到此列的对应的标题
					String titleString = (String) titlemap.get(k);
					// 如果这一列的标题和类中的某一列的Annotation相同，那么则调用此类的的set方法，进行设值
					if (fieldSetMap.containsKey(titleString)) {
						Method setMethod = (Method) fieldSetMap.get(titleString);
						// 得到setter方法的参数
						Type[] ts = setMethod.getGenericParameterTypes();
						// 只要一个参数
						String xclass = ts[0].toString();
						// 判断参数类型
						if (Cell.CELL_TYPE_STRING == cell.getCellType()
								&& fieldSetConvertMap.containsKey(titleString)) {
							// 目前只支持从String转换
							fieldSetConvertMap.get(titleString).invoke(tObject, cell.getStringCellValue());
						} else {
							if (xclass.equals("class java.lang.String")) {
								// 先设置Cell的类型，然后就可以把纯数字作为String类型读进来了：
								cell.setCellType(Cell.CELL_TYPE_STRING);
								setMethod.invoke(tObject, cell.getStringCellValue());
							} else if (xclass.equals("class java.util.Date")) {
								// update-start--Author:Quainty Date:20130523 for：日期类型数据导入不对(顺便扩大支持了Excel的数据类型)
								Date cellDate = null;
								if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
									// 日期格式
									cellDate = cell.getDateCellValue();
								} else {	// 全认为是  Cell.CELL_TYPE_STRING: 如果不是 yyyy-mm-dd hh:mm:ss 的格式就不对(wait to do:有局限性)
									cellDate = stringToDate(cell.getStringCellValue());
								}
			                    setMethod.invoke(tObject,cellDate);
								//// update-start--Author:lihuan Date:20130423 for：导入bug修复直接将导出的Excel导入出现的bug的修复
								//// --------------------------------------------------------------------------------------------
								//String cellValue = cell.getStringCellValue();
								//Date theDate = stringToDate(cellValue);
								//setMethod.invoke(tObject, theDate);
								//// update-end--Author:lihuan Date:20130423 for：导入bug修复直接将导出的Excel导入出现的bug的修复
								//// --------------------------------------------------------------------------------------------
							} else if (xclass.equals("class java.lang.Boolean")) {
								boolean valBool;
								if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {
									valBool = cell.getBooleanCellValue();
								} else {// 全认为是  Cell.CELL_TYPE_STRING
									valBool = cell.getStringCellValue().equalsIgnoreCase("true") 
											|| (!cell.getStringCellValue().equals("0"));
								}
								setMethod.invoke(tObject, valBool);
							} else if (xclass.equals("class java.lang.Integer")) {
								Integer valInt;
								if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
									valInt = (new Double(cell.getNumericCellValue())).intValue();
								} else {// 全认为是  Cell.CELL_TYPE_STRING
									valInt = new Integer(cell.getStringCellValue());
								}
								setMethod.invoke(tObject, valInt);
							} else if (xclass.equals("class java.lang.Long")) {
								Long valLong;
								if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
									valLong = (new Double(cell.getNumericCellValue())).longValue();
								} else {// 全认为是  Cell.CELL_TYPE_STRING
									valLong = new Long(cell.getStringCellValue());
								}
								setMethod.invoke(tObject, valLong);
							} else if (xclass.equals("class java.math.BigDecimal")) {
								BigDecimal valDecimal;
								if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
									valDecimal = new BigDecimal(cell.getNumericCellValue());
								} else {// 全认为是  Cell.CELL_TYPE_STRING
									valDecimal = new BigDecimal(cell.getStringCellValue());
								}
								setMethod.invoke(tObject, valDecimal);
								//// ----------------------------------------------------------------
								//// update-begin--Author:sky Date:20130422 for：取值类型调整cell.getNumberCellValue-->>getStringCellValue
								//setMethod.invoke(tObject, new BigDecimal(cell.getStringCellValue()));
								//// update-end--Author:sky Date:20130422 for：取值类型调整
								//// ----------------------------------------------------------------
								// update-end--Author:Quainty Date:20130523 for：日期类型数据导入不对(顺便扩大支持了Excel的数据类型)
							}
						}
					}
					// 下一列
					k = k + 1;
				}
				dist.add(tObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return dist;
	}

	// update-begin--Author:Quainty Date:20130523 for：日期类型数据导入不对(顺便扩大支持了Excel的数据类型)
	// --------------------------------------------------------------------------------------------
	// update-begin--Author:lihuan Date:20130423 for：直接将导出的Excel导入出现的bug的修复
	/**
	 * 字符串转换为Date类型数据（限定格式      YYYY-MM-DD hh:mm:ss）或（YYYY/MM/DD hh:mm:ss）
	 * 
	 * @param cellValue : 字符串类型的日期数据
	 * @return
	 */
	private static Date stringToDate(String cellValue) {
		if (cellValue.length() > 19)
			cellValue = cellValue.substring(0, 19);
		Calendar calendar = Calendar.getInstance();
		String[] dateStr = cellValue.split(" ");
		String[] dateInfo = dateStr[0].split("-");
		if (dateInfo.length != 3) {
			dateInfo = dateStr[0].split("/");	// 让  yyyy/mm/dd 的格式也支持
		}
		if (dateInfo.length == 3) {
			int year = Integer.parseInt(dateInfo[0]);
			int month = Integer.parseInt(dateInfo[1])-1;	// 0~11
			int day = Integer.parseInt(dateInfo[2]);
			calendar.set(year, month, day);
		} else {
			return null;	// 格式不正确
		}
		if (dateStr.length > 1) {// 有时间（限定格式     hh:mm:ss）
			String[] timeStr = dateStr[1].split(":");
			if (timeStr.length == 3) {
				int hour = Integer.parseInt(timeStr[0]);
				int minute = Integer.parseInt(timeStr[1]);
				int second = Integer.parseInt(timeStr[2]);
				calendar.set(Calendar.HOUR_OF_DAY, hour);
				calendar.set(Calendar.MINUTE, minute);
				calendar.set(Calendar.SECOND, second);
			} else {
				return null;	// 格式不正确
			}
		}
		return calendar.getTime();
	}
/*	public static void hrTotalExcel(List<JCHr> list,List totallist,String fileName,HttpServletResponse response){
		// 创建新的Excel 工作簿
		Workbook wb = new HSSFWorkbook();
		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		Sheet sheet = wb.createSheet();
	
		
		Row row=null;
		String aa=null;
		if(ObjectUtils.isNotEmpty(fileName)){
			aa=fileName.substring(0, fileName.indexOf("."));
		}
		// 创标题行
		createNormalHead(aa, 10, wb, sheet);
		//第一行
		Row row1=sheet.createRow(1);
		//设置第一行的样式
		row1.setHeight((short) 370);
		for(int i=1;i<=11;i++){
			ExcelUtil.setColumnWidth(3000, i, sheet);
		}
		
		//row1.setRowStyle(arg0)
		//表头
		createBoldCells(row1, 0, (short)200, "所属区域 ","人才总数  ","博士人数 "," 硕士研究生  ","本科人数  ","大专人数 "," 培训场次 "," 培训人次 "," 新产品  ","经费投入 "," 专利个数");
		// 把相应的Excel 工作簿存盘
		if(totallist.size()>0){
			row=sheet.createRow(sheet.getLastRowNum()+1);
			row.setHeight((short)400);
			JCHr hr=(JCHr)totallist.get(0);
			createCells(row, 0, "金水区",hr.getTotalRecord(),hr.getDoctorNumber(),hr.getMasterNumber(),hr.getUndergraduateNumber(),hr.getJuniorNumber(),hr.getTrainTimes(),hr.getTrainNumber(),hr.getNewProductNumber(),hr.getMoney(),hr.getPatentNumber()); 		   
		      
		}
		if(list.size()>0){
			for (JCHr hr : list){
				row=sheet.createRow(sheet.getLastRowNum()+1);
				row.setHeight((short)400);
				createCells(row, 0, hr.getOrgName(),hr.getTotalRecord(),hr.getDoctorNumber(),hr.getMasterNumber(),hr.getUndergraduateNumber(),hr.getJuniorNumber(),hr.getTrainTimes(),hr.getTrainNumber(),hr.getNewProductNumber(),hr.getMoney(),hr.getPatentNumber()); 		   
			      
			}
		      
		}
		sheet.setColumnWidth(0, 5000);
		excelOut(wb,fileName,response);
	}*/

	// update-end--Author:lihuan Date:20130423 for：导入bug修复直接将导出的Excel导入出现的bug的修复
	// --------------------------------------------------------------------------------------------
	// update-end--Author:Quainty Date:20130523 for：日期类型数据导入不对(顺便扩大支持了Excel的数据类型)
	
	public static void taizhangExcel(Report allreport,String fileName,HttpServletResponse response){
		// 创建新的Excel 工作簿
		Workbook wb = new HSSFWorkbook();
		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		Sheet sheet = wb.createSheet();
		Row row=null;
		
		// 创标题行
		createNormalHead("月度考勤表", 20, wb, sheet);
		
		//表头
		taizhangHeads(allreport,wb,sheet);
		
		//表体
		//将顶层数据添加一行（金水区）
		if(allreport.getReportIndexs()!=null&&allreport.getReportIndexs().size()>0){
			row=sheet.createRow(sheet.getLastRowNum()+1);
			row.setHeight((short)700);
			taizhangRow(allreport,row,(short) 210);
		}
		if(allreport.getReports()!=null){
			for(int i=0;i<allreport.getReports().size();i++){
				//将二层数据添加一行(办事处)
				Report report=allreport.getReports().get(i);
				if(report.getReportIndexs()!=null&&report.getReportIndexs().size()>0){
					row=sheet.createRow(sheet.getLastRowNum()+1);
					row.setHeight((short)500);
					taizhangRow(report,row,(short) 200);
				}
				if(report.getReports()!=null){
					//将三层数据添加一行(企业)
					for(int j=0;j<report.getReports().size();j++){
						Report creport=report.getReports().get(j);
						if(report.getReportIndexs()!=null&&report.getReportIndexs().size()>0){
							row=sheet.createRow(sheet.getLastRowNum()+1);
							row.setHeight((short)300);
							taizhangRow(creport,row);
						}
					}
				}
			}

		}
		
		// 把相应的Excel 工作簿存盘
		sheet.setColumnWidth(0, 5000);
		excelOut(wb,fileName,response);
	}
	/**
	 * 创建表头
	 * @param allreport
	 * @param wb
	 * @param sheet
	 */
	public static void taizhangHeads(Report allreport,Workbook wb,Sheet sheet){
		//第一行
		Row row1=sheet.createRow(1);
		//设置第一行的样式
		row1.setHeight((short) 370);
		
		//第二行
		Row row2=sheet.createRow(2);
		row2.setHeight((short) 370);
		
		//创建单元格
		sheet.addMergedRegion(new CellRangeAddress(1,2,0,0));
		createBoldCell("单位名称",row1,0,(short) 250);
		//构建两行表头的具体数据
		List<ReportIndex> iList=null;
			iList=allreport.getReportIndexs();
			if(iList==null){
				//如果上级没有指标，往下找
				iList=allreport.getReports().get(0).getReportIndexs();
			}
			for (int i = 1,detailCols=0; i < iList.size()+1; i++) {
				String content=iList.get(i-1).getIndexName();
				switch(iList.get(i-1).getIndexType()){
				case 1: 
				case 2: 
				case 3: 
				case 5: 
				case 6: 
				case 7: 
				case 8: 
				case 9: 
				case 10: 	
					createBoldCell(content,row1,detailCols+1);
					sheet.addMergedRegion(new CellRangeAddress(1,1,detailCols+1,detailCols+6));
					//创建第二行单元格(6个)
					String[] indexStr6={"本月","本月累计","去年本月","去年本月累计","本月增速","累计增速"};
					for(int j=0;j<6;j++){
						createBoldCell(indexStr6[j],row2,detailCols+1);
						detailCols++;
					}
					break;
				case 11: 
				case 12: 
				case 13: 
				case 14: 
				case 15: 
				case 16: 
				case 17:  
					createBoldCell(content,row1,detailCols+1);
					sheet.addMergedRegion(new CellRangeAddress(1,1,detailCols+1,detailCols+3));
					//创建第二行单元格(3个)
					String[] indexStr3={"本月累计","去年本月累计","累计增速"};
					for(int j=0;j<3;j++){
						createBoldCell(indexStr3[j],row2,detailCols+1);
						detailCols++;
					}
					break;
				case 4: 
					createBoldCell(content,row1,detailCols+1);
					sheet.addMergedRegion(new CellRangeAddress(1,1,detailCols+1,detailCols+4));
					//创建第二行单元格(4个)
					String[] indexStr4={"本月","本月累计","去年本月","去年本月累计"};
					for(int j=0;j<4;j++){
						createBoldCell(indexStr4[j],row2,detailCols+1);
						detailCols++;
					}
					break;
				}
			}
	}
	/**
	 * 创建单行（第一列需要特殊标示,加粗，放大）
	 * @param report
	 * @param row
	 * @param size 字体大小
	 */
	public static void taizhangRow(Report report,Row row,short size){
		taizhangRow(report,row);
		//获得第一列的数据，加粗显示
		Cell cell=row.getCell(0);
		Font font=row.getSheet().getWorkbook().createFont();
		CellStyle s=row.getSheet().getWorkbook().createCellStyle();
		s.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cell.setCellStyle(s);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontHeight(size);
		cell.getCellStyle().setFont(font);
	}
	/**
	 * 创建单行
	 * @param report 传入数据
	 * @param row
	 */
	public static void taizhangRow(Report report,Row row){
		Cell cell=null;
		if(report!=null&&report.getName()!=null){
			if(report.getReportIndexs()!=null){
				//第一列是名称
				cell=row.createCell(0);
				cell.setCellValue(report.getName());
				for(int i=0,dataCols=1;i<report.getReportIndexs().size();i++){
					ReportIndex ri=report.getReportIndexs().get(i);
					if(ri!=null&&ri.getIndexType()!=null){
						switch(ri.getIndexType()){
						case 1: 
						case 2: 
						case 3: 
						case 5: 
						case 6: 
						case 7: 
						case 8: 
						case 9: 
						case 10: 
							createCells(row,dataCols,ri.getTmTotale(),ri.getTmAddTotale(),ri.getLytmTotale(),ri.getLyTotale(),ri.getTmEnhance(),ri.getAddEnhance());
							dataCols=dataCols+6;
							break;
						case 11: 
						case 12: 
						case 13: 
						case 14: 
						case 15: 
						case 16: 
						case 17:  
							createCells(row,dataCols,ri.getTmTotale(),ri.getTmAddTotale(),ri.getLyTotale(),ri.getAddEnhance());
							dataCols=dataCols+3;
							break;
						case 4: 
							createCells(row,dataCols,ri.getTmTotale(),ri.getTmAddTotale(),ri.getLytmTotale(),ri.getLyTotale());
							dataCols=dataCols+4;
							break;
						}
					}
				}
			}
		}
	}
	/**
	 * 批量建立简单单元格
	 * @param row
	 * @param i
	 * @param objects
	 */
	public static void createCells(Row row,int i,Object...objects)
	{
		for (Object object : objects) {
			if(null==object){
				object="";
			}
			createCell(object.toString(),row,i);
			i++;
		}
	}
	/**
	 * 批量建立粗体单元格
	 * @param row
	 * @param i
	 * @param objects
	 */
	public static void createBoldCells(Row row,int i,short size,Object...objects)
	{
		for (Object object : objects) {
			if(null==object){
				object="";
			}
			createBoldCell(object.toString(),row,i,size);
			i++;
		}
	}
	
	/**
	 * 创建默认单元格，上下左右对齐
	 * value 单元格内容
	 * row 行
	 * size 创建单元格的位置，如果是第一列，i=0
	 */
	public static Cell createCell(String value,Row row,int i){
		Cell cell=row.createCell(i);
		CellStyle s=row.getSheet().getWorkbook().createCellStyle();
		s.setAlignment(CellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		s.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cell.setCellStyle(s);
		cell.setCellValue(value);
		return cell;
	}
	/**
	 * 创建单元格，上下左右对齐，字体加粗
	 * value 单元格内容
	 * row 行
	 * size 创建单元格的位置，如果是第一列，i=0
	 */
	public static Cell createBoldCell(String value,Row row,int i){
		Cell cell=createCell(value,row,i);
		Font font=row.getSheet().getWorkbook().createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		cell.getCellStyle().setFont(font);
		return cell;
	}
	/**
	 * 创建单元格，上下左右对齐，字体加粗，大小自定义
	 * value 单元格内容
	 * row 行
	 * i 创建单元格的位置，如果是第一列，i=0
	 * size 字体大小
	 */
	public static Cell createBoldCell(String value,Row row,int i,short size){
		Cell cell=createCell(value,row,i);
		Font font=row.getSheet().getWorkbook().createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontHeight(size);
		cell.getCellStyle().setFont(font);
		return cell;
	}
	/**
	 * 统一调整列宽
	 * @param size
	 * @param cloumnNums
	 * @param sheet
	 */
	public static void setColumnWidth(int size,int cloumnNums,Sheet sheet){
		for(int i=0;i<cloumnNums;i++){
			sheet.setColumnWidth(i,size);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void createExcel2(String excel_name, String[] headList,
			String[] fieldList, List<Map<String, Object>> dataList,String title,String date,HttpServletResponse response)
			throws Exception {
		// 创建新的Excel 工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
		cellStyle.setWrapText(true);// 指定单元格自动换行
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		HSSFFont font = workbook.createFont();
		font.setFontName("仿宋_GB2312");
		cellStyle.setFont(font);
		
		
		HSSFSheet sheet = workbook.createSheet();
		sheet.setColumnWidth(0, 1800); 
		sheet.setColumnWidth(1, 3000); 
		sheet.setColumnWidth(2, 3000); 
		sheet.setColumnWidth(3, 3000); 
		sheet.setColumnWidth(4, 3000); 
		sheet.setColumnWidth(5, 3000); 
		sheet.setColumnWidth(6, 3000); 
		sheet.setColumnWidth(7, 3000); 
		sheet.setColumnWidth(8, 3000); 
		sheet.setColumnWidth(9, 3000); 
		sheet.setColumnWidth(10, 3000); 
		sheet.setColumnWidth(11, 3000); 
		sheet.setColumnWidth(12, 3000); 
		sheet.setColumnWidth(13, 3000); 
		sheet.setColumnWidth(14, 3000); 
		sheet.setColumnWidth(15, 3000); 
		sheet.setColumnWidth(16, 3000); 
		sheet.setColumnWidth(17, 3000); 
		createNormalHead(title,17,workbook,sheet);		
		
		HSSFRow row = sheet.createRow(2);
		row.setHeight((short)300);
		// ===============================================================
		for (int i = 0; i < headList.length; i++) {

			// 在索引0的位置创建单元格（左上端）
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(cellStyle);
			// 定义单元格为字符串类型
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			// 在单元格中输入一些内容
			cell.setCellValue(headList[i]);
		}
		// ===============================================================
		if(dataList!=null&&dataList.size()>0){
			for (int n = 0; n < dataList.size(); n++) {
				// 在索引1的位置创建行（最顶端的行）
				HSSFRow row_value = sheet.createRow(n + 3);
				row_value.setHeight((short)300);
				Map<String, Object> dataMap = dataList.get(n);
				// ===============================================================
				for (int i = 0; i < fieldList.length; i++) {
					
					// 在索引0的位置创建单元格（左上端）
					HSSFCell cell = row_value.createCell(i);
					cell.setCellStyle(cellStyle);
					// 定义单元格为字符串类型
					cell.setCellType(HSSFCell.CELL_TYPE_STRING);
					// 在单元格中输入一些内容
					cell.setCellValue(objToString(dataMap.get(fieldList[i])));
				}
				// ===============================================================
			}
		}
		  response.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(excel_name, "UTF-8")); 
          response.setContentType("application/msexcel;charset=UTF-8"); 
		// 新建一输出文件流
		OutputStream out = response.getOutputStream(); 
		// 把相应的Excel 工作簿存盘
		workbook.write(out);
		out.flush();
		// 操作结束，关闭文件
		out.close();
		response.flushBuffer();  
		
		//System.out.println("[" + excel_name + "]" + "文件生成...");
	}
}
