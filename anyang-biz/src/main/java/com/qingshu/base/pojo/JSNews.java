package com.qingshu.base.pojo;

import java.util.Date;

import com.qingshu.core.hibernate.config.IdEntity;

/**
 * 法规文件
 */
public class JSNews extends IdEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String href;
	private Short type;
	private Date createDate;

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	// Constructor
	public JSNews() {
	}

	/**
	 * full Constructor
	 */
	public JSNews(String id, String title) {
		setId(id);
		this.title = title;
	}

	// getter && setter

	public String getTitle() {
		return title;
	}

	public JSNews setTitle(String title) {
		this.title = title;
		return this;
	}
	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

}
