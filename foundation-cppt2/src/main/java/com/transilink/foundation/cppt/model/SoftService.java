package com.transilink.foundation.cppt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.transilink.framework.core.model.CommonModel;

/**
 *
 * @author huangxin
 *
 */
@Table(name = "s_softservice")
@Entity
public class SoftService extends CommonModel {

	private static final long serialVersionUID = -5721553887568455358L;

	private String softservice_name;
	private String softservice_desc;

	@Column(name = "SOFTSERVICE_NAME", length = 32)
	public String getSoftservice_name() {
		return softservice_name;
	}

	public void setSoftservice_name(String softservice_name) {
		this.softservice_name = softservice_name;
	}

	@Column(name = "SOFTSERVICE_DESC", length = 255)
	public String getSoftservice_desc() {
		return softservice_desc;
	}

	public void setSoftservice_desc(String softservice_desc) {
		this.softservice_desc = softservice_desc;
	}

}
