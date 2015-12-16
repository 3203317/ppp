package com.qingshu.common.plugin.highcharts;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @description:(Highcharts y轴定义)
 * @author：flyme
 * @version：1.0
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class VUyAxis {
	private Title title = new Title();
	private Integer min = 0;
	private Integer max = 1;
	private String tickPosition = "outside";
	private Labels labels;
	private List<PlotBands> plotBands;
	private Integer pane = 0;
	private String minorTickPosition = "outside";  //副标记的位置
	private Integer tickInterval;/*刻度单位*/
	private Integer tickLength=15;/*刻度线长度*/
	public Integer getTickLength() {
		return tickLength;
	}

	public void setTickLength(Integer tickLength) {
		this.tickLength = tickLength;
	}

	public Integer getTickInterval() {
		return tickInterval;
	}

	public void setTickInterval(Integer tickInterval) {
		this.tickInterval = tickInterval;
	}
	public Integer getMax() {
		return max;
	}

	public void setMax(Integer max) {
		this.max = max;
	}

	public String getMinorTickPosition() {
		return minorTickPosition;
	}

	public void setMinorTickPosition(String minorTickPosition) {
		this.minorTickPosition = minorTickPosition;
	}

	public String getTickPosition() {
		return tickPosition;
	}

	public void setTickPosition(String tickPosition) {
		this.tickPosition = tickPosition;
	}

	public Labels getLabels() {
		return labels;
	}

	public void setLabels(Labels labels) {
		this.labels = labels;
	}

	public List<PlotBands> getPlotBands() {
		return plotBands;
	}

	public void setPlotBands(List<PlotBands> plotBands) {
		this.plotBands = plotBands;
	}

	public Integer getPane() {
		return pane;
	}

	public void setPane(Integer pane) {
		this.pane = pane;
	}

	public Title getTitle() {
		return title;
	}

	public void setTitle(Title title) {
		this.title = title;
	}

	public Integer getMin() {
		return min;
	}

	public void setMin(Integer min) {
		this.min = min;
	}

}
