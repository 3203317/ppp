package com.xcysoft.foundation.oa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.newcapec.framework.core.model.CommonModel;

/**
 *
 * @author huangxin
 *
 */
@Table(name = "oa_article")
@Entity
public class Article extends CommonModel {

	private static final long serialVersionUID = 4170561128898820100L;

	private String article_title;
	private String article_content;

	@Column(name = "ARTICLE_TITLE", length = 128)
	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	@Column(name = "ARTICLE_CONTENT", length = 256)
	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

}
