package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @description:(Highcharts基础定义模型)
 * @author：flyme
 * @version：1.0
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Chart {
	private Type type = Type.line;
	private Integer plotBorderWidth = 0;
	private String plotBackgroundImage;
	private Integer height = 300;
	
	public Integer getPlotBorderWidth() {
		return plotBorderWidth;
	}

	public void setPlotBorderWidth(Integer plotBorderWidth) {
		this.plotBorderWidth = plotBorderWidth;
	}

	public String getPlotBackgroundImage() {
		return plotBackgroundImage;
	}

	public void setPlotBackgroundImage(String plotBackgroundImage) {
		this.plotBackgroundImage = plotBackgroundImage;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
