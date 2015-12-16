package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @description:(Highcharts x轴定义)
 * @author：flyme
 * @version：1.0
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class xAxis {
	private String[] categories; /* x轴分类 */

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}
}
