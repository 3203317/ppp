package com.qingshu.common.pager;

import org.springframework.ui.Model;

import com.qingshu.common.filters.CriterionList;
import com.qingshu.common.filters.CriterionMap;
import com.qingshu.common.filters.FilterHandler;
import com.qingshu.common.filters.FilterSearch;
import com.qingshu.common.util.ObjectUtils;

/**
 * 分页查询类
 */
public class PagerInfo {
	private Integer pageSize = 10; /* 每一页的显示条数 */
	private Integer totalPage; /* 总的页数 */
	private Integer totalResult; /* 查询的数据总条数 */
	private Integer curPageNO = 1; /* 当前页 */
	private String actionName = ""; /* 数据请求地址 */
	private String formName = "form"; /* 表单名称 */
	private CriterionMap queryParams = new CriterionMap(); /* 手动拼接SQL查询条件 */
	private CriterionList criterionList = new CriterionList();/* 自动拼接SQL查询条件 */
	public static String PAGERSTR = "pager"; /* 分页工具条 */
	public Boolean simplePager = true;// 工具条不显示文字信息
	public Boolean isPage = true;// 是否分页
	public FilterHandler filterHandler;
	public FilterSearch filterSearch;

	public PagerInfo() {
	}

	public PagerInfo(int totalResult, int curPageNO, String actionName, String formName, int pageSize) {
		this.pageSize = pageSize;
		this.totalPage = (int) Math.ceil((double) totalResult / pageSize);
		this.totalResult = totalResult;
		this.curPageNO = curPageNO;
		this.actionName = actionName;
		this.formName = formName;
	}

	public PagerInfo(int totalResult, int curPageNO, int pageSize) {
		this.pageSize = pageSize;
		this.totalPage = (int) Math.ceil((double) totalResult / pageSize);
		this.totalResult = totalResult;
		this.curPageNO = curPageNO;
	}

	public PagerInfo(int curPageNO, int pageSize) {
		this.curPageNO = curPageNO;
		if (pageSize > 0) {
			this.pageSize = pageSize;
		}
		// 错误处理
		if (this.curPageNO < 1) {
			this.curPageNO = 1;
		}
	}

	public PagerInfo(FilterHandler filterHandler) {
		this.filterHandler = filterHandler;
		this.filterSearch = filterHandler.initFilterSearch(filterHandler);
		this.pageSize = (ObjectUtils.isEmpty(ObjectUtils.getParameter("pageSize"))) ? this.pageSize : ObjectUtils.getIntParameter("pageSize");
		this.curPageNO = (ObjectUtils.isEmpty(ObjectUtils.getParameter("curPageNO"))) ? this.curPageNO : ObjectUtils.getIntParameter("curPageNO");
	}

	public int getTotalPage() {
		return this.totalPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalResult() {
		return totalResult;
	}

	public void setTotalResult(int totalResult) {
		this.totalResult = totalResult;
	}

	public int getCurPageNO() {
		return curPageNO;
	}

	public void setCurPageNO(int curPageNO) {
		this.curPageNO = curPageNO;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentResult() {

		return (curPageNO - 1) * pageSize;
	}

	public int getStart() {

		return ((curPageNO - 1) * pageSize) + 1;
	}

	public int getEnd() {

		return (curPageNO * pageSize > totalResult) ? totalResult : curPageNO * pageSize;

	}

	public CriterionMap getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(CriterionMap queryParams) {
		this.queryParams = queryParams;
	}

	public void setQueryParams(Model model) {
		this.queryParams = new CriterionMap(model);
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public FilterHandler getFilterHandler() {
		return filterHandler;
	}

	public void setFilterHandler(FilterHandler filterHandler) {
		this.filterHandler = filterHandler;
	}

	public FilterSearch getFilterSearch() {
		return filterSearch;
	}

	public void setFilterSearch(FilterSearch filterSearch) {
		this.filterSearch = filterSearch;
	}

	public CriterionList getCriterionList() {
		return criterionList;
	}

	public void setCriterionList(CriterionList criterionList) {
		this.criterionList = criterionList;
	}

	public Boolean getSimplePager() {
		return simplePager;
	}

	public void setSimplePager(Boolean simplePager) {
		this.simplePager = simplePager;
	}

	public Boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(Boolean isPage) {
		this.isPage = isPage;
	}

	// 第一页
	public int first() {
		return 1;
	}

	// 最后一页
	public int last() {
		return totalPage;
	}

	// 上一页
	public int previous() {
		return (curPageNO - 1 < 1) ? 1 : curPageNO - 1;
	}

	// 下一页
	public int next() {
		return (curPageNO + 1 > totalPage) ? totalPage : curPageNO + 1;
	}

	// 第一页
	public boolean isFirst() {
		return (curPageNO == 1) ? true : false;
	}

	// 最后一页
	public boolean isLast() {
		return (curPageNO == totalPage) ? true : false;
	}

	/**
	 * 获取工具条
	 * 
	 * @return String
	 */
	public String getToolBar() {
		String str = "";
		str += "<span class=pageArea id=pageArea>共<b>" + totalResult + "</b>条&nbsp;当前第" + curPageNO + "/" + totalPage + "页&nbsp;&nbsp;&nbsp;";
		if (curPageNO == 1 || curPageNO == 0)
			str += "<a class=pageFirstDisable title=\"首页\">&nbsp;" + "<a class=pagePreviousDisable title=\"上一页\">上一页</a>";
		else {
			str += "<a class=\"pageFirst\" title=\"首页\"  onclick=\"commonSubmit(1,'" + formName + "')\">首页</a>";
			str += "<a class=pagePrevious title=\"上一页\"  onclick=\"commonSubmit(" + previous() + ",\'" + formName + "\')\">上一页</a>";
		}
		if (curPageNO - totalPage == 0 || totalPage == 0 || totalPage == 1)
			str += "<a class=pageNextDisable  title=\"下一页\">" + "<a class=pageLastDisable title=\"尾页\" >尾页</a>&nbsp;";
		else {
			str += "<a class=pageNext title=\"下一页\"  onclick=\"commonSubmit(" + next() + ",\'" + formName + "\')\">下一页</a>";
			str += "<a class=pageLast title=\"尾页\"  onclick=\"commonSubmit(" + totalPage + ",\'" + formName + "\')\">尾页</a>";
		}

		if (totalPage == 1 || totalPage == 0) {
			str += " 转到 <input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=3 name=\"pageroffsetll\" size=2 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;";
			str += "<a class=pageGoto id=pageGoto title=转到 onclick='return commonSubmit()'>确定</a></div>";
		} else {
			str += " 转到 <input class=SmallInput type=text style=TEXT-ALIGN: center maxLength=3 name=\"pageroffsetll\" size=2 onKeyPress=\"if (event.keyCode == 13) return commonSubmit(document.all.pageroffsetll.value)\" > 页&nbsp;";
			str += "<a class=pageGoto id=pageGoto title=转到 onclick=\"commonSubmit(document.all.pageroffsetll.value,\'" + formName + "\')\"></a>确定</span>";
		}
		return str;
	}

	public String getToolBar2() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"pagination\">");
		sb.append("<div style=\"float:left;padding:2px;\">");
		if(totalResult>0)
		{
		if (simplePager)
		{
			sb.append("每页 <select  style=\"width:40px;\" name=\"pageSize\" onchange=\"Fm.submitForm('" + formName + "');\" id=\"pageSize\" class=\"select\">");
			sb.append("<option selected=\"selected\" value=\"10\"");
			if (pageSize.equals(10)) {
				sb.append(" selected=\"selected\"");
			}
			sb.append(">10</option>");
			sb.append("<option value=\"20\"");
			if (pageSize.equals(20)) {
				sb.append(" selected=\"selected\"");
			}
			sb.append(">20</option>");
			sb.append("<option value=\"30\"");
			if (pageSize.equals(30)) {
				sb.append(" selected=\"selected\"");
			}
			sb.append(">30</option>");
			sb.append("</select> 条");
			sb.append("</div>");
			sb.append("<div class=\"info\">");
			
			sb.append("共" + totalResult + "条记录,显示第" + getStart() + "-" + getEnd() + "条</div>");
		}
		sb.append("<div class=\"page\">");
		sb.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"float:right\">");
		sb.append("<tbody>");
		sb.append("<tr>");
		sb.append("<td><div class=\"separator\" /></td>");
		if (isFirst()) {
			sb.append("<td>&nbsp;<a  title=\"首 页\"> <span class=\"firstpage_disable\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
			sb.append("<td>&nbsp;<a  title=\"上 页\"> <span class=\"previouspage_disable\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
		} else {
			sb.append("<td>&nbsp;<a  title=\"首 页\" onclick=\"Fm.pageTurn(" + first() + ",\'" + formName + "\');\"> <span class=\"first\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
			sb.append("<td>&nbsp;<a  title=\"上 页\" onclick=\"Fm.pageTurn(" + previous() + ",\'" + formName + "\');\"> <span class=\"prev\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
		}
		sb.append("<td><div class=\"separator\"/></td>");
		sb.append("<td>&nbsp;第 <span id=\"PageIndex\">" + curPageNO + "</span>&nbsp;/&nbsp;<span id=\"PageCount\">" + totalPage + "</span>页 &nbsp;&nbsp;</td>");
		sb.append("<td><div class=\"separator\"/> </td>");
		if (isLast()||totalResult==0) {
			sb.append("<td>&nbsp;<a title=\"下 页\" href=\"#\"> <span class=\"nextpage_disable\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
			sb.append("<td>&nbsp;<a title=\"尾 页\" href=\"#\"> <span class=\"lastpage_disable\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
		} else {
			sb.append("<td>&nbsp;<a id=\"PageNext\" title=\"下 页\" href=\"#\" onclick=\"Fm.pageTurn(" + next() + ",\'" + formName + "\');\"> <span id=\"spnext\" class=\"next\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
			sb.append("<td>&nbsp;<a id=\"PageLast\" title=\"尾 页\" href=\"#\" onclick=\"Fm.pageTurn(" + last() + ",\'" + formName + "\');\"> <span id=\"splast\" class=\"last\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
		}
		sb.append("<td><div class=\"separator\"/></td>");
		sb.append("<td>&nbsp;跳到第 <input class=SmallInput type=text style=\"margin-top: 2px;vertical-align: baseline\";maxLength=3 id=SmallInput size=2 onKeyPress=\"if (event.keyCode == 13){ if ($(\'#SmallInput\').val()>0&&$(\'#SmallInput\').val()<="+last()+") return Fm.pageTurn($(\'#SmallInput\').val(),\'" + formName + "\')};\" > 页</td><td>&nbsp;<a  href=\"#\" title=转到  onclick=\"if ($(\'#SmallInput\').val()>0&&$(\'#SmallInput\').val()<="+last()+") Fm.pageTurn($(\'#SmallInput\').val(),\'" + formName + "\');\">确定</a>");
		
		sb.append("</td>");
		sb.append("<td>&nbsp;&nbsp;</td>");
		sb.append("</tr>");
		sb.append("</tbody>");
		sb.append("</table>");
		}
		else {
			sb.append("很抱歉,没有找到数据记录");
		}
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();

	}
	public String getToolBar3() {
		StringBuffer sb = new StringBuffer();
		sb.append("<div class=\"pagination\">");
		sb.append("<div style=\"float:left;padding:2px;\">");
		if(totalResult>0)
		{
			sb.append("<div class=\"page\">");
			sb.append("<table border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"float:right\">");
			sb.append("<tbody>");
			sb.append("<tr>");
			sb.append("<td><div class=\"separator\" /></td>");
			if (isFirst()) {
				sb.append("<td>&nbsp;<a  title=\"首 页\"> <span class=\"firstpage_disable\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
				sb.append("<td>&nbsp;<a  title=\"上 页\"> <span class=\"previouspage_disable\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
			} else {
				sb.append("<td>&nbsp;<a  title=\"首 页\" onclick=\"Fm.pageTurn(" + first() + ",\'" + formName + "\');\"> <span class=\"first\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
				sb.append("<td>&nbsp;<a  title=\"上 页\" onclick=\"Fm.pageTurn(" + previous() + ",\'" + formName + "\');\"> <span class=\"prev\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
			}
			sb.append("<td><div class=\"separator\"/></td>");
			sb.append("<td>&nbsp;第 <span id=\"PageIndex\">" + curPageNO + "</span>&nbsp;/&nbsp;<span id=\"PageCount\">" + totalPage + "</span>页 &nbsp;&nbsp;</td>");
			sb.append("<td><div class=\"separator\"/> </td>");
			if (isLast()||totalResult==0) {
				sb.append("<td>&nbsp;<a title=\"下 页\" href=\"#\"> <span class=\"nextpage_disable\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
				sb.append("<td>&nbsp;<a title=\"尾 页\" href=\"#\"> <span class=\"lastpage_disable\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
			} else {
				sb.append("<td>&nbsp;<a id=\"PageNext\" title=\"下 页\" href=\"#\" onclick=\"Fm.pageTurn(" + next() + ",\'" + formName + "\');\"> <span id=\"spnext\" class=\"next\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
				sb.append("<td>&nbsp;<a id=\"PageLast\" title=\"尾 页\" href=\"#\" onclick=\"Fm.pageTurn(" + last() + ",\'" + formName + "\');\"> <span id=\"splast\" class=\"last\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span></a></td>");
			}
			sb.append("<td><div class=\"separator\"/></td>");
			sb.append("<td>");
			
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</tbody>");
			sb.append("</table>");
		}
		else {
			sb.append("很抱歉,没有找到数据记录");
		}
		sb.append("</div>");
		sb.append("</div>");
		return sb.toString();
		
	}

	public static int getOffset(int rowCounts, int curPageNO, int pageSize) {
		// 计算从第几条获取数据
		return (curPageNO - 1) * pageSize;
	}

}