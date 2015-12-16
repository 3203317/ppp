package com.qingshu.base.pojo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.qingshu.core.hibernate.config.IdEntity;
@Entity
@Table(name = "j_s_table")
public class JSTable extends IdEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private String tableDesc;
	private String tableName;
	private String pojoName;
	private String entityName;
	public String getTableDesc() {
		return tableDesc;
	}

	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getPojoName() {
		return pojoName;
	}

	public void setPojoName(String pojoName) {
		this.pojoName = pojoName;
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
