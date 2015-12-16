package com.qingshu.common.json.model;

import java.util.List;
import java.util.Map;

/**
 * @description:(Ztree树模型)
 * @author：flyme
 * @data：2013-7-8 上午09:27:18
 * @version：1.0
 */
public class TreeModel{
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private String id; // 编号
	private String pId; // 父节点
	private Boolean isParent=false; // 是否父节点
	private String name; // 节点名称
	private Boolean open=false; // 是否打开
	private Boolean doCheck=true;//是否 禁止选中
	private Boolean checked=false;//默认是否选中
	private boolean nocheck=false;
	private String url;//节点编辑的URL
	private String target;//设置点击节点后在何处打开 url。[treeNode.url 存在时有效]
	private List<TreeModel> children;//子节点
	private Map<String, Object> otherParam;
	private String icon;
	
	public Map<String, Object> getOtherParam() {
		return otherParam;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getTarget() {
		return target;
	}


	public void setTarget(String target) {
		this.target = target;
	}

	public void setOtherParam(Map<String, Object> otherParam) {
		this.otherParam = otherParam;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}
	public Boolean getDoCheck() {
		return doCheck;
	}
	public void setDoCheck(Boolean doCheck) {
		this.doCheck = doCheck;
	}
	public List<TreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<TreeModel> children) {
		this.children = children;
	}
	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIcon() {
		return icon;
	}
}
