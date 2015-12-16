package com.qingshu.common.filters;

import java.util.ArrayList;
import java.util.List;
/**
 * JqGrid多字段查询规则
 */
public class FilterSearch {
	private GroupOp groupOp; // 多字段查询时分组类型，主要是AND或者OR
	private List<SearchRule> rules=new ArrayList<SearchRule>(); // 多字段查询时候，查询条件的集合
	private List<FilterSearch> groups=new ArrayList<FilterSearch>();
	
	public GroupOp getGroupOp() {
		return groupOp;
	}

	public void setGroupOp(GroupOp groupOp) {
		this.groupOp = groupOp;
	}

	public List<SearchRule> getRules() {
		return rules;
	}

	public void setRules(List<SearchRule> rules) {
		this.rules = rules;
	}
	public List<FilterSearch> getGroups() {
		return groups;
	}

	public void setGroups(List<FilterSearch> groups) {
		this.groups = groups;
	}
}
