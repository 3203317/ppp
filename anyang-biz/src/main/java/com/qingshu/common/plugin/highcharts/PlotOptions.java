package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * plotOptions(绘图区选项)用于设置图表中的数据点相关属性
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PlotOptions {
	private Gauge gauge;/*仪表盘*/
	private Pie pie;/*饼图*/
	private PlotSeries series;/*绘图区数列*/
	public PlotSeries getSeries() {
		return series;
	}

	public void setSeries(PlotSeries series) {
		this.series = series;
	}

	public Pie getPie() {
		return pie;
	}

	public void setPie(Pie pie) {
		this.pie = pie;
	}

	public Gauge getGauge() {
		return gauge;
	}

	public void setGauge(Gauge gauge) {
		this.gauge = gauge;
	}
}
