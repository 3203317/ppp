package com.qingshu.base.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.qingshu.core.hibernate.config.IdEntity;

@Entity
@Table(name = "j_s_tablefield")
public class JSTableField extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String tableName;
	private String fieldName;
	private String fieldType;
	private String fieldDesc;
	private Integer fieldLength;
	private String pojoName;
	private Integer isLogField;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Integer getFieldLength() {
		return fieldLength;
	}

	public void setFieldLength(Integer fieldLength) {
		this.fieldLength = fieldLength;
	}

	public String getPojoName() {
		return pojoName;
	}

	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}

	public Integer getIsLogField() {
		return isLogField;
	}

	public void setIsLogField(Integer isLogField) {
		this.isLogField = isLogField;
	}
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFieldDesc() {
		return fieldDesc;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

}
