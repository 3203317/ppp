package com.qingshu.common.json.model;
/**
 * Tree模型字段设置(反射取值)
 */
public class TreeModelParm {
	private String idField; // 编号
	private String pIdField; // 父节点字段名称
	private String pIdValue;//父节点值
	private String nameField; // 节点名称 
	private String urlField;//链接地址
	private String urlValue;//链接地址固定值
	private String targetValue;//连接目标
	private String childField;//子节点对应字段
	private String categoryField;//节点级别
	private String otherchildField;//子节点对应字段
	public String getOtherchildField() {
		return otherchildField;
	}
	public void setOtherchildField(String otherchildField) {
		this.otherchildField = otherchildField;
	}
	private Boolean isOpen=false;//是否默认展开节点

	public TreeModelParm(String idField, String nameField) {
		this.idField = idField;
		this.nameField = nameField;
	}
	public TreeModelParm(String idField, String pIdField, String nameField) {
		this.idField = idField;
		this.pIdField = pIdField;
		this.nameField = nameField;
	}
	public TreeModelParm(String idField, String pIdField, String nameField,
			String urlField,String childField) {
		this.idField = idField;
		this.pIdField = pIdField;
		this.nameField = nameField;
		this.urlField = urlField;
		this.childField=childField;
	}
	public TreeModelParm(String idField, String pIdField, String nameField,
			String urlField,String childField,String categoryField) {
		this.idField = idField;
		this.pIdField = pIdField;
		this.nameField = nameField;
		this.urlField = urlField;
		this.childField=childField;
		this.categoryField=categoryField;
	}
	/**
	 * 构造函数
	 * @param idField值字段
	 * @param pIdField父ID字段
	 * @param nameField文本字段
	 */
	public TreeModelParm(String idField, String pIdField, String nameField,String childField) {
		this.idField = idField;
		this.pIdField = pIdField;
		this.nameField = nameField;
		this.childField=childField;
	}
	/**
	 * 构造函数
	 * @param idField值字段
	 * @param pIdField父ID字段
	 * @param nameField文本字段
	 */
	public TreeModelParm(String idField, String pIdField, String nameField,String childField,Boolean isOpen) {
		this.idField = idField;
		this.pIdField = pIdField;
		this.nameField = nameField;
		this.childField=childField;
		this.isOpen=isOpen;
	}
	public TreeModelParm(String idField, String pIdField, String nameField,String childField,String categoryField,Boolean isOpen) {
		this.idField = idField;
		this.pIdField = pIdField;
		this.nameField = nameField;
		this.childField=childField;
		this.categoryField=categoryField;
		this.isOpen=isOpen;
	}
	public String getUrlField() {
		return urlField;
	}
	public void setUrlField(String urlField) {
		this.urlField = urlField;
	}
	public String getIdField() {
		return idField;
	}
	public void setIdField(String idField) {
		this.idField = idField;
	}
	public String getpIdField() {
		return pIdField;
	}
	public void setpIdField(String pIdField) {
		this.pIdField = pIdField;
	}
	public String getNameField() {
		return nameField;
	}
	public void setNameField(String nameField) {
		this.nameField = nameField;
	}
	public String getChildField() {
		return childField;
	}
	public void setChildField(String childField) {
		this.childField = childField;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getCategoryField() {
		return categoryField;
	}
	public void setCategoryField(String categoryField) {
		this.categoryField = categoryField;
	}
	public String getpIdValue() {
		return pIdValue;
	}
	public void setpIdValue(String pIdValue) {
		this.pIdValue = pIdValue;
	}
	public String getUrlValue() {
		return urlValue;
	}
	public void setUrlValue(String urlValue) {
		this.urlValue = urlValue;
	}
	public String getTargetValue() {
		return targetValue;
	}
	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}
	
}
