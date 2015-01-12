package com.xcysoft.foundation.oa.dao.base;

import java.io.Serializable;

import cn.newcapec.framework.core.dao.hibernate.HibernateEntityDao;

import com.xcysoft.foundation.oa.model.Article;

/**
 *
 * @author huangxin
 *
 */
@SuppressWarnings("all")
public abstract class BaseArticleDao extends HibernateEntityDao {

	@Override
	protected Class getReferenceClass() {
		return Article.class;
	}

	public Article get(Serializable key) {
		return (Article) get(getReferenceClass(), key);
	}
}
