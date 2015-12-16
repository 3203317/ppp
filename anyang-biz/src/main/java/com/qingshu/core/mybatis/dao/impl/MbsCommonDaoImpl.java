package com.qingshu.core.mybatis.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.qingshu.common.json.model.Upload;
import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.plugin.poi.PoiModel;
import com.qingshu.common.plugin.poi.PoiUtils;
import com.qingshu.common.plugin.upload.UploadFile;
import com.qingshu.common.util.ContextUtils;
import com.qingshu.common.util.FileUtils;
import com.qingshu.common.util.ObjectUtils;
import com.qingshu.common.util.PathUtils;
import com.qingshu.common.util.ReflectHelper;
import com.qingshu.common.util.ResourceUtil;
import com.qingshu.core.mybatis.dao.MbsCommonDao;

/**
 * mybaits基层DAO扩展类
 * 
 * @author zyf
 * @version 1.0
 */
@Repository
public class MbsCommonDaoImpl extends MbsGenericDaoImpl implements MbsCommonDao {

	/**
	 * 文件导入
	 */
	public PoiModel importFile(PoiModel poiModel) {
		PagerInfo pagerInfo = poiModel.getPagerInfo();
		InputStream in = null;
		try {
			if (ObjectUtils.isEmpty(poiModel.getMultipartRequest())) {
				in = FileUtils.getFileInputStream(poiModel.getFilePath());

			} else {
				uploadFile(poiModel);
				in = poiModel.getMultipartFile().getInputStream();
			}
			String[] cellField = poiModel.getCellField();
			Workbook workbook = WorkbookFactory.create(in);
			Sheet sheet = workbook.getSheetAt(0);
			Row firstRow = sheet.getRow(0);
			String[] names = new String[firstRow.getLastCellNum()];
			for (int k = 0; k < firstRow.getLastCellNum(); k++) {
				names[k] = firstRow.getCell(k).getStringCellValue();
			}
			List<Object> rowData = poiModel.getRowDates();
			Integer allCounts = sheet.getLastRowNum();/* 总记录数 */
			Integer offset = 1;
			Integer readCount = allCounts;

			if (pagerInfo != null) {
				Integer pageSize = pagerInfo.getPageSize();
				Integer curPageNO = pagerInfo.getCurPageNO();
				offset = PagerInfo.getOffset(allCounts, curPageNO, pageSize) == 0 ? 1 : PagerInfo.getOffset(allCounts, curPageNO, pageSize);
				readCount = pageSize * curPageNO >= allCounts ? allCounts : pageSize * curPageNO;
				PagerInfo pa = new PagerInfo(allCounts, pagerInfo.getCurPageNO(), pageSize);
				ContextUtils.getRequest().setAttribute(PagerInfo.PAGERSTR, pa.getToolBar2());
			}
			// 注释原因：少读了一行数据
//			for (int i = offset; i <= readCount - 1; i++) {  
//				if (i < allCounts) {
			for (int i = offset; i <= readCount; i++) {
				if (i <= allCounts) {
					ReflectHelper reflectHelper = new ReflectHelper(ObjectUtils.getObject(poiModel.getCls()));
					Row row = sheet.getRow(i); // 获取行(row)对象
					for (int j = 0; j < cellField.length; j++) {
						Object cellStr = "";
						Cell cell = row.getCell(j); // 获得单元格(cell)对象
						// 转换接收的单元格
						cellStr = PoiUtils.ConvertCellStr(cell, cellStr);
						reflectHelper.setMethodValue(cellField[j], cellStr);
					}

					rowData.add(reflectHelper.obj);
				}
			}

		} catch (IOException e) {

		} catch (InvalidFormatException e) {

		}
		return poiModel;
	}

	/**
	 * 文件上传
	 */
	public T uploadFile(UploadFile uploadFile) {
		String uploadPath = uploadFile.getUploadPath();// 文件上传根目录
		if (ObjectUtils.isEmpty(uploadPath)) {
			uploadPath = ResourceUtil.getConfigByName("uploadPath");
		}
		String realPath = PathUtils.getRealPath(uploadPath);// 文件的服务器存放路径
		String file = realPath + uploadFile.getFileName();
		try {
			FileCopyUtils.copy(uploadFile.getMultipartFile().getBytes(), FileUtils.getFile(file));
		} catch (IOException e) {
		}
		return null;
	}

	/**
	 * 文件上传
	 */
	public Upload upload(UploadFile uploadFile) {
		Upload upload = new Upload();
		String uploadPath = uploadFile.getUploadPath();// 文件上传根目录
		if (ObjectUtils.isEmpty(uploadPath)) {
			uploadPath = ResourceUtil.getConfigByName("uploadPath");
		}
		if (!ObjectUtils.isEmpty(uploadFile.getCusPath())) {
			String cusPath = "/" + uploadFile.getCusPath();
			uploadPath += cusPath;
		}
		String realPath = PathUtils.getRealPath(uploadPath);// 文件的服务器存放路径
		String fileName = "";
		Map<String, MultipartFile> fileMap = uploadFile.getMultipartRequest().getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile mf = entity.getValue();// 获取上传文件对象
			fileName = mf.getOriginalFilename();// 获取文件名
			String extend = FileUtils.getExtend(fileName);// 获取文件扩展名
			//图片中文文件名转换"*.png;*.jpg;*.gif;*.jpeg",
			if(extend.equals("png")||extend.equals("jpg")||extend.equals("gif")||extend.equals("jpeg")) {
				fileName = Long.toString(System.currentTimeMillis())+fileName.substring(fileName.length()-4, fileName.length());
			}
			
			if (!ObjectUtils.isEmpty(uploadFile.getCusFileName())) {
				fileName = uploadFile.getCusFileName() + "." + extend;
			}
			String file = realPath + fileName;
			try {
				FileCopyUtils.copy(mf.getBytes(), FileUtils.getFile(file));
				upload.setFileName(fileName);
				upload.setSuccess(true);
				upload.setFilePath(file);
			} catch (IOException e) {
			}
		}

		return upload;
	}

	/**
	 * 文件下载或预览
	 * 
	 * @param request
	 * @throws Exception
	 * @throws Exception
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile) {
		uploadFile.getResponse().setContentType("UTF-8");
		uploadFile.getResponse().setCharacterEncoding("UTF-8");
		InputStream bis = null;
		BufferedOutputStream bos = null;
		HttpServletResponse response = uploadFile.getResponse();
		HttpServletRequest request = uploadFile.getRequest();
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		String downLoadPath = "";
		long fileLength = 0;
		if (uploadFile.getContent() == null) {
			downLoadPath = ctxPath + uploadFile.getRealPath();
			fileLength = new File(downLoadPath).length();
			try {
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			if (uploadFile.getContent() != null)
			{
				bis = new ByteArrayInputStream(uploadFile.getContent());
			}
			else {
				
			}
			fileLength = uploadFile.getContent().length;
		}
		if(bis!=null)
		{
		try {
			if (!uploadFile.isView() && uploadFile.getExtend() != null) {
				if (uploadFile.getExtend().equals("text")) {
					response.setContentType("text/plain;");
				} else if (uploadFile.getExtend().equals("doc")) {
					response.setContentType("application/msword;");
				} else if (uploadFile.getExtend().equals("xls")) {
					response.setContentType("application/ms-excel;");
				} else if (uploadFile.getExtend().equals("pdf")) {
					response.setContentType("application/pdf;");
				} else if (uploadFile.getExtend().equals("jpg") || uploadFile.getExtend().equals("jpeg")) {
					response.setContentType("image/jpeg;");
				} else {
					response.setContentType("application/x-msdownload;");
				}
				response.setHeader("Content-disposition", "attachment; filename=" + new String((uploadFile.getTitleField() + "." + uploadFile.getExtend()).getBytes("GBK"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
			}
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
		return response;
	}
}
