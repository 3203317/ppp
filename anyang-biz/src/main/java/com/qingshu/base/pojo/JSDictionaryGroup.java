package com.qingshu.base.pojo;

import java.io.Serializable;

import com.qingshu.core.hibernate.config.IdEntity;
/**
 * 字典分组
 */
public class JSDictionaryGroup extends IdEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	private String dictGroupName;/*字典分类*/
	private String dictGroupCode;/*编码*/
	public String getDictGroupName() {
		return dictGroupName;
	}
	public void setDictGroupName(String dictGroupName) {
		this.dictGroupName = dictGroupName;
	}
	public String getDictGroupCode() {
		return dictGroupCode;
	}
	public void setDictGroupCode(String dictGroupCode) {
		this.dictGroupCode = dictGroupCode;
	}
}
