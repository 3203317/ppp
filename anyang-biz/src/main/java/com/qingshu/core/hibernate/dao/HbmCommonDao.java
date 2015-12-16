package com.qingshu.core.hibernate.dao;

import java.io.Serializable;
import java.util.List;

import com.qingshu.core.hibernate.qbc.CriteriaQuery;

public interface HbmCommonDao {
	public <T> T save(T entity);
	public <T> T getEntity(Class<T> entityName, Serializable id);
	public <T> T findByExample(T exampleEntity);
	public <T> List<T> getPagerList(CriteriaQuery cq,Boolean isOffset);
}
