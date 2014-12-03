package net.foreworld.cppt.model;

import java.io.Serializable;

import net.foreworld.framework.core.model.AppBaseModel;

/**
 *
 * @author huangxin
 *
 */
public class Tenant extends AppBaseModel implements Serializable {

	private static final long serialVersionUID = 4013663019577320644L;

	private String name;
	private String password;
	private String tel;
	private Integer status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
