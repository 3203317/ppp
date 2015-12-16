package com.qingshu.core.hibernate.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qingshu.common.pager.PagerInfo;
import com.qingshu.common.util.ContextUtils;
import com.qingshu.core.hibernate.qbc.CriteriaQuery;

/**
 *
 * 类描述： DAO层泛型基类
 */
@Component
public abstract class HbmGenericDao {
	private static final Logger logger = Logger.getLogger(HbmGenericDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	// 事务必须是开启的(Required)，否则获取不到
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 持久化并返回对象
	 *
	 * @param entity
	 * @return
	 */
	public <T> T save(T entity) {
		try {
			getSession().save(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("保存成功:" + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("保存异常:", e);
			throw e;
		}
		return entity;

	}
	/**
	 * 根据主键获取实体
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getEntity(Class<T> entityName, Serializable id) {
		Object t =  getSession().get(entityName, id);
		if (t != null) {
			getSession().flush();
		}
		return (T) t;
	}
	 /**
     * 根据给定的实例查找对象
     */
    @SuppressWarnings("unchecked")
	public <T> T findByExample(T exampleEntity){
    	T t=null;
		Criteria executableCriteria = (getSession().createCriteria(exampleEntity.getClass()));
		executableCriteria.add(Example.create(exampleEntity));
		List<T> list = executableCriteria.list();
		if(list.size()>0)
		{
			t=list.get(0);
		}
		return t;
    }
    /**
	 * 获取分页记录CriteriaQuery 老方法final int allCounts = oConvertUtils.getInt(criteria .setProjection(Projections.rowCount()).uniqueResult(), 0);
	 *
	 * @param cq
	 * @param isOffset
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public <T> List<T> getPagerList(CriteriaQuery cq, Boolean isOffset) {
		List<T> list=new ArrayList<T>();
		Criteria criteria = cq.getDetachedCriteria().getExecutableCriteria(getSession());
		CriteriaImpl impl = (CriteriaImpl) criteria;
		// 先把Projection和OrderBy条件取出来,清空两者来执行Count操作
		Projection projection = impl.getProjection();
	    int allCounts = ((Long) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		criteria.setProjection(projection);
		if (projection == null) {
			criteria.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
		}
		// 判断是否有排序字段
		if (cq.getOrdermap() != null) {
			cq.setOrder(cq.getOrdermap());
		}
		// 每页显示数
		int pageSize = cq.getPagerInfo().getPageSize();
		int curPageNO = cq.getPagerInfo().getCurPageNO();
		int offset = PagerInfo.getOffset(allCounts, curPageNO, pageSize);
		// 是否分页
		if (isOffset){
			criteria.setFirstResult(offset);
			criteria.setMaxResults(pageSize);
		} else {
			pageSize = allCounts;
		}
		PagerInfo pagerInfo=new PagerInfo(allCounts, curPageNO, cq.getPagerInfo().getActionName(),cq.getPagerInfo().getFormName(),pageSize);
		list=criteria.list();
		ContextUtils.getRequest().setAttribute(PagerInfo.PAGERSTR, pagerInfo.getToolBar2());
		return list;
	}
}
