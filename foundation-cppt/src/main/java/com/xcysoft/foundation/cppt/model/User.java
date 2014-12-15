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
@Table(name = "s_user")
@Entity
public class User extends CommonModel {

	private static final long serialVersionUID = 1968235354973491611L;

	@Column(name = "USERNAME", length = 32)
	private String userName;
	@Column(name = "PASSWORD", length = 32)
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
