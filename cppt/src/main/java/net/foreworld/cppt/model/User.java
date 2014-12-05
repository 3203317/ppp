package net.foreworld.cppt.model;

import java.io.Serializable;

import framework.core.model.AppBaseModel;

/**
 *
 * @author huangxin
 *
 */
public class User extends AppBaseModel implements Serializable {

	private static final long serialVersionUID = -6710231742180177443L;

	private String userName;
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
