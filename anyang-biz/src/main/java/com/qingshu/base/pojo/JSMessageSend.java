package com.qingshu.base.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.qingshu.core.hibernate.config.IdEntity;
/**
 * 发件箱
 */
public class JSMessageSend extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private JSUser sendUser=new JSUser();
	private JSMessage jsMessage=new JSMessage();
	private Timestamp sendTime;
	private Short status;

	public JSUser getSendUser() {
		return sendUser;
	}

	public void setSendUser(JSUser sendUser) {
		this.sendUser = sendUser;
	}

	public JSMessage getJsMessage() {
		return jsMessage;
	}

	public void setJsMessage(JSMessage jsMessage) {
		this.jsMessage = jsMessage;
	}

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

}
