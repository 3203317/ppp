package com.qingshu.base.vo;

public class WarningVO {
	private String nickName;
	private String reportId;
	private String indexId;
	private String warningId;
	private Integer tbIsNormal;
	private Integer hbIsNormal;
	private Double value;
	private Double tbValue;
	private Double hbValue;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getIndexId() {
		return indexId;
	}

	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}

	public String getWarningId() {
		return warningId;
	}

	public void setWarningId(String warningId) {
		this.warningId = warningId;
	}

	public Integer getTbIsNormal() {
		return tbIsNormal;
	}

	public void setTbIsNormal(Integer tbIsNormal) {
		this.tbIsNormal = tbIsNormal;
	}

	public Integer getHbIsNormal() {
		return hbIsNormal;
	}

	public void setHbIsNormal(Integer hbIsNormal) {
		this.hbIsNormal = hbIsNormal;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getTbValue() {
		return tbValue;
	}

	public void setTbValue(Double tbValue) {
		this.tbValue = tbValue;
	}

	public Double getHbValue() {
		return hbValue;
	}

	public void setHbValue(Double hbValue) {
		this.hbValue = hbValue;
	}
}
