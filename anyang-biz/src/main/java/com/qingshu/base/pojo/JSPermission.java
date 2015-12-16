package com.qingshu.base.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 系统操作者(用户、角色、用户组等)拥有的权限
 */
@Entity
@Table(name = "j_s_permission")
public class JSPermission extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String ownerId;                  /*用户、角色、用户组ID*/
	private String permissionId;             /*不同类型的权限ID*/ 
	private String ownerType;                /*权限拥有者类型*/
	private String permissionType;           /*权限类型*/
	private List<JSOperation> operationList; /*操作集合*/

	@Column(name = "ownerId", length = 32)
	public String getOwnerId() {
		return this.ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	@Column(name = "permissionId", length = 32)
	public String getPermissionId() {
		return this.permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	@Column(name = "permissionType", length = 50)
	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	@Column(name = "ownerType", length = 50)
	public String getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(String ownerType) {
		this.ownerType = ownerType;
	}
	@ManyToMany()
    @JoinTable(name="j_s_permission_operation", joinColumns = { 
    @JoinColumn(name="permissionId", nullable=false, updatable=false) }, inverseJoinColumns = { 
    @JoinColumn(name="operationId", nullable=false, updatable=false) })
	public List<JSOperation> getOperationList() {
		return operationList;
	}

	public void setOperationList(List<JSOperation> operationList) {
		this.operationList = operationList;
	}

}