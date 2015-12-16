package com.qingshu.base.pojo;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 操作日志详细
 */
public class JSLogDetail extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private JSLog jsLog=new JSLog();
	private String fieldDesc;
	private String fieldName;
	private String newValue;
	private String oldValue;

	public JSLog getJsLog() {
		return jsLog;
	}

	public void setJsLog(JSLog jsLog) {
		this.jsLog = jsLog;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

}
