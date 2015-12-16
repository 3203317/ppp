package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @description:(Highcharts数据模型)
 * @author：flyme
 * @version：1.0
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Series {
	private String name;
	private String type;/*图表类型*/
	private Object data;
	private  boolean visible=true;

	
	
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
