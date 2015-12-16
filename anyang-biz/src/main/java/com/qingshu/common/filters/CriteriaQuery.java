package com.qingshu.common.filters;

import org.springframework.ui.Model;

import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ObjectUtils;

public class CriteriaQuery {
	private FilterSearch filterSearch = new FilterSearch();/* 自定义查询条件集合 */
	private PagerInfo pagerInfo;
	private Model model;

	public CriteriaQuery(PagerInfo pagerInfo, GroupOp groupOp) {
		this.pagerInfo = pagerInfo;
		filterSearch.setGroupOp(groupOp);
		pagerInfo.setFilterSearch(filterSearch);
	}

	public CriteriaQuery(FilterHandler filterHandler, GroupOp groupOp) {
		this.pagerInfo = new PagerInfo(filterHandler);
		filterSearch.setGroupOp(groupOp);
		pagerInfo.setFilterSearch(filterSearch);
	}

	public CriteriaQuery(FilterHandler filterHandler, GroupOp groupOp, Model model) {
		this.pagerInfo = new PagerInfo(filterHandler);
		filterSearch.setGroupOp(groupOp);
		pagerInfo.setFilterSearch(filterSearch);
		pagerInfo.setQueryParams(model);
		this.model = model;
	}
	public CriteriaQuery( GroupOp groupOp, Model model) {
		this.pagerInfo = new PagerInfo(new FilterHandler());
		filterSearch.setGroupOp(groupOp);
		pagerInfo.setFilterSearch(filterSearch);
		pagerInfo.setQueryParams(model);
		this.model = model;
	}
	

	public void add(FilterSearch filterSearch) {
		pagerInfo.getFilterHandler().setSearch(true);
		pagerInfo.getFilterHandler().setFilterSearch(filterSearch);
	}

	public void addModel(String field, Object value) {
		if (!ObjectUtils.isEmpty(model)) {
			model.addAttribute(SearchUtil.getField(field), value);
		}
	}

	/**
	 * 设置相等条件
	 */
	public FilterSearch eq(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.eq,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置不相等条件
	 */
	public FilterSearch ne(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field, CompareType.ne,value));
			add(filterSearch);
		}
		return filterSearch;
	}

	/**
	 * 设置模糊条件(以...开始)like '001%'
	 */
	public FilterSearch bw(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.bw,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置模糊条件(以...结束)like '%001'
	 */
	public FilterSearch ew(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.ew,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}
	/**
	 * 设置空查询field is null 
	 */
	public FilterSearch isNull(String field) {
		if (!ObjectUtils.isEmpty(field)) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.nu));
			add(filterSearch);
		}
		return filterSearch;
	}
	/**
	 * 设置空查询field is not null 
	 */
	public FilterSearch notNUll(String field) {
		if (!ObjectUtils.isEmpty(field)) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.nn));
			add(filterSearch);
		}
		return filterSearch;
	}

	/**
	 * 设置模糊条件(不结束于...)not like '%001'
	 */
	public FilterSearch en(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.en,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置模糊条件(包含)like '%001%'
	 */
	public FilterSearch cn(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.cn,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置模糊条件(不包含)not like '%001%'
	 */
	public FilterSearch nc(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.nc,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置模糊条件(不以...开始)not like '001%'
	 */
	public FilterSearch bn(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.bn,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置大于等于>='001'
	 */
	public FilterSearch ge(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.ge,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置大于>'001'
	 */
	public FilterSearch gt(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.gt,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置小于等于<='001'
	 */
	public FilterSearch le(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.le,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

/**
	 * 设置小于<'001'
	 */
	public FilterSearch lt(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.lt,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置排序字段
	 */
	public FilterSearch order(String field, Sort sort) {
		if (!ObjectUtils.isEmpty(sort)) {
			pagerInfo.getFilterHandler().setSidx(field);
			pagerInfo.getFilterHandler().setSord(sort.toString());
		}
		return filterSearch;
	}

	/**
	 * 设置in查询
	 */
	public FilterSearch in(String field, Object...value) {
		if (!ObjectUtils.isEmpty(value[0])) {
			filterSearch.getRules().add(SearchUtil.getSearchRule(field,CompareType.in,value));
			add(filterSearch);
			addModel(field, value[0]);
		}
		return filterSearch;
	}

	/**
	 * 设置分组字段
	 */
	public FilterSearch groupBy(String field) {
		if (!ObjectUtils.isEmpty(field)) {
			pagerInfo.getFilterHandler().setGroupBy(field);
		}
		return filterSearch;
	}

	public FilterSearch getFilterSearch() {
		return filterSearch;
	}

	public void setFilterSearch(FilterSearch filterSearch) {
		this.filterSearch = filterSearch;
	}

	public PagerInfo getPagerInfo() {
		return pagerInfo;
	}

	public void setPagerInfo(PagerInfo pagerInfo) {
		this.pagerInfo = pagerInfo;
	}

}
