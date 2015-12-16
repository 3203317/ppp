package com.qingshu.base.vo;

import java.util.List;


/**
 * 
 * 分类汇总统计模型
 * 
 */
public class TotalAllReport {
	private TotalReport totalReport;
	private List<TotalReports> totalReportsList;
	public TotalReport getTotalReport() {
		return totalReport;
	}
	public void setTotalReport(TotalReport totalReport) {
		this.totalReport = totalReport;
	}
	public List<TotalReports> getTotalReportsList() {
		return totalReportsList;
	}
	public void setTotalReportsList(List<TotalReports> totalReportsList) {
		this.totalReportsList = totalReportsList;
	}
}
