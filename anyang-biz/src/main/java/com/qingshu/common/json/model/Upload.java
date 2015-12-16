package com.qingshu.common.json.model;


/**
 * AJAX请求时返回的JSON对象
 */
public class Upload {
	private boolean success = true;// 是否成功
	private String fileName;
	private String filePath;
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
