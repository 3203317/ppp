package com.qingshu.base.vo;

public class Warning {
	private Integer total;
	private Integer zc=0;/*高于预警线总值*/
	private Integer bzc=0;/*低于预警线总值*/

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getZc() {
		return zc;
	}

	public void setZc(Integer zc) {
		this.zc = zc;
	}

	public Integer getBzc() {
		return bzc;
	}

	public void setBzc(Integer bzc) {
		this.bzc = bzc;
	}
}
