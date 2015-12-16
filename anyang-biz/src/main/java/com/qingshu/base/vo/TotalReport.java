package com.qingshu.base.vo;


/**
 * 
 * 分类汇总统计模型
 * 
 */
public class TotalReport {
	private String areaName;
	private String organizationName;
	private Integer wfCount=0;
	private Double wfAreas=0.0;
	private Integer hfCount=0;
	private Double hfAreas=0.0;
	private Integer ccCount=0;
	private Double ccAreas=0.0;
	private Integer wccCount=0;
	private Double wccAreas=0.0;
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public Integer getWfCount() {
		return wfCount;
	}
	public void setWfCount(Integer wfCount) {
		this.wfCount = wfCount;
	}
	public Double getWfAreas() {
		return wfAreas;
	}
	public void setWfAreas(Double wfAreas) {
		this.wfAreas = wfAreas;
	}
	public Integer getHfCount() {
		return hfCount;
	}
	public void setHfCount(Integer hfCount) {
		this.hfCount = hfCount;
	}
	public Double getHfAreas() {
		return hfAreas;
	}
	public void setHfAreas(Double hfAreas) {
		this.hfAreas = hfAreas;
	}
	public Integer getCcCount() {
		return ccCount;
	}
	public void setCcCount(Integer ccCount) {
		this.ccCount = ccCount;
	}
	public Double getCcAreas() {
		return ccAreas;
	}
	public void setCcAreas(Double ccAreas) {
		this.ccAreas = ccAreas;
	}
	public Integer getWccCount() {
		return wccCount;
	}
	public void setWccCount(Integer wccCount) {
		this.wccCount = wccCount;
	}
	public Double getWccAreas() {
		return wccAreas;
	}
	public void setWccAreas(Double wccAreas) {
		this.wccAreas = wccAreas;
	}
	
}
