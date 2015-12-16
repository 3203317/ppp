package com.qingshu.common.filters;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.qingshu.common.util.ObjectUtils;

public class SearchUtil {
	public static String hasWhere = "where";

	/**
	 * 根据表名称的别名以及JqGridHandler的查询条件组合HQL语句
	 * 
	 * @param alias
	 *            查询对象对应的别名
	 * @param handler
	 * @return
	 */
	public static String getCombOperation(FilterHandler handler) {
		StringBuilder result = new StringBuilder("");
		if (handler != null) {
			if (!handler.isSearch()) { // 不是查询,则直接组合排序条件即可
				getGroup(handler, result);
				getOrder(handler, result);
			} else if (handler.isSearch() && ObjectUtils.isNotEmpty(handler.getSearchField()) && ObjectUtils.isNotEmpty(handler.getSearchOper())) {
				// 单字段的查询
				String cond = SearchUtil.getOperation(handler.getAlias(), handler.getSearchField(), handler.getSearchOper(), handler.getSearchString());
				if (cond != null && cond.trim().length() != 0) {
					result.append(" " + hasWhere + " ");
					result.append(cond);
				}
				getGroup(handler, result);
				getOrder(handler, result);
			} else if (handler.isSearch() && handler.getFilterSearch().getGroupOp() != null) {
				// 多字段的组合查询
				String cond = getStringForGroup(handler.getAlias(), handler.getFilterSearch());
				if (ObjectUtils.isNotEmpty(cond)) {
					result.append(" " + hasWhere + " ");
					result.append(cond);
				}
				getGroup(handler, result);
				getOrder(handler, result);

			}
		}
		hasWhere = "where";
		return result.toString();
	}

	/**
	 * 拼接排序
	 * 
	 * @param handler
	 * @param result
	 * @param alias
	 */
	protected static void getOrder(FilterHandler handler, StringBuilder result) {

		if (ObjectUtils.isNotEmpty(handler.getSidx())) {
			result.append(" ORDER BY ");
			if (ObjectUtils.isNotEmpty(handler.getAlias())) {
				result.append(handler.getAlias());
				result.append(".");
			}
			result.append(handler.getSidx());
			result.append(" " + handler.getSord());
		}

	}

	/**
	 * 拼接分组: 该分组只适合mysql数据库
	 * 
	 * @param handler
	 * @param result
	 * @param alias
	 */
	protected static void getGroup(FilterHandler handler, StringBuilder result) {
		
	/*	if (ObjectUtils.isNotEmpty(handler.getGroupBy())) {
			result.append(" Group BY ");
			if (ObjectUtils.isNotEmpty(handler.getAlias())) {
				result.append(handler.getAlias());
				result.append(".");
			}
			result.append(" " + handler.getGroupBy());
		}*/
	}

	/**
	 * 根据参数获取查询HQL的语句
	 * 
	 * @param sField
	 *            字段名称
	 * @param sOper
	 *            操作名称
	 * @param sValue
	 *            值
	 * @return
	 */
	protected static String getOperation(String alias, String sField, String sOper, Object sValue) {
		StringBuffer result = new StringBuffer("");
		if (sValue == null || sValue == null) {
			if (sOper.trim().equals("nn")) // 非空
			{
				result.append(sField + " is not null ");
			} else if (sOper.trim().equals("nu")) {
				result.append(sField + " is null ");
			}
			return result.toString();
		} else {
			if (ObjectUtils.isNotEmpty(alias)) {
				result.append(alias);
				result.append(".");
			}
			if (sOper.trim().equals("eq")) // 等于
				result.append(sField + "='" + sValue + "' ");
			else if (sOper.trim().equals("ne")) // 不等于
				result.append(sField + " != " + sValue + " ");
			else if (sOper.trim().equals("lt")) // 小于
				result.append(sField + " < " + sValue + " ");
			else if (sOper.trim().equals("le")) // 小于等于
				result.append(sField + " <= '" + sValue + "' ");
			else if (sOper.trim().equals("gt")) // 大于
				result.append(sField + " > " + sValue + " ");
			else if (sOper.trim().equals("ge")) // 大于等于
				result.append(sField + " >= '" + sValue + "' ");
			else if (sOper.trim().equals("bw")) // 以...开始
				result.append(sField + " like '" + sValue + "%' ");
			else if (sOper.trim().equals("bn")) // 不以...开始
				result.append(sField + " not like '" + sValue + "%' ");
			else if (sOper.trim().equals("ni")) // 不属于
				result.append(sField + " not in (" + sValue + ") ");
			else if (sOper.trim().equals("in")) // 属于
				result.append(sField + " in (" + sValue + ") ");
			else if (sOper.trim().equals("nc")) // 不包含
				result.append(sField + " not like '%" + sValue + "%' ");
			else if (sOper.trim().equals("cn")) // 包含
				result.append(sField + " like '%" + sValue + "%' ");
			else if (sOper.trim().equals("en")) // 不结束于
				result.append(sField + " not like '%" + sValue + "' ");
			else if (sOper.trim().equals("ew")) // 结束于
				result.append(sField + " like '%" + sValue + "' ");
		}
		return result.toString();
	}

	/**
	 * 拼装查询条件
	 * 
	 * @return
	 */
	protected static String getStringForGroup(String alias, FilterSearch filterSearch) {
		List<SearchRule> rules = filterSearch.getRules();
		List<FilterSearch> groups = filterSearch.getGroups();
		StringBuffer s = new StringBuffer("(");
		if (groups != null && groups.size() > 0) {
			for (FilterSearch group : groups) {
				if (s.length() > 1) {
					s.append(" " + filterSearch.getGroupOp() + " ");
				}
				s.append(getStringForGroup(alias, group));
			}
		}
		if (rules != null && rules.size() > 0) {
			for (SearchRule searchRule : rules) {
				if (s.length() > 1) {
					s.append(" " + filterSearch.getGroupOp() + " ");
				}
				s.append(getOperation(alias, searchRule.getField(), searchRule.getOp(), searchRule.getData()));
			}
		}
		s.append(")");
		if (s.toString().equals("()")) {
			return "";
		}
		return s.toString();
	}

	/**
	 * 过滤非法字符
	 */
	public static String sql_Injection(String str) {
		String inj_str = "and exec insert select delete update" + " count * % chr mid master truncate char declare ; or + ";
		String arr[] = inj_str.split(" ");
		for (int i = 0; i < arr.length; i++) {
			if (str.indexOf(arr[i]) != -1) {
				str = "";
			}
		}
		return str;
	}

	public static SearchRule getSearchRule(String field, CompareType compareType, Object... data) {
		String dataString = data[0].toString();
		boolean isDate = data[0] instanceof Timestamp || data[0] instanceof Date;
		if(isDate){
			dataString = "to_date('"+data[0].toString().substring(0,data[0].toString().length()-2)+"','yyyy-MM-dd HH24:mi:ss')";
		}
		SearchRule searchRule = new SearchRule();
		if (!ObjectUtils.isEmpty(data[0]) && data.length == 1 && data[0] instanceof String) {
			searchRule.setData(SearchUtil.sql_Injection(dataString));
		}
		else if(isDate){
			searchRule.setData(dataString);
		}
		else {
			searchRule.setData(dataString);
		}
		searchRule.setField(field);
		searchRule.setOp(compareType.toString());
		return searchRule;
	}

	public static String getField(String field) {
		int s = field.indexOf(".");
		if (s > -1) {
			return field.substring(s + 1);
		} else {
			return field;
		}
	}

}
