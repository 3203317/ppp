package com.qingshu.common.filters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.qingshu.common.util.ConvertUtils;
import com.qingshu.common.util.ObjectUtils;


/**
* @description：TODO(高级查询)   
* @author：flyme  
* @data：2013-8-13 下午04:59:55    
* @version：v 1.0
 */
public class FilterHandler {
	// 多条件查询JSON
	private String filters;
	// 用于排序的列名
	private String sidx="";
	// 排序的体式格式desc/asc
	private String sord="asc";
	// 分组字段
	private String groupBy;
	// 查询表别名
	private String alias;
	// 是否是查询请求
	private boolean search=false;
	// 查找的字段
	private String searchField;
	// 查找的方式 ，如:等于，包含于，大于等
	private String searchOper;
	// 查询的值
	private String searchString;
	// 存储总体的查询条件
	private FilterSearch filterSearch=new FilterSearch();
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public boolean isSearch() {
		return search;
	}
	public void setSearch(boolean search) {
		this.search = search;
	}
	public FilterSearch getFilterSearch() {
		return filterSearch;
	}
	public void setFilterSearch(FilterSearch filterSearch) {
		this.filterSearch = filterSearch;
	}
	public String getSearchField() {
		return searchField;
	}
	public void setSearchField(String searchField) {
		this.searchField = searchField;
	}
	public String getSearchOper() {
		return searchOper;
	}
	public void setSearchOper(String searchOper) {
		this.searchOper = searchOper;
	}
	public String getSearchString() {
		return searchString;
	}
	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getGroupBy() {
		return groupBy;
	}
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}
	/**
	 * 初始化FilterSearch对象
	 */
	public FilterSearch initFilterSearch(FilterHandler handler) {
		if (handler.isSearch()) {
			if (ObjectUtils.isNotEmpty(handler.getFilters())) {
				JSONObject jsonObject = null;
				jsonObject = JSONObject.fromObject(ConvertUtils.getURLDecoder(handler.getFilters()));
				Boolean group = jsonObject.containsKey("groups");
				JSONArray rules = jsonObject.getJSONArray("rules");
				String op = jsonObject.getString("groupOp");
				JSONObject obj = new JSONObject();
				Map<String, Object> m = new HashMap<String, Object>();
				obj.put("rules", rules);
				if(op.equals(GroupOp.and.toString()))
				{
					obj.put("groupOp",GroupOp.and);
				}
				else {
					obj.put("groupOp", GroupOp.or);
				}
				
				m.put("rules", SearchRule.class);
				if (group) {
					JSONArray groups = jsonObject.getJSONArray("groups");
					obj.put("groups", groups);
					m.put("groups", FilterSearch.class);
					m.put("groupOp",GroupOp.class);
				}
				JSONObject filt = JSONObject.fromObject(obj);
				filterSearch = (FilterSearch) JSONObject.toBean(filt, FilterSearch.class, m);
			} else {
				SearchRule rule = new SearchRule();
				rule.setData(handler.getSearchString());
				rule.setOp(handler.getSearchOper());
				rule.setField(handler.getSearchField());
				filterSearch = new FilterSearch();
				filterSearch.setGroupOp(null);
				List<SearchRule> rules = new ArrayList<SearchRule>();
				rules.add(rule);
				filterSearch.setRules(rules);
			}
		}
		return filterSearch;
	}
}
