package com.xcysoft.foundation.cppt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.newcapec.framework.core.model.CommonModel;

/**
 *
 * @author huangxin
 *
 */
@Table(name = "s_menu")
@Entity
public class Menu extends CommonModel {

	private static final long serialVersionUID = -4831791085842205655L;

	private String pid;
	private String menu_name;
	private String menu_url;
	private Integer sort;

	@Column(name = "PID", length = 32)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "MENU_NAME", length = 32)
	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	@Column(name = "MENU_URL", length = 128)
	public String getMenu_url() {
		return menu_url;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

}
