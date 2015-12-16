package com.qingshu.common.json.model;

import java.util.Map;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * AJAX请求时返回的JSON对象
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AjaxJson {
	private boolean success = true;// 是否成功
	private String alerttype ="tips";//弹出框方式
	private Map<String, Object> attributes;// 其他参数
	private String href="list.do";//父页面刷新地址
	private String callback;//回调函数
	private String status ="y";// 是否成功
	private String info = "请在后台设置提示信息";//提示信息
	private Object object;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public Map<String, Object> getAttributes() {
		return attributes;
	}
	/**
	 * 设置其他参数
	 */
	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getAlerttype() {
		return alerttype;
	}
	/**
	 * 设置弹出框类型可选tip,alert
	 */
	public void setAlerttype(String alerttype) {
		this.alerttype = alerttype;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info,String status) {
		this.info = info;
		this.status=status;
	}
	public void setInfo(String info,Boolean success) {
		this.info = info;
		this.success=success;
	}
	public void setInfo(String info,Boolean success,String alerttype) {
		this.info = info;
		this.success=success;
		this.alerttype=alerttype;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}

}
