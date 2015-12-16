package com.qingshu.base.vo;

import java.util.List;

/**
 * 
 * 经济指标统计模型
 * 
 */
public class Report {
	private String reportId; /* 报表Id */
	private String code;
	private String name; /* 单位名称 */
	private Integer month;
	private List<ReportIndex> reportIndexs;/*办事处指标统计*/
	private List<Report> reports;/*下属企业指标统计*/

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ReportIndex> getReportIndexs() {
		return reportIndexs;
	}

	public void setReportIndexs(List<ReportIndex> reportIndexs) {
		this.reportIndexs = reportIndexs;
	}
	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}
	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
