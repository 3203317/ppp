package com.qingshu.common.plugin.highcharts;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @description:(Highcharts模型)
 * @author：flyme
 * @version：1.0
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Highcharts {
	private Title title = new Title(); /* 标题 */
	private Title subtitle = new Title(); /* 副标题 */
	private Chart chart = new Chart(); /* 基础定义 */
	private Credits credits = new Credits(); /* logo文本设置 */
	private xAxis xAxis;
	private yAxis yAxis;
	private PlotOptions plotOptions;
	private Tooltip tooltip = new Tooltip();
	private List<Series> series; /* 数据集 */
	private Exporting exporting = new Exporting();

	public Exporting getExporting() {
		return exporting;
	}

	public void setExporting(Exporting exporting) {
		this.exporting = exporting;
	}

	public yAxis getyAxis() {
		return yAxis;
	}

	public void setyAxis(yAxis yAxis) {
		this.yAxis = yAxis;
	}

	public xAxis getxAxis() {
		return xAxis;
	}

	public void setxAxis(xAxis xAxis) {
		this.xAxis = xAxis;
	}

	public Tooltip getTooltip() {
		return tooltip;
	}

	public void setTooltip(Tooltip tooltip) {
		this.tooltip = tooltip;
	}

	public Title getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Title subtitle) {
		this.subtitle = subtitle;
	}

	public Title getTitle() {
		return title;
	}

	public List<Series> getSeries() {
		return series;
	}

	public void setSeries(List<Series> series) {
		this.series = series;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Credits getCredits() {
		return credits;
	}

	public void setCredits(Credits credits) {
		this.credits = credits;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}
	public PlotOptions getPlotOptions() {
		return plotOptions;
	}

	public void setPlotOptions(PlotOptions plotOptions) {
		this.plotOptions = plotOptions;
	}

	
}
