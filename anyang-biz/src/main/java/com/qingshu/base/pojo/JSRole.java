package com.qingshu.base.pojo;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.qingshu.common.annotation.MyField;
import com.qingshu.common.annotation.Pojo;
import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 系统角色表
 */
@Entity
@Table(name = "j_s_role")
//@Pojo(name="j_s_role",desc="系统角色")
public class JSRole extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String roleName;   /*角色名称*/
	private Short roleLevel;   /*角色级别*/
	private String roleCode;   /*角色编码*/
	private JSRole rolePid;    /*父角色*/
	private Short isSuper;	   /*是否是超级管理员*/
	private Short allowDelete; /*是否允许删除*/

	private Set<JSUser> jsUsers;
	

	@Column(name = "roleName", length = 20)
	@MyField(name="roleName",islog=true,indesc=true)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "roleLevel")
	@MyField(name="roleLevel",islog=true)
	public Short getRoleLevel() {
		return this.roleLevel;
	}

	public void setRoleLevel(Short roleLevel) {
		this.roleLevel = roleLevel;
	}

	@Column(name = "roleCode", length = 10)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	@Column(name = "rolePid")
	public JSRole getRolePid() {
		return rolePid;
	}

	public void setRolePid(JSRole rolePid) {
		this.rolePid = rolePid;
	}
	@Column(name = "isSuper")
	public short getIsSuper() {
		return this.isSuper;
	}
	
	public void setIsSuper(Short isSuper) {
		this.isSuper = isSuper;
	}
	@Column(name = "allowDelete")
	public Short getAllowDelete() {
		return allowDelete;
	}

	public void setAllowDelete(Short allowDelete) {
		this.allowDelete = allowDelete;
	}
	@ManyToMany()
    @JoinTable(name="j_s_user_role", joinColumns = { 
    @JoinColumn(name="role_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
    @JoinColumn(name="user_id", nullable=false, updatable=false) })
	public Set<JSUser> getJsUsers() {
		return jsUsers;
	}

	public void setJsUsers(Set<JSUser> jsUsers) {
		this.jsUsers = jsUsers;
	}

}