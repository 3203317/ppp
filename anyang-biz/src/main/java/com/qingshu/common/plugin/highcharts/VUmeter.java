package com.qingshu.common.plugin.highcharts;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * @description:(Highcharts仪表盘模型)
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class VUmeter {
	private Title title = new Title(); /* 标题 */
	private Title subtitle = new Title(); /* 副标题 */
	private Chart chart = new Chart(); /* 基础定义 */
	private Credits credits = new Credits(); /* logo文本设置 */
	private List<VUyAxis> yAxis = new ArrayList<VUyAxis>();
	private List<VUSeries> series; /* 数据集 */
	private List<Pane> pane = new ArrayList<Pane>();
	private PlotOptions plotOptions = new PlotOptions();
	private Exporting exporting = new Exporting();/*图片导出定义*/
	public Exporting getExporting() {
		return exporting;
	}

	public void setExporting(Exporting exporting) {
		this.exporting = exporting;
	}

	public Title getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(Title subtitle) {
		this.subtitle = subtitle;
	}

	public List<VUyAxis> getyAxis() {
		return yAxis;
	}

	public void setyAxis(List<VUyAxis> yAxis) {
		this.yAxis = yAxis;
	}

	public Title getTitle() {
		return title;
	}

	public List<VUSeries> getSeries() {
		return series;
	}

	public void setSeries(List<VUSeries> series) {
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

	public List<Pane> getPane() {
		return pane;
	}

	public void setPane(List<Pane> pane) {
		this.pane = pane;
	}

	public PlotOptions getPlotOptions() {
		return plotOptions;
	}

	public void setPlotOptions(PlotOptions plotOptions) {
		this.plotOptions = plotOptions;
	}
}
