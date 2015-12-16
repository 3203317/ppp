package com.qingshu.common.filters;



/**
 * JqGrid查询参数规则
 * @author Administrator
 *
 */
public class SearchRule {
	private String field; // 查询字段
	private String op; // 查询操作
	private Object data; // 选择的查询值

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}