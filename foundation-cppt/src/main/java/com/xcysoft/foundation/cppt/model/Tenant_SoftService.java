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
@Table(name = "s_tenant_softservice")
@Entity
public class Tenant_SoftService extends CommonModel {

	private static final long serialVersionUID = -3538774876779424367L;

	private String tenant_id;
	private String softservice_id;

	@Column(name = "TENANT_ID", length = 32)
	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	@Column(name = "SOFTSERVICE_ID", length = 32)
	public String getSoftservice_id() {
		return softservice_id;
	}

	public void setSoftservice_id(String softservice_id) {
		this.softservice_id = softservice_id;
	}

}
