package com.qingshu.base.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.qingshu.core.hibernate.config.IdEntity;

/**
* @description：TODO(系统用户分组(部门))   
* @author：flyme  
* @data：2013-8-5 上午09:18:04    
* @version：v 1.0
*/
@Entity
@Table(name = "j_s_group")
public class JSGroup extends IdEntity implements java.io.Serializable {


	private static final long serialVersionUID = 1L;
	private String groupName;//用户组名称
	private String groupPid;//用户组父类ID
	
	@Column(name = "groupName", length = 30)
	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Column(name = "groupPid", length = 32)
	public String getGroupPid() {
		return this.groupPid;
	}

	public void setGroupPid(String groupPid) {
		this.groupPid = groupPid;
	}
}