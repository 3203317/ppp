package com.qingshu.common.filters;


public class Criterion {
	private String field; 					/* 字段 */
	private Object value; 					/* 值 */
	private CompareType compareType; 		/* 条件 */
	
	public Criterion()
	{
	}
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public CompareType getCompareType() {
		return compareType;
	}

	public void setCompareType(CompareType compareType) {
		this.compareType = compareType;
	}


	public static Criterion getCriterion(String field, Object value, CompareType compareType) {
		Criterion criterion = new Criterion();
		criterion.setCompareType(compareType);
		criterion.setField(field);
		criterion.setValue(value);
		return criterion;
	}

}
