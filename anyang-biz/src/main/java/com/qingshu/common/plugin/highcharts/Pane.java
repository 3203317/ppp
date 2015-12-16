package com.qingshu.common.plugin.highcharts;

public class Pane {
	private Integer startAngle = -45;
	private Integer endAngle = 45;
	private String background;
	private String[] center = new String[] { "20%", "145%" };
	private Integer size = 300;

	public Integer getStartAngle() {
		return startAngle;
	}

	public void setStartAngle(Integer startAngle) {
		this.startAngle = startAngle;
	}

	public Integer getEndAngle() {
		return endAngle;
	}

	public void setEndAngle(Integer endAngle) {
		this.endAngle = endAngle;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String[] getCenter() {
		return center;
	}

	public void setCenter(String[] center) {
		this.center = center;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}
}
