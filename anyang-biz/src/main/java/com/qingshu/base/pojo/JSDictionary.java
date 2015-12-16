package com.qingshu.base.pojo;

import java.io.Serializable;

import com.qingshu.core.hibernate.config.IdEntity;

public class JSDictionary extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private String dictName;/*字典名称*/
	private String dictCode;/*字典编码*/
	private JSDictionaryGroup dictGroup;/*所属分组*/

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictCode() {
		return dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public JSDictionaryGroup getDictGroup() {
		return dictGroup;
	}

	public void setDictGroup(JSDictionaryGroup dictGroup) {
		this.dictGroup = dictGroup;
	}
}
