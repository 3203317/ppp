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
@Table(name = "s_softservice")
@Entity
public class SoftService extends CommonModel {

	private static final long serialVersionUID = -5721553887568455358L;

	private String service_name;
	private String service_version;
	private String service_desc;

	@Column(name = "SERVICE_NAME", length = 32)
	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	@Column(name = "SERVICE_VERSION", length = 32)
	public String getService_version() {
		return service_version;
	}

	public void setService_version(String service_version) {
		this.service_version = service_version;
	}

	@Column(name = "SERVICE_DESC", length = 255)
	public String getService_desc() {
		return service_desc;
	}

	public void setService_desc(String service_desc) {
		this.service_desc = service_desc;
	}

}
