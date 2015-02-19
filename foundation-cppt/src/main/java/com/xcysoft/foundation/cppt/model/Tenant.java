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
@Table(name = "s_tenant")
@Entity
public class Tenant extends CommonModel {

	private static final long serialVersionUID = 7647583745725460714L;

	private String tenant_name;
	private String tel;
	private Integer status;

	@Column(name = "TENANT_NAME", length = 32)
	public String getTenant_name() {
		return tenant_name;
	}

	public void setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
	}

	@Column(name = "TEL", length = 32)
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "STATUS", length = 2)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
