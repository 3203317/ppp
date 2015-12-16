package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class PlotSeries {
	private Integer pointWidth;/*柱面宽度*/

	public Integer getPointWidth() {
		return pointWidth;
	}

	public void setPointWidth(Integer pointWidth) {
		this.pointWidth = pointWidth;
	}

}
