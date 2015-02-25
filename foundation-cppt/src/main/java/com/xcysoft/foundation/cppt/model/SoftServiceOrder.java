package com.xcysoft.foundation.cppt.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.newcapec.framework.core.model.CommonModel;

/**
 *
 * @author huangxin
 *
 */
@Table(name = "s_softserviceorder")
@Entity
public class SoftServiceOrder extends CommonModel {

	private static final long serialVersionUID = 5133830657376169028L;

	private String tenant_id;
	private String softservice_id;
	private Integer user_nums;
	private Date expired_date;

	private Integer cost;

	@Column(name = "COST", length = 8)
	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	@Column(name = "EXPIRED_DATE")
	public Date getExpired_date() {
		return expired_date;
	}

	public void setExpired_date(Date expired_date) {
		this.expired_date = expired_date;
	}

	@Column(name = "USER_NUMS", length = 8)
	public Integer getUser_nums() {
		return user_nums;
	}

	public void setUser_nums(Integer user_nums) {
		this.user_nums = user_nums;
	}

	@Column(name = "TENANT_ID", length = 8)
	public String getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(String tenant_id) {
		this.tenant_id = tenant_id;
	}

	@Column(name = "SOFTSERVICE_ID", length = 8)
	public String getSoftservice_id() {
		return softservice_id;
	}

	public void setSoftservice_id(String softservice_id) {
		this.softservice_id = softservice_id;
	}

}
