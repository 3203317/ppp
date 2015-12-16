package com.qingshu.common.plugin.highcharts;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class DataLabels {
	private boolean enabled=true;
	private boolean useHTML;
	private String format;/*格式化*/

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isUseHTML() {
		return useHTML;
	}

	public void setUseHTML(boolean useHTML) {
		this.useHTML = useHTML;
	}
}
