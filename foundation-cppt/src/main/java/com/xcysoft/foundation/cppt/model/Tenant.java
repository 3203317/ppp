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
@Table(name = "s_tenant")
@Entity
public class Tenant extends CommonModel {

	private static final long serialVersionUID = 7647583745725460714L;

	private String tenant_name;
	private String tel;
	private Integer status;

	private String password;
	private String corp_name;

	private Date reg_time;

	@Column(name = "REG_TIME")
	public Date getReg_time() {
		return reg_time;
	}

	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}

	@Column(name = "CORP_NAME", length = 128)
	public String getCorp_name() {
		return corp_name;
	}

	public void setCorp_name(String corp_name) {
		this.corp_name = corp_name;
	}

	public String getPassword() {
		return password;
	}

	@Column(name = "PASSWORD", length = 32)
	public void setPassword(String password) {
		this.password = password;
	}

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
