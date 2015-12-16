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
 * @description：TODO(行政区域))
 */
@Entity
@Table(name = "j_s_area")
public class JSArea extends IdEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String areaCode; /* 区域编码 */
	private String areaNo; /* 区域代码(国内統一代码)*/
	private String areaName; /* 机构名称 */
	private JSArea areaParent; /* 父节点 */
	private Short areaLevel;/* 区域级别 */
	private List<JSArea> childAreas=new ArrayList<JSArea>();
	
	@Column(name = "areaCode", length = 20)
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	@Column(name = "areaName", length = 50)
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "areaParentId", length = 32)
	public JSArea getAreaParent() {
		return areaParent;
	}

	public void setAreaParent(JSArea areaParent) {
		this.areaParent = areaParent;
	}

	@Column(name = "areaLevel")
	public Short getAreaLevel() {
		return areaLevel;
	}

	public void setAreaLevel(Short areaLevel) {
		this.areaLevel = areaLevel;
	}
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "childAreas")
	public List<JSArea> getChildAreas() {
		return childAreas;
	}

	public void setChildAreas(List<JSArea> childAreas) {
		this.childAreas = childAreas;
	}

}