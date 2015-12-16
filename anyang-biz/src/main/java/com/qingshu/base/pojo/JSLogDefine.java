package com.qingshu.base.pojo;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 操作日志信息定义
 */
public class JSLogDefine extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String tableDesc;
	private String pojoName;
	private String findPojoName;
	private String findMethod;
	private String methodName;
	private String logDesc;

	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	public String getPojoName() {
		return pojoName;
	}

	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getLogDesc() {
		return logDesc;
	}

	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	public String getFindPojoName() {
		return findPojoName;
	}

	public void setFindPojoName(String findPojoName) {
		this.findPojoName = findPojoName;
	}

	public String getFindMethod() {
		return findMethod;
	}

	public void setFindMethod(String findMethod) {
		this.findMethod = findMethod;
	}
}
