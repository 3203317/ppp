package com.qingshu.common.plugin.highcharts;

public class Exporting {
	private boolean enabled = false;// 用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
