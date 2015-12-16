package com.qingshu.core.mybatis.dao;

import javax.servlet.http.HttpServletResponse;

import com.qingshu.common.json.model.Upload;
import com.qingshu.common.plugin.poi.PoiModel;
import com.qingshu.common.plugin.upload.UploadFile;

public interface MbsCommonDao extends MbsGenericDao {
	/**
	 * EXCEL导入
	 */
	public PoiModel importFile(PoiModel poiModel);
	/**
	 * 文件上传
	 */
	public Upload upload(UploadFile uploadFile);
	/**
	 * 文件下载或预览
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);

}
