package com.qingshu.common.plugin.highcharts;

public class PlotBands {
	private Integer from = 0;
	private Integer to = 0;
	private String color = "#4EC1F3";
	private String innerRadius = "100%";
	private String outerRadius = "105%";

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(String innerRadius) {
		this.innerRadius = innerRadius;
	}

	public String getOuterRadius() {
		return outerRadius;
	}

	public void setOuterRadius(String outerRadius) {
		this.outerRadius = outerRadius;
	}
}
