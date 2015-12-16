package com.qingshu.base.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 系统功能操作(按钮及其他操作)表
 */
@Entity
@Table(name = "j_s_operation")
public class JSOperation extends IdEntity  implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private String permissionId;        /*所属权限ID*/
	private String operationName;       /*功能操作名称*/
	private String operationCode;       /*功能操作编码*/
	private String operationPrefixurl;  /*拦截URL前缀*/
	private String operationPid;        /*操作父ID*/
	private String permissionType;      /*操作所属权限类型*/

	@Column(name = "operationName", length = 10)
	public String getOperationName() {
		return this.operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	@Column(name = "operationCode", length = 10)
	public String getOperationCode() {
		return this.operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	@Column(name = "operationPrefixurl", length = 20)
	public String getOperationPrefixurl() {
		return this.operationPrefixurl;
	}

	public void setOperationPrefixurl(String operationPrefixurl) {
		this.operationPrefixurl = operationPrefixurl;
	}

	@Column(name = "operationPid", length = 32)
	public String getOperationPid() {
		return this.operationPid;
	}

	public void setOperationPid(String operationPid) {
		this.operationPid = operationPid;
	}
	@Column(name = "permissionId", length = 32)
	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}
	@Column(name = "permissionType", length = 32)
	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

}