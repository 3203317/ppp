package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @description:(Highcharts标题模型)
 * @author：flyme
 * @version：1.0
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Title {
	private String text;
	private Integer y;
	private boolean useHTML = true;
	private Integer rotation;/*文字旋转角度*/
	public Integer getRotation() {
		return rotation;
	}

	public void setRotation(Integer rotation) {
		this.rotation = rotation;
	}

	public boolean isUseHTML() {
		return useHTML;
	}

	public void setUseHTML(boolean useHTML) {
		this.useHTML = useHTML;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
