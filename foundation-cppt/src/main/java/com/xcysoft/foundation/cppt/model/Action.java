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
@Table(name = "s_action")
@Entity
public class Action extends CommonModel {

	private static final long serialVersionUID = 8025707299784554082L;

	private String action_name;
	private String action_uri;
	private Integer action_type;

	@Column(name = "ACTION_TYPE", length = 2)
	public Integer getAction_type() {
		return action_type;
	}

	public void setAction_type(Integer action_type) {
		this.action_type = action_type;
	}

	@Column(name = "ACTION_NAME", length = 32)
	public String getAction_name() {
		return action_name;
	}

	public void setAction_name(String action_name) {
		this.action_name = action_name;
	}

	@Column(name = "ACTION_URI", length = 32)
	public String getAction_uri() {
		return action_uri;
	}

	public void setAction_uri(String action_uri) {
		this.action_uri = action_uri;
	}

}
