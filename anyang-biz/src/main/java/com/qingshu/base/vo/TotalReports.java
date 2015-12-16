package com.qingshu.base.vo;

import java.util.List;

/**
 * 
 * 数据分类汇总统计模型
 * 
 */
public class TotalReports {

	private TotalReport totalReport;
	private List<TotalReport> totalReportList;
	public TotalReport getTotalReport() {
		return totalReport;
	}

	public void setTotalReport(TotalReport totalReport) {
		this.totalReport = totalReport;
	}

	public List<TotalReport> getTotalReportList() {
		return totalReportList;
	}

	public void setTotalReportList(List<TotalReport> totalReportList) {
		this.totalReportList = totalReportList;
	}

	
	

}
