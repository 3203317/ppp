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

	private String article_name;
	private String article_content;

	@Column(name = "ARTICLE_NAME", length = 128)
	public String getArticle_name() {
		return article_name;
	}

	public void setArticle_name(String article_name) {
		this.article_name = article_name;
	}

	@Column(name = "ARTICLE_CONTENT", length = 256)
	public String getArticle_content() {
		return article_content;
	}

	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}

}
