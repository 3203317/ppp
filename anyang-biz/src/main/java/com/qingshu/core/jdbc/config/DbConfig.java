package com.qingshu.core.jdbc.config;

/**
 * JDBC数据库连接配置
 */
public class DbConfig {
	private String driverClassName;
	private String username;
	private String password;
	private String url;

	
	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "{driverClass=" + driverClassName + ", username=" + username + ", password=" + password + ", url="
				+ url + "}";
	}
}
