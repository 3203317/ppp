package com.xcysoft.foundation.cppt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.transilink.framework.core.model.CommonModel;

/**
 *
 * @author huangxin
 *
 */
@Table(name = "s_tenantorg")
@Entity
public class TenantOrg extends CommonModel {

	private static final long serialVersionUID = -1754167444971334188L;

	private String tenant_id;

	private String org_name;

	private String pid;

	private Date create_time;

	private Integer sort;
	private Integer isParent;

	private String path;

	@Column(name = "PATH", length = 1000)
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "SORT", length = 2)
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "ISPARENT", length = 1)
	public Integer getIsParent() {
		return isParent;
	}

	public void setIsParent(Integer isParent) {
		this.isParent = isParent;
	}

	@Column(name = "PID", length = 255)
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	@Column(name = "CREATE_TIME")
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Column(name = "TENANT_ID", length = 32)
	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	@Column(name = "ORG_NAME", length = 32)
	public String getOrg_name() {
		return org_name;
	}

	public void setOrg_name(String org_name) {
		this.org_name = org_name;
	}

}
