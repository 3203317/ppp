package com.qingshu.base.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 短消息
 */
public class JSMessage extends IdEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private String title;						/*消息标题*/
	private String content;						/*消息内容*/
	private JSUser sendUser=new JSUser();		/*消息发送人*/
	private String receiverId;					/*消息接收者ID*/
	private String receiverType;				/*消息接受者类型*/
	private Short messageType;					/*消息类型*/
	private Timestamp createTime;				/*消息创建时间*/
	private Short status;						/*消息状态*/
	public JSUser getSendUser() {
		return sendUser;
	}

	public void setSendUser(JSUser sendUser) {
		this.sendUser = sendUser;
	}

	public String getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}

	public Short getMessageType() {
		return messageType;
	}

	public void setMessageType(Short messageType) {
		this.messageType = messageType;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public JSMessage() {
		System.out.print("");
	}

}
