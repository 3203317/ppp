package com.qingshu.base.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.qingshu.core.hibernate.config.IdEntity;
/**
 * 收件箱
 */
public class JSMessageReceiver extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private JSUser receiverUser=new JSUser();
	private JSMessage jsMessage=new JSMessage();
	private Timestamp receiverTime;
	private Short status;

	public JSUser getReceiverUser() {
		return receiverUser;
	}

	public void setReceiverUser(JSUser receiverUser) {
		this.receiverUser = receiverUser;
	}

	public JSMessage getJsMessage() {
		return jsMessage;
	}

	public void setJsMessage(JSMessage jsMessage) {
		this.jsMessage = jsMessage;
	}

	public Timestamp getReceiverTime() {
		return receiverTime;
	}

	public void setReceiverTime(Timestamp receiverTime) {
		this.receiverTime = receiverTime;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
}
