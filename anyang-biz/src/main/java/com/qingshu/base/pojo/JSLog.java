package com.qingshu.base.pojo;

import java.sql.Timestamp;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 操作日志
 */
public class JSLog extends IdEntity implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private JSUser jsUser;
	private String userName;
	private Timestamp logDate;
	private String logIp;
	private String logDesc;
	private JSTable jsTable;
	private String entityName;
	private String entityPk;
	private String methodName;
	private String logNewComparyOld;
	public JSUser getJsUser() {
		return jsUser;
	}
	public void setJsUser(JSUser jsUser) {
		this.jsUser = jsUser;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getLogDate() {
		return logDate;
	}
	public void setLogDate(Timestamp logDate) {
		this.logDate = logDate;
	}
	public String getLogIp() {
		return logIp;
	}
	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}
	public String getLogDesc() {
		return logDesc;
	}
	public void setLogDesc(String logDesc) {
		this.logDesc = logDesc;
	}
	public String getLogNewComparyOld() {
		return logNewComparyOld;
	}
	public void setLogNewComparyOld(String logNewComparyOld) {
		this.logNewComparyOld = logNewComparyOld;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityPk() {
		return entityPk;
	}
	public void setEntityPk(String entityPk) {
		this.entityPk = entityPk;
	}
	public JSTable getJsTable() {
		return jsTable;
	}
	public void setJsTable(JSTable jsTable) {
		this.jsTable = jsTable;
	}
	
}
