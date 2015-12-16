package com.qingshu.base.vo;
/**
 * 分类汇总查询结果集模型
 */
public class SubTotalDetail {
	private String indexId;//经济指标ID
	private String name;//经济指标名称
	private Double value;//经济指标值
	
	private String organizationid;//行政区域ID
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public String getOrganizationid() {
		return organizationid;
	}
	public void setOrganizationid(String organizationid) {
		this.organizationid = organizationid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
