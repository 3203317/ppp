package com.qingshu.common.plugin.log;

import java.util.List;

import com.qingshu.base.pojo.JSTableField;

public class LogField {
	private String logField;
	private List<JSTableField> jsTableFields;
	public String getLogField() {
		return logField;
	}

	public void setLogField(String logField) {
		this.logField = logField;
	}

	public List<JSTableField> getJsTableFields() {
		return jsTableFields;
	}

	public void setJsTableFields(List<JSTableField> jsTableFields) {
		this.jsTableFields = jsTableFields;
	}
}
