package com.qingshu.base.vo;


/**
 * 
 * 经济指标统计模型
 * 
 */
public class ReportIndex {
	private String reportIndexId;
	private Integer month;
	
	private Integer indexType; /* 指标类型 */
	private String indexName;//经济指标名称
	private Double tmTotale=0.0; /* 本月 */
	private Double tmAddTotale=0.0; /* 本月累计 */
	private Double lytmTotale=0.0; /* 去年本月 */
	private Double lyTotale=0.0; /* 去年累计 */
	private Double tmEnhance=0.0; /* 本月提高 */
	private Double addEnhance=0.0; /* 累计提高 */
	public Integer getIndexType() {
		return indexType;
	}

	public void setIndexType(Integer indexType) {
		this.indexType = indexType;
	}

	public Double getTmTotale() {
		return tmTotale;
	}

	public void setTmTotale(Double tmTotale) {
		this.tmTotale = tmTotale;
	}

	public Double getTmAddTotale() {
		return tmAddTotale;
	}

	public void setTmAddTotale(Double tmAddTotale) {
		this.tmAddTotale = tmAddTotale;
	}

	public Double getLytmTotale() {
		return lytmTotale;
	}

	public void setLytmTotale(Double lytmTotale) {
		this.lytmTotale = lytmTotale;
	}

	public Double getLyTotale() {
		return lyTotale;
	}

	public void setLyTotale(Double lyTotale) {
		this.lyTotale = lyTotale;
	}

	public Double getTmEnhance() {
		return tmEnhance;
	}

	public void setTmEnhance(Double tmEnhance) {
		this.tmEnhance = tmEnhance;
	}

	public Double getAddEnhance() {
		return addEnhance;
	}

	public void setAddEnhance(Double addEnhance) {
		this.addEnhance = addEnhance;
	}
	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
	public String getReportIndexId() {
		return reportIndexId;
	}

	public void setReportIndexId(String reportIndexId) {
		this.reportIndexId = reportIndexId;
	}
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
}
