package com.qingshu.common.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.qingshu.base.vo.Question;

public final class Excelwenti {

	public static void xexcel(List<Question> list,String fileName,HttpServletResponse response){
		// 创建新的Excel 工作簿
		Workbook wb = new HSSFWorkbook();
		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		Sheet sheet = wb.createSheet();
		// 创标题行
		ExcelUtil.createNormalHead(fileName, 20, wb, sheet);
		
		//表头
		xcreateHead(wb,sheet);
		//表体
		//将顶层数据添加一行（金水区）
		xcreateBody(wb,sheet,list);
		
		//调整列的宽度
		sheet.setColumnWidth(0, 5000);
		// 把相应的Excel 工作簿存盘
		ExcelUtil.excelOut(wb,fileName+".xls",response);
	}
	public static void gexcel(List<Question> list,String fileName,HttpServletResponse response){
		// 创建新的Excel 工作簿
		Workbook wb = new HSSFWorkbook();
		// 在Excel工作簿中建一工作表，其名为缺省值
		// 如要新建一名为"效益指标"的工作表，其语句为：
		// HSSFSheet sheet = workbook.createSheet("效益指标");
		Sheet sheet = wb.createSheet();
		// 创标题行
		ExcelUtil.createNormalHead(fileName, 20, wb, sheet);
		
		//表头
		gcreateHead(wb,sheet);
		//表体
		//将顶层数据添加一行（金水区）
		gcreateBody(wb,sheet,list);
		
		//调整列的宽度
		sheet.setColumnWidth(0, 5000);
		// 把相应的Excel 工作簿存盘
		ExcelUtil.excelOut(wb,fileName+".xls",response);
	}
	
	public static void xcreateHead(Workbook wb,Sheet sheet){
		//创建第一行
		short headSize=250;
		Row row;
		row=sheet.createRow(1);
		ExcelUtil.createBoldCell("企业名称", row, 0, headSize);
		ExcelUtil.createBoldCell("问题", row, 1, headSize);
		ExcelUtil.createBoldCell("解决情况", row, 11, headSize);
		ExcelUtil.createBoldCell("备注", row, 14, headSize);
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 10));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 11, 13));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
		//创建第二行
		row=sheet.createRow(2);
		ExcelUtil.createCells(row, 2, "已解决","正在协调","不符合政策");
	}
	public static void xcreateBody(Workbook wb,Sheet sheet,List<Question> list){
		//从第三行开始
		int i=3;
		short bodySize=200;
		Row row;
		if(list!=null&&list.size()>0){
			for(Question q:list){
				if(q!=null){
					row=sheet.createRow(i);
					ExcelUtil.createBoldCell(q.getCompanyName(), row, 0,bodySize);
					ExcelUtil.createCells(row, 1, q.getQuestion(),q.getStatus1(),q.getStatus2(),q.getStatus3());
				}
				i++;
			}
		}
	}
	public static void gcreateHead(Workbook wb,Sheet sheet){
		//创建第一行
		short headSize=250;
		Row row;
		row=sheet.createRow(1);
		ExcelUtil.createBoldCell("企业名称", row, 0, headSize);
		ExcelUtil.createBoldCell("问题", row, 1, headSize);
		ExcelUtil.createBoldCell("问题分类", row, 2, headSize);
		ExcelUtil.createBoldCell("解决情况", row, 11, headSize);
		ExcelUtil.createBoldCell("备注", row, 14, headSize);
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 2, 10));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 11, 13));
		sheet.addMergedRegion(new CellRangeAddress(1, 2, 14, 14));
		//创建第二行
		row=sheet.createRow(2);
		ExcelUtil.createCells(row, 2,"融资","要素保障(煤电油气运)","土地","环评","项目审批","市场开拓","企业减负","周边环境","其他", "已解决","正在协调","不符合政策");
	}
	public static void gcreateBody(Workbook wb,Sheet sheet,List<Question> list){
		//从第三行开始
		int i=3;
		short bodySize=200;
		Row row;
		if(list!=null&&list.size()>0){
			for(Question q:list){
				if(q!=null){
					row=sheet.createRow(i);
					ExcelUtil.createBoldCell(q.getCompanyName(), row, 0,bodySize);
					ExcelUtil.createCells(row, 1, q.getQuestion(),q.getStatus1(),q.getStatus2(),q.getStatus3());
				}
				i++;
			}
		}
	}
	
}
