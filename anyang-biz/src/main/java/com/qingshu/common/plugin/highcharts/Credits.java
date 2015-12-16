package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * @description:(Highcharts(Logo参数设置))
 * @author：flyme
 * @version：1.0
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class Credits {
	private Boolean enabled = false;	/*是否显示logo文本*/
	private String href;				/*Logo链接,默认http://www.highcharts.com.*/
	private String style;				/*样式*/
	private String text;				/*logo文本*/
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
