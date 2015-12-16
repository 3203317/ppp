package com.qingshu.base.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * @description：TODO(组织机构表))
 * @author：flyme
 * @data：2013-8-5 上午09:18:04
 * @version：v 1.0
 */
@Entity
@Table(name = "j_s_organization")
public class JSOrganization extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private JSArea jsArea;
	private String organizationCode; /* 机构代码 */
	private String organizationName; /* 机构名称 */
	private String organizationManager; /* 经营者 */
	private JSOrganization organizationPid; /* 父节点 */
	private Short organizationLevel; /*机构级别*/
	private String organizationSort ;/*排序号*/
	private String orgType ;/*机构类型 1、企业  2、门店*/
	
	private List<JSOrganization> childOrganizations=new ArrayList<JSOrganization>();
	/**
	 * 构造方法
	 */
	public JSOrganization(){}
    public JSOrganization(String id){
    	this.id = id;
    }
	
	@Column(name = "organizationCode", length = 20)
	public String getOrganizationCode() {
		return organizationCode;
	}

	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}

	@Column(name = "organizationName", length = 50)
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Column(name = "organizationManager", length = 32)
	public String getOrganizationManager() {
		return organizationManager;
	}

	public void setOrganizationManager(String organizationManager) {
		this.organizationManager = organizationManager;
	}

	@Column(name = "organizationPid", length = 32)
	public JSOrganization getOrganizationPid() {
		return organizationPid;
	}

	public void setOrganizationPid(JSOrganization organizationPid) {
		this.organizationPid = organizationPid;
	}
	@Column(name = "organizationLevel")
	public Short getOrganizationLevel() {
		return organizationLevel;
	}

	public void setOrganizationLevel(Short organizationLevel) {
		this.organizationLevel = organizationLevel;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "childOrganizations")
	public List<JSOrganization> getChildOrganizations() {
		return childOrganizations;
	}

	public void setChildOrganizations(List<JSOrganization> childOrganizations) {
		this.childOrganizations = childOrganizations;
	}

	public JSArea getJsArea() {
		return jsArea;
	}

	public void setJsArea(JSArea jsArea) {
		this.jsArea = jsArea;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getOrganizationSort() {
		return organizationSort;
	}

	public void setOrganizationSort(String organizationSort) {
		this.organizationSort = organizationSort;
	}
	
	
}