package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * 饼图模型
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Pie {
	private Boolean allowPointSelect=true;/*是否允许点击动画*/
	private String cursor="point";/*鼠标样式*/
	private DataLabels dataLabels;/*数据lable*/
	private Boolean showInLegend=true;/*是否图示说明*/
	public Boolean getShowInLegend() {
		return showInLegend;
	}
	public void setShowInLegend(Boolean showInLegend) {
		this.showInLegend = showInLegend;
	}
	public Boolean getAllowPointSelect() {
		return allowPointSelect;
	}
	public void setAllowPointSelect(Boolean allowPointSelect) {
		this.allowPointSelect = allowPointSelect;
	}
	public String getCursor() {
		return cursor;
	}
	public void setCursor(String cursor) {
		this.cursor = cursor;
	}
	public DataLabels getDataLabels() {
		return dataLabels;
	}
	public void setDataLabels(DataLabels dataLabels) {
		this.dataLabels = dataLabels;
	}
}
